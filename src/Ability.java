import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public abstract class Ability {

    protected String name;
    private int currentGrade;
    public static Map<String, String> listOfAbilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(int currentGrade) {
        this.currentGrade = currentGrade;
    }

    abstract public void upgrade();
}
