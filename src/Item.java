import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Item {
    private String name;
    private String owner; // is this necessary?!
    private ArrayList<String> effectedHero;
    private int size;
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private int time;
    private int remainingTime;
    private boolean isInstantlyUsed;
    private int maximumTimeOfUsed;
    private int remainingTimeOfUsed;
    private ArrayList<Property> properties;

    public void isActivated() {
        //TODO
    }

    public void useItem() {
        //TODO
    }
}
