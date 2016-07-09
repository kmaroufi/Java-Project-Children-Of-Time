import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubAbilityComponentHandler<T> {
    protected ClassName classOfEffectedObjects;
    protected Tree<ArrayList<Property<T, ?>>> mapOfConditions;
    protected SelectingObjectsDetail<T> selectingEffectedObjectsDetails;

    protected ArrayList<T> listOfEffectedObjects;
    protected java.util.Map<T, ArrayList<Property<T, ?>>> mapOfEffectedProperties = new HashMap<>(); // Key = className, Value = { Key = object, Value = array of properties that affect the object

    //-------------------------------------------------------- Constructors

    public SubAbilityComponentHandler(ClassName classOfEffectedObjects, Tree<ArrayList<Property<T, ?>>> mapOfConditions, SelectingObjectsDetail<T> selectingEffectedObjectsDetails, ArrayList<T> listOfEffectedObjects, Map<T, ArrayList<Property<T, ?>>> mapOfEffectedProperties) {
        this.classOfEffectedObjects = classOfEffectedObjects;
        this.mapOfConditions = mapOfConditions;
        this.selectingEffectedObjectsDetails = selectingEffectedObjectsDetails;
        this.listOfEffectedObjects = listOfEffectedObjects;
        this.mapOfEffectedProperties = mapOfEffectedProperties;
    }


    //-------------------------------------------------------- Getter And Setters


    public ClassName getClassOfEffectedObjects() {
        return classOfEffectedObjects;
    }

    public Tree<ArrayList<Property<T, ?>>> getMapOfConditions() {
        return mapOfConditions;
    }

    public SelectingObjectsDetail<T> getSelectingEffectedObjectsDetails() {
        return selectingEffectedObjectsDetails;
    }

    public ArrayList<T> getListOfEffectedObjects() {
        return listOfEffectedObjects;
    }

    public Map<T, ArrayList<Property<T, ?>>> getMapOfEffectedProperties() {
        return mapOfEffectedProperties;
    }
}
