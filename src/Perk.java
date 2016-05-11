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
    private boolean isConditionDependOnRelatedSoldier;

    public void upgrade() {};

    public <E> Condition validCondition(E relatedSoldier) {
        for (Condition condition: this.listOfCondition) {
            if (condition.checkCondition(relatedSoldier)) {
                return condition;
            }
        }
        return null;
    }

    public <E> void updatePerkEffect(ArrayList<E> relatedSoldiers) {
        if (this.isConditionDependOnRelatedSoldier == false) {
            Hero owner = Hero.mapOfHeroes.get(this.ownerName);
            PerkMode perkMode = this.mapOfCondition.get(this.validCondition(owner));
            for (E soldier: relatedSoldiers) {
                perkMode.effect(relatedSoldiers, owner);
            }
            return;
        }
        for (E soldier: relatedSoldiers) {
            PerkMode perkMode = this.mapOfCondition.get(this.validCondition(relatedSoldiers));
            perkMode.effect(relatedSoldiers, Hero.mapOfHeroes.get(this.ownerName));
        }
    }
}
