import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Condition {
//    private double criticalHitChance;
//    private int criticalHitChanceStatus;
//    private double criticalHitDamage;
//    private int criticalHitDamageStatus;
//    private double maximumHealth; // in uml, we missed health in condition
//    private int maximumHealthStatus;
//    private double healthRefillRate;
//    private int healthRefillRateStatus;
//    private double currentHealth;
//    private int currentHealthStatus;
//    private double maximumMagic; // is double true?!
//    private int maximumMagicStatus;
//    private double magicRefillRate;
//    private int magicRefillRateStatus;
//    private double currentMagic;
//    private int currentMagicStatus;
//    private double attackPower; // in uml, we missed attackPower
//    private int attackPowerStatus;
//    private double attackPowerRatio;
//    private int attackPowerRatioStatus;
//    private double attackPowerRatioOnNonTargetedEnemy;
//    private int attackPowerRatioOnNonTargetedEnemyStatus;
//    private double numberOfNonTargetedEnemyEffected;
//    private int numberOfNonTargetedEnemyEffectedStatus;
//    private double maximumEnergyPoints; // change to maximumEnergyPoint
//    private int maximumEnergyPointsStatus;
    private String nameOfField;
    private double valueOfField;
    private double statusOfFields;
    private boolean isAlwaysTrue;

    public Condition(String nameOfField, double valueOfField, double statusOfFields, boolean isAlwaysTrue) {
        this.nameOfField = nameOfField;
        this.valueOfField = valueOfField;
        this.statusOfFields = statusOfFields;
        this.isAlwaysTrue = isAlwaysTrue;
    }

    Condition() {
        setAlwaysTrue(true);
    }

    public <T> boolean checkCondition(T object) {
        if (this.isAlwaysTrue)
            return true;
        double currentFieldValue = (double) Property.getFieldValue(object, this.nameOfField);
        double conditionFieldValue = this.valueOfField;
        if ((this.statusOfFields == 1) && (currentFieldValue > conditionFieldValue)) {
            return true;
        }
        if ((this.statusOfFields == 0) && (currentFieldValue == conditionFieldValue)) {
            return true;
        }
        if ((this.statusOfFields == -1) && (currentFieldValue < conditionFieldValue)) {
            return true;
        }
        return false;
    }

    public boolean isAlwaysTrue() {
        return isAlwaysTrue;
    }

    public void setAlwaysTrue(boolean alwaysTrue) {
        isAlwaysTrue = alwaysTrue;
    }

}

// add health and attackPower
// change energyPoint field to maximumEnergyPoint.
// do we add currentEnergyPoint near maximumEnergyPoints?
// this class should extremely review!!! baraye mesal: perki darim ke dar moghabele doshman haye weak zarbeye kamtari mikhorad.