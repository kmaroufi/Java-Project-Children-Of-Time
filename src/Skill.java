import GUI.Display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public double getRequiredEnergyPoint() {
        return this.subSkills.get(this.currentGrade - 1).getRequiredEnergyPoint();
    }

    public double getRequiredMagicPoint() {
        return this.subSkills.get(this.currentGrade - 1).getRequiredMagicPoint();
    }

    public double getCooldown() {
        return this.subSkills.get(this.currentGrade - 1).getCooldown();
    }

    public int getRemainingCooldown() {
        return this.subSkills.get(this.currentGrade - 1).getRemainingCooldown();
    }

    public boolean isGlobal() {
        return this.subSkills.get(this.currentGrade - 1).isGlobal();
    }

    public int getCostOfUpgrade() {
        return this.subSkills.get(this.currentGrade).getCostOfUpgrade();
    }

    public int getCostOfUpgrade(int grade) {
        if (grade > this.numberOfGrades) {
            Display.printInEachLine("Out of bound grade!");
            return -1;
        }
        return this.subSkills.get(grade - 1).getCostOfUpgrade();
    }

    public void showDescription(){
        Display.printInEachLine(this.getDescription());
        for (SubSkill subSkill: this.subSkills) {
            Display.printInEachLine(subSkill.getUpgradeDescription());
        }
    }

    public String getThisGradeDescription(){
        return (this.subSkills.get(this.currentGrade - 1).getUpgradeDescription());
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
