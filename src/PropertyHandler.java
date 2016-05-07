/**
 * Created by asus-pc on 5/6/2016.
 */
public class PropertyHandler {
    private String name;
    private double[] constantProperty ;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;

    // constructor should created!

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

    public double[] getAttackPowerCoefficient() {
        return attackPowerCoefficient;
    }

    public void setAttackPowerCoefficient(double[] attackPowerCoefficient) {
        this.attackPowerCoefficient = attackPowerCoefficient;
    }

    public double[] getMaximumHealthCoefficient() {
        return maximumHealthCoefficient;
    }

    public void setMaximumHealthCoefficient(double[] maximumHealthCoefficient) {
        this.maximumHealthCoefficient = maximumHealthCoefficient;
    }

    public double[] getMaximumMagicCoefficient() {
        return maximumMagicCoefficient;
    }

    public void setMaximumMagicCoefficient(double[] maximumMagicCoefficient) {
        this.maximumMagicCoefficient = maximumMagicCoefficient;
    }

    public double[] getHealthCoefficient() {
        return healthCoefficient;
    }

    public void setHealthCoefficient(double[] healthCoefficient) {
        this.healthCoefficient = healthCoefficient;
    }

    public double[] getMagicCoefficient() {
        return magicCoefficient;
    }

    public void setMagicCoefficient(double[] magicCoefficient) {
        this.magicCoefficient = magicCoefficient;
    }

    public double[] getHealthRefillRateCoefficient() {
        return healthRefillRateCoefficient;
    }

    public void setHealthRefillRateCoefficient(double[] healthRefillRateCoefficient) {
        this.healthRefillRateCoefficient = healthRefillRateCoefficient;
    }

    public double[] getMagicRefillRateCoefficient() {
        return magicRefillRateCoefficient;
    }

    public void setMagicRefillRateCoefficient(double[] magicRefillRateCoefficient) {
        this.magicRefillRateCoefficient = magicRefillRateCoefficient;
    }
}
