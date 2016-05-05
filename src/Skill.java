import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill {
    public static Map<String, Skill> listOfSkills;
    private String effectedOnHero;
    private Health health;
    private AttackPower attackPower;
    private Magic magic;
    private EnergyPoint energyPoint;
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

    public boolean isActivated() {};

    public void useSkill() {};

    public void upgrade() {};

}
