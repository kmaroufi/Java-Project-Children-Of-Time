import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class AbilityHandler<T> {
    private String name;
    private String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    private boolean isGlobal;
    private int numberOfGrades;
    private int[] costOfUpgrade;                //cost of it per Upgrade
    private Map<Integer, ArrayList<String>> nameOfNecessaryAbilities; // Key = currentGrade, Value = list Of necessary abilities
    private Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities; // Key = currentGrade, Value = map Of necess
    private String[] upgradeDescription;
    private String description;

    private ArrayList<String> classOfEffectedObjects = new ArrayList<>(); // Array of className
    private Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass = new HashMap<>(); // Key = className, Value = treeCondition
    private Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails = new HashMap<>(); // Key = className, Value = Details about how effected objects are selected
    private Map<Property, SelectingObjectsDetail> selectingEffectingObjectsDetails = new HashMap<>(); // Key = Property, Value = Details about how effecting objects are selected

    private Map<String, ArrayList> listOfEffectedObjectsByClass = new HashMap<>(); // Key = className, Value = list of effected objects
    private Map<String, Map> mapOfEffectedPropertiesByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    private ArrayList<Property> properties = new ArrayList<>(); // array of all properties that included in treeConditions

    //-----------------------------------------------          Constructor


    public AbilityHandler(String name, String ownerName, boolean isGlobal, int numberOfGrades, int[] costOfUpgrade, Map<Integer, ArrayList<String>> nameOfNecessaryAbilities, Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities, String[] upgradeDescription, String description, ArrayList<String> classOfEffectedObjects, Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass, Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails, Map<Property, SelectingObjectsDetail> selectingEffectingObjectsDetails, Map<String, ArrayList> listOfEffectedObjectsByClass, Map<String, Map> mapOfEffectedPropertiesByClass, ArrayList<Property> properties) {
        this.name = name;
        this.ownerName = ownerName;
        this.isGlobal = isGlobal;
        this.numberOfGrades = numberOfGrades;
        this.costOfUpgrade = costOfUpgrade;
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
        this.upgradeDescription = upgradeDescription;
        this.description = description;
        this.classOfEffectedObjects = classOfEffectedObjects;
        this.mapOfConditionsByClass = mapOfConditionsByClass;
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
        this.selectingEffectingObjectsDetails = selectingEffectingObjectsDetails;
        this.listOfEffectedObjectsByClass = listOfEffectedObjectsByClass;
        this.mapOfEffectedPropertiesByClass = mapOfEffectedPropertiesByClass;
        this.properties = properties;
    }

    public AbilityHandler() {

    }


    //---------------------------------------------------------------- Getter && Setters


    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public int getNumberOfGrades() {
        return numberOfGrades;
    }

    public int[] getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public Map<Integer, ArrayList<String>> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public Map<Integer, Map<String, Integer>> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public String[] getUpgradeDescription() {
        return upgradeDescription;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public Map<String, Tree<ArrayList<Property>>> getMapOfConditionsByClass() {
        return mapOfConditionsByClass;
    }

    public Map<String, SelectingObjectsDetail> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public Map<String, ArrayList> getListOfEffectedObjectsByClass() {
        return listOfEffectedObjectsByClass;
    }

    public Map<String, Map> getMapOfEffectedPropertiesByClass() {
        return mapOfEffectedPropertiesByClass;
    }

    public Map<Property, SelectingObjectsDetail> getSelectingEffectingObjectsDetails() {
        return selectingEffectingObjectsDetails;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }
}
