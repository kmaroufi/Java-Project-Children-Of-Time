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
    public void attack(Enemy enemy){
        enemy.setCurrentHealth(enemy.getCurrentHealth() - this.attackPower);
    }
    public void useSkill(String skillName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with Skillname
            if(this.skills.get(i).getName().equals(skillName)){
                 this.skills.get(i).useSkill();
            }
        }
    }
    public void upgradeAbility(String abilityName){
        for(int i = 0;i < this.skills.size();i++){                  //finding Skill with abilityName
            if(this.skills.get(i).getName().equals(abilityName)){
                this.skills.get(i).upgrade();
                return;
            }
        }
        for(int i = 0;i < this.perks.size();i++){                  //finding perk with abilityName
            if(this.perks.get(i).getName().equals(abilityName)){
                this.perks.get(i).upgrade();
                return;
            }
        }
    }
    public void useItem(String itemName){

    }

}
