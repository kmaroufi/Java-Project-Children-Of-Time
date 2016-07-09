import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/7/2016.
 */
public abstract class SubAbility {

    protected boolean isGlobal; // Just clear that other heroes can use this Ability or no.
    protected int costOfUpgrade;
    protected ArrayList<String> nameOfNecessaryAbilities; // list Of necessary abilities
    protected Map<String, Integer> gradeOfNecessaryAbilities; // map Of necessary abilities
    protected String upgradeDescription;

    //-------------------------------------------------------- Constructors

    public SubAbility(boolean isGlobal, int costOfUpgrade, ArrayList<String> nameOfNecessaryAbilities, Map<String, Integer> gradeOfNecessaryAbilities, String upgradeDescription) {
        this.isGlobal = isGlobal;
        this.costOfUpgrade = costOfUpgrade;
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
        this.upgradeDescription = upgradeDescription;
    }

    public SubAbility(SubAbilityHandler subAbilityHandler) {
        this.setGlobal(subAbilityHandler.isGlobal());
        this.setCostOfUpgrade(subAbilityHandler.getCostOfUpgrade());
        this.setNameOfNecessaryAbilities(subAbilityHandler.getNameOfNecessaryAbilities());
        this.setGradeOfNecessaryAbilities(subAbilityHandler.getGradeOfNecessaryAbilities());
        this.setUpgradeDescription(subAbilityHandler.getUpgradeDescription());
    }


    //-------------------------------------------------------- Functions

    abstract public boolean canAcquireThisGrade();

    //-------------------------------------------------------- Getter And Setters


    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public int getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public void setCostOfUpgrade(int costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

    public ArrayList<String> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public void setNameOfNecessaryAbilities(ArrayList<String> nameOfNecessaryAbilities) {
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
    }

    public Map<String, Integer> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public void setGradeOfNecessaryAbilities(Map<String, Integer> gradeOfNecessaryAbilities) {
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
    }

    public String getUpgradeDescription() {
        return upgradeDescription;
    }

    public void setUpgradeDescription(String upgradeDescription) {
        this.upgradeDescription = upgradeDescription;
    }
}
