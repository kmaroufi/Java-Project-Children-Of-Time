import java.util.ArrayList;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class AbilityHandler<T> {
    private String name;
    private String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    private ArrayList<T> effectedSoldiers;
    private ArrayList<T> relatedSoldiers;
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

    //---------------------------------------------------------------- Getter && Setters


    public ArrayList<T> getEffectedSoldiers() {
        return effectedSoldiers;
    }

    public void setEffectedSoldiers(ArrayList<T> effectedSoldiers) {
        this.effectedSoldiers = effectedSoldiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public ArrayList<T> getRelatedSoldiers() {
        return relatedSoldiers;
    }

    public void setRelatedSoldiers(ArrayList<T> relatedSoldiers) {
        this.relatedSoldiers = relatedSoldiers;
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

    public boolean isRandomSoldierSelecting() {
        return isRandomSoldierSelecting;
    }

    public void setRandomSoldierSelecting(boolean randomSoldierSelecting) {
        isRandomSoldierSelecting = randomSoldierSelecting;
    }

    public int getNumberOfRelatedSoldiers() {
        return numberOfRelatedSoldiers;
    }

    public void setNumberOfRelatedSoldiers(int numberOfRelatedSoldiers) {
        this.numberOfRelatedSoldiers = numberOfRelatedSoldiers;
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

    public String getFieldOfEffecting() {
        return fieldOfEffecting;
    }

    public void setFieldOfEffecting(String fieldOfEffecting) {
        this.fieldOfEffecting = fieldOfEffecting;
    }

    public boolean isPermanently() {
        return isPermanently;
    }

    public void setPermanently(boolean permanently) {
        isPermanently = permanently;
    }

    public boolean isEffectDuringAttack() {
        return isEffectDuringAttack;
    }

    public void setEffectDuringAttack(boolean effectDuringAttack) {
        isEffectDuringAttack = effectDuringAttack;
    }

    public boolean isHasCondition() {
        return hasCondition;
    }

    public void setHasCondition(boolean hasCondition) {
        this.hasCondition = hasCondition;
    }

    public int[] getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public void setCostOfUpgrade(int[] costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

}
