import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Player {
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

    private ArrayList<Hero> listOfHeros;
    private int xp;
    private double money;

    public void doBattle() {
        //TODO
    }
    public void addHero(Hero hero){
        this.listOfHeros.add(hero);
    }
}
