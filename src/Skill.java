import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill extends Ability implements Cloneable{
    public static Map<String, Skill> listOfSkills = new HashMap<String, Skill>();

    private ArrayList<SubSkill> subSkills = new ArrayList<>();

    //---------------------------------------------------------------- Constructors

    public Skill(AbilityHandler abilityHandler) {
        super(abilityHandler);
    }

    //---------------------------------------------------------------- Functions

    protected Skill clone() {
        Skill skill = null;
        try {
            skill = (Skill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        skill.subSkills = new ArrayList<>();
        for (SubSkill subSkill: this.subSkills) {
            SubSkill newSubSkill = subSkill.clone();
            newSubSkill.setRelatedSkill(skill);
            skill.subSkills.add(newSubSkill);
        }
        return skill;
    }       // Creates A Copy of This Object (Skill)

    public void useSkill(Hero userHero) {
        this.subSkills.get(currentGrade - 1).useSkill(userHero);
    }

    public void removeEffect() {
        this.subSkills.get(currentGrade - 1).removeEffect();
    }

    public void reduceTime(String typeOfTime) {
        this.subSkills.get(currentGrade - 1).reduceTime(typeOfTime);
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
    }

    public boolean equals(Skill skill){
        if(this.name.equals(skill.getName())){
            return true;
        }
        return false;
    }

    public boolean upgrade(Player player) {
        boolean canAcquireThisGrade = this.subSkills.get(currentGrade).canAcquireThisGrade();
        if (canAcquireThisGrade) {
            this.currentGrade += 1;
            if (this.isAcquire == false)
                this.isAcquire = true;
            return true;
        }
        else {
            return false;
        }
    }
    //---------------------------------------------------- Getter && Setters


    public ArrayList<SubSkill> getSubSkills() {
        return subSkills;
    }

    public void setSubSkills(ArrayList<SubSkill> subSkills) {
        this.subSkills = subSkills;
    }
}
