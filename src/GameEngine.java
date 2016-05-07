import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class GameEngine {
    private Player player;
    public static ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
    public static ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();
    public static ArrayList<Hero> listOfHeroes = new ArrayList<Hero>();

    //------------------------------------------ Getter && Setters

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<HeroClass> getListOfHeroClasses() {
        return listOfHeroClasses;
    }

    public void setListOfHeroClasses(ArrayList<HeroClass> listOfHeroClasses) {
        this.listOfHeroClasses = listOfHeroClasses;
    }

    public ArrayList<Hero> getListOfHeroes() {
        return listOfHeroes;
    }

    public void setListOfHeroes(ArrayList<Hero> listOfHeroes) {
        this.listOfHeroes = listOfHeroes;
    }

    //------------------------------------------ Functions

    public void addNewHeroClass() {
        HeroClassHandler heroClassHandler = Console.getHeroClass();
        this.listOfHeroClasses.add(new HeroClass(heroClassHandler));
    }

    public void addNewHero() {
        //TODO
    }

    public void addNewAbility() {
        //TODO
    }

    public void addNewItem() {
        //TODO
    }

    public void addNewMap() {
        //TODO
    }

    public void reset() {
        //TODO
    }

    public void doBattle() {
        //TODO
    }

    public void play() {
        //TODO
    }


}
