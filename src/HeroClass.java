import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClass extends Soldier{
    public static HashMap<String, HeroClass> listOfHeroClasses;
    private ArrayList<Hero> listOfHeroes;

    public ArrayList<Hero> getListOfHeroes() {
        return listOfHeroes;
    }

    public void setListOfHeroes(ArrayList<Hero> listOfHeroes) {
        this.listOfHeroes = listOfHeroes;
    }

    public Double getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(Double criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public Double getCriticalHitChanceRatio() {
        return criticalHitChanceRatio;
    }

    public void setCriticalHitChanceRatio(Double criticalHitChanceRatio) {
        this.criticalHitChanceRatio = criticalHitChanceRatio;
    }

    public Double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public void setCriticalHitDamage(Double criticalHitDamage) {
        this.criticalHitDamage = criticalHitDamage;
    }

    public Double getCriticalHitDamageRatio() {
        return criticalHitDamageRatio;
    }

    public void setCriticalHitDamageRatio(Double criticalHitDamageRatio) {
        this.criticalHitDamageRatio = criticalHitDamageRatio;
    }

    public Integer getMaximumMagic() {
        return maximumMagic;
    }

    public void setMaximumMagic(Integer maximumMagic) {
        this.maximumMagic = maximumMagic;
    }

    public Double getMagicRefillRate() {
        return magicRefillRate;
    }

    public void setMagicRefillRate(Double magicRefillRate) {
        this.magicRefillRate = magicRefillRate;
    }

    public Integer getCurrentMagic() {
        return currentMagic;
    }

    public void setCurrentMagic(Integer currentMagic) {
        this.currentMagic = currentMagic;
    }

    public Double getAttackPowerRatioDuringAttack() {
        return attackPowerRatioDuringAttack;
    }

    public void setAttackPowerRatioDuringAttack(Double attackPowerRatioDuringAttack) {
        this.attackPowerRatioDuringAttack = attackPowerRatioDuringAttack;
    }

    public Double getHealthRefillRateRatio() {
        return healthRefillRateRatio;
    }

    public void setHealthRefillRateRatio(Double healthRefillRateRatio) {
        this.healthRefillRateRatio = healthRefillRateRatio;
    }

    public Double getMagicRefillRateRatio() {
        return magicRefillRateRatio;
    }

    public void setMagicRefillRateRatio(Double magicRefillRateRatio) {
        this.magicRefillRateRatio = magicRefillRateRatio;
    }

    public Double getEnergyPointRatio() {
        return energyPointRatio;
    }

    public void setEnergyPointRatio(Double energyPointRatio) {
        this.energyPointRatio = energyPointRatio;
    }

    public Double getAttackPowerRatioOnNonTargetedEnemy() {
        return attackPowerRatioOnNonTargetedEnemy;
    }

    public void setAttackPowerRatioOnNonTargetedEnemy(Double attackPowerRatioOnNonTargetedEnemy) {
        this.attackPowerRatioOnNonTargetedEnemy = attackPowerRatioOnNonTargetedEnemy;
    }

    public Double getAttackPowerOnNonTargetedEnemy() {
        return attackPowerOnNonTargetedEnemy;
    }

    public void setAttackPowerOnNonTargetedEnemy(Double attackPowerOnNonTargetedEnemy) {
        this.attackPowerOnNonTargetedEnemy = attackPowerOnNonTargetedEnemy;
    }

    public Integer getNumberOfNonTargetedEnemyEffected() {
        return numberOfNonTargetedEnemyEffected;
    }

    public void setNumberOfNonTargetedEnemyEffected(Integer numberOfNonTargetedEnemyEffected) {
        this.numberOfNonTargetedEnemyEffected = numberOfNonTargetedEnemyEffected;
    }

    public Integer getMaximumEnergyPoint() {
        return maximumEnergyPoint;
    }

    public void setMaximumEnergyPoint(Integer maximumEnergyPoint) {
        this.maximumEnergyPoint = maximumEnergyPoint;
    }

    public Integer getCurrentEnergyPoint() {
        return currentEnergyPoint;
    }

    public void setCurrentEnergyPoint(Integer currentEnergyPoint) {
        this.currentEnergyPoint = currentEnergyPoint;
    }

    public Integer getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(Integer inventorySize) {
        this.inventorySize = inventorySize;
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

    protected Double criticalHitChance;
    protected Double criticalHitChanceRatio;
    protected Double criticalHitDamage;
    protected Double criticalHitDamageRatio;
    protected Integer maximumMagic;
    protected Double magicRefillRate;
    protected Integer currentMagic;
    protected Double attackPowerRatioDuringAttack;
    protected Double healthRefillRateRatio;
    protected Double magicRefillRateRatio;
    protected Double energyPointRatio;
    protected Double attackPowerRatioOnNonTargetedEnemy;
    protected Double attackPowerOnNonTargetedEnemy;
    protected Integer numberOfNonTargetedEnemyEffected;
    protected Integer maximumEnergyPoint;
    protected Integer currentEnergyPoint;
    protected Integer inventorySize;
    protected ArrayList<Perk> perks;
    protected ArrayList<Skill> skills;
    //--------------------------------------------------
    public HeroClass(){

    }
    public HeroClass(HeroClassHandler heroClassHandler){

    }
}
