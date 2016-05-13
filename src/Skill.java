import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill extends Ability{
    public static Map<String, Skill> listOfSkills;
    private ArrayList<Property> properties;
    private int nonTargetedEnemy;
    private boolean isRepeated;
    private int time;
    private int remainingTime;
    private int cooldown;
    private int remainingCooldown;
    private boolean casStackUp;                                 // What is this Shit?
    private boolean isUsed;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    //---------------------------------------------------------------- Functions
    public boolean isActivated() {
        //TODO
        return false;
    }

    public void useSkill() {
        if (this.remainingCooldown != 0) {
            return;
        }
        if ((this.remainingTime == 0) || ((this.remainingTime != 0) && (this.casStackUp))) {
        }
    };

    public void choosingRelatedSoldiers() {
        if (this.hasEffectedOnEnemy) {
            if (this.isRandomSoldierSelecting) {
                ArrayList<Enemy> enemies = new ArrayList<Enemy>();
                enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(enemies.size());
                    this.relatedSoldiers.add(enemies.get(randomIndex));
                    enemies.remove(randomIndex);
                }
            }
            else {
                ArrayList<String> nameOfEnemies = Display.getAbilityDetailsBeforeUsing();
                for (String nameOfEnemy: nameOfEnemies) {
                    this.relatedSoldiers.add(Enemy.mapOfEnemies.get(nameOfEnemy));
                }
            }
        }
        else {
            if (this.isRandomSoldierSelecting) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    this.relatedSoldiers.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            else {
                ArrayList<String> nameOfHeroes = Display.getAbilityDetailsBeforeUsing();
                for (String nameOfHero: nameOfHeroes) {
                    this.relatedSoldiers.add(Hero.mapOfHeroes.get(nameOfHero));
                }
            }
        }
    }
    //---------------------------------------------------- Getter && Setters

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
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

}
