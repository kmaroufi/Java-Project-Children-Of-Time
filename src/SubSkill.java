import GUI.Display;

import java.util.*;

/**
 * Created by asus-pc on 7/7/2016.
 */
public class SubSkill extends SubAbility implements Cloneable{
    private Skill relatedSkill;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private int cooldown;
    private int remainingCooldown;
    private boolean canStackUp;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;

    private String name;

    private ArrayList<SubSkillComponent<?>> subSkillComponents = new ArrayList<>();

    //---------------------------------------------------------------- Constructors

    public SubSkill(SubSkillHandler subSkillHandler, SubAbilityHandler subAbilityHandler) {
        super(subAbilityHandler);
        setRepeated(subSkillHandler.isRepeated());
        setTimeOfEffecting(subSkillHandler.getTimeOfEffecting());
        setCooldown(subSkillHandler.getCooldown());
        setRemainingCooldown(0);
        setCanStackUp(subSkillHandler.isCanStackUp());
        setRequiredEnergyPoint(subSkillHandler.getRequiredEnergyPoint());
        setRequiredMagicPoint(subSkillHandler.getRequiredMagicPoint());
        setSubSkillComponents(subSkillHandler.getSubSkillComponents());
        setName(subSkillHandler.getName());
    }

    //---------------------------------------------------------------- Functions

    protected SubSkill clone() {
        SubSkill subSkill = null;
        try {
            subSkill = (SubSkill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // set relatedSkill nabayad faramoosh she.
        ArrayList<SubSkillComponent<?>> newSubSkillComponents = new ArrayList<>();
        for (SubSkillComponent subSkillComponent: this.subSkillComponents) {
            SubSkillComponent<?> newSubSkillComponent = subSkillComponent.clone();
            newSubSkillComponent.setRelatedSubSkill(subSkill);
            newSubSkillComponents.add(newSubSkillComponent);
        }
        subSkill.setSubSkillComponents(newSubSkillComponents);
        return subSkill;
    }       // Creates A Copy of This Object (Skill)

    public void useSkill(Hero userHero) {

        if (this.remainingCooldown != 0) {
            return;
        }

        boolean isEffectedOnAtLeastOnObject = false;

        for (SubSkillComponent subSkillComponent: this.subSkillComponents) {
            isEffectedOnAtLeastOnObject = isEffectedOnAtLeastOnObject | subSkillComponent.effect(userHero);
        }

        if (isEffectedOnAtLeastOnObject == false) {
            return;
        }

        this.remainingCooldown = this.cooldown;
        userHero.setCurrentEnergyPoint(userHero.getCurrentEnergyPoint() - this.requiredEnergyPoint);
        userHero.setCurrentMagic(userHero.getCurrentMagic() - this.requiredMagicPoint);
    }

    public void removeEffect() {
        for (SubSkillComponent subSkillComponent: this.subSkillComponents) {
            subSkillComponent.removeEffect();
        }
    }

    public void reduceTime(String typeOfTime) {
        for (SubSkillComponent subSkillComponent: this.subSkillComponents) {
            subSkillComponent.reduceTime(typeOfTime);
        }
    }

    public boolean canAcquireThisGrade() {

        if (this.nameOfNecessaryAbilities != null) {
            for (String nameOfAbility: this.nameOfNecessaryAbilities) {
                if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                    for (Skill skill: Hero.mapOfHeroes.get(relatedSkill.getOwnerName()).getSkills()) {
                        if (skill.getName().equals(nameOfAbility)) {
                            if (skill.getCurrentGrade() < this.gradeOfNecessaryAbilities.get(skill.getName()))
                                return false;
                            break;
                        }
                    }
                }
                if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                    for (Perk perk: Hero.mapOfHeroes.get(relatedSkill.getOwnerName()).getPerks()) {
                        if (perk.getName().equals(nameOfAbility)) {
                            if (perk.getCurrentGrade() < this.gradeOfNecessaryAbilities.get(perk.getName()))
                                return false;
                            break;
                        }
                    }
                }
            }
        }

        return true;
    }

    public void showDescription(){
        Display.printInEachLine(this.upgradeDescription);
    }

    //---------------------------------------------------- Getter && Setters


    public Skill getRelatedSkill() {
        return relatedSkill;
    }

    public void setRelatedSkill(Skill relatedSkill) {
        this.relatedSkill = relatedSkill;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public void setTimeOfEffecting(Time timeOfEffecting) {
        this.timeOfEffecting = timeOfEffecting;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public void setCanStackUp(boolean canStackUp) {
        this.canStackUp = canStackUp;
    }

    public int getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public void setRequiredEnergyPoint(int requiredEnergyPoint) {
        this.requiredEnergyPoint = requiredEnergyPoint;
    }

    public int getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public void setRequiredMagicPoint(int requiredMagicPoint) {
        this.requiredMagicPoint = requiredMagicPoint;
    }

    public ArrayList<SubSkillComponent<?>> getSubSkillComponents() {
        return subSkillComponents;
    }

    public void setSubSkillComponents(ArrayList<SubSkillComponent<?>> subSkillComponents) {
        this.subSkillComponents = subSkillComponents;
        for (SubSkillComponent subSkillComponent: this.subSkillComponents) {
            subSkillComponent.setRelatedSubSkill(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
