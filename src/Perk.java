import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Perk extends Ability{
    public static Map<String, Perk> listOfPerks;
    private ArrayList<Condition> listOfCondition;
    private ArrayList<PerkMode> listOfModes;
    private Map<Condition, PerkMode> mapOfCondition;
    private Condition currentCondition;
    private PerkMode currentMode;

    public void upgrade() {};

    public boolean updateCurrentPerkMode() {
        Condition validCondition = new Condition();
        for (Condition condition: this.listOfCondition) {
            if (condition.checkCondition()) {
                validCondition = condition;
                break;
            }
        }
        if (validCondition == this.currentCondition)
            return false;
        this.currentCondition = validCondition;
        this.currentMode = this.mapOfCondition.get(validCondition);
        return true;
    }

    public void updatePerkEffect() {
        if (this.updateCurrentPerkMode() == false)
            return;
        this.currentMode.effect(this.relatedSoldiers);
    };
}
