import java.util.ArrayList;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClassHandler extends Soldier{
    private ArrayList<Perk> perks;
    private ArrayList<Skill> skills;
    private double criticalHitChance;
    private double criticalHitChanceRatio;
    private double criticalHitDamage;
    private double criticalHitDamageRatio;
    private double magicRefillRate;
    private double attackPowerRatioDuringAttack;
    private double healthRefillRateRatio;
    private double magicRefillRateRatio;
    private double energyPointRatio;
    private double attackPowerRatioOnNonTargetedEnemy;
    private double attackPowerOnNonTargetedEnemy;
    private int maximumMagic;
    private int currentMagic;
    private int numberOfNonTargetedEnemyEffected;
    private int maximumEnergyPoint;
    private int currentEnergyPoint;
    private int inventorySize;
    private int energyPoint;

    private String name;


    //---------------------------------------------------------------- Constructors
    public HeroClassHandler(){}
    public HeroClassHandler(String name,int maximumHealth,int attackPower,int maximumMagic,int energyPoint,int inventorySize,double healthRefillRate, double magicRefillRate){
        super(maximumHealth,attackPower,healthRefillRate);
        this.setName(name);
        this.setMaximumMagic(maximumMagic);
        this.setEnergyPoint(energyPoint);
        this.setInventorySize(inventorySize);
    }
    //---------------------------------------------------------------- Getter && Setters


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

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public double getCriticalHitChanceRatio() {
        return criticalHitChanceRatio;
    }

    public void setCriticalHitChanceRatio(double criticalHitChanceRatio) {
        this.criticalHitChanceRatio = criticalHitChanceRatio;
    }

    public double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public void setCriticalHitDamage(double criticalHitDamage) {
        this.criticalHitDamage = criticalHitDamage;
    }

    public double getCriticalHitDamageRatio() {
        return criticalHitDamageRatio;
    }

    public void setCriticalHitDamageRatio(double criticalHitDamageRatio) {
        this.criticalHitDamageRatio = criticalHitDamageRatio;
    }

    public double getMagicRefillRate() {
        return magicRefillRate;
    }

    public void setMagicRefillRate(double magicRefillRate) {
        this.magicRefillRate = magicRefillRate;
    }

    public double getAttackPowerRatioDuringAttack() {
        return attackPowerRatioDuringAttack;
    }

    public void setAttackPowerRatioDuringAttack(double attackPowerRatioDuringAttack) {
        this.attackPowerRatioDuringAttack = attackPowerRatioDuringAttack;
    }

    public double getHealthRefillRateRatio() {
        return healthRefillRateRatio;
    }

    public void setHealthRefillRateRatio(double healthRefillRateRatio) {
        this.healthRefillRateRatio = healthRefillRateRatio;
    }

    public double getMagicRefillRateRatio() {
        return magicRefillRateRatio;
    }

    public void setMagicRefillRateRatio(double magicRefillRateRatio) {
        this.magicRefillRateRatio = magicRefillRateRatio;
    }

    public double getEnergyPointRatio() {
        return energyPointRatio;
    }

    public void setEnergyPointRatio(double energyPointRatio) {
        this.energyPointRatio = energyPointRatio;
    }

    public double getAttackPowerRatioOnNonTargetedEnemy() {
        return attackPowerRatioOnNonTargetedEnemy;
    }

    public void setAttackPowerRatioOnNonTargetedEnemy(double attackPowerRatioOnNonTargetedEnemy) {
        this.attackPowerRatioOnNonTargetedEnemy = attackPowerRatioOnNonTargetedEnemy;
    }

    public double getAttackPowerOnNonTargetedEnemy() {
        return attackPowerOnNonTargetedEnemy;
    }

    public void setAttackPowerOnNonTargetedEnemy(double attackPowerOnNonTargetedEnemy) {
        this.attackPowerOnNonTargetedEnemy = attackPowerOnNonTargetedEnemy;
    }

    public int getMaximumMagic() {
        return maximumMagic;
    }

    public void setMaximumMagic(int maximumMagic) {
        this.maximumMagic = maximumMagic;
    }

    public int getCurrentMagic() {
        return currentMagic;
    }

    public void setCurrentMagic(int currentMagic) {
        this.currentMagic = currentMagic;
    }

    public int getNumberOfNonTargetedEnemyEffected() {
        return numberOfNonTargetedEnemyEffected;
    }

    public void setNumberOfNonTargetedEnemyEffected(int numberOfNonTargetedEnemyEffected) {
        this.numberOfNonTargetedEnemyEffected = numberOfNonTargetedEnemyEffected;
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

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public void setEnergyPoint(int energyPoint) {
        this.energyPoint = energyPoint;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
