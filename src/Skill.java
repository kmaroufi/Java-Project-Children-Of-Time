import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill<E> extends Ability{
    public static Map<String, Skill> listOfSkills = new HashMap<String, Skill>();
    private ArrayList<Property> propertiesOfRelatedSoldiers;
    private Property propertiesOfUser;
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

    //---------------------------------------------------------------- Functions

    Skill(SkillHandler skillHandler, AbilityHandler<E> abilityHandler) {
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


    public boolean isActivated() {
       return false;
    }

    public void useSkill(ArrayList<E> relatedSoldiers, Hero userHero) {
        if (this.remainingCooldown != 0) {
            return;
        }
        for (E soldier: relatedSoldiers) {
            if (this.effectedSoldiers.contains(soldier) && (this.canStackUp == false))
                continue;
            for (Property property: this.propertiesOfRelatedSoldiers) {
                property.effect(soldier, Hero.mapOfHeroes.get(this.ownerName), userHero);
            }
            this.effectedSoldiers.add(soldier);
            this.mapOfEffectedSoldiers.put(soldier, new Time(this.timeOfEffecting));
        }
        if (this.propertiesOfUser != null)
            this.propertiesOfUser.effect(userHero, Hero.mapOfHeroes.get(this.ownerName), userHero);
        this.remainingCooldown = this.cooldown[this.currentGrade];
    }

    public void removeEffect() {
        ArrayList<Integer> indexOfRemovedSoldiers = new ArrayList<Integer>();
        for (int i = 0; i < this.effectedSoldiers.size(); i++) {
            E soldier = (E) this.effectedSoldiers.get(i);
            if (this.mapOfEffectedSoldiers.get(soldier).isTimePassed()) {
                for (Property property: this.propertiesOfRelatedSoldiers) {
                    property.removeEffect(soldier);
                }
                indexOfRemovedSoldiers.add(new Integer(i));
                this.mapOfEffectedSoldiers.remove(soldier);
            }
        }
        for (int index: indexOfRemovedSoldiers) {
            this.effectedSoldiers.remove(index);
        }
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

    public void choosingRelatedSoldiers() {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            if (this.numberOfRelatedSoldiers == GameEngine.listOfEnemies.size())
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
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer){
                ArrayList<String> nameOfEnemies = Display.getAbilityDetailsBeforeUsing(this.blackList);
                for (String nameOfEnemy: nameOfEnemies) {
                    this.relatedSoldiers.add(Enemy.mapOfEnemies.get(nameOfEnemy));
                }
            }
        }
        else {
            if (this.numberOfRelatedSoldiers == GameEngine.listOfHeroes.size())
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
            else if (this.isDependsRelatedSoldiersSelectingOnPlayer){
                ArrayList<String> nameOfHeroes = Display.getAbilityDetailsBeforeUsing(this.blackList);
                for (String nameOfHero: nameOfHeroes) {
                    this.relatedSoldiers.add(Hero.mapOfHeroes.get(nameOfHero));
                }
            }
            else {
                this.relatedSoldiers.add(Hero.mapOfHeroes.get(this.ownerName));
            }
        }
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

    public ArrayList<Property> getPropertiesOfRelatedSoldiers() {
        return propertiesOfRelatedSoldiers;
    }

    public void setPropertiesOfRelatedSoldiers(ArrayList<Property> propertiesOfRelatedSoldiers) {
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

}
