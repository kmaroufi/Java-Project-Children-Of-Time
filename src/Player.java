import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Player {
    private String name;

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
