import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/8/2016.
 */
public class SubPerk extends SubAbility{

    private Perk relatedPerk;

    //---------------------------------------------------------- Constructors

    public SubPerk(SubAbilityHandler subAbilityHandler, Perk relatedPerk) {
        super(subAbilityHandler);
        setRelatedPerk(relatedPerk);
    }

    //---------------------------------------------------------- Functions

    public boolean canAcquireThisGrade() {

        if (this.nameOfNecessaryAbilities != null) {
            for (String nameOfAbility: (ArrayList<String>)this.nameOfNecessaryAbilities) {
                if (Ability.listOfAbilities.get(nameOfAbility).equals("skill")) {
                    for (Skill skill: Hero.mapOfHeroes.get(relatedPerk.getOwnerName()).getSkills()) {
                        if (skill.getName().equals(nameOfAbility)) {
                            if (skill.getCurrentGrade() < ((Map<String, Integer>)this.gradeOfNecessaryAbilities).get(skill.getName()))
                                return false;
                            break;
                        }
                    }
                }
                if (Ability.listOfAbilities.get(nameOfAbility).equals("perk")) {
                    for (Perk perk: Hero.mapOfHeroes.get(relatedPerk.getOwnerName()).getPerks()) {
                        if (perk.getName().equals(nameOfAbility)) {
                            if (perk.getCurrentGrade() < ((Map<String, Integer>)this.gradeOfNecessaryAbilities).get(perk.getName()))
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
        subPerk.mapOfConditionsByClass = new HashMap<>();
        subPerk.listOfEffectedObjectsByClass = new HashMap<>();
        subPerk.mapOfEffectedPropertiesByClass = new HashMap<>();
        for (ClassName className: subPerk.classOfEffectedObjects) {
            subPerk.mapOfConditionsByClass.put(className, this.mapOfConditionsByClass.get(className).clone());
            subPerk.listOfEffectedObjectsByClass.put(className, new ArrayList());
            subPerk.mapOfEffectedPropertiesByClass.put(className, new HashMap<>());
        }
        return subPerk;
    }

    public boolean equals(Perk perk){
        if(perk.getName().equals(relatedPerk.getName())){
            return true;
        }
        return false;
    }

    public void removeEffect() {
        for (ClassName className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.listOfEffectedObjectsByClass.get(className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                for (Property property: (ArrayList<Property>)this.mapOfEffectedPropertiesByClass.get(className).get(effectedObjects.get(i))) {
                    property.removeEffect(effectedObjects.get(i));
                }
            }
            this.listOfEffectedObjectsByClass.get(className).clear();
            this.mapOfEffectedPropertiesByClass.get(className).clear();
        }
    }

    public void updatePerkEffect(Enemy enemy, Hero hero, Hero userHero) {
        removeEffect();
        for (ClassName className: this.classOfEffectedObjects) {
            ArrayList<?> effectedObjects = this.choosingEffectedObjects(enemy, hero, className);
            for (int i = 0; i < effectedObjects.size(); i++) {
                this.mapOfEffectedPropertiesByClass.get(className).put(effectedObjects.get(i), this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i)));
                for (Property property: this.mapOfConditionsByClass.get(className).findCorrectNode(effectedObjects.get(i))) {
                    ArrayList<?> effectingObjects = this.choosingEffectingObjects(enemy, hero, property);
                    property.effect(effectedObjects.get(i), effectingObjects);
                }
                this.listOfEffectedObjectsByClass.get(className).add(effectedObjects.get(i));
            }
        }
    }

    public ArrayList<?> choosingEffectingObjects(Enemy enemy, Hero hero, Property property) {
        if (property.getClassOfEffectingObjects() == ClassName.Hero) {
            SelectingObjectsDetail<Hero> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
            ArrayList<Hero> effectingHeroes = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectingHeroes.addAll(GameEngine.listOfHeroes);
                return effectingHeroes;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectingHeroes.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    effectingHeroes.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isHeroEffecting()) {
                    effectingHeroes.add(hero);
                }
            }
            return effectingHeroes;
        } else if (property.getClassOfEffectingObjects() == ClassName.Enemy) {
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
            ArrayList<Enemy> effectingEnemies = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectingEnemies.addAll(GameEngine.listOfEnemies);
                return effectingEnemies;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectingEnemies.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Enemy> Enemies = new ArrayList<>();
                Enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(Enemies.size());
                    effectingEnemies.add(Enemies.get(randomIndex));
                    Enemies.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isEnemyEffecting()) {
                    effectingEnemies.add(enemy);
                }
            }
            return effectingEnemies;
        }
        return null;
    }

    public ArrayList<?> choosingEffectedObjects(Enemy enemy, Hero hero, ClassName classOfEffectedSoldiers) {       // in method dar moghe attack ya defend call mishavad;
        if ((classOfEffectedSoldiers == ClassName.Hero) && this.selectingEffectedObjectsDetails.containsKey(ClassName.Hero)) {
            SelectingObjectsDetail<Hero> selectingObjectsDetail = this.selectingEffectedObjectsDetails.get(ClassName.Hero);
            ArrayList<Hero> effectedHeroes = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectedHeroes.addAll(GameEngine.listOfHeroes);
                return effectedHeroes;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectedHeroes.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Hero> heroes = new ArrayList<Hero>();
                heroes.addAll(GameEngine.listOfHeroes);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(heroes.size());
                    effectedHeroes.add(heroes.get(randomIndex));
                    heroes.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isHeroEffected()) {
                    effectedHeroes.add(hero);
                }
            }
            return effectedHeroes;
        }
        else if ((classOfEffectedSoldiers == ClassName.Enemy) && this.selectingEffectedObjectsDetails.containsKey(ClassName.Enemy)) {
            SelectingObjectsDetail<Enemy> selectingObjectsDetail = this.selectingEffectedObjectsDetails.get(ClassName.Enemy);
            ArrayList<Enemy> effectedEnemies = new ArrayList<>();
            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
                effectedEnemies.addAll(GameEngine.listOfEnemies);
                return effectedEnemies;
            }
            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
                effectedEnemies.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
            }
            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
                ArrayList<Enemy> Enemies = new ArrayList<>();
                Enemies.addAll(GameEngine.listOfEnemies);
                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(Enemies.size());
                    effectedEnemies.add(Enemies.get(randomIndex));
                    Enemies.remove(randomIndex);
                }
            }
            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
                if (selectingObjectsDetail.isEnemyEffected()) {
                    effectedEnemies.add(enemy);
                }
            }
            return effectedEnemies;
        }
        return null;
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

}
