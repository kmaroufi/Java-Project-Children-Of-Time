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

    protected ArrayList<ClassName> classOfEffectedObjects = new ArrayList<>(); // Array of className
    protected Map<ClassName, Tree<?>> mapOfConditionsByClass = new HashMap<>(); // Key = className, Value = treeCondition
    protected Map<ClassName, SelectingObjectsDetail<?>> selectingEffectedObjectsDetails = new HashMap<>(); // Key = className, Value = Details about how effected objects are selected

    protected Map<ClassName, ArrayList> listOfEffectedObjectsByClass = new HashMap<>(); // Key = className, Value = list of effected objects
    protected Map<ClassName, Map> mapOfEffectedPropertiesByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

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

    public ArrayList<ClassName> getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public void setClassOfEffectedObjects(ArrayList<ClassName> classOfEffectedObjects) {
        this.classOfEffectedObjects = classOfEffectedObjects;
    }

    public Map<ClassName, Tree<?>> getMapOfConditionsByClass() {
        return mapOfConditionsByClass;
    }

    public void setMapOfConditionsByClass(Map<ClassName, Tree<?>> mapOfConditionsByClass) {
        this.mapOfConditionsByClass = mapOfConditionsByClass;
    }

    public Map<ClassName, SelectingObjectsDetail<?>> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public void setSelectingEffectedObjectsDetails(Map<ClassName, SelectingObjectsDetail<?>> selectingEffectedObjectsDetails) {
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
    }

    public Map<ClassName, ArrayList> getListOfEffectedObjectsByClass() {
        return listOfEffectedObjectsByClass;
    }

    public void setListOfEffectedObjectsByClass(Map<ClassName, ArrayList> listOfEffectedObjectsByClass) {
        this.listOfEffectedObjectsByClass = listOfEffectedObjectsByClass;
    }

    public Map<ClassName, Map> getMapOfEffectedPropertiesByClass() {
        return mapOfEffectedPropertiesByClass;
    }

    public void setMapOfEffectedPropertiesByClass(Map<ClassName, Map> mapOfEffectedPropertiesByClass) {
        this.mapOfEffectedPropertiesByClass = mapOfEffectedPropertiesByClass;
    }
}
