/**
 * Created by Future on 5/6/2016.
 */
public class Soldier {
    protected int maximumHealth;
    protected int attackPower;
    protected double healthRefillRate;
    protected int currentHealth;
    protected String name;

    //------------------------------------------- Functions
    public boolean isDead(){
        if(this.currentHealth <= 0){
            return true;
        }
        return false;
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

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }


}
