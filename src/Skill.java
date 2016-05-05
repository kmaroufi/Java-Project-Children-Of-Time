import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill {
    public static Map<String, Skill> listOfSkills;
    private String effectedOnHero;
    private ArrayList<Property> properties;
    private int nonTargetedEnemy;
    private boolean isRepeated;
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private int time;
    private int remainingTime;
    private int cooldown;
    private int remainingCooldown;
    private boolean isUsed;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    public boolean isActivated() {
        //TODO
        return false;
    };

    public void useSkill() {
        //TODO
    };

    public void upgrade() {
        //TODO
    };

}
