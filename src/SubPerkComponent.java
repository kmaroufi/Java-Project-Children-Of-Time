import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubPerkComponent<T> extends SubAbilityComponent<T> {

    private SubPerk relatedSubPerk;

    //---------------------------------------------------------------- Constructors

    public SubPerkComponent(SubSkill relatedSubSkill, SubAbilityComponentHandler subAbilityComponentHandler) {
        super(subAbilityComponentHandler);
        this.relatedSubPerk = relatedSubPerk;
    }

    //---------------------------------------------------------------- Functions

    public SubPerkComponent<T> clone() {
        SubPerkComponent<T> subPerkComponent = null;
        try {
            subPerkComponent = (SubPerkComponent<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // related SubPerk
        subPerkComponent.trieConditions = this.trieConditions.clone();
        subPerkComponent.listOfEffectedObjects = new ArrayList<>();
        subPerkComponent.mapOfEffectedProperties = new HashMap<>();
        return subPerkComponent;
    }

    public void removeEffect() {
        ArrayList<T> effectedObjects = this.listOfEffectedObjects;
        for (int i = 0; i < effectedObjects.size(); i++) {
            for (Property property : this.mapOfEffectedProperties.get(effectedObjects.get(i))) {
                property.removeEffect(effectedObjects.get(i));
            }
        }
        this.listOfEffectedObjects.clear();
        this.mapOfEffectedProperties.clear();
    }

    public void effect(Enemy enemy, Hero hero, Hero userHero) {
        ArrayList<T> effectedObjects = this.choosingEffectedObjects(enemy, hero);
        for (int i = 0; i < effectedObjects.size(); i++) {
            this.mapOfEffectedProperties.put((T) effectedObjects.get(i), this.trieConditions.findCorrectNode(effectedObjects.get(i)));
            for (Property property : this.trieConditions.findCorrectNode(effectedObjects.get(i))) {
                ArrayList<?> effectingObjects = this.choosingEffectingObjects(enemy, hero, property);
                property.effect(effectedObjects.get(i), effectingObjects);
            }
            this.listOfEffectedObjects.add(effectedObjects.get(i));
        }
    }

    public ArrayList<?> choosingEffectingObjects(Enemy enemy, Hero hero, Property property) {
        return property.getSelectingEffectingObjectsDetails().selectingObjects(enemy, hero);
//        if (property.getClassOfEffectingObjects() == ClassName.Hero) {
//            SelectingObjectsDetail<?> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
//            ArrayList effectingObjects = new ArrayList<>();
//            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
//                effectingObjects.addAll(selectingObjectsDetail.getClassOfObjects().getListOfObjects());
//                return effectingObjects;
//            }
//            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
//                effectingObjects.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
//            }
//            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
//                ArrayList objects = new ArrayList<>();
//                objects.addAll(selectingObjectsDetail.getClassOfObjects().getListOfObjects());
//                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
//                    Random random = new Random();
//                    int randomIndex = random.nextInt(objects.size());
//                    effectingObjects.add(objects.get(randomIndex));
//                    objects.remove(randomIndex);
//                }
//            }
//            if (selectingObjectsDetail.isRelatedToAttackDefend()) {
//                if (selectingObjectsDetail.getClassOfObjects() == ClassName.Hero) {
//                    if (selectingObjectsDetail.isHeroEffecting()) {
//                        effectingObjects.add((T) hero);
//                    }
//                } else if (selectingObjectsDetail.getClassOfObjects() == ClassName.Enemy) {
//                    if (selectingObjectsDetail.isEnemyEffecting()) {
//                        effectingObjects.add((T) enemy);
//                    }
//                }
//            }
//            return effectingObjects;
//        }
    }

    public ArrayList<T> choosingEffectedObjects(Enemy enemy, Hero hero) {       // in method dar moghe attack ya defend call mishavad;
        return this.selectingEffectedObjectsDetails.selectingObjects(enemy, hero);
//        SelectingObjectsDetail<T> selectingObjectsDetail = this.selectingEffectedObjectsDetails;
//        ArrayList<T> effectedObjects = new ArrayList<>();
//        if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
//            effectedObjects.addAll((ArrayList<? extends T>) this.classOfEffectedObjects.getListOfObjects());
//            return effectedObjects;
//        }
//        if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
//            effectedObjects.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
//        }
//        if (selectingObjectsDetail.isRandomObjectsSelecting()) {
//            ArrayList<T> objects = new ArrayList<>();
//            objects.addAll((ArrayList<? extends T>) this.classOfEffectedObjects.getListOfObjects());
//            for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
//                Random random = new Random();
//                int randomIndex = random.nextInt(objects.size());
//                effectedObjects.add(objects.get(randomIndex));
//                objects.remove(randomIndex);
//            }
//        }
//        if (selectingObjectsDetail.isRelatedToAttackDefend()) {
//            if (this.classOfEffectedObjects == ClassName.Hero) {
//                if (selectingObjectsDetail.isHeroEffected()) {
//                    effectedObjects.add((T) hero);
//                }
//            } else if (this.classOfEffectedObjects == ClassName.Enemy) {
//                if (selectingObjectsDetail.isEnemyEffected()) {
//                    effectedObjects.add((T) enemy);
//                }
//            }
//        }
//        return effectedObjects;
    }

    //---------------------------------------------------- Getter && Setters


    public SubPerk getRelatedSubPerk() {
        return relatedSubPerk;
    }

    public void setRelatedSubPerk(SubPerk relatedSubPerk) {
        this.relatedSubPerk = relatedSubPerk;
    }
}

