/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Enemy extends Soldier{
    public static HashMap<String , Enemy> listOfEnemies;        // ArrayList is Wrong
    private String version;                         // it has problem
    private String className;
    private Double attackPowerRatio;

    //---------------------------------------------------- Constructors
    public Enemy(){

    }
    public Enemy(Integer attackPower , String className, String name, String version){
        this.setAttackPower(attackPower);
        this.setClassName(className);
        this.setName(name);
        this.setVersion(version);
        listOfEnemies.put(this.name,this);
    }

    //---------------------------------------------------- Functions
    protected void doTurn(){

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
