import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Perk extends Ability implements Cloneable{
    public static Map<String, Perk> listOfPerks = new HashMap<>();

    private ArrayList<SubPerk> subPerks = new ArrayList<>();

    private TimeOfCheck timeOfCheck; // Can equals "duringAttackDefend" and "eachActivity"

    public enum TimeOfCheck {
        duringAttackDefend, eachActivity
    }

    //---------------------------------------------------------- Constructors

    public Perk(AbilityHandler abilityHandler, TimeOfCheck timeOfCheck) {
        super(abilityHandler);
        setTimeOfCheck(timeOfCheck);
    }

    //---------------------------------------------------------- Functions

    public boolean upgrade(Player player) {
        boolean canAcquireThisGrade = this.subPerks.get(this.currentGrade).canAcquireThisGrade();
        if (canAcquireThisGrade) {
            this.currentGrade += 1;
            if (this.isAcquire == false) {
                this.isAcquire = true;
            }
            if (this.timeOfCheck != TimeOfCheck.duringAttackDefend) {
                if (this.currentGrade != 1) {
                    this.removeEffect();
                }
                this.updatePerkEffect(Hero.mapOfHeroes.get(this.ownerName));
            }
            return true;
        }
        else {
            return false;
        }
    }

    protected Perk clone() {
        Perk perk = null;
        try {
            perk = (Perk) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        perk.subPerks = new ArrayList<>();
        for (SubPerk subPerk: this.subPerks) {
            SubPerk newSubPerk = subPerk.clone();
            newSubPerk.setRelatedPerk(perk);
            perk.subPerks.add(newSubPerk);
        }
        return perk;
    }

    public boolean equals(Perk perk){
        if(perk.getName().equals(this.name)){
            return true;
        }
        return false;
    }

    public void removeEffect() {
        this.subPerks.get(this.currentGrade - 1).removeEffect();
    }

    public void updatePerkEffect(Hero userHero) {
        this.updatePerkEffect(null, null, userHero);
    }

    public void updatePerkEffect(Enemy enemy, Hero hero, Hero userHero) {
        this.subPerks.get(this.currentGrade - 1).updatePerkEffect(enemy, hero, userHero);
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    //---------------------------------------------------------- Getter && Setters

    public TimeOfCheck getTimeOfCheck() {
        return timeOfCheck;
    }

    public void setTimeOfCheck(TimeOfCheck timeOfCheck) {
        this.timeOfCheck = timeOfCheck;
    }

}
