import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Perk<E> extends Ability{
    public static Map<String, Perk> listOfPerks;
    private ArrayList<Condition> listOfCondition;
    private ArrayList<PerkMode> listOfModes;
    private Map<Condition, PerkMode> mapOfCondition;
    private Map<E, PerkMode> mapOfRelatedSoldiers;
    private boolean isConditionDependOnRelatedSoldier;
    private boolean isConditionDependOnUserHero;
    private String timeOfCheck; // Can equals "duringAttack", "duringDefend" and "eachActivity"


    private Condition validCondition(E relatedSoldier) {
        for (Condition condition: this.listOfCondition) {
            if (condition.checkCondition(relatedSoldier)) {
                return condition;
            }
        }
        return null;
    }

    public void updatePerkEffect(ArrayList<E> relatedSoldiers, Hero userHero) {
        Hero owner = Hero.mapOfHeroes.get(this.ownerName);
        if (this.listOfCondition.size() == 1) {
            for (E relatedSoldier: relatedSoldiers) {
                if (this.mapOfRelatedSoldiers.containsKey(relatedSoldier))
                    continue;
                else {
                    this.mapOfRelatedSoldiers.put(relatedSoldier, this.listOfModes.get(0));
                    this.listOfModes.get(0).effect(relatedSoldier, owner, userHero);
                }

            }
            return;
        }
        for (E soldier: relatedSoldiers) {
            PerkMode perkMode;
            if (this.isConditionDependOnRelatedSoldier)
                perkMode = this.mapOfCondition.get(this.validCondition((E) relatedSoldiers));
            else if (this.isConditionDependOnUserHero)
                perkMode = this.mapOfCondition.get(this.validCondition((E) userHero));
            else {
                perkMode = this.mapOfCondition.get(this.validCondition((E) owner));
            }
            if (this.mapOfRelatedSoldiers.containsKey(soldier) == false) {
                this.mapOfRelatedSoldiers.put(soldier, perkMode);
                perkMode.effect(relatedSoldiers, owner, userHero);
                continue;
            }
            if (this.mapOfRelatedSoldiers.get(soldier) == perkMode)
                continue;
            else {
                this.mapOfRelatedSoldiers.get(soldier).removeEffect(soldier);
                this.mapOfRelatedSoldiers.put(soldier, perkMode);
                perkMode.effect(relatedSoldiers, owner, userHero);
            }
        }

    }

    public void choosingRelatedSoldiers(Enemy enemy,Hero hero) {  // in method dar moghe attack ya defend call mishavad;
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            this.relatedSoldiers.add(enemy);
            return;
        }
        this.relatedSoldiers.add(hero);
    }


    public void choosingRelatedSoldiers() {
        this.relatedSoldiers.clear();
        if (this.hasEffectedOnEnemy) {
            this.relatedSoldiers.addAll(GameEngine.listOfEnemies);
            return;
        }
        if (this.numberOfRelatedSoldiers == 1) {
            this.relatedSoldiers.add(Hero.mapOfHeroes.get(this.ownerName));
            return;
        }
        this.relatedSoldiers.addAll(GameEngine.listOfHeroes);
    }
}
