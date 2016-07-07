import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability {

    public static Map<String, String> listOfAbilities = new HashMap<>();

    protected String name;
    protected String ownerName;
    protected boolean isGlobal; // Just clear that other heroes can use this Ability or no.
    protected int numberOfGrades;
    protected int currentGrade;
    protected int[] costOfUpgrade;
    protected Map<Integer, ArrayList<String>> nameOfNecessaryAbilities; // Key = currentGrade, Value = list Of necessary abilities
    protected Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities; // Key = currentGrade, Value = map Of necessary abilities
    protected String[] upgradeDescription;
    protected String description;
    protected boolean isAcquire;

    protected ArrayList<String> classOfEffectedObjects = new ArrayList<>(); // Array of className
    protected Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass = new HashMap<>(); // Key = className, Value = treeCondition
    protected Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails = new HashMap<>(); // Key = className, Value = Details about how effected objects are selected
    protected Map<Property, SelectingObjectsDetail> selectingEffectingObjectsDetails = new HashMap<>(); // Key = Property, Value = Details about how effecting objects are selected

    protected Map<String, ArrayList> listOfEffectedObjectsByClass = new HashMap<>(); // Key = className, Value = list of effected objects
    protected Map<String, Map> mapOfEffectedPropertiesByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    protected ArrayList<Property> properties = new ArrayList<>(); // array of all properties that included in treeConditions

    //-------------------------------------------------------- Constructors
    public Ability(AbilityHandler abilityHandler) {
        this.setName(abilityHandler.getName());
        this.setOwnerName(abilityHandler.getOwnerName());
        this.setGlobal(abilityHandler.isGlobal());
        this.setHasEffectedOnEnemy(abilityHandler.isHasEffectedOnEnemy());
        this.setRandomSoldierSelecting(abilityHandler.isRandomSoldierSelecting());
        this.setNumberOfRelatedSoldiers(abilityHandler.getNumberOfRelatedSoldiers());
        this.setNumberOfGrades(abilityHandler.getNumberOfGrades());
        this.setCurrentGrade(0);
        this.setFieldOfEffecting(abilityHandler.getFieldOfEffecting());
        this.setHasCondition(abilityHandler.isHasCondition());
        this.setCostOfUpgrade(abilityHandler.getCostOfUpgrade());
        this.setNameOfNecessaryAbilities(abilityHandler.getNameOfNecessaryAbilities());
        this.setGradeOfNecessaryAbilities(abilityHandler.getGradeOfNecessaryAbilities());
        this.setUpgradeDescription(abilityHandler.getUpgradeDescription());
        this.setDescription(abilityHandler.getDescription());
        this.setAcquire(abilityHandler.isAcquire());
    }


    public Ability() {

    }
    //-------------------------------------------------------- Functions

    abstract public boolean upgrade(Player player);

    //-------------------------------------------------------- Getter And Setters


    public boolean isAcquire() {
        return isAcquire;
    }

    public void setAcquire(boolean acquire) {
        isAcquire = acquire;
    }

    public String[] getUpgradeDescription() {
        return upgradeDescription;
    }

    public void setUpgradeDescription(String[] upgradeDescription) {
        this.upgradeDescription = upgradeDescription;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
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

    public int[] getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public void setCostOfUpgrade(int[] costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

}
