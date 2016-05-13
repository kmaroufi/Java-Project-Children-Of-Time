import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class AbilityHandler<T> {
    private String name;
    private String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private boolean isRandomSoldierSelecting;
    private int numberOfRelatedSoldiers;                // soldiers under effect of this ability
    private int numberOfGrades;
    private String fieldOfEffecting; // this field can get Hero, Enemy, Ability, item, Shop and ... value.
    private boolean isEffectDuringAttack;
    private boolean hasCondition;
    private int[] costOfUpgrade;                //cost of it per Upgrade
    private Map<Integer, ArrayList<String>> nameOfNecessaryAbilities; // Key = currentGrade, Value = list Of necessary abilities
    private Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities; // Key = currentGrade, Value = map Of necess

    //-----------------------------------------------          Constructor

    public AbilityHandler(String name, String ownerName, boolean isGlobal, boolean hasEffectedOnEnemy, boolean isRandomSoldierSelecting, int numberOfRelatedSoldiers, int numberOfGrades, String fieldOfEffecting, boolean isEffectDuringAttack, boolean hasCondition, int[] costOfUpgrade, Map<Integer, ArrayList<String>> nameOfNecessaryAbilities, Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities) {
        this.name = name;
        this.ownerName = ownerName;
        this.isGlobal = isGlobal;
        this.hasEffectedOnEnemy = hasEffectedOnEnemy;
        this.isRandomSoldierSelecting = isRandomSoldierSelecting;
        this.numberOfRelatedSoldiers = numberOfRelatedSoldiers;
        this.numberOfGrades = numberOfGrades;
        this.fieldOfEffecting = fieldOfEffecting;
        this.isEffectDuringAttack = isEffectDuringAttack;
        this.hasCondition = hasCondition;
        this.costOfUpgrade = costOfUpgrade;
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
    }


    //---------------------------------------------------------------- Getter && Setters


    public Map<Integer, ArrayList<String>> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public void setNameOfNecessaryAbilities(Map<Integer, ArrayList<String>> nameOfNecessaryAbilities) {
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
    }

    public Map<Integer, Map<String, Integer>> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public void setGradeOfNecessaryAbilities(Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities) {
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
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

    public String getFieldOfEffecting() {
        return fieldOfEffecting;
    }

    public void setFieldOfEffecting(String fieldOfEffecting) {
        this.fieldOfEffecting = fieldOfEffecting;
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
