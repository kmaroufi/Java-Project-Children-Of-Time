import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability {

    protected String name;
    protected String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    protected ArrayList<String> effectedSoldiers;
    protected ArrayList<String> listOfSoldiersThatCanGetEffected;
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private int numberOfGrades;
    private int currentGrade;
    private String fieldOfEffecting; // this field can get Hero, Enemy, Ability, item, Shop and ... value.
    private boolean isPermanently;
    private int[] costOfUpgrade;
    public static Map<String, String> listOfAbilities;

    Ability(AbilityHandler abilityHandler) {

    }

    Ability() {

    }

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

    abstract public void upgrade();
}
