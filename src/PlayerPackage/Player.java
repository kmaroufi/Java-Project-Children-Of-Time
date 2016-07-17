package PlayerPackage;

import java.io.Serializable;
import java.util.ArrayList;
import GUI.*;
import SoldierPackage.*;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Player implements Serializable{
    private String name;

    public static int imortalityPotion = 3;
    private ArrayList<Hero> heroes;
    private int xp;
    private double money;
    //----------------------------------------------- Constructors
    public Player(){}

    public Player(String name,int xp,double money){
        this.setName(name);
        this.setXp(xp);
        this.setMoney(money);
    }
    //----------------------------------------------- Functions
    public void useImortalityPotion(Hero hero){
        if(this.imortalityPotion > 0) {
            this.imortalityPotion -= 1;
            hero.setCurrentHealth(hero.getMaximumHealth());
            Display.printInEachLine(hero.getName() + " is dying, immortality potion was used for reincarnation process, you now have " + imortalityPotion + " immortality potions left");
        }
        else{
            Display.printInEachLine(hero.getName() + " is dead and so is the spirit of this adventure, Game Over!”");
        }
    }
    public void doBattle() {
        //TODO
    }

    public void addHero(Hero hero){
        this.heroes.add(hero);
    }
    //----------------------------------------------- Getter And Setter
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
