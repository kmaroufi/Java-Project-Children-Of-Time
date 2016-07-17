package SoldierPackage;
import GUI.*;
import AbilityPackage.*;
import ItemPackage.*;
import Structure.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClass extends Soldier implements Cloneable, Serializable {
    public static HashMap<String, HeroClass> mapOfHeroClasses = new HashMap<>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    protected CraftingRequirement craftingRequirement;
    protected double criticalHitChance;
    protected double criticalHitDamage;
    protected SelfImprovement selfImprovement;
    protected SelectingObjectsDetail<Soldier> selectingNonTargetedSoldiersForAttack;
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
        this.setSelectingNonTargetedSoldiersForAttack(heroClassHandler.getSelectingNonTargetedSoldiersForAttack());
        this.setInventorySize(heroClassHandler.getInventorySize());
        this.setSizeOfOccupiedInventory(0);
    }

    public HeroClass(HeroClass heroClass) {
        this.className = heroClass.getClassName();
        this.perks = heroClass.getPerks();
        this.skills = heroClass.getSkills();
        this.maximumHealth = heroClass.getMaximumHealth();
        this.healthRefillRate = heroClass.getHealthRefillRate();
        this.currentHealth = heroClass.getMaximumHealth();
        this.maximumMagic = heroClass.getMaximumMagic();
        this.magicRefillRate = heroClass.getMagicRefillRate();
        this.currentMagic = heroClass.getMaximumMagic();
        this.maximumEnergyPoint = heroClass.getMaximumEnergyPoint();
        this.currentEnergyPoint = heroClass.getMaximumEnergyPoint();
        this.attackPower = heroClass.getAttackPower();
        this.attackPowerRatioDuringAttack = heroClass.getAttackPowerRatioDuringAttack();
        this.attackPowerRatioOnNonTargetedSoldiers = heroClass.getAttackPowerRatioOnNonTargetedSoldiers();
        this.attackPowerOnNonTargetedSoldiers = heroClass.getAttackPowerOnNonTargetedSoldiers();
        this.craftingRequirement = heroClass.getCraftingRequirement();
        this.criticalHitChance = heroClass.getCriticalHitChance();
        this.criticalHitDamage = heroClass.getCriticalHitDamage();
        this.selfImprovement = heroClass.getSelfImprovement();
        this.selectingNonTargetedSoldiersForAttack = heroClass.getSelectingNonTargetedSoldiersForAttack();
        this.inventorySize = heroClass.getInventorySize();
        this.sizeOfOccupiedInventory = heroClass.getSizeOfOccupiedInventory();
    }

    //-------------------------------------------------- Functions

    public HeroClass clone() {
        HeroClass copy = null;
        try {
            copy = (HeroClass) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ArrayList<Perk> newPerks = new ArrayList<>();
        ArrayList<Skill> newSkills = new ArrayList<>();
        for (Perk perk: this.perks) {
            newPerks.add(perk.clone());
        }
        for (Skill skill: this.skills) {
            newSkills.add(skill.clone());
        }
        copy.setPerks(newPerks);
        copy.setSkills(newSkills);
        copy.setCraftingRequirement(this.craftingRequirement.clone());
        copy.setSelfImprovement(this.selfImprovement.clone());
        copy.setSelectingNonTargetedSoldiersForAttack(this.selectingNonTargetedSoldiersForAttack.clone());
        return copy;
    }

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

    public SelectingObjectsDetail<Soldier> getSelectingNonTargetedSoldiersForAttack() {
        return selectingNonTargetedSoldiersForAttack;
    }

    public void setSelectingNonTargetedSoldiersForAttack(SelectingObjectsDetail<Soldier> selectingNonTargetedSoldiersForAttack) {
        this.selectingNonTargetedSoldiersForAttack = selectingNonTargetedSoldiersForAttack;
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
