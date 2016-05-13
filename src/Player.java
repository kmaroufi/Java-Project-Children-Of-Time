import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Player {
    private String name;

    private static int imortalityPotion = 3;
    private ArrayList<Hero> listOfHeros;
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
        this.listOfHeros.add(hero);
    }
    //----------------------------------------------- Getter And Setter
    public ArrayList<Hero> getListOfHeros() {
        return listOfHeros;
    }

    public void setListOfHeros(ArrayList<Hero> listOfHeros) {
        this.listOfHeros = listOfHeros;
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
