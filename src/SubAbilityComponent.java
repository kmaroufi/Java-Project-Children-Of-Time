import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubAbilityComponent<T> {

    protected ClassName classOfEffectedObjects;
    protected Tree<ArrayList<Property<T, ?>>> mapOfConditions;
    protected SelectingObjectsDetail<T> selectingEffectedObjectsDetails;

    protected ArrayList<T> listOfEffectedObjects;
    protected Map<T, Property<T, ?>> mapOfEffectedProperties = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    //-------------------------------------------------------- Constructors

    public SubAbilityComponent(ClassName classOfEffectedObjects, Tree<ArrayList<Property<T, ?>>> mapOfConditions, SelectingObjectsDetail<T> selectingEffectedObjectsDetails, ArrayList<T> listOfEffectedObjects, Map<T, Property<T, ?>> mapOfEffectedProperties) {
        this.classOfEffectedObjects = classOfEffectedObjects;
        this.mapOfConditions = mapOfConditions;
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
        this.listOfEffectedObjects = listOfEffectedObjects;
        this.mapOfEffectedProperties = mapOfEffectedProperties;
    }


    //-------------------------------------------------------- Functions



    //-------------------------------------------------------- Getter And Setters


    public ClassName getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public void setClassOfEffectedObjects(ClassName classOfEffectedObjects) {
        this.classOfEffectedObjects = classOfEffectedObjects;
    }

    public Tree<ArrayList<Property<T, ?>>> getMapOfConditions() {
        return mapOfConditions;
    }

    public void setMapOfConditions(Tree<ArrayList<Property<T, ?>>> mapOfConditions) {
        this.mapOfConditions = mapOfConditions;
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

    public Map<T, Property<T, ?>> getMapOfEffectedProperties() {
        return mapOfEffectedProperties;
    }

    public void setMapOfEffectedProperties(Map<T, Property<T, ?>> mapOfEffectedProperties) {
        this.mapOfEffectedProperties = mapOfEffectedProperties;
    }
}
