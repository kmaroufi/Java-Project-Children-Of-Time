import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClassHandler extends Soldier implements Cloneable{
    private ArrayList<Perk> perks = new ArrayList<>();
    private ArrayList<Skill> skills = new ArrayList<>();
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


    //---------------------------------------------------------------- Constructors


    protected HeroClassHandler clone() throws CloneNotSupportedException {
        HeroClassHandler clone = (HeroClassHandler) super.clone();
        ArrayList<Perk> cloneOfPerks = new ArrayList<>();
        for (Perk perk: this.perks) {
            cloneOfPerks.add(perk.clone());
        }
        ArrayList<Skill> cloneOfSkills = new ArrayList<>();
        for (Skill skill: this.getSkills()) {
            cloneOfSkills.add(skill.clone());
        }
        clone.setPerks(cloneOfPerks);
        clone.setSkills(cloneOfSkills);
        return clone;
    }

    public HeroClassHandler(){}


    public HeroClassHandler(int maximumHealth, int attackPower, double healthRefillRate, double currentHealth, String className, ArrayList<Perk> perks, ArrayList<Skill> skills, double criticalHitChance, double criticalHitChanceRatio, double criticalHitDamage, double criticalHitDamageRatio, double magicRefillRate, double attackPowerRatioDuringAttack, double healthRefillRateRatio, double magicRefillRateRatio, double energyPointRatio, double attackPowerRatioOnNonTargetedEnemy, double attackPowerOnNonTargetedEnemy, int maximumMagic, int currentMagic, int numberOfNonTargetedEnemyEffected, int maximumEnergyPoint, int currentEnergyPoint, int inventorySize) {
        super(maximumHealth, attackPower, healthRefillRate, currentHealth, className);
        this.perks = perks;
        this.skills = skills;
        this.criticalHitChance = criticalHitChance;
        this.criticalHitChanceRatio = criticalHitChanceRatio;
        this.criticalHitDamage = criticalHitDamage;
        this.criticalHitDamageRatio = criticalHitDamageRatio;
        this.magicRefillRate = magicRefillRate;
        this.attackPowerRatioDuringAttack = attackPowerRatioDuringAttack;
        this.healthRefillRateRatio = healthRefillRateRatio;
        this.magicRefillRateRatio = magicRefillRateRatio;
        this.energyPointRatio = energyPointRatio;
        this.attackPowerRatioOnNonTargetedEnemy = attackPowerRatioOnNonTargetedEnemy;
        this.attackPowerOnNonTargetedEnemy = attackPowerOnNonTargetedEnemy;
        this.maximumMagic = maximumMagic;
        this.currentMagic = currentMagic;
        this.numberOfNonTargetedEnemyEffected = numberOfNonTargetedEnemyEffected;
        this.maximumEnergyPoint = maximumEnergyPoint;
        this.currentEnergyPoint = currentEnergyPoint;
        this.inventorySize = inventorySize;
    }

    //---------------------------------------------------------------- Functions
    public void addSkill(Skill skill){
        this.skills.add(skill);
    }
    public void addPerk(Perk perk){
        this.perks.add(perk);
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


}
