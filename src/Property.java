/**
 * Created by asus-pc on 5/5/2016.
 */
public class Property {
    private double[] totalEffectOnProperty;
    private String name;

    private double[] constantProperty;
    private double[] attackPowerCoefficient;
    private double[] maximumHealthCoefficient;
    private double[] maximumMagicCoefficient;
    private double[] healthCoefficient;
    private double[] magicCoefficient;
    private double[] healthRefillRateCoefficient;
    private double[] magicRefillRateCoefficient;

    //--------------------------------------------------------------

    public Property(PropertyHandler propertyHandler){
        setName(propertyHandler.getName());
        setConstantProperty(propertyHandler.getConstantProperty());
        setAttackPowerCoefficient(propertyHandler.getAttackPowerCoefficient());
        setMaximumHealthCoefficient(propertyHandler.getMaximumHealthCoefficient());
        setMaximumMagicCoefficient(propertyHandler.getMaximumMagicCoefficient());
        setHealthCoefficient(propertyHandler.getHealthCoefficient());
        setMagicCoefficient(propertyHandler.getMagicCoefficient());
        setHealthRefillRateCoefficient(propertyHandler.getHealthRefillRateCoefficient());
        setMagicRefillRateCoefficient(propertyHandler.getMagicRefillRateCoefficient());
    }

    public String getName(){
        return this.name;
    }

    //--------------------------------------------------------------

    public double[] getConstantProperty() {
        return constantProperty;
    }

    public void setConstantProperty(double[] constantProperty) {
        this.constantProperty = constantProperty;
    }

    public double[] getTotalEffectOnProperty() {
        return totalEffectOnProperty;
    }

    public void setTotalEffectOnProperty(double[] totalEffectOnProperty) {
        this.totalEffectOnProperty = totalEffectOnProperty;
    }

    public void setName(String name) {
        this.name = name;
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

    private void calculateProperty() {
        //TODO
    }

    public void effect(String heroName) {
        Hero hero = Hero.mapOfHeroes.get(heroName);
        if(getName().equals("attackPower")){

        }
    }

    public void removeEffect() {
        //TODO
    }

}
