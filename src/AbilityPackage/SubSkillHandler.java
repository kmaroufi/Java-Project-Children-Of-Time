package AbilityPackage;
import Structure.*;

import java.io.Serializable;
import java.util.*;

/**
 * Created by asus-pc on 7/7/2016.
 */
public class SubSkillHandler implements Serializable {

    private boolean isRepeated;
    private Time timeOfEffecting;
    private int cooldown;
    private boolean canStackUp;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    private String name;

    private ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();

    //---------------------------------------------------------------- Constructors

    public SubSkillHandler(boolean isRepeated, Time timeOfEffecting, int cooldown, boolean canStackUp, int requiredEnergyPoint, int requiredMagicPoint, String name, ArrayList<SubSkillComponent<?>> subSkillComponents) {
        this.isRepeated = isRepeated;
        this.timeOfEffecting = timeOfEffecting;
        this.cooldown = cooldown;
        this.canStackUp = canStackUp;
        this.requiredEnergyPoint = requiredEnergyPoint;
        this.requiredMagicPoint = requiredMagicPoint;
        this.name = name;
        this.subSkillComponents = subSkillComponents;
    }

    //---------------------------------------------------- Getter && Setters


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

    public ArrayList<SubSkillComponent<?>> getSubSkillComponents() {
        return subSkillComponents;
    }

    public String getName() {
        return name;
    }
}
