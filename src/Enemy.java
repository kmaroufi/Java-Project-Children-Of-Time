/**
 * Created by Future on 5/6/2016.
 */
import java.util.*;
public class Enemy {
    public static HashMap<String , ArrayList<Enemy>> listOfEnemies;
    private String version;
    private Integer attackPower;
    private Double attackPowerRatio;
    //----------------------------------------------------
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(Integer attackPower) {
        this.attackPower = attackPower;
    }

    public Double getAttackPowerRatio() {
        return attackPowerRatio;
    }

    public void setAttackPowerRatio(Double attackPowerRatio) {
        this.attackPowerRatio = attackPowerRatio;
    }
    //----------------------------------------------------
    protected void doTurn(){

    }
}
