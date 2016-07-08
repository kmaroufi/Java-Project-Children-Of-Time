import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/7/2016.
 */
public abstract class SubAbility {

    protected boolean isGlobal; // Just clear that other heroes can use this Ability or no.
    protected int costOfUpgrade;
    protected ArrayList<String> nameOfNecessaryAbilities; // list Of necessary abilities
    protected Map<String, Integer> gradeOfNecessaryAbilities; // map Of necessary abilities
    protected String upgradeDescription;

    protected ArrayList<String> classOfEffectedObjects = new ArrayList<>(); // Array of className
    protected Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass = new HashMap<>(); // Key = className, Value = treeCondition
    protected Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails = new HashMap<>(); // Key = className, Value = Details about how effected objects are selected

    protected Map<String, ArrayList> listOfEffectedObjectsByClass = new HashMap<>(); // Key = className, Value = list of effected objects
    protected Map<String, Map> mapOfEffectedPropertiesByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    //-------------------------------------------------------- Constructors
    public SubAbility(SubAbilityHandler subAbilityHandler) {
        this.setGlobal(subAbilityHandler.isGlobal());
        this.setCostOfUpgrade(subAbilityHandler.getCostOfUpgrade());
        this.setNameOfNecessaryAbilities(subAbilityHandler.getNameOfNecessaryAbilities());
        this.setGradeOfNecessaryAbilities(subAbilityHandler.getGradeOfNecessaryAbilities());
        this.setUpgradeDescription(subAbilityHandler.getUpgradeDescription());
        this.setClassOfEffectedObjects(subAbilityHandler.getClassOfEffectedObjects());
        this.setMapOfConditionsByClass(subAbilityHandler.getMapOfConditionsByClass());
        this.setSelectingEffectedObjectsDetails(subAbilityHandler.getSelectingEffectedObjectsDetails());
        this.setListOfEffectedObjectsByClass(subAbilityHandler.getListOfEffectedObjectsByClass());
        this.setMapOfEffectedPropertiesByClass(subAbilityHandler.getMapOfEffectedPropertiesByClass());
    }

    //-------------------------------------------------------- Functions

    abstract public boolean canAcquireThisGrade();

    //-------------------------------------------------------- Getter And Setters


    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public int getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public void setCostOfUpgrade(int costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

    public ArrayList<String> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public void setNameOfNecessaryAbilities(ArrayList<String> nameOfNecessaryAbilities) {
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
    }

    public Map<String, Integer> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public void setGradeOfNecessaryAbilities(Map<String, Integer> gradeOfNecessaryAbilities) {
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
    }

    public String getUpgradeDescription() {
        return upgradeDescription;
    }

    public void setUpgradeDescription(String upgradeDescription) {
        this.upgradeDescription = upgradeDescription;
    }

    public ArrayList<String> getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public void setClassOfEffectedObjects(ArrayList<String> classOfEffectedObjects) {
        this.classOfEffectedObjects = classOfEffectedObjects;
    }

    public Map<String, Tree<ArrayList<Property>>> getMapOfConditionsByClass() {
        return mapOfConditionsByClass;
    }

    public void setMapOfConditionsByClass(Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass) {
        this.mapOfConditionsByClass = mapOfConditionsByClass;
    }

    public Map<String, SelectingObjectsDetail> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public void setSelectingEffectedObjectsDetails(Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails) {
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
    }

    public Map<String, ArrayList> getListOfEffectedObjectsByClass() {
        return listOfEffectedObjectsByClass;
    }

    public void setListOfEffectedObjectsByClass(Map<String, ArrayList> listOfEffectedObjectsByClass) {
        this.listOfEffectedObjectsByClass = listOfEffectedObjectsByClass;
    }

    public Map<String, Map> getMapOfEffectedPropertiesByClass() {
        return mapOfEffectedPropertiesByClass;
    }

    public void setMapOfEffectedPropertiesByClass(Map<String, Map> mapOfEffectedPropertiesByClass) {
        this.mapOfEffectedPropertiesByClass = mapOfEffectedPropertiesByClass;
    }

}
