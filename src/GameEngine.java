import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class GameEngine {
    private Player player;
    private ArrayList<HeroClass> listOfHeroClasses = new ArrayList<HeroClass>();

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
