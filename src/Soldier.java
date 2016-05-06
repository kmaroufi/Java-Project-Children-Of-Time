/**
 * Created by Future on 5/6/2016.
 */
public class Soldier {
    protected int maximumHealth;
    protected int attackPower;
    protected double attackRefillRate;
    protected int currentHealth;

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

    public double getAttackRefillRate() {
        return attackRefillRate;
    }

    public void setAttackRefillRate(double attackRefillRate) {
        this.attackRefillRate = attackRefillRate;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }


}
