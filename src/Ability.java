import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability<T> {

    protected String name;
    protected String ownerName;
    protected ArrayList<T> effectedSoldiers = new ArrayList<>();
    protected ArrayList<T> relatedSoldiers = new ArrayList<>();
    private boolean isGlobal; // Just clear that other heroes can use this Ability or no.
    protected boolean hasEffectedOnEnemy;
    protected boolean isRandomSoldierSelecting;
    protected int numberOfRelatedSoldiers;
    protected String description;
    private int numberOfGrades;
    protected int currentGrade;
    private String fieldOfEffecting; // this field can get Hero, Enemy, Ability, item, Shop and ... value.
    private boolean hasCondition;
    private int[] costOfUpgrade;
    private Map<Integer, ArrayList<String>> nameOfNecessaryAbilities; // Key = currentGrade, Value = list Of necessary abilities
    private Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities; // Key = currentGrade, Value = map Of necessary abilities
    public static Map<String, String> listOfAbilities;
    //-------------------------------------------------------- Constructors
    public Ability(AbilityHandler abilityHandler) {
        this.setHasEffectedOnEnemy(abilityHandler.isHasEffectedOnEnemy());
        this.setNameOfNecessaryAbilities(abilityHandler.getNameOfNecessaryAbilities());
        this.setGradeOfNecessaryAbilities(abilityHandler.getGradeOfNecessaryAbilities());
        this.setRandomSoldierSelecting(abilityHandler.isRandomSoldierSelecting());
        this.setName(abilityHandler.getName());
        this.setCostOfUpgrade(abilityHandler.getCostOfUpgrade());
        this.setCurrentGrade(0);
        this.setFieldOfEffecting(abilityHandler.getFieldOfEffecting());
        this.setGlobal(abilityHandler.isGlobal());
        this.setHasCondition(abilityHandler.isHasCondition());
        this.setNumberOfGrades(abilityHandler.getNumberOfGrades());
        this.setNumberOfRelatedSoldiers(abilityHandler.getNumberOfRelatedSoldiers());
        this.setOwnerName(abilityHandler.getOwnerName());
        this.setRandomSoldierSelecting(abilityHandler.isRandomSoldierSelecting());
    }


    public Ability() {

    }
    //-------------------------------------------------------- Functions

    public void upgrade(Player player) {

        if (this.currentGrade == this.numberOfGrades)
            return;

        for (String nameOfAbility: this.nameOfNecessaryAbilities.get(this.currentGrade + 1)) {
            if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                Skill skill = Skill.listOfSkills.get(nameOfAbility);
                if (skill.getCurrentGrade() > this.gradeOfNecessaryAbilities.get(this.currentGrade + 1).get(nameOfAbility))
                    return;
            }
            if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                Perk perk = Perk.listOfPerks.get(nameOfAbility);
                if (perk.getCurrentGrade() > this.gradeOfNecessaryAbilities.get(this.currentGrade + 1).get(nameOfAbility))
                    return;
            }
        }

        this.currentGrade += 1;
        player.setXp(player.getXp() - this.costOfUpgrade[this.currentGrade]);
    }

    //-------------------------------------------------------- Getter And Setters


    public Map<Integer, ArrayList<String>> getNameOfNecessaryAbilities() {
        return nameOfNecessaryAbilities;
    }

    public void setNameOfNecessaryAbilities(Map<Integer, ArrayList<String>> nameOfNecessaryAbilities) {
        this.nameOfNecessaryAbilities = nameOfNecessaryAbilities;
    }

    public Map<Integer, Map<String, Integer>> getGradeOfNecessaryAbilities() {
        return gradeOfNecessaryAbilities;
    }

    public void setGradeOfNecessaryAbilities(Map<Integer, Map<String, Integer>> gradeOfNecessaryAbilities) {
        this.gradeOfNecessaryAbilities = gradeOfNecessaryAbilities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public boolean isHasEffectedOnEnemy() {
        return hasEffectedOnEnemy;
    }

    public void setHasEffectedOnEnemy(boolean hasEffectedOnEnemy) {
        this.hasEffectedOnEnemy = hasEffectedOnEnemy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGrades() {
        return numberOfGrades;
    }

    public void setNumberOfGrades(int numberOfGrades) {
        this.numberOfGrades = numberOfGrades;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }


    public ArrayList<T> getEffectedSoldiers() {
        return effectedSoldiers;
    }

    public void setEffectedSoldiers(ArrayList<T> effectedSoldiers) {
        this.effectedSoldiers = effectedSoldiers;
    }

    public ArrayList<T> getRelatedSoldiers() {
        return relatedSoldiers;
    }

    public void setRelatedSoldiers(ArrayList<T> relatedSoldiers) {
        this.relatedSoldiers = relatedSoldiers;
    }

    public boolean isRandomSoldierSelecting() {
        return isRandomSoldierSelecting;
    }

    public void setRandomSoldierSelecting(boolean randomSoldierSelecting) {
        isRandomSoldierSelecting = randomSoldierSelecting;
    }

    public int getNumberOfRelatedSoldiers() {
        return numberOfRelatedSoldiers;
    }

    public void setNumberOfRelatedSoldiers(int numberOfRelatedSoldiers) {
        this.numberOfRelatedSoldiers = numberOfRelatedSoldiers;
    }

    public String getFieldOfEffecting() {
        return fieldOfEffecting;
    }

    public void setFieldOfEffecting(String fieldOfEffecting) {
        this.fieldOfEffecting = fieldOfEffecting;
    }

    public boolean isHasCondition() {
        return hasCondition;
    }

    public void setHasCondition(boolean hasCondition) {
        this.hasCondition = hasCondition;
    }

    public int[] getCostOfUpgrade() {
        return costOfUpgrade;
    }

    public void setCostOfUpgrade(int[] costOfUpgrade) {
        this.costOfUpgrade = costOfUpgrade;
    }

}
