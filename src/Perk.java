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
    private PerkMode currentMode;

    public void upgrade() {};

    public void updatePerkEffect() {};
}
