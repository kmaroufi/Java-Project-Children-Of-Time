package SoldierPackage;
import ItemPackage.*;
import Structure.*;
/**
 * Created by Future on 5/6/2016.
 */
public class HeroClassHandler implements Cloneable{
    private CraftingRequirement craftingRequirement;
    private double criticalHitChance;
    private double criticalHitDamage;
    private SelfImprovement selfImprovement;
    private SelectingObjectsDetail<Soldier> selectingNonTargetedSoldiersForAttack;
    private int inventorySize;

    public HeroClassHandler(CraftingRequirement craftingRequirement, double criticalHitChance, double criticalHitDamage, SelfImprovement selfImprovement, SelectingObjectsDetail<Soldier> selectingNonTargetedEnemiesForAttack, int inventorySize) {
        this.craftingRequirement = craftingRequirement;
        this.criticalHitChance = criticalHitChance;
        this.criticalHitDamage = criticalHitDamage;
        this.selfImprovement = selfImprovement;
        this.selectingNonTargetedSoldiersForAttack = selectingNonTargetedEnemiesForAttack;
        this.inventorySize = inventorySize;
    }

    public CraftingRequirement getCraftingRequirement() {
        return craftingRequirement;
    }

    public double getCriticalHitChance() {
        return criticalHitChance;
    }

    public double getCriticalHitDamage() {
        return criticalHitDamage;
    }

    public SelfImprovement getSelfImprovement() {
        return selfImprovement;
    }

    public SelectingObjectsDetail<Soldier> getSelectingNonTargetedSoldiersForAttack() {
        return selectingNonTargetedSoldiersForAttack;
    }

    public int getInventorySize() {
        return inventorySize;
    }

}
