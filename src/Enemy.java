/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public abstract class Enemy extends Soldier{
    public static HashMap<String , Enemy> mapOfEnemies;        // ArrayList is Wrong
    private String version;                         // it has problem (Weak-Able-Mighty)
    private Double attackPowerRatio;
    private String name;                            // Full name of An Enemy        (ClassName is Like Thug)

    //---------------------------------------------------- Constructors
    public Enemy(){}
    //---------------------------------------------------- Functions
    public abstract void doTurn();
    public void setFullName(){
        int numberOfEnemiesWithSameName = 0;
        for(Enemy e : GameEngine.listOfEnemies){
            String formalNameE = e.getVersion() + " " + e.getClassName();
            String formalNameThis = this.getVersion() + " " + this.getClassName();
            if(formalNameE.equals(formalNameThis)){
                numberOfEnemiesWithSameName++;
            }
        }
        this.setName(this.getVersion() + " " + this.getClassName() + " " + (numberOfEnemiesWithSameName + 1));

    }
    //---------------------------------------------------- Getter && Setters
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Double getAttackPowerRatio() {
        return attackPowerRatio;
    }

    public void setAttackPowerRatio(Double attackPowerRatio) {
        this.attackPowerRatio = attackPowerRatio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
