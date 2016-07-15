import java.util.ArrayList;

/**
 * Created by Future on 5/6/2016.
 */
public class Soldier {
    protected String className;
    protected int maximumHealth;
    protected double healthRefillRate;
    protected double currentHealth;
    protected int attackPower;
    protected int maximumEnergyPoint;
    protected int currentEnergyPoint;
    protected ArrayList<Perk> perks = new ArrayList<Perk>();
    protected ArrayList<Skill> skills = new ArrayList<Skill>();
    //------------------------------------------- Constructors
    public Soldier(){}

    public Soldier(int maximumHealth, int attackPower, double healthRefillRate, double currentHealth, String className) {
        this.maximumHealth = maximumHealth;
        this.attackPower = attackPower;
        this.healthRefillRate = healthRefillRate;
        this.currentHealth = currentHealth;
        this.className = className;
    }

    //------------------------------------------- Functions
    public boolean isDead(){
        if(this.currentHealth <= 0){
            return true;
        }
        return false;
    }

    public void getDamage(double attackPower) {
        this.setCurrentHealth(currentHealth - attackPower);             // did not check the skills!
        if (this.currentHealth < 0)
            this.currentHealth = 0;
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

    public String getName(){
        return null;
    }

    public int getMaximumEnergyPoint() {
        return maximumEnergyPoint;
    }

    public void setMaximumEnergyPoint(int maximumEnergyPoint) {
        this.maximumEnergyPoint = maximumEnergyPoint;
    }

    public int getCurrentEnergyPoint() {
        return currentEnergyPoint;
    }

    public void setCurrentEnergyPoint(int currentEnergyPoint) {
        this.currentEnergyPoint = currentEnergyPoint;
    }

    public ArrayList<Perk> getPerks() {
        return perks;
    }

    public void setPerks(ArrayList<Perk> perks) {
        this.perks = perks;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
}
