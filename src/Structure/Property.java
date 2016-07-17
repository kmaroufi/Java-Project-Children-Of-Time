package Structure;

import javafx.util.Pair;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property<E, T> implements Cloneable, Serializable{
    private Double totalEffectOnProperty = new Double(0);               // az che noii e??????
    private String name;
    private boolean isPermanently;

    private ClassName classOfEffectingObjects;
    private ClassName classOfEffectedObject;

    private double constantProperty;

    private Tree<ArrayList<Pair<String, Double>>> trieCondition;

    private SelectingObjectsDetail<T> selectingEffectingObjectsDetails; // Key = Property, Value = Details about how effecting objects are selected

    private Map<E, Double> valueOfEffectingOnEffectedSoldiers = new HashMap<>();

    //-------------------------------------------------------------- Constructors

    public Property(PropertyHandler propertyHandler) {
        setName(propertyHandler.getName());
        setPermanently(propertyHandler.isPermanently());
        setClassOfEffectingObjects(propertyHandler.getClassOfEffectingObjects());
        setClassOfEffectedObject(propertyHandler.getClassOfEffectedObject());
        setConstantProperty(propertyHandler.getConstantProperty());
        setTrieCondition(propertyHandler.getTrieCondition());
        setSelectingEffectingObjectsDetails(propertyHandler.getSelectingEffectingObjectsDetails());
    }

    public String getName() {
        return this.name;
    }

    //---------------------------------------    Functions

    protected Property<E, T> clone() {
        Property<E, T> property = null;
        try {
            property = (Property<E, T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        property.setValueOfEffectingOnEffectedSoldiers(new HashMap<>());
        return property;
    }

    public static <U> Object getFieldValue(U object, String fieldName) {
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

    public static <U, S> void setFieldValue(U object, String fieldName, S value) {
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
        this.totalEffectOnProperty = this.constantProperty;
        for (T effectingObject : effectingObjects) {
            ArrayList<Pair<String, Double>> result = this.trieCondition.findCorrectNode(effectingObject);
            for (Pair<String, Double> variableDetail : result) {
                if (Property.getFieldValue(effectingObject, variableDetail.getKey()) instanceof Integer) {
                    int variableValue = (int) Property.getFieldValue(effectingObject, variableDetail.getKey());
                    double variableCoefficient = variableDetail.getValue();
                    this.totalEffectOnProperty += variableCoefficient * variableValue;
                } else if (Property.getFieldValue(effectingObject, variableDetail.getKey()) instanceof Double) {
                    double variableValue = (double) Property.getFieldValue(effectingObject, variableDetail.getKey());
                    double variableCoefficient = variableDetail.getValue();
                    this.totalEffectOnProperty += variableCoefficient * variableValue;
                }
            }
        }
    }

    public double effect(E effectedObject, ArrayList<T> effectingObjects) {
        calculateProperty(effectingObjects);
        if (Property.getFieldValue(effectedObject, this.name) instanceof Integer) {
            int previousValue = (int) Property.getFieldValue(effectedObject, this.name);
            Property.setFieldValue(effectedObject, this.name, (int) (previousValue + this.totalEffectOnProperty));
        } else if (Property.getFieldValue(effectedObject, this.name) instanceof Double) {
            double previousValue = (double) Property.getFieldValue(effectedObject, this.name);
            Property.setFieldValue(effectedObject, this.name, previousValue + this.totalEffectOnProperty);
        }
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
        if (Property.getFieldValue(effectedObject, this.name) instanceof Integer) {
            int previousValue = (int) Property.getFieldValue(effectedObject, this.name);
            Property.setFieldValue(effectedObject, this.name, (int)(previousValue - this.valueOfEffectingOnEffectedSoldiers.get(effectedObject)));
        } else if (Property.getFieldValue(effectedObject, this.name) instanceof Double) {
            double previousValue = (double) Property.getFieldValue(effectedObject, this.name);
            Property.setFieldValue(effectedObject, this.name, previousValue - this.valueOfEffectingOnEffectedSoldiers.get(effectedObject));
        }
        this.valueOfEffectingOnEffectedSoldiers.remove(effectedObject);
    }

    //--------------------------------------------------------------      Getter & Setter


    public Double getTotalEffectOnProperty() {
        return totalEffectOnProperty;
    }

    public void setTotalEffectOnProperty(Double totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPermanently() {
        return isPermanently;
    }

    public void setPermanently(boolean permanently) {
        isPermanently = permanently;
    }

    public ClassName getClassOfEffectingObjects() {
        return classOfEffectingObjects;
    }

    public void setClassOfEffectingObjects(ClassName classOfEffectingObjects) {
        this.classOfEffectingObjects = classOfEffectingObjects;
    }

    public ClassName getClassOfEffectedObject() {
        return classOfEffectedObject;
    }

    public void setClassOfEffectedObject(ClassName classOfEffectedObject) {
        this.classOfEffectedObject = classOfEffectedObject;
    }

    public Tree<ArrayList<Pair<String, Double>>> getTrieCondition() {
        return trieCondition;
    }

    public void setTrieCondition(Tree<ArrayList<Pair<String, Double>>> trieCondition) {
        this.trieCondition = trieCondition;
    }

    public double getConstantProperty() {
        return constantProperty;
    }

    public void setConstantProperty(double constantProperty) {
        this.constantProperty = constantProperty;
    }

    public Map<E, Double> getValueOfEffectingOnEffectedSoldiers() {
        return valueOfEffectingOnEffectedSoldiers;
    }

    public void setValueOfEffectingOnEffectedSoldiers(Map<E, Double> valueOfEffectingOnEffectedSoldiers) {
        this.valueOfEffectingOnEffectedSoldiers = valueOfEffectingOnEffectedSoldiers;
    }

    public SelectingObjectsDetail<T> getSelectingEffectingObjectsDetails() {
        return selectingEffectingObjectsDetails;
    }

    public void setSelectingEffectingObjectsDetails(SelectingObjectsDetail<T> selectingEffectingObjectsDetails) {
        this.selectingEffectingObjectsDetails = selectingEffectingObjectsDetails;
    }
}