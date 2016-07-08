import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/7/2016.
 */
public class SubSkillHandler {

    private Skill relatedSkill;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private int cooldown;
    private boolean canStackUp;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    private Map<String, Map> mapOfRemainTimeOfEffectByClass = new HashMap<>(); // Key = className, Value = { Key = object, Value = Time of how much this effect will be remain

    //---------------------------------------------------------------- Constructors

    public SubSkillHandler(Skill relatedSkill, boolean isRepeated, Time timeOfEffecting, int cooldown, boolean canStackUp, int requiredEnergyPoint, int requiredMagicPoint, Map<String, Map> mapOfRemainTimeOfEffectByClass) {
        this.relatedSkill = relatedSkill;
        this.isRepeated = isRepeated;
        this.timeOfEffecting = timeOfEffecting;
        this.cooldown = cooldown;
        this.canStackUp = canStackUp;
        this.requiredEnergyPoint = requiredEnergyPoint;
        this.requiredMagicPoint = requiredMagicPoint;
        this.mapOfRemainTimeOfEffectByClass = mapOfRemainTimeOfEffectByClass;
    }

    //---------------------------------------------------- Getter && Setters


    public Skill getRelatedSkill() {
        return relatedSkill;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public int getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public int getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public Map<String, Map> getMapOfRemainTimeOfEffectByClass() {
        return mapOfRemainTimeOfEffectByClass;
    }
}
