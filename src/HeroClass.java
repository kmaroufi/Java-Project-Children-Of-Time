import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClass extends Soldier{
    private String name;
    public static HashMap<String, HeroClass> mapOfHeroClasses;
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    protected ArrayList<Perk> perks = new ArrayList<Perk>();
    protected ArrayList<Skill> skills = new ArrayList<Skill>();
    protected double criticalHitChance;
    protected double criticalHitChanceRatio;
    protected double criticalHitDamage;
    protected double criticalHitDamageRatio;
    protected double magicRefillRate;
    protected double attackPowerRatioDuringAttack;
    protected double healthRefillRateRatio;
    protected double magicRefillRateRatio;                                  //F*** All Ratioes! :((((
    protected double energyPointRatio;
    protected double attackPowerRatioOnNonTargetedEnemy;
    protected double attackPowerOnNonTargetedEnemy;
    protected int numberOfNonTargetedEnemyEffected;
    protected int maximumEnergyPoint;
    protected int currentEnergyPoint;
    protected int inventorySize;
    protected int maximumMagic;
    protected int currentMagic;
    protected double armor;
    protected double armorRatio; // I know you have problem with this. just keep calm :))
    protected double damageResistance;
    protected double criticalDamageResistanceChance;
    protected double criticalDamageResistance;
    protected double headShotChance; // when you headShot an enemy, you kill it immediately!

    //--------------------------------------------------
    public HeroClass(){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).isGlobal() && !Hero.listOfActiveGlobalSkills.contains(this.skills.get(i))){       // if it is not repeated
                Hero.listOfActiveGlobalSkills.add(this.skills.get(i));
            }
        }
    }
    public HeroClass(HeroClassHandler heroClassHandler){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).isGlobal() && !Hero.listOfActiveGlobalSkills.contains(this.skills.get(i))){       // if it is not repeated
                Hero.listOfActiveGlobalSkills.add(this.skills.get(i));
            }
        }
        this.setName(heroClassHandler.getName());
        this.setAttackPowerOnNonTargetedEnemy(heroClassHandler.getAttackPowerOnNonTargetedEnemy());
        this.setAttackPowerRatioDuringAttack(heroClassHandler.getAttackPowerRatioDuringAttack());
        this.setAttackPowerRatioOnNonTargetedEnemy(heroClassHandler.getAttackPowerRatioOnNonTargetedEnemy());
        this.setCriticalHitChance(heroClassHandler.getCriticalHitChance());
        this.setCriticalHitChanceRatio(heroClassHandler.getCriticalHitChanceRatio());
        this.setCriticalHitDamage(heroClassHandler.getCriticalHitDamage());
        this.setCriticalHitDamageRatio(heroClassHandler.getCriticalHitDamageRatio());
        this.setCurrentEnergyPoint(heroClassHandler.getCurrentEnergyPoint());
        this.setCurrentMagic(heroClassHandler.getCurrentMagic());
        this.setEnergyPointRatio(heroClassHandler.getEnergyPointRatio());
        this.setHealthRefillRateRatio(heroClassHandler.getHealthRefillRateRatio());
        this.setInventorySize(heroClassHandler.getInventorySize());
        this.setSkills(heroClassHandler.getSkills());
        this.setPerks(heroClassHandler.getPerks());
        this.setMaximumEnergyPoint(heroClassHandler.getMaximumEnergyPoint());
        this.setMaximumMagic(heroClassHandler.getMaximumMagic());
        this.setNumberOfNonTargetedEnemyEffected(heroClassHandler.getNumberOfNonTargetedEnemyEffected());
        this.setMagicRefillRateRatio(heroClassHandler.getMagicRefillRateRatio());
        this.setMagicRefillRate(heroClassHandler.getMagicRefillRate());

    }

    public HeroClass(HeroClass heroClass){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).isGlobal() && !Hero.listOfActiveGlobalSkills.contains(this.skills.get(i))){       // if it is not repeated
                Hero.listOfActiveGlobalSkills.add(this.skills.get(i));
            }
        }
        this.setName(heroClass.getName());
        this.setAttackPowerOnNonTargetedEnemy(heroClass.getAttackPowerOnNonTargetedEnemy());
        this.setAttackPowerRatioDuringAttack(heroClass.getAttackPowerRatioDuringAttack());
        this.setAttackPowerRatioOnNonTargetedEnemy(heroClass.getAttackPowerRatioOnNonTargetedEnemy());
        this.setCriticalHitChance(heroClass.getCriticalHitChance());
        this.setCriticalHitChanceRatio(heroClass.getCriticalHitChanceRatio());
        this.setCriticalHitDamage(heroClass.getCriticalHitDamage());
        this.setCriticalHitDamageRatio(heroClass.getCriticalHitDamageRatio());
        this.setCurrentEnergyPoint(heroClass.getCurrentEnergyPoint());
        this.setCurrentMagic(heroClass.getCurrentMagic());
        this.setEnergyPointRatio(heroClass.getEnergyPointRatio());
        this.setHealthRefillRateRatio(heroClass.getHealthRefillRateRatio());
        this.setInventorySize(heroClass.getInventorySize());
        this.setSkills(heroClass.getSkills());
        this.setPerks(heroClass.getPerks());
        this.setMaximumEnergyPoint(heroClass.getMaximumEnergyPoint());
        this.setMaximumMagic(heroClass.getMaximumMagic());
        this.setNumberOfNonTargetedEnemyEffected(heroClass.getNumberOfNonTargetedEnemyEffected());
        this.setMagicRefillRateRatio(heroClass.getMagicRefillRateRatio());
        this.setMagicRefillRate(heroClass.getMagicRefillRate());

    }
    //-------------------------------------------------- Getter && Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static HashMap<String, HeroClass> getMapOfHeroClasses() {
        return mapOfHeroClasses;
    }

    public static void setMapOfHeroClasses(HashMap<String, HeroClass> mapOfHeroClasses) {
        HeroClass.mapOfHeroClasses = mapOfHeroClasses;
    }

    public static ArrayList<HeroClass> getListOfHeroClasses() {
        return listOfHeroClasses;
    }

    public static void setListOfHeroClasses(ArrayList<HeroClass> listOfHeroClasses) {
        HeroClass.listOfHeroClasses = listOfHeroClasses;
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

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getArmorRatio() {
        return armorRatio;
    }

    public void setArmorRatio(double armorRatio) {
        this.armorRatio = armorRatio;
    }

    public double getDamageResistance() {
        return damageResistance;
    }

    public void setDamageResistance(double damageResistance) {
        this.damageResistance = damageResistance;
    }

    public double getCriticalDamageResistanceChance() {
        return criticalDamageResistanceChance;
    }

    public void setCriticalDamageResistanceChance(double criticalDamageResistanceChance) {
        this.criticalDamageResistanceChance = criticalDamageResistanceChance;
    }

    public double getCriticalDamageResistance() {
        return criticalDamageResistance;
    }

    public void setCriticalDamageResistance(double criticalDamageResistance) {
        this.criticalDamageResistance = criticalDamageResistance;
    }

    public double getHeadShotChance() {
        return headShotChance;
    }

    public void setHeadShotChance(double headShotChance) {
        this.headShotChance = headShotChance;
    }
}
