import java.util.ArrayList;

/**
 * Created by Future on 5/6/2016.
 */
public class Soldier {
    protected String className;
    protected ArrayList<Perk> perks = new ArrayList<Perk>();
    protected ArrayList<Skill> skills = new ArrayList<Skill>();
    protected int maximumHealth;
    protected double healthRefillRate;
    protected double currentHealth;
    protected int maximumMagic;
    protected double magicRefillRate;
    protected int currentMagic;
    protected int maximumEnergyPoint;
    protected int currentEnergyPoint;
    protected int attackPower;
    protected double attackPowerRatioDuringAttack;
    protected double attackPowerRatioOnNonTargetedSoldiers;
    protected double attackPowerOnNonTargetedSoldiers;

    //------------------------------------------- Constructors
    public Soldier() {

    }

    public Soldier(SoldierHandler soldierHandler){
        setClassName(soldierHandler.getClassName());
        setPerks(soldierHandler.getPerks());
        setSkills(soldierHandler.getSkills());
        setMaximumHealth(soldierHandler.getMaximumHealth());
        setHealthRefillRate(soldierHandler.getHealthRefillRate());
        setCurrentHealth(soldierHandler.getMaximumHealth());
        setMaximumMagic(soldierHandler.getMaximumMagic());
        setMagicRefillRate(soldierHandler.getMagicRefillRate());
        setCurrentMagic(soldierHandler.getMaximumMagic());
        setMaximumEnergyPoint(soldierHandler.getMaximumEnergyPoint());
        setCurrentEnergyPoint(soldierHandler.getMaximumEnergyPoint());
        setAttackPower(soldierHandler.getAttackPower());
        setAttackPowerRatioDuringAttack(1);
        setAttackPowerRatioOnNonTargetedSoldiers(1);
        setAttackPowerOnNonTargetedSoldiers(soldierHandler.getAttackPowerOnNonTargetedSoldiers());
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

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void addPerk(Perk perk){
        this.perks.add(perk);
    }

    public boolean hasPerk(Perk perk){
        for(int i = 0;i < this.perks.size();i++){
            if(perk.equals(this.perks.get(i))){
                return true;
            }
        }
        return false;
    }

    public boolean hasSkill(Skill skill){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).equals(skill)){
                return true;
            }
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

    public double getAttackPowerRatioDuringAttack() {
        return attackPowerRatioDuringAttack;
    }

    public void setAttackPowerRatioDuringAttack(double attackPowerRatioDuringAttack) {
        this.attackPowerRatioDuringAttack = attackPowerRatioDuringAttack;
    }

    public double getAttackPowerRatioOnNonTargetedSoldiers() {
        return attackPowerRatioOnNonTargetedSoldiers;
    }

    public void setAttackPowerRatioOnNonTargetedSoldiers(double attackPowerRatioOnNonTargetedSoldiers) {
        this.attackPowerRatioOnNonTargetedSoldiers = attackPowerRatioOnNonTargetedSoldiers;
    }

    public double getAttackPowerOnNonTargetedSoldiers() {
        return attackPowerOnNonTargetedSoldiers;
    }

    public void setAttackPowerOnNonTargetedSoldiers(double attackPowerOnNonTargetedSoldiers) {
        this.attackPowerOnNonTargetedSoldiers = attackPowerOnNonTargetedSoldiers;
    }

    public int getMaximumMagic() {
        return maximumMagic;
    }

    public void setMaximumMagic(int maximumMagic) {
        this.maximumMagic = maximumMagic;
    }

    public double getMagicRefillRate() {
        return magicRefillRate;
    }

    public void setMagicRefillRate(double magicRefillRate) {
        this.magicRefillRate = magicRefillRate;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }
}
