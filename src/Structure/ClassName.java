package Structure;
import Engine.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by asus-pc on 7/8/2016.
 */
public enum  ClassName implements Serializable {
    Hero("Hero"), Enemy("Enemy"), Item("Item"), Skill("Skill"), Perk("Perk");

    String name;
    ArrayList<?> listOfObjects;


    ClassName(String name) {
        this.name = name;
        switch (name) {
            case "Hero":
                this.listOfObjects = GameEngine.listOfHeroes;
                break;
            case "Enemy":
                this.listOfObjects = GameEngine.listOfEnemies;
                break;
//            case "ItemPackage":
//                this.listOfObjects = GameEngine.listOfItems;
//                break;
            case "Skill":
                this.listOfObjects = GameEngine.listOfSkills;
                break;
            case "Perk":
                this.listOfObjects = GameEngine.listOfPerks;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<?> getListOfObjects() {
        return listOfObjects;
    }
}
