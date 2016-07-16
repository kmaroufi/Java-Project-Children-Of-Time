package AbilityPackage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Ability {

    public static Map<String, String> listOfAbilities = new HashMap<>();

    protected String name;
    protected String ownerName;
    protected int numberOfGrades;
    protected int currentGrade;
    protected String description;
    protected boolean isAcquire;

    //-------------------------------------------------------- Constructors

    public Ability(AbilityHandler abilityHandler) {
        this.setName(abilityHandler.getName());
        this.setOwnerName(abilityHandler.getOwnerName());
        this.setNumberOfGrades(abilityHandler.getNumberOfGrades());
        this.setCurrentGrade(0);
        this.setDescription(abilityHandler.getDescription());
        this.setAcquire(false);
    }

    //-------------------------------------------------------- Functions



    //-------------------------------------------------------- Getter And Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAcquire() {
        return isAcquire;
    }

    public void setAcquire(boolean acquire) {
        isAcquire = acquire;
    }
}
