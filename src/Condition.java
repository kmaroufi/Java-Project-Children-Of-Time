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
    private Map<String, Double> mapOfFields;
    private Map<String, Double> mapOfStatusOfFields;
//    private boolean isDependsOnEnemy;
    private String checkThisConditionOn; // This condition on who will be checked?

    public boolean checkCondition() {
        //TODO
        return false;
    }

    private boolean checkCriticalHitChance() {
        //TODO
        return false;
    }

    private boolean checkCriticalHitDamage() {
        //ToDO
        return false;
    }

    private boolean checkMaximumHealth() {
        //ToDO
        return false;
    }

    private boolean checkHealthRefillRate() {
        //ToDO
        return false;
    }

    private boolean checkCurrentHealth() {
        //ToDO
        return false;
    }

    private boolean checkMaximumMagic() {
        //ToDO
        return false;
    }

    private boolean checkMagicRefillRate() {
        //ToDO
        return false;
    }

    private boolean checkCurrentMagic() {
        //ToDO
        return false;
    }

    private boolean checkAttackPower() {
        //ToDO
        return false;
    }

    private boolean checkAttackPowerRatio() {
        //ToDO
        return false;
    }

    private boolean checkAttackPowerRatioOnNonTargetedEnemy() {
        //ToDO
        return false;
    }

    private boolean checkNumberOfNonTargetedEnemyEffected() {
        //ToDO
        return false;
    }

    private boolean checkEnergyPoints() {
        //ToDO
        return false;
    }
}

// add health and attackPower
// change energyPoint field to maximumEnergyPoint.
// do we add currentEnergyPoint near maximumEnergyPoints?
// this class should extremely review!!! baraye mesal: perki darim ke dar moghabele doshman haye weak zarbeye kamtari mikhorad.