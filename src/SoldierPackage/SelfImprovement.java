package SoldierPackage;

import PlayerPackage.*;
import AbilityPackage.*;
import Structure.*;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by asus-pc on 7/14/2016.
 */
public class SelfImprovement implements Cloneable {
    Perk perk;
    Map<Integer, Condition> conditions;

    public SelfImprovement(Perk perk, Map<Integer, Condition> conditions) {
        this.perk = perk;
        this.conditions = conditions;
    }

    public SelfImprovement clone() {
        SelfImprovement copy = null;
        try {
            copy = (SelfImprovement) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if (this.perk != null) {
            copy.setPerk(this.perk.clone());
        }
        return copy;
    }

    public void checkCondition(Player player) {
        if (this.perk == null) {
            return;
        }
        boolean result = conditions.get(perk.getCurrentGrade() + 1).checkCondition(player);
        if (result) {
            ArrayList<SubPerk> nextGradeSubPerks = this.perk.getNextGradeSubPerks();
            perk.upgrade(player, nextGradeSubPerks.get(0));
        }
    }

    public Perk getPerk() {
        return perk;
    }

    public void setPerk(Perk perk) {
        this.perk = perk;
    }

    public Map<Integer, Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Map<Integer, Condition> conditions) {
        this.conditions = conditions;
    }
}
