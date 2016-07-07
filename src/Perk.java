import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Perk extends Ability implements Cloneable{
    public static Map<String, Perk> listOfPerks = new HashMap<>();
    private ArrayList<Condition> listOfCondition;
    private boolean isConditionDependOnRelatedSoldier;
    private boolean isConditionDependOnUserHero;
    private TimeOfCheck timeOfCheck; // Can equals "duringAttack", "duringDefend" and "eachActivity"

    private enum TimeOfCheck {
        duringAttackDefend, eachActivity
    }


    //---------------------------------------------------------- Constructors


    public Perk(AbilityHandler<E> abilityHandler, ArrayList<Condition> listOfCondition, ArrayList<PerkMode<E>> listOfModes, Map<Condition, PerkMode<E>> mapOfCondition, boolean isConditionDependOnRelatedSoldier, boolean isConditionDependOnUserHero, String timeOfCheck) {
        super(abilityHandler);
        this.listOfCondition = listOfCondition;
        this.listOfModes = listOfModes;
        this.mapOfCondition = mapOfCondition;
        this.isConditionDependOnRelatedSoldier = isConditionDependOnRelatedSoldier;
        this.isConditionDependOnUserHero = isConditionDependOnUserHero;
        this.timeOfCheck = timeOfCheck;
    }


    public Perk() {}

    //---------------------------------------------------------- Functions

    public boolean upgrade(Player player) {

        if (this.nameOfNecessaryAbilities.get(this.currentGrade + 1) != null) {
            for (String nameOfAbility: (ArrayList<String>)this.nameOfNecessaryAbilities.get(this.currentGrade + 1)) {
                if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                    for (Skill skill: Hero.mapOfHeroes.get(this.ownerName).getSkills()) {
                        if (skill.getName().equals(nameOfAbility)) {
                            if (skill.getCurrentGrade() < ((Map<String, Integer>)this.gradeOfNecessaryAbilities.get(this.currentGrade + 1)).get(skill.getName()))
                                return false;
                            break;
                        }
                    }
                }
                if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                    for (Perk perk: Hero.mapOfHeroes.get(this.ownerName).getPerks()) {
                        if (perk.getName().equals(nameOfAbility)) {
                            if (perk.getCurrentGrade() < ((Map<String, Integer>)this.gradeOfNecessaryAbilities.get(this.currentGrade + 1)).get(perk.getName()))
                                return false;
                            break;
                        }
                    }
                }
            }
        }

        this.currentGrade += 1;
        if (this.isAcquire == false)
            this.isAcquire = true;

        for (Property property: this.properties) {
            property.setCurrentGrade(this.currentGrade);
        }

        if (this.timeOfCheck != TimeOfCheck.duringAttackDefend) {
            this.updatePerkEffect(Hero.mapOfHeroes.get(this.ownerName));
        }

        return true;
    }

    protected Perk clone() throws CloneNotSupportedException {
        Perk<E> perk = (Perk) super.clone();
        perk.setMapOfEffectedSoldiers(new HashMap<>());
        ArrayList<PerkMode<E>> cloneOfListOfModes = new ArrayList<>();
        Map<Condition, PerkMode<E>> cloneOfMapOfConditions = new HashMap<>();
        for (Condition condition: this.listOfCondition) {
            PerkMode<E> cloneOfPerkMode = this.mapOfCondition.get(condition).clone();
            cloneOfListOfModes.add(cloneOfPerkMode);
            cloneOfMapOfConditions.put(condition, cloneOfPerkMode);
        }
        perk.setListOfModes(cloneOfListOfModes);
        perk.setMapOfCondition(cloneOfMapOfConditions);
        perk.setRelatedSoldiers(new ArrayList());
        perk.setEffectedSoldiers(new ArrayList());
        return perk;
    }

    public boolean equals(Perk perk){
        if(perk.getName().equals(this.name)){
            return true;
        }
        return false;
    }

    public void removeEffect() {
        for (String className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.listOfEffectedObjectsByClass.get(className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                for (Property property: (ArrayList<Property>)this.mapOfEffectedPropertiesByClass.get(className).get(effectedObjects.get(i))) {
                    property.removeEffect(effectedObjects.get(i));
                }
            }
            this.listOfEffectedObjectsByClass.get(className).clear();
            this.mapOfEffectedPropertiesByClass.get(className).clear();
        }
    }

    public void updatePerkEffect(Hero userHero) {
        this.updatePerkEffect(null, null, userHero);
    }

    public void updatePerkEffect(Enemy enemy, Hero hero, Hero userHero) {
        removeEffect();
        for (String className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.choosingEffectedObjects(enemy, hero, className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                this.mapOfEffectedPropertiesByClass.get(className).put(effectedObjects.get(i), this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i)));
                for (Property property: this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i))) {
                    ArrayList<?> effectingObjects = this.choosingEffectingObjects(enemy, hero, property);
                    property.effect(effectedObjects.get(i), effectingObjects);
                }
                this.listOfEffectedObjectsByClass.get(className).add(effectedObjects.get(i));
            }
        }
    }

    public ArrayList<?> choosingEffectingObjects(Enemy enemy, Hero hero, Property property) {
        if (property.getClassOfEffectingObjects().equals("Hero")) {
            SelectingObjectsDetail<Hero> selectingObjectsDetail = this.selectingEffectingObjectsDetails.get(property);
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
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isHeroEffecting()) {
                    effectingHeroes.add(hero);
                }
            }
            return effectingHeroes;
        } else if (property.getClassOfEffectingObjects().equals("Enemy")) {
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = this.selectingEffectingObjectsDetails.get(property);
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
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isEnemyEffecting()) {
                    effectingEnemies.add(enemy);
                }
            }
            return effectingEnemies;
        }
        return null;
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    public ArrayList<?> choosingEffectedObjects(Enemy enemy, Hero hero, String classOfEffectedSoldiers) {       // in method dar moghe attack ya defend call mishavad;
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
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isHeroEffected()) {
                    effectedHeroes.add(hero);
                }
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
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isEnemyEffected()) {
                    effectedEnemies.add(enemy);
                }
            }
            return effectedEnemies;
        }
        return null;
    }
    //---------------------------------------------------------- Getter && Setters

    public ArrayList<Condition> getListOfCondition() {
        return listOfCondition;
    }

    public void setListOfCondition(ArrayList<Condition> listOfCondition) {
        this.listOfCondition = listOfCondition;
    }

    public boolean isConditionDependOnRelatedSoldier() {
        return isConditionDependOnRelatedSoldier;
    }

    public void setConditionDependOnRelatedSoldier(boolean conditionDependOnRelatedSoldier) {
        isConditionDependOnRelatedSoldier = conditionDependOnRelatedSoldier;
    }

    public boolean isConditionDependOnUserHero() {
        return isConditionDependOnUserHero;
    }

    public void setConditionDependOnUserHero(boolean conditionDependOnUserHero) {
        isConditionDependOnUserHero = conditionDependOnUserHero;
    }

}
