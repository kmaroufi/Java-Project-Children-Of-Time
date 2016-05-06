import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Future on 5/6/2016.
 */
public class HeroClass extends Soldier{
    public static HashMap<String, HeroClass> listOfHeroClasses;
    private ArrayList<Hero> listOfHeroes;
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
