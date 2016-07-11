import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class PropertyHandler<T> {
    private String name;
    private boolean isPermanently;
    private ClassName classOfEffectingObjects;
    private ClassName classOfEffectedObject;
    private double constantProperty;
    private SelectingObjectsDetail<T> selectingEffectingObjectsDetails;
    private Tree<ArrayList<Pair<String, Double>>> trieCondition;


    //-------------------------------------------------------------- Constructors

    public PropertyHandler(String name, boolean isPermanently, ClassName classOfEffectingObjects, ClassName classOfEffectedObject, double constantProperty, SelectingObjectsDetail<T> selectingEffectingObjectsDetails, Tree<ArrayList<Pair<String, Double>>> trieCondition) {
        this.name = name;
        this.isPermanently = isPermanently;
        this.classOfEffectingObjects = classOfEffectingObjects;
        this.classOfEffectedObject = classOfEffectedObject;
        this.constantProperty = constantProperty;
        this.selectingEffectingObjectsDetails = selectingEffectingObjectsDetails;
        this.trieCondition = trieCondition;
    }

    //--------------------------------------------------------------- Getter && Setters


    public String getName() {
        return name;
    }

    public boolean isPermanently() {
        return isPermanently;
    }

    public ClassName getClassOfEffectingObjects() {
        return classOfEffectingObjects;
    }

    public ClassName getClassOfEffectedObject() {
        return classOfEffectedObject;
    }

    public double getConstantProperty() {
        return constantProperty;
    }

    public Tree<ArrayList<Pair<String, Double>>> getTrieCondition() {
        return trieCondition;
    }

    public SelectingObjectsDetail<T> getSelectingEffectingObjectsDetails() {
        return selectingEffectingObjectsDetails;
    }
}
