/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public abstract class Enemy extends Soldier{
    public static HashMap<String , Enemy> mapOfEnemies;        // ArrayList is Wrong
    public static ArrayList<Enemy> listOfEnemies;
    private String version;                         // it has problem
    private Double attackPowerRatio;
    private String name;

    //---------------------------------------------------- Constructors
    public Enemy(){

    }
    public Enemy(Integer attackPower , String className, String name, String version){
        this.setAttackPower(attackPower);
        this.setClassName(className);
        this.setName(name);
        this.setVersion(version);
        mapOfEnemies.put(this.className,this);
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
