import GUI.Display;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Skill extends Ability implements Cloneable{
    public static Map<String, Skill> listOfSkills = new HashMap<String, Skill>();

    private Tree<SubSkill> subSkills;

    private Tree.Node<SubSkill> currentNode;

    //---------------------------------------------------------------- Constructors

    public Skill(AbilityHandler abilityHandler, Tree<SubSkill> subSkills) {
        super(abilityHandler);
        setSubSkills(subSkills);
        setCurrentNode(subSkills.getRoot());
    }

    //---------------------------------------------------------------- Functions

    protected Skill clone() {
        Skill skill = null;
        try {
            skill = (Skill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Tree<SubSkill> newSubSkills = this.subSkills.clone();
        for (SubSkill subSkill: newSubSkills.getDataSet()) {
            subSkill.setRelatedSkill(skill);
        }
        skill.currentNode = newSubSkills.getRoot();
        return skill;
    }

    public void useSkill(Hero userHero) {
//        this.subSkills.get(currentGrade - 1).useSkill(userHero);
        this.currentNode.getData().useSkill(userHero);
    }

    public void removeEffect() {
//        this.subSkills.get(currentGrade - 1).removeEffect();
        this.currentNode.getData().removeEffect();
    }

    public void reduceTime(String typeOfTime) {
//        this.subSkills.get(currentGrade - 1).reduceTime(typeOfTime);
        this.currentNode.getData().reduceTime(typeOfTime);
    }

    public double getRequiredEnergyPoint() {
//        return this.subSkills.get(this.currentGrade - 1).getRequiredEnergyPoint();
        return this.currentNode.getData().getRequiredEnergyPoint();
    }

    public double getRequiredMagicPoint() {
//        return this.subSkills.get(this.currentGrade - 1).getRequiredMagicPoint();
        return this.currentNode.getData().getRequiredMagicPoint();
    }

    public double getCooldown() {
//        return this.subSkills.get(this.currentGrade - 1).getCooldown();
        return this.currentNode.getData().getCooldown();
    }

    public int getRemainingCooldown() {
//        return this.subSkills.get(this.currentGrade - 1).getRemainingCooldown();
        return this.currentNode.getData().getRemainingCooldown();
    }

    public void setRemainingCooldown(int cooldown) {
        this.currentNode.getData().setCooldown(cooldown);
    }

    public boolean isGlobal() {
//        return this.subSkills.get(this.currentGrade - 1).isGlobal();
        return this.currentNode.getData().isGlobal();
    }

//    public int getCostOfUpgrade() {
//        return this.subSkills.get(this.currentGrade).getCostOfUpgrade();
//    }
//
//    public int getCostOfUpgrade(int grade) {
//        if (grade > this.numberOfGrades) {
//            Display.printInEachLine("Out of bound grade!");
//            return -1;
//        }
//        return this.subSkills.get(grade - 1).getCostOfUpgrade();
//    }

    public ArrayList<SubSkill> getNextGradeSubSkills() {
        ArrayList<SubSkill> nextGradeSubSkills = new ArrayList<>();
        for (Tree.Node<SubSkill> node: this.currentNode.getChildren()) {
            nextGradeSubSkills.add(node.getData());
        }
        return nextGradeSubSkills;
    }

    public void showDescription(){
        Display.printInEachLine("Skill description: " + this.getDescription());
        Display.printInEachLine("Current grade description: " + this.currentNode.getData().getUpgradeDescription());
    }

    public String getThisGradeDescription(){
//        return this.subSkills.get(this.currentGrade - 1).getUpgradeDescription();
        return this.currentNode.getData().getUpgradeDescription();
    }

    public boolean equals(Skill skill){
        if(this.name.equals(skill.getName())){
            return true;
        }
        return false;
    }

    public boolean upgrade(Player player, SubSkill subSkill) {
        boolean canAcquireThisGrade = subSkill.canAcquireThisGrade();
        if (canAcquireThisGrade) {
            this.currentGrade += 1;
            if (this.isAcquire == false)
                this.isAcquire = true;
            for (Tree.Node<SubSkill> node: this.currentNode.getChildren()) {
                if (subSkill == node.getData()) {
                    this.currentNode = node;
                    break;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
    //---------------------------------------------------- Getter && Setters


    public Tree<SubSkill> getSubSkills() {
        return subSkills;
    }

    public void setSubSkills(Tree<SubSkill> subSkills) {
        this.subSkills = subSkills;
        for (SubSkill subSkill: this.subSkills.getDataSet()) {
            subSkill.setRelatedSkill(this);
        }
    }

    public Tree.Node<SubSkill> getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Tree.Node<SubSkill> currentNode) {
        this.currentNode = currentNode;
    }
}
