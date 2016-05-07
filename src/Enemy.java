/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public abstract class Enemy extends Soldier{
    public static HashMap<String , Enemy> mapOfEnemies;        // ArrayList is Wrong
    private String version;                         // it has problem
    private Double attackPowerRatio;
    private String name;

    //---------------------------------------------------- Constructors
    public Enemy(){}
    public Enemy(String name,String version){
        this.setName(name);
        this.setVersion(version);
    }
    public Enemy(Integer attackPower,Integer maximumHealth){
        this.setAttackPower(attackPower);
        this.setMaximumHealth(maximumHealth);
    }

    //---------------------------------------------------- Functions
    public abstract void doTurn();
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
