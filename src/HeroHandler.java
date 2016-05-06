import java.util.ArrayList;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroHandler extends Hero{
    private ArrayList<Perk> perks;
    private ArrayList<Skill> skills;
    private Double criticalHitChance;
    private Double criticalHitChanceRatio;
    private Double criticalHitDamage;
    private Double criticalHitDamageRatio;
    private Integer maximumMagic;
    private Double magicRefillRate;
    private Integer currentMagic;
    private Double attackPowerRatioDuringAttack;
    private Double healthRefillRateRatio;
    private Double magicRefillRateRatio;
    private Double energyPointRatio;
    private Double attackPowerRatioOnNonTargetedEnemy;
    private Double attackPowerOnNonTargetedEnemy;
    private Integer numberOfNonTargetedEnemyEffected;
    private Integer maximumEnergyPoint;
    private Integer currentEnergyPoint;
    private Integer inventorySize;
    //----------------------------------------------------------------
    public void setCurrentMagic(int magic){
        this.currentMagic = magic;
    }
}
