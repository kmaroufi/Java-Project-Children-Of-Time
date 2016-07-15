import GUI.Display;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClass extends Soldier {
    public static HashMap<String, HeroClass> mapOfHeroClasses = new HashMap<>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    protected CraftingRequirement craftingRequirement;
    protected double criticalHitChance;
    protected double criticalHitDamage;
    protected double magicRefillRate;
    protected int maximumMagic;
    protected int currentMagic;
    protected double attackPowerRatioDuringAttack;
    protected double attackPowerRatioOnNonTargetedEnemy;
    protected double attackPowerOnNonTargetedEnemy;
    protected SelfImprovement selfImprovement;
    protected boolean isAttackOnAllOfTheNon;
    protected SelectingObjectsDetail<Enemy> selectingEnemyForAttack;
    protected int numberOfNonTargetedEnemyEffected;
    protected int inventorySize;
    protected int sizeOfOccupiedInventory;
//    protected double armor;
//    protected double armorRatio; // I know you have problem with this. just keep calm :))
//    protected double damageResistance;
//    protected double criticalDamageResistanceChance;
//    protected double criticalDamageResistance;
//    protected double headShotChance; // when you headShot an enemy, you kill it immediately!


    //-------------------------------------------------- Constructors


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
        this.setClassName(heroClassHandler.getClassName());
        this.setAttackPowerOnNonTargetedEnemy(heroClassHandler.getAttackPowerOnNonTargetedEnemy());
        this.setAttackPowerRatioDuringAttack(heroClassHandler.getAttackPowerRatioDuringAttack());
        this.setAttackPowerRatioOnNonTargetedEnemy(heroClassHandler.getAttackPowerRatioOnNonTargetedEnemy());
        this.setCriticalHitChance(heroClassHandler.getCriticalHitChance());
        this.setCriticalHitDamage(heroClassHandler.getCriticalHitDamage());
        this.setHealthRefillRate(heroClassHandler.getHealthRefillRate());
        this.setInventorySize(heroClassHandler.getInventorySize());
        this.setSkills(heroClassHandler.getSkills());
        this.setPerks(heroClassHandler.getPerks());
        this.setMaximumEnergyPoint(heroClassHandler.getMaximumEnergyPoint());
        this.setMaximumMagic(heroClassHandler.getMaximumMagic());
        this.setNumberOfNonTargetedEnemyEffected(heroClassHandler.getNumberOfNonTargetedEnemyEffected());
        this.setMagicRefillRate(heroClassHandler.getMagicRefillRate());
        this.setMaximumHealth(heroClassHandler.getMaximumHealth());
        this.setCurrentMagic(this.getMaximumMagic());
        this.setCurrentEnergyPoint(this.getMaximumEnergyPoint());
        this.setCurrentHealth(this.getMaximumHealth());
        this.setAttackPower(heroClassHandler.getAttackPower());
        this.setSizeOfOccupiedInventory(0);
    }

    public HeroClass(HeroClass heroClass){
        for(int i = 0;i < this.skills.size();i++){
            if(this.skills.get(i).isGlobal() && !Hero.listOfActiveGlobalSkills.contains(this.skills.get(i))){       // if it is not repeated
                Hero.listOfActiveGlobalSkills.add(this.skills.get(i));
            }
        }
        this.setClassName(heroClass.getClassName());
        this.setAttackPowerOnNonTargetedEnemy(heroClass.getAttackPowerOnNonTargetedEnemy());
        this.setAttackPowerRatioDuringAttack(heroClass.getAttackPowerRatioDuringAttack());
        this.setAttackPowerRatioOnNonTargetedEnemy(heroClass.getAttackPowerRatioOnNonTargetedEnemy());
        this.setCriticalHitChance(heroClass.getCriticalHitChance());
        this.setCriticalHitDamage(heroClass.getCriticalHitDamage());
        this.setInventorySize(heroClass.getInventorySize());
        this.setSkills(heroClass.getSkills());
        this.setPerks(heroClass.getPerks());
        this.setMaximumEnergyPoint(heroClass.getMaximumEnergyPoint());
        this.setMaximumMagic(heroClass.getMaximumMagic());
        this.setNumberOfNonTargetedEnemyEffected(heroClass.getNumberOfNonTargetedEnemyEffected());
        this.setMagicRefillRate(heroClass.getMagicRefillRate());
        this.setMaximumHealth(heroClass.getMaximumHealth());
        this.setCurrentMagic(this.getMaximumMagic());
        this.setCurrentEnergyPoint(this.getMaximumEnergyPoint());
        this.setCurrentHealth(this.getMaximumHealth());
        this.setAttackPower(heroClass.getAttackPower());
        this.setSizeOfOccupiedInventory(0);
    }

    //-------------------------------------------------- Functions
    public void showDescription(){
        Display.printInEachLine("");
    }
    //-------------------------------------------------- Getter && Setters


    public int getSizeOfOccupiedInventory() {
        return sizeOfOccupiedInventory;
    }

    public void setSizeOfOccupiedInventory(int sizeOfOccupiedInventory) {
        this.sizeOfOccupiedInventory = sizeOfOccupiedInventory;
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

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(double criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public void setCriticalHitDamage(double criticalHitDamage) {
        this.criticalHitDamage = criticalHitDamage;
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

    public CraftingRequirement getCraftingRequirement() {
        return craftingRequirement;
    }

    public void setCraftingRequirement(CraftingRequirement craftingRequirement) {
        this.craftingRequirement = craftingRequirement;
    }

    public SelfImprovement getSelfImprovement() {
        return selfImprovement;
    }

    public void setSelfImprovement(SelfImprovement selfImprovement) {
        this.selfImprovement = selfImprovement;
    }
}
