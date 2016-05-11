import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability<T> {

    protected String name;
    protected String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    protected ArrayList<T> effectedSoldiers;
    protected ArrayList<T> relatedSoldiers;
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private boolean isRandomSoldierSelecting;
    private int numberOfRelatedSoldiers;
    private int numberOfGrades;
    private int currentGrade;
    private String fieldOfEffecting; // this field can get Hero, Enemy, Ability, item, Shop and ... value.
    private boolean isPermanently;
    private boolean isEffectDuringAttack;
    private boolean hasCondition;
    private int[] costOfUpgrade;
    public static Map<String, String> listOfAbilities;
    //-------------------------------------------------------- Constructors
    Ability(AbilityHandler abilityHandler) {

    }
    Ability() {

    }
    //-------------------------------------------------------- Functions

    abstract public void upgrade();

    private void choosingRelatedSoldiers() {
        if (this.hasEffectedOnEnemy) {
            if (this.isRandomSoldierSelecting) {
                ArrayList<Enemy> enemies = new ArrayList<>();
                enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(enemies.size());
                    this.relatedSoldiers.add((T) enemies.get(randomIndex));
                    enemies.remove(randomIndex);
                }
            }
            else {
                ArrayList<String> nameOfEnemies = Display.getAbilityDetailsBeforeUsing();
                for (String nameOfEnemy: nameOfEnemies) {
                    this.relatedSoldiers.add((T) Enemy.mapOfEnemies.get(nameOfEnemy));
                }
            }
        }
        else {
            if (this.isRandomSoldierSelecting) {
                ArrayList<Hero> heroes = new ArrayList<>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    this.relatedSoldiers.add((T) heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            else {
                ArrayList<String> nameOfHeroes = Display.getAbilityDetailsBeforeUsing();
                for (String nameOfHero: nameOfHeroes) {
                    this.relatedSoldiers.add((T) Hero.mapOfHeroes.get(nameOfHero));
                }
            }
        }
    }
    //-------------------------------------------------------- Getter And Setters

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public boolean isHasEffectedOnEnemy() {
        return hasEffectedOnEnemy;
    }

    public void setHasEffectedOnEnemy(boolean hasEffectedOnEnemy) {
        this.hasEffectedOnEnemy = hasEffectedOnEnemy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGrades() {
        return numberOfGrades;
    }

    public void setNumberOfGrades(int numberOfGrades) {
        this.numberOfGrades = numberOfGrades;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

}
