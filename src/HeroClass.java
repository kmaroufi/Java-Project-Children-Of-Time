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
    protected SelfImprovement selfImprovement;
    protected SelectingObjectsDetail<Enemy> selectingNonTargetedEnemiesForAttack;
    protected int inventorySize;
    protected int sizeOfOccupiedInventory;
//    protected double armor;
//    protected double armorRatio; // I know you have problem with this. just keep calm :))
//    protected double damageResistance;
//    protected double criticalDamageResistanceChance;
//    protected double criticalDamageResistance;
//    protected double headShotChance; // when you headShot an enemy, you kill it immediately!


    //-------------------------------------------------- Constructors

    public HeroClass(SoldierHandler soldierHandler, HeroClassHandler heroClassHandler){
        super(soldierHandler);
        this.setCraftingRequirement(heroClassHandler.getCraftingRequirement());
        this.setCriticalHitChance(heroClassHandler.getCriticalHitChance());
        this.setCriticalHitDamage(heroClassHandler.getCriticalHitDamage());
        this.setSelfImprovement(heroClassHandler.getSelfImprovement());
        this.setSelectingNonTargetedEnemiesForAttack(heroClassHandler.getSelectingNonTargetedEnemiesForAttack());
        this.setInventorySize(heroClassHandler.getInventorySize());
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

    public SelectingObjectsDetail<Enemy> getSelectingNonTargetedEnemiesForAttack() {
        return selectingNonTargetedEnemiesForAttack;
    }

    public void setSelectingNonTargetedEnemiesForAttack(SelectingObjectsDetail<Enemy> selectingNonTargetedEnemiesForAttack) {
        this.selectingNonTargetedEnemiesForAttack = selectingNonTargetedEnemiesForAttack;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
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
