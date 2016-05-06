/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Hero extends HeroClass {
    public static HashMap<String, Hero> listOfHeroes;
    public static ArrayList<Skill> listOfActiveGlobalSkills;
    public static ArrayList<Perk> listOfActiveGlobalPerks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Item> getListOfitems() {
        return listOfitems;
    }

    public void setListOfitems(HashMap<String, Item> listOfitems) {
        this.listOfitems = listOfitems;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

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
