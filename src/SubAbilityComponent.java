import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubAbilityComponent<T> {

    protected ClassName classOfEffectedObjects;
    protected Tree<ArrayList<Property<T, ?>>> trieConditions;
    protected SelectingObjectsDetail<T> selectingEffectedObjectsDetails;

    protected ArrayList<T> listOfEffectedObjects;
    protected Map<T, ArrayList<Property<T, ?>>> mapOfEffectedProperties = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    //-------------------------------------------------------- Constructors

    public SubAbilityComponent(ClassName classOfEffectedObjects, Tree<ArrayList<Property<T, ?>>> mapOfConditions, SelectingObjectsDetail<T> selectingEffectedObjectsDetails, ArrayList<T> listOfEffectedObjects, Map<T, ArrayList<Property<T, ?>>> mapOfEffectedProperties) {
        this.classOfEffectedObjects = classOfEffectedObjects;
        this.trieConditions = mapOfConditions;
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
        this.listOfEffectedObjects = listOfEffectedObjects;
        this.mapOfEffectedProperties = mapOfEffectedProperties;
    }

    public SubAbilityComponent(SubAbilityComponentHandler subAbilityComponentHandler) {
        this.setClassOfEffectedObjects(subAbilityComponentHandler.getClassOfEffectedObjects());
        this.setTrieConditions(subAbilityComponentHandler.getMapOfConditions());
        this.setSelectingEffectedObjectsDetails(subAbilityComponentHandler.getSelectingEffectedObjectsDetails());
        this.setListOfEffectedObjects(subAbilityComponentHandler.getListOfEffectedObjects());
        this.setMapOfEffectedProperties(subAbilityComponentHandler.getMapOfEffectedProperties());
    }


    //-------------------------------------------------------- Functions



    //-------------------------------------------------------- Getter And Setters


    public ClassName getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public void setClassOfEffectedObjects(ClassName classOfEffectedObjects) {
        this.classOfEffectedObjects = classOfEffectedObjects;
    }

    public Tree<ArrayList<Property<T, ?>>> getTrieConditions() {
        return trieConditions;
    }

    public void setTrieConditions(Tree<ArrayList<Property<T, ?>>> trieConditions) {
        this.trieConditions = trieConditions;
    }

    public SelectingObjectsDetail<T> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public void setSelectingEffectedObjectsDetails(SelectingObjectsDetail<T> selectingEffectedObjectsDetails) {
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
    }

    public ArrayList<T> getListOfEffectedObjects() {
        return listOfEffectedObjects;
    }

    public void setListOfEffectedObjects(ArrayList<T> listOfEffectedObjects) {
        this.listOfEffectedObjects = listOfEffectedObjects;
    }

    public Map<T, ArrayList<Property<T, ?>>> getMapOfEffectedProperties() {
        return mapOfEffectedProperties;
    }

    public void setMapOfEffectedProperties(Map<T, ArrayList<Property<T, ?>>> mapOfEffectedProperties) {
        this.mapOfEffectedProperties = mapOfEffectedProperties;
    }
}
