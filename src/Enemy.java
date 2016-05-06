/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Enemy extends Soldier{
    public static HashMap<String , ArrayList<Enemy>> listOfEnemies;
    private String version;
    private Double attackPowerRatio;
    private String className;
    private String name;

    //---------------------------------------------------- Constructors
    public Enemy(){
    }
    public Enemy(Integer attackPower , String className, String name){
        this.setAttackPower(attackPower);
        this.setClassName(className);
        this.setName(name);
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
