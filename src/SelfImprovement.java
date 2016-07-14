import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by asus-pc on 7/14/2016.
 */
public class SelfImprovement {
    Perk perk;
    Map<Integer, Condition> conditions;

    public void checkCondition(Player player) {
        boolean result = conditions.get(perk.getCurrentGrade() + 1).checkCondition(player);
        if (result) {
            ArrayList<SubPerk> nextGradeSubPerks = this.perk.getNextGradeSubPerks();
            perk.upgrade(player, nextGradeSubPerks.get(0));
        }
    }
}
