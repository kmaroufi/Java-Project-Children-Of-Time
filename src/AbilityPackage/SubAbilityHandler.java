package AbilityPackage;
import java.io.Serializable;
import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/8/2016.
 */
public class SubAbilityHandler implements Serializable{

    protected boolean isGlobal; // Just clear that other heroes can use this AbilityPackage or no.
    protected int costOfUpgrade;
    protected ArrayList<String> nameOfNecessaryAbilities; // list Of necessary abilities
    protected Map<String, Integer> gradeOfNecessaryAbilities; // map Of necessary abilities
    protected String upgradeDescription;

    //-------------------------------------------------------- Constructors

    public SubAbilityHandler(boolean isGlobal, int costOfUpgrade, ArrayList<String> nameOfNecessaryAbilities, Map<String, Integer> gradeOfNecessaryAbilities, String upgradeDescription) {
        this.isGlobal = isGlobal;
        this.costOfUpgrade = costOfUpgrade;
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
        this.upgradeDescription = upgradeDescription;
    }


    //-------------------------------------------------------- Getter And Setters


    public boolean isGlobal() {
        return isGlobal;
    }

    public int getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public ArrayList<String> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public Map<String, Integer> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public String getUpgradeDescription() {
        return upgradeDescription;
    }
}
