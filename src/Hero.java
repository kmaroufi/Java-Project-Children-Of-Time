/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Hero extends HeroClass {
    public static HashMap<String, Hero> listOfHeroes = new HashMap<String, Hero>();
    public static ArrayList<Skill> listOfActiveGlobalSkills = new ArrayList<Skill>();
//  public static ArrayList<Perk> listOfActiveGlobalPerks = new ArrayList<Perk>();
    private HashMap<String, Item> listOfitems = new HashMap<String, Item>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private String name;

    //------------------------------------------ Constructors
    public Hero(){

    }

    public Hero(String name){
        if(listOfHeroes.containsKey(name)){
            this.name = name + "-2";
        }
        this.name = name;
    }
    //------------------------------------------ Getter && Setters
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

    //------------------------------------------ Functions
    public void attack(){

    }
    public void useSkill(String skillName){

    }
    public void upgradeAbility(String abilityName){

    }
    public void useItem(String itemName){

    }

}
