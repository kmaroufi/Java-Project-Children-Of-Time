/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Hero extends HeroClass {
    public static HashMap<String, Hero> listOfHeroes;
    public static ArrayList<Skill> listOfActiveGlobalSkills;
    public static ArrayList<Perk> listOfActiveGlobalPerks;
    private String name;
    private HashMap<String, Item> listOfitems;
    private ArrayList<Item> items;



    //------------------------------------------
    public Hero(String name){
        this.name = name;
    }
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
