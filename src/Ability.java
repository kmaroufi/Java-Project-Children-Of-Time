import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability<T> {

    protected String name;
    protected String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    protected ArrayList<T> effectedSoldiers;
    protected ArrayList<T> relatedSoldiers;
    private boolean isGlobal; // Just clear that other heroes can use this Ability or no.
    protected boolean hasEffectedOnEnemy;
    protected boolean isRandomSoldierSelecting;
    protected int numberOfRelatedSoldiers;
    private int numberOfGrades;
    private int currentGrade;
    private String fieldOfEffecting; // this field can get Hero, Enemy, Ability, item, Shop and ... value.
    private boolean isEffectDuringAttack;
    private boolean hasCondition;
    private int[] costOfUpgrade;
    public static Map<String, String> listOfAbilities;
    //-------------------------------------------------------- Constructors
    public Ability(AbilityHandler abilityHandler) {
        this.setName(abilityHandler.getName());
        this.setCostOfUpgrade(abilityHandler.getCostOfUpgrade());
        this.setCurrentGrade(abilityHandler.getCurrentGrade());
        this.setFieldOfEffecting(abilityHandler.getFieldOfEffecting());
        this.setGlobal(abilityHandler.isGlobal());
        this.setHasCondition(abilityHandler.isHasCondition());
        this.setNumberOfGrades(abilityHandler.getNumberOfGrades());
        this.setNumberOfRelatedSoldiers(abilityHandler.getNumberOfRelatedSoldiers());
        this.setOwnerName(abilityHandler.getOwnerName());
        this.setRandomSoldierSelecting(abilityHandler.isRandomSoldierSelecting());
        this.setRelatedSoldiers(abilityHandler.getRelatedSoldiers());
        this.setEffectedSoldiers(abilityHandler.getEffectedSoldiers());
    }


    public Ability() {

    }
    //-------------------------------------------------------- Functions

    abstract public void upgrade();

    abstract public void choosingRelatedSoldiers();
    //-------------------------------------------------------- Getter And Setters

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

    public boolean isEffectDuringAttack() {
        return isEffectDuringAttack;
    }

    public void setEffectDuringAttack(boolean effectDuringAttack) {
        isEffectDuringAttack = effectDuringAttack;
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
