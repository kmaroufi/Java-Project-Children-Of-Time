import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/7/2016.
 */
public class SubSkill extends SubAbility implements Cloneable{
    private Skill relatedSkill;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private int cooldown;
    private int remainingCooldown;
    private boolean canStackUp;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    private Map<String, Map> mapOfRemainTimeOfEffectByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = Time of how much this effect will be remain


    //---------------------------------------------------------------- Constructors

    public SubSkill(SubSkillHandler subSkillHandler, SubAbilityHandler subAbilityHandler) {
        super(subAbilityHandler);
        setRelatedSkill(subSkillHandler.getRelatedSkill());
        setRepeated(subSkillHandler.isRepeated());
        setTimeOfEffecting(subSkillHandler.getTimeOfEffecting());
        setCooldown(subSkillHandler.getCooldown());
        setRemainingCooldown(0);
        setCanStackUp(subSkillHandler.isCanStackUp());
        setRequiredEnergyPoint(subSkillHandler.getRequiredEnergyPoint());
        setRequiredMagicPoint(subSkillHandler.getRequiredMagicPoint());
        setMapOfRemainTimeOfEffectByClass(subSkillHandler.getMapOfRemainTimeOfEffectByClass());
    }

    //---------------------------------------------------------------- Functions

    protected SubSkill clone() {
        SubSkill subSkill = null;
        try {
            subSkill = (SubSkill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // set relatedSkill nabayad faramoosh she.
        subSkill.mapOfRemainTimeOfEffectByClass = new HashMap<>();
        subSkill.mapOfConditionsByClass = new HashMap<>();
        subSkill.listOfEffectedObjectsByClass = new HashMap<>();
        subSkill.mapOfEffectedPropertiesByClass = new HashMap<>();
        for (String className: subSkill.classOfEffectedObjects) {
            subSkill.mapOfRemainTimeOfEffectByClass.put(className, new HashMap<>());
            subSkill.mapOfConditionsByClass.put(className, this.mapOfConditionsByClass.get(className).clone());
            subSkill.listOfEffectedObjectsByClass.put(className, new ArrayList());
            subSkill.mapOfEffectedPropertiesByClass.put(className, new HashMap<>());
        }
        return subSkill;
    }       // Creates A Copy of This Object (Skill)

    public void useSkill(Hero userHero) {

        if (this.remainingCooldown != 0) {
            return;
        }

        boolean isEffectedOnAtLeastOnObject = false;

        for (String className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.choosingEffectedObjects(new ArrayList<>(), className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                if ((this.listOfEffectedObjectsByClass.get(className).contains(effectedObjects.get(i))) && (this.canStackUp == false)) {
                    continue;
                }
                isEffectedOnAtLeastOnObject = true;
                this.mapOfEffectedPropertiesByClass.get(className).put(effectedObjects.get(i), this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i)));
                for (Property property: this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i))) {
                    ArrayList<?> effectingObjects = this.choosingEffectingObjects(new ArrayList<>(), property);
                    double effectValue = property.effect(effectedObjects.get(i), effectingObjects);
                    Display.printInEachLine(userHero.getName() + " just used " + this.relatedSkill.getName() + " on " + effectedObjects.get(i).toString() +  " and effecting on " + property.getName() + " with " + Math.abs(effectValue));
                }
                if (this.listOfEffectedObjectsByClass.get(className).contains(effectedObjects.get(i)) == false) {
                    this.listOfEffectedObjectsByClass.get(className).add(effectedObjects.get(i));
                }
                this.mapOfRemainTimeOfEffectByClass.get(className).put(effectedObjects.get(i), new Time(this.timeOfEffecting));
            }
        }

        if (isEffectedOnAtLeastOnObject == false) {
            return;
        }

        this.remainingCooldown = this.cooldown;
        userHero.setCurrentEnergyPoint(userHero.getCurrentEnergyPoint() - this.requiredEnergyPoint);
        userHero.setCurrentMagic(userHero.getCurrentMagic() - this.requiredMagicPoint);
    }

    public void removeEffect() {
        for (String className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.listOfEffectedObjectsByClass.get(className);
            ArrayList removedEffectedObjects = new ArrayList();
            for (int i = 0; i < effectedObjects.size(); i++) {
                if (((Time)this.mapOfRemainTimeOfEffectByClass.get(className).get(effectedObjects.get(i))).isTimePassed()) {
                    for (Property property: (ArrayList<Property>)this.mapOfEffectedPropertiesByClass.get(className).get(effectedObjects.get(i))) {
                        property.removeEffect(effectedObjects.get(i));
                    }
                    removedEffectedObjects.add(effectedObjects.get(i));
                    this.mapOfRemainTimeOfEffectByClass.get(className).remove(effectedObjects.get(i));
                    this.mapOfEffectedPropertiesByClass.get(className).remove(effectedObjects.get(i));
                }
            }
            this.listOfEffectedObjectsByClass.get(className).removeAll(removedEffectedObjects);
        }
    }

    public void reduceTime(String typeOfTime) {
        for (String className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.listOfEffectedObjectsByClass.get(className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                ((Time)this.mapOfRemainTimeOfEffectByClass.get(className).get(effectedObjects.get(i))).reduceTime(typeOfTime);
            }
        }
    }

    private ArrayList<?> choosingEffectedObjects(ArrayList<String> fromCommandLine, String classOfEffectedSoldiers) {
        if (classOfEffectedSoldiers.equals("Hero") && this.selectingEffectedObjectsDetails.containsKey("Hero")) {
            SelectingObjectsDetail<Hero> selectingObjectsDetail = this.selectingEffectedObjectsDetails.get("Hero");
            ArrayList<Hero> effectedHeroes = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectedHeroes.addAll(GameEngine.listOfHeroes);
                return effectedHeroes;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectedHeroes.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    effectedHeroes.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
                // TODO
            }
            return effectedHeroes;
        }
        else if (classOfEffectedSoldiers.equals("Enemy") && this.selectingEffectedObjectsDetails.containsKey("Enemy")) {
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = this.selectingEffectedObjectsDetails.get("Enemy");
            ArrayList<Enemy> effectedEnemies = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectedEnemies.addAll(GameEngine.listOfEnemies);
                return effectedEnemies;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectedEnemies.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Enemy> Enemies = new ArrayList<>();
                Enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(Enemies.size());
                    effectedEnemies.add(Enemies.get(randomIndex));
                    Enemies.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
                // TODO
            }
            return effectedEnemies;
        }
        return null;
    }

    private ArrayList<?> choosingEffectingObjects(ArrayList<String> fromCommandLine, Property property) {
        if (property.getClassOfEffectingObjects().equals("Hero")) {
            SelectingObjectsDetail<Hero> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
            ArrayList<Hero> effectingHeroes = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectingHeroes.addAll(GameEngine.listOfHeroes);
                return effectingHeroes;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectingHeroes.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    effectingHeroes.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
                // TODO
            }
            return effectingHeroes;
        }
        else if (property.getClassOfEffectingObjects().equals("Enemy")) {
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
            ArrayList<Enemy> effectingEnemies = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectingEnemies.addAll(GameEngine.listOfEnemies);
                return effectingEnemies;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectingEnemies.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Enemy> Enemies = new ArrayList<>();
                Enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(Enemies.size());
                    effectingEnemies.add(Enemies.get(randomIndex));
                    Enemies.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
                // TODO
            }
            return effectingEnemies;
        }
        return null;
    }

    public boolean canAcquireThisGrade() {

        if (this.nameOfNecessaryAbilities != null) {
            for (String nameOfAbility: this.nameOfNecessaryAbilities) {
                if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                    for (Skill skill: Hero.mapOfHeroes.get(relatedSkill.getOwnerName()).getSkills()) {
                        if (skill.getName().equals(nameOfAbility)) {
                            if (skill.getCurrentGrade() < this.gradeOfNecessaryAbilities.get(skill.getName()))
                                return false;
                            break;
                        }
                    }
                }
                if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                    for (Perk perk: Hero.mapOfHeroes.get(relatedSkill.getOwnerName()).getPerks()) {
                        if (perk.getName().equals(nameOfAbility)) {
                            if (perk.getCurrentGrade() < this.gradeOfNecessaryAbilities.get(perk.getName()))
                                return false;
                            break;
                        }
                    }
                }
            }
        }

        return true;
    }

    public void showDescription(){
        Display.printInEachLine(this.upgradeDescription);
    }

    //---------------------------------------------------- Getter && Setters


    public Skill getRelatedSkill() {
        return relatedSkill;
    }

    public void setRelatedSkill(Skill relatedSkill) {
        this.relatedSkill = relatedSkill;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public void setTimeOfEffecting(Time timeOfEffecting) {
        this.timeOfEffecting = timeOfEffecting;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public void setCanStackUp(boolean canStackUp) {
        this.canStackUp = canStackUp;
    }

    public int getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public void setRequiredEnergyPoint(int requiredEnergyPoint) {
        this.requiredEnergyPoint = requiredEnergyPoint;
    }

    public int getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public void setRequiredMagicPoint(int requiredMagicPoint) {
        this.requiredMagicPoint = requiredMagicPoint;
    }

    public Map<String, Map> getMapOfRemainTimeOfEffectByClass() {
        return mapOfRemainTimeOfEffectByClass;
    }

    public void setMapOfRemainTimeOfEffectByClass(Map<String, Map> mapOfRemainTimeOfEffectByClass) {
        this.mapOfRemainTimeOfEffectByClass = mapOfRemainTimeOfEffectByClass;
    }
}
