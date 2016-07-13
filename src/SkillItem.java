import java.util.ArrayList;

/**
 * Created by asus-pc on 7/12/2016.
 */
public class SkillItem extends Item {
    private Skill skill;
    private int maximumTimeOfUsed;
    private int remainingTimeOfUsed;

    //--------------------------------------------------- Constructors

    public SkillItem(ItemHandler itemHandler, Skill skill, int maximumTimeOfUsed) {
        super(itemHandler);
        this.skill = skill;
        this.maximumTimeOfUsed = maximumTimeOfUsed;
        this.remainingTimeOfUsed = maximumTimeOfUsed;
    }


    //--------------------------------------------------- Functions

    public SkillItem clone(){
        SkillItem skillItem = null;
        try {
            skillItem = (SkillItem) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        skillItem.setSkill(this.skill.clone());
        return skillItem;
    }

    public void useItem() {
        this.skill.useSkill(Hero.mapOfHeroes.get(this.ownerName));
        this.remainingTimeOfUsed--;
        if (this.remainingTimeOfUsed == 0) {
            Hero.mapOfHeroes.get(this.ownerName).removeItem(this);
            GameEngine.listOfRemovedSkillItemsButHaveEffect.add(this);
        }
    }

    public void removeEffect() {
        this.skill.removeEffect();
    }

    public double getRequiredEnergyPoint() {
        return this.skill.getRequiredEnergyPoint();
    }

    public double getRequiredMagicPoint() {
        return this.skill.getRequiredMagicPoint();
    }

    public double getCooldown() {
        return this.skill.getCooldown();
    }

    public int getRemainingCooldown() {
        return this.skill.getRemainingCooldown();
    }

    public double getWorth() {
        return this.worth * this.remainingTimeOfUsed / (double) this.maximumTimeOfUsed;
    }

    //--------------------------------------------------- Getter && Setters


    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getMaximumTimeOfUsed() {
        return maximumTimeOfUsed;
    }

    public void setMaximumTimeOfUsed(int maximumTimeOfUsed) {
        this.maximumTimeOfUsed = maximumTimeOfUsed;
    }

    public int getRemainingTimeOfUsed() {
        return remainingTimeOfUsed;
    }

    public void setRemainingTimeOfUsed(int remainingTimeOfUsed) {
        this.remainingTimeOfUsed = remainingTimeOfUsed;
    }
}
