import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class PropertyHandler {
    private String name;
    private Class classOfField;
    private final int NumberOfUpgrades;
    private boolean isDependOnEffectedSoldier;
    private boolean isDependOnUserHero;
    private boolean isPermanently;

    private Map<String, ArrayList<String>> variablesOfObjects;
    private Map<String, Map<String, Double[]>> variablesCoefficientOfObjects;

    private double[] constantProperty ;


    //-------------------------------------------------------------- Constructors

    public PropertyHandler(String name, int numberOfUpgrades, boolean isDependOnEffectedSoldier, boolean isDependOnUserHero, boolean isPermanently, double[] constantProperty, Map<String, ArrayList<String>> variablesOfObjects, Map<String, Map<String, Double[]>> variablesCoefficientOfObjects) {
        this.name = name;
        NumberOfUpgrades = numberOfUpgrades;
        this.isDependOnEffectedSoldier = isDependOnEffectedSoldier;
        this.isDependOnUserHero = isDependOnUserHero;
        this.isPermanently = isPermanently;
        this.constantProperty = constantProperty;
        this.variablesOfObjects = variablesOfObjects;
        this.variablesCoefficientOfObjects = variablesCoefficientOfObjects;
    }

    public PropertyHandler(){
        this.NumberOfUpgrades = 0;
    }
    //--------------------------------------------------------------- Getter && Setters


    public boolean isDependOnEffectedSoldier() {
        return isDependOnEffectedSoldier;
    }

    public void setDependOnEffectedSoldier(boolean dependOnEffectedSoldier) {
        isDependOnEffectedSoldier = dependOnEffectedSoldier;
    }

    public boolean isDependOnUserHero() {
        return isDependOnUserHero;
    }

    public void setDependOnUserHero(boolean dependOnUserHero) {
        isDependOnUserHero = dependOnUserHero;
    }

    public boolean isPermanently() {
        return isPermanently;
    }

    public void setPermanently(boolean permanently) {
        isPermanently = permanently;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getConstantProperty() {
        return constantProperty;
    }

    public void setConstantProperty(double[] constantProperty) {
        this.constantProperty = constantProperty;
    }

    public int getNumberOfUpgrades() {
        return NumberOfUpgrades;
    }

    public Map<String, ArrayList<String>> getVariablesOfObjects() {
        return variablesOfObjects;
    }

    public void setVariablesOfObjects(Map<String, ArrayList<String>> variablesOfObjects) {
        this.variablesOfObjects = variablesOfObjects;
    }

    public Map<String, Map<String, Double[]>> getVariablesCoefficientOfObjects() {
        return variablesCoefficientOfObjects;
    }

    public void setVariablesCoefficientOfObjects(Map<String, Map<String, Double[]>> variablesCoefficientOfObjects) {
        this.variablesCoefficientOfObjects = variablesCoefficientOfObjects;
    }
}
