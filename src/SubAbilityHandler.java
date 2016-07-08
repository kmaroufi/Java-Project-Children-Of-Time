import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/8/2016.
 */
public class SubAbilityHandler {
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
//    nabayad bezarim null be SubAbility berese!

    //-------------------------------------------------------- Constructors

    public SubAbilityHandler(boolean isGlobal, int costOfUpgrade, ArrayList<String> nameOfNecessaryAbilities, Map<String, Integer> gradeOfNecessaryAbilities, String upgradeDescription, ArrayList<String> classOfEffectedObjects, Map<String, Tree<ArrayList<Property>>> mapOfConditionsByClass, Map<String, SelectingObjectsDetail> selectingEffectedObjectsDetails, Map<String, ArrayList> listOfEffectedObjectsByClass, Map<String, Map> mapOfEffectedPropertiesByClass) {
        this.isGlobal = isGlobal;
        this.costOfUpgrade = costOfUpgrade;
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
        this.upgradeDescription = upgradeDescription;
        this.classOfEffectedObjects = classOfEffectedObjects;
        this.mapOfConditionsByClass = mapOfConditionsByClass;
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
        this.listOfEffectedObjectsByClass = listOfEffectedObjectsByClass;
        this.mapOfEffectedPropertiesByClass = mapOfEffectedPropertiesByClass;
    }

    //-------------------------------------------------------- Getter And Setters

    public boolean isGlobal() {
        return isGlobal;
    }

    public int getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public ArrayList<String> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public Map<String, Integer> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public String getUpgradeDescription() {
        return upgradeDescription;
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

}
