/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Hero {
    public static HashMap<String,ArrayList<Hero>> listOfHeros;
    public static HashMap<String, String> listOfHeroClasses;
    public static ArrayList<Skill> listOfActiveGlobalSkills;
    public static ArrayList<Perk> listOfActiveGlobalPerks;
    private HashMap<String, Item> listOfitems;
    private Double criticalHitChance;
    private Double criticalHitChanceRatio;
    private Double criticalHitDamage;
    private Double criticalHitDamageRatio;
    private Integer maximumMagic;
    private Double magicRefillRate;
    private Integer currentMagic;
    private Double attackPowerRatioDuringAttack;		//   :| namusan be hich dardi nemikhore!
    private Double healthRefillRateRatio;
    private Double magicRefillRateRatio;
    private Double energyPointRatio;
    private Double attackPowerRatioOnNonTargetedEnemy;
    private Double attackPowerOnNonTargetedEnemy;
    private Integer numberOfNonTargetedEnemyEffected;
    private Integer maximumEnergyPoint;
    private Integer currentEnergyPoint;
    private Integer inventorySize;
    private ArrayList<Item> items;
    private ArrayList<Perk> perks;
    private ArrayList<Skill> skills;


    //------------------------------------------
    //------------------------------------------
    public void attack(){

    }
    public void useSkill(String skillName){

    }
    public void upgradeAbility(String abilityName){

    }
    public void useItem(String itemName){

    }

}
