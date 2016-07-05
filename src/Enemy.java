/**
 * Created by Future on 5/6/2016.
 */
import java.lang.reflect.Field;
import java.util.*;
public abstract class Enemy extends Soldier{
    public static HashMap<String , Enemy> mapOfEnemies = new HashMap<>();        // ArrayList is Wrong
    private String version;                         // (Weak-Able-Mighty)
    private Double attackPowerRatio;
    private Integer isAstounded;
    protected String name;                            // Full name of An Enemy   ----->     (ClassName is Like Thug)

    private static Map<String, Field> fieldsMap = new HashMap<>();

    static {
        Class clazz = Hero.class;
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                fieldsMap.put(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }
    }

    //---------------------------------------------------- Constructors

    public Enemy(){

    }

    //---------------------------------------------------- Functions
    public void showDescription(){
        Display.printInEachLine(this.getName());
        Display.printInEachLine("Health: " + this.getCurrentHealth() + " / " + this.getMaximumHealth());
    }

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
    }                               // gets The Full Name

    public void setName(String name) {
        this.name = name;
    }

    public static Map<String, Field> getFieldsMap() {
        return fieldsMap;
    }

    public static void setFieldsMap(Map<String, Field> fieldsMap) {
        Enemy.fieldsMap = fieldsMap;
    }
}
