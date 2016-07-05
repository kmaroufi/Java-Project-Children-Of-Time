import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property<E, T> implements Cloneable {
    private Double totalEffectOnProperty = new Double(0);               // az che noii e??????
    private String name;
    private int currentGrade;
    private int numberOfUpgrades;
    private boolean isDependOnEffectedSoldier;
    private boolean isDependOnUserHero;
    private boolean isPermanently;

    private Map<String, ArrayList<String>> variablesOfObjects;
    private Map<String, Map<String, Double[]>> variablesCoefficientOfObjects;

    private double[] constantProperty;


    private Map<E, Double> valueOfEffectingOnEffectedSoldiers = new HashMap<>();

    //-------------------------------------------------------------- Constructors

    public Property(PropertyHandler propertyHandler) {
        setName(propertyHandler.getName());
        setNumberOfUpgrades(propertyHandler.getNumberOfUpgrades());
        setCurrentGrade(0);
        setDependOnEffectedSoldier(propertyHandler.isDependOnEffectedSoldier());
        setDependOnUserHero(propertyHandler.isDependOnUserHero());
        setPermanently(propertyHandler.isPermanently());
        setConstantProperty(propertyHandler.getConstantProperty());
        setVariablesOfObjects(propertyHandler.getVariablesOfObjects());
        setVariablesCoefficientOfObjects(propertyHandler.getVariablesCoefficientOfObjects());
    }

    public String getName() {
        return this.name;
    }

    //---------------------------------------    Functions

    protected Property<E, T> clone() throws CloneNotSupportedException {
        Property<E, T> property = (Property<E, T>) super.clone();
        property.setValueOfEffectingOnEffectedSoldiers(new HashMap<>());
        return property;
    }

    private <U> Object getFieldValue(U object, String fieldName) {
        Class clazz = object.getClass();
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <U> void setFieldValue(U object, String fieldName, Object value) {
        Class clazz = object.getClass();
        Field field = null;
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void calculateProperty(ArrayList<T> effectingObjects) {
        this.totalEffectOnProperty = this.constantProperty[this.currentGrade - 1];
        for (T effectingObject : effectingObjects) {
            for (String variablesName : this.variablesOfObjects.get(this.getFieldValue(effectingObject, "name"))) {
                double variableValue = (double) this.getFieldValue(effectingObject, variablesName);
                double variableCoefficient = this.variablesCoefficientOfObjects.get(this.getFieldValue(effectingObject, "name")).get(variablesName)[currentGrade - 1];
                this.totalEffectOnProperty += variableCoefficient * variableValue;
            }
        }
    }

    public double effect(E effectedObject, َArrayList<T> effectingObjects) {
        calculateProperty(effectingObjects);
        Double previousValue = (Double) this.getFieldValue(effectedObject, this.name);
        this.setFieldValue(effectedObject, this.name, new Double(previousValue + this.totalEffectOnProperty));
        if (this.valueOfEffectingOnEffectedSoldiers.containsKey(effectedObject)) {
            this.valueOfEffectingOnEffectedSoldiers.put(effectedObject, this.valueOfEffectingOnEffectedSoldiers.get(effectedObject) + this.totalEffectOnProperty);
        } else {
            this.valueOfEffectingOnEffectedSoldiers.put(effectedObject, this.totalEffectOnProperty);
        }
        return this.totalEffectOnProperty;
//        String type = field.getGenericType().toString();
//        Class classOfSoldier2 = relatedSoldier.getClass();
//        Field field2 = null;
//        if (this.name.equals("currentHealth")) {
//            cond = 0;
//            try {
//                field2 = classOfSoldier2.getDeclaredField("maximumHealth");
//            } catch (NoSuchFieldException e) {
//                cond = 1;
//            }
//            if (cond == 1) {
//                cond = 0;
//                classOfSoldier2 = classOfSoldier2.getSuperclass();
//                try {
//                    field2 = classOfSoldier2.getDeclaredField("maximumHealth");
//                } catch (NoSuchFieldException e) {
//                    cond = 1;
//                }
//            }
//            if (cond == 1) {
//                cond = 0;
//                classOfSoldier2 = classOfSoldier2.getSuperclass();
//                try {
//                    field2 = classOfSoldier2.getDeclaredField("maximumHealth");
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                    cond = 1;
//                }
//            }
//        }
//        if (this.name.equals("currentMagic")) {
//            cond = 0;
//            try {
//                field2 = classOfSoldier2.getDeclaredField("maximumMagic");
//            } catch (NoSuchFieldException e) {
//                cond = 1;
//            }
//            if (cond == 1) {
//                cond = 0;
//                classOfSoldier2 = classOfSoldier2.getSuperclass();
//                try {
//                    field2 = classOfSoldier2.getDeclaredField("maximumMagic");
//                } catch (NoSuchFieldException e) {
//                    cond = 1;
//                }
//            }
//            if (cond == 1) {
//                cond = 0;
//                classOfSoldier2 = classOfSoldier2.getSuperclass();
//                try {
//                    field2 = classOfSoldier2.getDeclaredField("maximumMagic");
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                    cond = 1;
//                }
//            }
//        }
//        try {
//            this.calculateProperty(effectingObjects);
//            if (type.equals("int")) {
//                if (this.name.equals("currentHealth") || this.name.equals("currentMagic")) {
//                    if (((int) field.get(relatedSoldier) + this.totalEffectOnProperty.intValue()) > ((int)field2.get(relatedSoldier)))
//                        totalEffectOnProperty = (double)((int) field2.get(relatedSoldier) - (int) field.get(relatedSoldier));
//                }
//                field.set(relatedSoldier, (int) field.get(relatedSoldier) + this.totalEffectOnProperty.intValue());
//            }
//            if (type.equals("double")) {
//                if (this.name.equals("currentHealth") || this.name.equals("currentMagic")) {
//                    if ((((double) field.get(relatedSoldier)) + this.totalEffectOnProperty) > ((int) field2.get(relatedSoldier)))
//                        totalEffectOnProperty = (int) field2.get(relatedSoldier) - (double) field.get(relatedSoldier);
//                }
//                field.set(relatedSoldier, (double) field.get(relatedSoldier) + this.totalEffectOnProperty);
//            }
//            if (this.valueOfEffectingOnEffectedSoldiers.containsKey(relatedSoldier)) {
//                this.valueOfEffectingOnEffectedSoldiers.put((E) relatedSoldier, this.valueOfEffectingOnEffectedSoldiers.get(relatedSoldier) + this.totalEffectOnProperty);
//            }
//            else {
//                this.valueOfEffectingOnEffectedSoldiers.put((E) relatedSoldier, this.totalEffectOnProperty);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return this.totalEffectOnProperty;
    }

    public void removeEffect(E effectedObject) {
        if (isPermanently)
            return;
        Double previousValue = (Double) this.getFieldValue(effectedObject, this.name);
        this.setFieldValue(effectedObject, this.name, previousValue - this.valueOfEffectingOnEffectedSoldiers.get(effectedObject));
        this.valueOfEffectingOnEffectedSoldiers.remove(effectedObject);
    }

    public String showCurrentUpgradeNumber() {
        if (this.numberOfUpgrades == 0) {
            return "not acquired";
        } else {
            String sentence = "Upgrade Number : " + this.currentGrade;
            return sentence;
        }
    }

    //--------------------------------------------------------------      Getter & Setter
    public int getNumberOfUpgrades() {
        return numberOfUpgrades;
    }

    public void setNumberOfUpgrades(int numberOfUpgrades) {
        this.numberOfUpgrades = numberOfUpgrades;
    }

    public double[] getConstantProperty() {
        return constantProperty;
    }

    public void setConstantProperty(double[] constantProperty) {
        this.constantProperty = constantProperty;
    }

    public double getTotalEffectOnProperty() {
        return totalEffectOnProperty;
    }

    public void setTotalEffectOnProperty(double totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

    public void setTotalEffectOnProperty(Double totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public boolean isDependOnEffectedSoldier() {
        return isDependOnEffectedSoldier;
    }

    public void setDependOnEffectedSoldier(boolean dependOnEffectedSoldier) {
        isDependOnEffectedSoldier = dependOnEffectedSoldier;
    }

    public Map<E, Double> getValueOfEffectingOnEffectedSoldiers() {
        return valueOfEffectingOnEffectedSoldiers;
    }

    public void setValueOfEffectingOnEffectedSoldiers(Map<E, Double> valueOfEffectingOnEffectedSoldiers) {
        this.valueOfEffectingOnEffectedSoldiers = valueOfEffectingOnEffectedSoldiers;
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

    public Map<String, Map<String, Double[]>> getVariablesCoefficientOfObjects() {
        return variablesCoefficientOfObjects;
    }

    public void setVariablesCoefficientOfObjects(Map<String, Map<String, Double[]>> variablesCoefficientOfObjects) {
        this.variablesCoefficientOfObjects = variablesCoefficientOfObjects;
    }

    public Map<String, ArrayList<String>> getVariablesOfObjects() {
        return variablesOfObjects;
    }

    public void setVariablesOfObjects(Map<String, ArrayList<String>> variablesOfObjects) {
        this.variablesOfObjects = variablesOfObjects;
    }
}