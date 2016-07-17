package AbilityPackage;

import java.io.Serializable;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class AbilityHandler implements Serializable {

    private String name;
    private String ownerName; // in field shayad lazem nabashe, ama be mafhoome code komak mikone.
    private int numberOfGrades;
    private String description;

    //-----------------------------------------------          Constructor

    public AbilityHandler(String name, String ownerName, int numberOfGrades, String description) {
        this.name = name;
        this.ownerName = ownerName;
        this.numberOfGrades = numberOfGrades;
        this.description = description;
    }

    //---------------------------------------------------------------- Getter && Setters


    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getNumberOfGrades() {
        return numberOfGrades;
    }

    public String getDescription() {
        return description;
    }
}
