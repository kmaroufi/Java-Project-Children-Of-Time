package Structure;


/**
 * Created by asus-pc on 5/5/2016.
 */
public class Condition {
    private String nameOfField;
    private Object valueOfField;
    private int statusOfFields;
    private boolean isAlwaysTrue;

    //------------------------------------------ Constructors

    public Condition(String nameOfField, Object valueOfField, int statusOfFields, boolean isAlwaysTrue) {
        this.nameOfField = nameOfField;
        this.valueOfField = valueOfField;
        this.statusOfFields = statusOfFields;
        this.isAlwaysTrue = isAlwaysTrue;
    }

    public Condition() {
        setAlwaysTrue(true);
    }

    //------------------------------------------ Functions

    public <T> boolean checkCondition(T object) {
        if (this.isAlwaysTrue)
            return true;
        if (Property.getFieldValue(object, this.nameOfField) instanceof Integer) {
            int currentFieldValue = (int) Property.getFieldValue(object, this.nameOfField);
            int conditionFieldValue = (int) this.valueOfField;
            if ((this.statusOfFields == 1) && (currentFieldValue > conditionFieldValue)) {
                return true;
            }
            if ((this.statusOfFields == 0) && (currentFieldValue == (conditionFieldValue))) {
                return true;
            }
            if ((this.statusOfFields == -1) && (currentFieldValue < conditionFieldValue)) {
                return true;
            }
        } else if (Property.getFieldValue(object, this.nameOfField) instanceof Double) {
            double currentFieldValue = (double) Property.getFieldValue(object, this.nameOfField);
            double conditionFieldValue = (double) this.valueOfField;
            if ((this.statusOfFields == 1) && (currentFieldValue > conditionFieldValue)) {
                return true;
            }
            if ((this.statusOfFields == 0) && (currentFieldValue == (conditionFieldValue))) {
                return true;
            }
            if ((this.statusOfFields == -1) && (currentFieldValue < conditionFieldValue)) {
                return true;
            }
        } else if (Property.getFieldValue(object, this.nameOfField) instanceof String) {
            String currentFieldValue = (String) Property.getFieldValue(object, this.nameOfField);
            String conditionFieldValue = (String) this.valueOfField;
            if ((this.statusOfFields == 1) && (currentFieldValue.compareTo(conditionFieldValue) == 1)) {
                return true;
            }
            if ((this.statusOfFields == 0) && (currentFieldValue.compareTo(conditionFieldValue) == 0)) {
                return true;
            }
            if ((this.statusOfFields == -1) && (currentFieldValue.compareTo(conditionFieldValue) == -1)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object condition) {
        if (this == condition) {
            return true;
        }
        if (condition instanceof Condition) {
            Condition secondCondition = (Condition) condition;
            if (this.isAlwaysTrue != secondCondition.isAlwaysTrue)
                return false;
            if (!this.nameOfField.equals(secondCondition.nameOfField))
                return false;
            if (!this.valueOfField.equals(secondCondition.valueOfField))
                return false;
            if (this.statusOfFields != secondCondition.statusOfFields)
                return false;
            return true;
        }
        return false;
    }

    //------------------------------------------ Getter && Setters

    public boolean isAlwaysTrue() {
        return isAlwaysTrue;
    }

    public void setAlwaysTrue(boolean alwaysTrue) {
        isAlwaysTrue = alwaysTrue;
    }

    public String getNameOfField() {
        return nameOfField;
    }

    public void setNameOfField(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public Object getValueOfField() {
        return valueOfField;
    }

    public void setValueOfField(Object valueOfField) {
        this.valueOfField = valueOfField;
    }

    public int getStatusOfFields() {
        return statusOfFields;
    }

    public void setStatusOfFields(int statusOfFields) {
        this.statusOfFields = statusOfFields;
    }
}



// add health and attackPower
// change energyPoint field to maximumEnergyPoint.
// do we add currentEnergyPoint near maximumEnergyPoints?
// this class should extremely review!!! baraye mesal: perki darim ke dar moghabele doshman haye weak zarbeye kamtari mikhorad.