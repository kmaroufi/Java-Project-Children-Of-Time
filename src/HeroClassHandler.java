import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClassHandler implements Cloneable{
    private CraftingRequirement craftingRequirement;
    private double criticalHitChance;
    private double criticalHitDamage;
    private SelfImprovement selfImprovement;
    private SelectingObjectsDetail<Enemy> selectingNonTargetedEnemiesForAttack;
    private int inventorySize;
    private int sizeOfOccupiedInventory;

    public HeroClassHandler(CraftingRequirement craftingRequirement, double criticalHitChance, double criticalHitDamage, SelfImprovement selfImprovement, SelectingObjectsDetail<Enemy> selectingNonTargetedEnemiesForAttack, int inventorySize, int sizeOfOccupiedInventory) {
        this.craftingRequirement = craftingRequirement;
        this.criticalHitChance = criticalHitChance;
        this.criticalHitDamage = criticalHitDamage;
        this.selfImprovement = selfImprovement;
        this.selectingNonTargetedEnemiesForAttack = selectingNonTargetedEnemiesForAttack;
        this.inventorySize = inventorySize;
        this.sizeOfOccupiedInventory = sizeOfOccupiedInventory;
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

    public SelectingObjectsDetail<Enemy> getSelectingNonTargetedEnemiesForAttack() {
        return selectingNonTargetedEnemiesForAttack;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public int getSizeOfOccupiedInventory() {
        return sizeOfOccupiedInventory;
    }
}
