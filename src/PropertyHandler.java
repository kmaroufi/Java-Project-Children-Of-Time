/**
 * Created by asus-pc on 5/6/2016.
 */
public class PropertyHandler {
    private String name;
    private final int NumberOfUpgrades;
    private boolean isDependOnEffectedSoldier;
    private boolean isDependOnUserHero;
    private boolean isPermanently;

    private double[] constantProperty ;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;

    //-------------------------------------------------------------- Constructors
    public PropertyHandler(int numberOfUpgrades){
        this.NumberOfUpgrades = numberOfUpgrades;
        this.constantProperty = new double[this.NumberOfUpgrades];
        this.attackPowerCoefficient = new double[this.NumberOfUpgrades];
        this.maximumHealthCoefficient = new double[this.NumberOfUpgrades];
        this.maximumMagicCoefficient = new double[this.NumberOfUpgrades];
        this.healthCoefficient = new double[this.NumberOfUpgrades];
        this.magicCoefficient = new double[this.NumberOfUpgrades];
        this.healthRefillRateCoefficient = new double[this.NumberOfUpgrades];
        this.magicRefillRateCoefficient = new double[this.NumberOfUpgrades];
    }

    public PropertyHandler(String name, int numberOfUpgrades, boolean isDependOnEffectedSoldier, boolean isDependOnUserHero, boolean isPermanently, double[] constantProperty, double[] attackPowerCoefficient, double[] maximumHealthCoefficient, double[] maximumMagicCoefficient, double[] healthCoefficient, double[] magicCoefficient, double[] healthRefillRateCoefficient, double[] magicRefillRateCoefficient) {
        this.name = name;
        NumberOfUpgrades = numberOfUpgrades;
        this.isDependOnEffectedSoldier = isDependOnEffectedSoldier;
        this.isDependOnUserHero = isDependOnUserHero;
        this.isPermanently = isPermanently;
        this.constantProperty = constantProperty;
        this.attackPowerCoefficient = attackPowerCoefficient;
        this.maximumHealthCoefficient = maximumHealthCoefficient;
        this.maximumMagicCoefficient = maximumMagicCoefficient;
        this.healthCoefficient = healthCoefficient;
        this.magicCoefficient = magicCoefficient;
        this.healthRefillRateCoefficient = healthRefillRateCoefficient;
        this.magicRefillRateCoefficient = magicRefillRateCoefficient;
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

    public int getNumberOfUpgrades() {
        return NumberOfUpgrades;
    }


}
