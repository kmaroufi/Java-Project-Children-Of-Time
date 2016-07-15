import java.util.ArrayList;

/**
 * Created by asus-pc on 7/15/2016.
 */
public class SoldierHandler {
    private String className;
    private ArrayList<Perk> perks = new ArrayList<Perk>();
    private ArrayList<Skill> skills = new ArrayList<Skill>();
    private int maximumHealth;
    private double healthRefillRate;
    private int maximumMagic;
    private double magicRefillRate;
    private int maximumEnergyPoint;
    private int attackPower;
    private double attackPowerOnNonTargetedSoldiers;

    public SoldierHandler(String className, ArrayList<Perk> perks, ArrayList<Skill> skills, int maximumHealth, double healthRefillRate, int maximumMagic, double magicRefillRate, int maximumEnergyPoint, int attackPower, double attackPowerOnNonTargetedSoldiers) {
        this.className = className;
        this.perks = perks;
        this.skills = skills;
        this.maximumHealth = maximumHealth;
        this.healthRefillRate = healthRefillRate;
        this.maximumMagic = maximumMagic;
        this.magicRefillRate = magicRefillRate;
        this.maximumEnergyPoint = maximumEnergyPoint;
        this.attackPower = attackPower;
        this.attackPowerOnNonTargetedSoldiers = attackPowerOnNonTargetedSoldiers;
    }

    public void addSkill(Skill skill){
        this.skills.add(skill);
    }

    public void addPerk(Perk perk){
        this.perks.add(perk);
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Perk> getPerks() {
        return perks;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public double getHealthRefillRate() {
        return healthRefillRate;
    }

    public int getMaximumMagic() {
        return maximumMagic;
    }

    public double getMagicRefillRate() {
        return magicRefillRate;
    }

    public int getMaximumEnergyPoint() {
        return maximumEnergyPoint;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public double getAttackPowerOnNonTargetedSoldiers() {
        return attackPowerOnNonTargetedSoldiers;
    }
}