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

    protected ArrayList<ClassName> classOfEffectedObjects = new ArrayList<>(); // Array of className
    protected Map<ClassName, Tree<?>> mapOfConditionsByClass = new HashMap<>(); // Key = className, Value = treeCondition
    protected Map<ClassName, SelectingObjectsDetail> selectingEffectedObjectsDetails = new HashMap<>(); // Key = className, Value = Details about how effected objects are selected

    protected Map<ClassName, ArrayList> listOfEffectedObjectsByClass = new HashMap<>(); // Key = className, Value = list of effected objects
    protected Map<ClassName, Map> mapOfEffectedPropertiesByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object
//    nabayad bezarim null be SubAbility berese!

    //-------------------------------------------------------- Constructors

    public SubAbilityHandler(boolean isGlobal, int costOfUpgrade, ArrayList<String> nameOfNecessaryAbilities, Map<String, Integer> gradeOfNecessaryAbilities, String upgradeDescription, ArrayList<ClassName> classOfEffectedObjects, Map<ClassName, Tree<?>> mapOfConditionsByClass, Map<ClassName, SelectingObjectsDetail> selectingEffectedObjectsDetails, Map<ClassName, ArrayList> listOfEffectedObjectsByClass, Map<ClassName, Map> mapOfEffectedPropertiesByClass) {
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

    public ArrayList<ClassName> getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public Map<ClassName, Tree<?>> getMapOfConditionsByClass() {
        return mapOfConditionsByClass;
    }

    public Map<ClassName, SelectingObjectsDetail> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public Map<ClassName, ArrayList> getListOfEffectedObjectsByClass() {
        return listOfEffectedObjectsByClass;
    }

    public Map<ClassName, Map> getMapOfEffectedPropertiesByClass() {
        return mapOfEffectedPropertiesByClass;
    }
}
