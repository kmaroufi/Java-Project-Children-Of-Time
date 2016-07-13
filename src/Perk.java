import GUI.Display;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class Perk extends Ability implements Cloneable{
    public static Map<String, Perk> listOfPerks = new HashMap<>();

    private Tree<SubPerk> subPerks;

    private Tree.Node<SubPerk> currentNode;

    private TimeOfCheck timeOfCheck; // Can equals "duringAttackDefend" and "eachActivity"

    public enum TimeOfCheck {
        duringAttack, duringDefend, duringAttackDefend, eachActivity
    }

    //---------------------------------------------------------- Constructors

    public Perk(AbilityHandler abilityHandler, TimeOfCheck timeOfCheck, Tree<SubPerk> subPerks) {
        super(abilityHandler);
        setTimeOfCheck(timeOfCheck);
        setSubPerks(subPerks);
        setCurrentNode(this.subPerks.getRoot());
    }

    //---------------------------------------------------------- Functions

    public boolean upgrade(Player player, SubPerk subPerk) {
        boolean canAcquireThisGrade = subPerk.canAcquireThisGrade();
        if (canAcquireThisGrade) {
            this.currentGrade += 1;
            if (this.isAcquire == false) {
                this.isAcquire = true;
            }

            if (this.timeOfCheck != TimeOfCheck.duringAttackDefend) {
                if (this.currentGrade != 1) {
                    this.removeEffect();
                }
                for (Tree.Node<SubPerk> node: this.currentNode.getChildren()) {
                    if (subPerk == node.getData()) {
                        this.currentNode = node;
                        break;
                    }
                }
                this.updatePerkEffect(Hero.mapOfHeroes.get(this.ownerName));
            }
            else {
                for (Tree.Node<SubPerk> node: this.currentNode.getChildren()) {
                    if (subPerk == node.getData()) {
                        this.currentNode = node;
                        break;
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    protected Perk clone() {
        Perk perk = null;
        try {
            perk = (Perk) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Tree<SubPerk> newSubPerks = this.subPerks.clone();
        for (SubPerk subPerk: newSubPerks.getDataSet()) {
            subPerk.setRelatedPerk(perk);
        }
        perk.currentNode = newSubPerks.getRoot();
        return perk;
    }

    public boolean equals(Perk perk){
        if(perk.getName().equals(this.name)){
            return true;
        }
        return false;
    }

    public void removeEffect() {
//        this.subPerks.get(this.currentGrade - 1).removeEffect();
        this.currentNode.getData().removeEffect();
    }

    public <T> void updatePerkEffect(T user) {
        this.updatePerkEffect(null, null, user);
    }

    public <T> void updatePerkEffect(Enemy enemy, Hero hero, T user) {
//        this.subPerks.get(this.currentGrade - 1).updatePerkEffect(enemy, hero, userHero);
        this.currentNode.getData().updatePerkEffect(enemy, hero, user);
    }

    public void showDescription(){
        Display.printInEachLine("Perk description: " + this.getDescription());
        Display.printInEachLine("Current grade description: " + this.currentNode.getData().getUpgradeDescription());
    }

    public String getThisGradeDescription(){
        return this.currentNode.getData().getUpgradeDescription();
    }

    public ArrayList<SubPerk> getNextGradeSubPerks() {
        ArrayList<SubPerk> nextGradeSubPerks = new ArrayList<>();
        for (Tree.Node<SubPerk> node: this.currentNode.getChildren()) {
            nextGradeSubPerks.add(node.getData());
        }
        return nextGradeSubPerks;
    }

    //---------------------------------------------------------- Getter && Setters

    public TimeOfCheck getTimeOfCheck() {
        return timeOfCheck;
    }

    public void setTimeOfCheck(TimeOfCheck timeOfCheck) {
        this.timeOfCheck = timeOfCheck;
    }

    public Tree<SubPerk> getSubPerks() {
        return subPerks;
    }

    public void setSubPerks(Tree<SubPerk> subPerks) {
        this.subPerks = subPerks;
//        for (SubPerk subPerk: this.subPerks.getDataSet()) {
//            subPerk.setRelatedPerk(this);
//        }
    }

    public Tree.Node<SubPerk> getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Tree.Node<SubPerk> currentNode) {
        this.currentNode = currentNode;
    }
}
