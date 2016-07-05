import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill<E> extends Ability implements Cloneable{
    public static Map<String, Skill> listOfSkills = new HashMap<String, Skill>();
    private ArrayList<Property<E>> propertiesOfRelatedSoldiers;
    private Property<Hero> propertiesOfUser;
    protected Map<E, Time> mapOfEffectedSoldiers = new HashMap<E, Time>();
    private int nonTargetedEnemy;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private ArrayList<String> blackList;
    private int[] cooldown;
    private int remainingCooldown;
    private boolean isDependsRelatedSoldiersSelectingOnPlayer;
    private boolean canStackUp;
    private boolean isUsed;
    private int[] requiredEnergyPoint;
    private int[] requiredMagicPoint;


    //---------------------------------------------------------------- Constructors

    public Skill(SkillHandler skillHandler, AbilityHandler<E> abilityHandler) {
        super(abilityHandler);
        setPropertiesOfRelatedSoldiers(skillHandler.getPropertiesOfRelatedSoldiers());
        setPropertiesOfUser(skillHandler.getPropertiesOfUser());
        setNonTargetedEnemy(skillHandler.getNonTargetedEnemy());
        setRepeated(skillHandler.isRepeated());
        setTimeOfEffecting(skillHandler.getTimeOfEffecting());
        setBlackList(skillHandler.getBlackList());
        setCooldown(skillHandler.getCooldown());
        setRemainingCooldown(0);
        setDependsRelatedSoldiersSelectingOnPlayer(skillHandler.isDependsRelatedSoldiersSelectingOnPlayer());
        setCanStackUp(skillHandler.isCanStackUp());
        setUsed(false);
        setRequiredEnergyPoint(skillHandler.getRequiredEnergyPoint());
        setRequiredMagicPoint(skillHandler.getRequiredMagicPoint());
    }

    //---------------------------------------------------------------- Functions

    protected Skill<E> clone() throws CloneNotSupportedException {
        Skill<E> skill = (Skill<E>) super.clone();
        ArrayList<Property<E>> cloneOfPropertiesOfRelatedSoldiers = new ArrayList<>();
        for (Property<E> property: this.propertiesOfRelatedSoldiers) {
            cloneOfPropertiesOfRelatedSoldiers.add(property.clone());
        }
        skill.setPropertiesOfRelatedSoldiers(cloneOfPropertiesOfRelatedSoldiers);
        if (this.propertiesOfUser != null)
            skill.setPropertiesOfUser(this.propertiesOfUser.clone());
        skill.setMapOfEffectedSoldiers(new HashMap<>());
        return skill;
    }       // Creates A Copy of This Object (Skill)

    public boolean isActivated() {
       return false;
    }

    public void useSkill(ArrayList<E> relatedSoldiers, Hero userHero, ArrayList<E> soldiers) {
        this.choosingRelatedSoldiers(soldiers);
        if (this.remainingCooldown != 0) {
            return;
        }

        boolean cond = false;

        for (E soldier: relatedSoldiers) {
            if (this.effectedSoldiers.contains(soldier) && (this.canStackUp == false))
                continue;
            cond = true;
            for (Property property: this.propertiesOfRelatedSoldiers) {
                double effect = property.effect(soldier, Hero.mapOfHeroes.get(this.ownerName), userHero);
                Display.printInEachLine(userHero.getName() + " just used " + this.name + " on " + ((Soldier) soldier).getName() +  " and effecting on " + property.getName() + " with " + Math.abs(effect));
            }
            if (this.effectedSoldiers.contains(soldier) == false)
                this.effectedSoldiers.add(soldier);
            this.mapOfEffectedSoldiers.put(soldier, new Time(this.timeOfEffecting));
        }

        if (cond == false)
            return;

        if (this.propertiesOfUser != null)
            this.propertiesOfUser.effect(userHero, Hero.mapOfHeroes.get(this.ownerName), userHero);
        this.remainingCooldown = this.cooldown[this.currentGrade - 1];
        userHero.setCurrentEnergyPoint(userHero.getCurrentEnergyPoint() - this.requiredEnergyPoint[this.currentGrade - 1]);
        userHero.setCurrentMagic(userHero.getCurrentMagic() - this.requiredMagicPoint[this.currentGrade - 1]);

    }

    public void removeEffect() {
        ArrayList<E> removedSoldiers = new ArrayList<>();
        for (int i = 0; i < this.effectedSoldiers.size(); i++) {
            E soldier = (E) this.effectedSoldiers.get(i);
            if (this.mapOfEffectedSoldiers.get(soldier).isTimePassed()) {
                for (Property property: this.propertiesOfRelatedSoldiers) {
                    property.removeEffect(soldier);
                }
                removedSoldiers.add(soldier);
                this.mapOfEffectedSoldiers.remove(soldier);
            }
        }
        this.effectedSoldiers.removeAll(removedSoldiers);
    }

    public void reduceTime(String typeOfTime) {
        for (int i = 0; i < this.effectedSoldiers.size(); i++) {
            E soldier = (E) this.effectedSoldiers.get(i);
            this.mapOfEffectedSoldiers.get(soldier).reduceTime(typeOfTime);
        }
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    public void choosingRelatedSoldiers(Enemy enemy,Hero hero) {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            this.relatedSoldiers.add(enemy);
            return;
        }
        this.relatedSoldiers.add(hero);
    }

    public boolean equals(Skill skill){
        if(this.name.equals(skill.getName())){
            return true;
        }
        return false;
    }

    public void choosingRelatedSoldiers(ArrayList<E> fromCommandLine) {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            if (this.numberOfRelatedSoldiers == -5)
                this.relatedSoldiers.addAll(GameEngine.listOfEnemies);
            else if (this.isRandomSoldierSelecting) {
                ArrayList<Enemy> enemies = new ArrayList<Enemy>();
                enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(enemies.size());
                    this.relatedSoldiers.add(enemies.get(randomIndex));
                    enemies.remove(randomIndex);
                }
            }
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer()) {
                this.relatedSoldiers.addAll(fromCommandLine);
            }
        }
        else {
            if (this.numberOfRelatedSoldiers == -5)
                this.relatedSoldiers.addAll(GameEngine.listOfHeroes);
            else if (this.isRandomSoldierSelecting) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    this.relatedSoldiers.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer()) {
                this.relatedSoldiers.addAll(fromCommandLine);
            }
            else {
                this.relatedSoldiers.add(Hero.mapOfHeroes.get(this.ownerName));
            }
        }
    }

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

        for (Property<E> property: this.propertiesOfRelatedSoldiers) {
            property.setCurrentGrade(this.currentGrade);
        }

        if (this.propertiesOfUser != null)
            this.propertiesOfUser.setCurrentGrade(this.currentGrade);

        return true;

    }
    //---------------------------------------------------- Getter && Setters


    public Property getPropertiesOfUser() {
        return propertiesOfUser;
    }

    public void setPropertiesOfUser(Property propertiesOfUser) {
        this.propertiesOfUser = propertiesOfUser;
    }

    public Map<E, Time> getMapOfEffectedSoldiers() {
        return mapOfEffectedSoldiers;
    }

    public void setMapOfEffectedSoldiers(Map<E, Time> mapOfEffectedSoldiers) {
        this.mapOfEffectedSoldiers = mapOfEffectedSoldiers;
    }

    public ArrayList<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    public ArrayList<Property<E>> getPropertiesOfRelatedSoldiers() {
        return propertiesOfRelatedSoldiers;
    }

    public void setPropertiesOfRelatedSoldiers(ArrayList<Property<E>> propertiesOfRelatedSoldiers) {
        this.propertiesOfRelatedSoldiers = propertiesOfRelatedSoldiers;
    }

    public int getNonTargetedEnemy() {
        return nonTargetedEnemy;
    }

    public void setNonTargetedEnemy(int nonTargetedEnemy) {
        this.nonTargetedEnemy = nonTargetedEnemy;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public int[] getCooldown() {
        return cooldown;
    }

    public void setCooldown(int[] cooldown) {
        this.cooldown = cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int[] getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public void setRequiredEnergyPoint(int[] requiredEnergyPoint) {
        this.requiredEnergyPoint = requiredEnergyPoint;
    }

    public int[] getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public void setRequiredMagicPoint(int[] requiredMagicPoint) {
        this.requiredMagicPoint = requiredMagicPoint;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public void setTimeOfEffecting(Time timeOfEffecting) {
        this.timeOfEffecting = timeOfEffecting;
    }

    public boolean isDependsRelatedSoldiersSelectingOnPlayer() {
        return isDependsRelatedSoldiersSelectingOnPlayer;
    }

    public void setDependsRelatedSoldiersSelectingOnPlayer(boolean dependsRelatedSoldiersSelectingOnPlayer) {
        isDependsRelatedSoldiersSelectingOnPlayer = dependsRelatedSoldiersSelectingOnPlayer;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public void setCanStackUp(boolean canStackUp) {
        this.canStackUp = canStackUp;
    }

    public static Map<String, Field> getFieldsMap() {
        return fieldsMap;
    }

    public static void setFieldsMap(Map<String, Field> fieldsMap) {
        Skill.fieldsMap = fieldsMap;
    }
}
