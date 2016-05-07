/**
 * Created by Future on 5/6/2016.
 */
public class Soldier {
    protected int maximumHealth;
    protected int attackPower;
    protected double healthRefillRate;
    protected double currentHealth;
    protected String className;

    //------------------------------------------- Functions
    public boolean isDead(){
        if(this.currentHealth <= 0){
            return true;
        }
        return false;
    }
    public void getDamage(int attackPower) {
        this.setCurrentHealth(currentHealth - attackPower);             // did not check the skills!
    }
    //------------------------------------------- Getters && Setters

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(int maximumHealth) {
        this.maximumHealth = maximumHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public double getHealthRefillRate() {
        return healthRefillRate;
    }

    public void setHealthRefillRate(double attackRefillRate) {
        this.healthRefillRate = attackRefillRate;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
