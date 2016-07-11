import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/8/2016.
 */
public class SubPerk extends SubAbility{

    private Perk relatedPerk;

    private String name;

    private ArrayList<SubPerkComponent<?>> subPerkComponents = new ArrayList<>();

    //---------------------------------------------------------- Constructors

    public SubPerk(SubAbilityHandler subAbilityHandler, ArrayList<SubPerkComponent<?>> subPerkComponents, String name) {
        super(subAbilityHandler);
        setSubPerkComponents(subPerkComponents);
        setName(name);
    }

    //---------------------------------------------------------- Functions

    public boolean canAcquireThisGrade() {

        if (this.nameOfNecessaryAbilities != null) {
            for (String nameOfAbility: this.nameOfNecessaryAbilities) {
                if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                    for (Skill skill: Hero.mapOfHeroes.get(relatedPerk.getOwnerName()).getSkills()) {
                        if (skill.getName().equals(nameOfAbility)) {
                            if (skill.getCurrentGrade() < this.gradeOfNecessaryAbilities.get(skill.getName()))
                                return false;
                            break;
                        }
                    }
                }
                if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                    for (Perk perk: Hero.mapOfHeroes.get(relatedPerk.getOwnerName()).getPerks()) {
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

    protected SubPerk clone() {
        SubPerk subPerk = null;
        try {
            subPerk = (SubPerk) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // related Perk
        ArrayList<SubPerkComponent<?>> newSubPerkComponents = new ArrayList<>();
        for (SubPerkComponent subPerkComponent: this.subPerkComponents) {
            SubPerkComponent<?> newSubPerkComponent = subPerkComponent.clone();
            newSubPerkComponent.setRelatedSubPerk(subPerk);
            newSubPerkComponents.add(newSubPerkComponent);
        }
        subPerk.setSubPerkComponents(newSubPerkComponents);
        return subPerk;
    }

    public boolean equals(Perk perk){
        if(perk.getName().equals(relatedPerk.getName())){
            return true;
        }
        return false;
    }

    public void removeEffect() {
        for (SubPerkComponent subPerkComponent: this.subPerkComponents) {
            subPerkComponent.removeEffect();
        }
    }

    public <T> void updatePerkEffect(Enemy enemy, Hero hero, T user) {
        removeEffect();
        for (SubPerkComponent subPerkComponent: this.subPerkComponents) {
            subPerkComponent.effect(enemy, hero, user);
        }
    }

    public void showDescription(){
        Display.printInEachLine(this.upgradeDescription);
    }

    //---------------------------------------------------------- Getter && Setters

    public Perk getRelatedPerk() {
        return relatedPerk;
    }

    public void setRelatedPerk(Perk relatedPerk) {
        this.relatedPerk = relatedPerk;
    }

    public ArrayList<SubPerkComponent<?>> getSubPerkComponents() {
        return subPerkComponents;
    }

    public void setSubPerkComponents(ArrayList<SubPerkComponent<?>> subPerkComponents) {
        this.subPerkComponents = subPerkComponents;
        for (SubPerkComponent subPerkComponent: this.subPerkComponents) {
            subPerkComponent.setRelatedSubPerk(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
