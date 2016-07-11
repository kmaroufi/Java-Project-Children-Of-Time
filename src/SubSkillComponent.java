import GUI.Display;

import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubSkillComponent<T> extends SubAbilityComponent<T> {

    private SubSkill relatedSubSkill;

    private Map<T, Time> mapOfRemainTimeOfEffect = new HashMap<>(); // Key = className, Value = { Key = object, Value = Time of how much this effect will be remain

    //---------------------------------------------------------------- Constructors

    public SubSkillComponent(SubSkill relatedSubSkill, SubAbilityComponentHandler subAbilityComponentHandler) {
        super(subAbilityComponentHandler);
        this.relatedSubSkill = relatedSubSkill;
    }

    //---------------------------------------------------------------- Functions

    public SubSkillComponent<T> clone() {
        SubSkillComponent<T> subSkillComponent = null;
        try {
            subSkillComponent = (SubSkillComponent<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        // set relatedSubSkill nabayad faramoosh she.
        subSkillComponent.mapOfRemainTimeOfEffect = new HashMap<>();
        subSkillComponent.trieConditions = this.trieConditions.clone();
        subSkillComponent.listOfEffectedObjects= new ArrayList<>();
        subSkillComponent.mapOfEffectedProperties = new HashMap<>();
        return subSkillComponent;
    }       // Creates A Copy of This Object (Skill)

    public boolean effect(Hero userHero) {

        boolean isEffectedOnAtLeastOnObject = false;

        ArrayList<T> effectedObjects = (ArrayList<T>) this.choosingEffectedObjects();
        for (int i = 0; i < effectedObjects.size(); i++) {
            if ((this.listOfEffectedObjects.contains(effectedObjects.get(i))) && (relatedSubSkill.isCanStackUp() == false)) {
                continue;
            }
            isEffectedOnAtLeastOnObject = true;
            this.mapOfEffectedProperties.put(effectedObjects.get(i), this.trieConditions.findCorrectNode(effectedObjects.get(i)));
            for (Property property : this.trieConditions.findCorrectNode(effectedObjects.get(i))) {
                ArrayList<?> effectingObjects = this.choosingEffectingObjects(property);
                double effectValue = property.effect(effectedObjects.get(i), effectingObjects);
                Display.printInEachLine(userHero.getName() + " just used " + this.relatedSubSkill.getRelatedSkill().getName() + " on " + effectedObjects.get(i).toString() + " and effecting on " + property.getName() + " with " + Math.abs(effectValue));
            }
            if (this.listOfEffectedObjects.contains(effectedObjects.get(i)) == false) {
                this.listOfEffectedObjects.add(effectedObjects.get(i));
            }
            this.mapOfRemainTimeOfEffect.put(effectedObjects.get(i), new Time(relatedSubSkill.getTimeOfEffecting()));
        }

        if (isEffectedOnAtLeastOnObject == false) {
            return false;
        }

        return true;
    }

    public void removeEffect() {
        ArrayList<T> effectedObjects = this.listOfEffectedObjects;
        ArrayList<T> removedEffectedObjects = new ArrayList<>();
        for (int i = 0; i < effectedObjects.size(); i++) {
            if (this.mapOfRemainTimeOfEffect.get(effectedObjects.get(i)).isTimePassed()) {
                for (Property<T, ?> property : this.mapOfEffectedProperties.get(effectedObjects.get(i))) {
                    property.removeEffect(effectedObjects.get(i));
                }
                removedEffectedObjects.add(effectedObjects.get(i));
                this.mapOfRemainTimeOfEffect.remove(effectedObjects.get(i));
                this.mapOfEffectedProperties.remove(effectedObjects.get(i));
            }
        }
        this.listOfEffectedObjects.removeAll(removedEffectedObjects);
    }

    public void reduceTime(String typeOfTime) {
        ArrayList<T> effectedObjects = this.listOfEffectedObjects;
        for (int i = 0; i < effectedObjects.size(); i++) {
            this.mapOfRemainTimeOfEffect.get(effectedObjects.get(i)).reduceTime(typeOfTime);
        }
    }

    private ArrayList<T> choosingEffectedObjects() {
        return this.selectingEffectedObjectsDetails.selectingObjects();
//        SelectingObjectsDetail<T> selectingObjectsDetail = this.selectingEffectedObjectsDetails;
//        ArrayList<T> effectedObjects = new ArrayList<>();
//        if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
//            effectedObjects.addAll((ArrayList<? extends T>) this.classOfEffectedObjects.listOfObjects);
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
//        if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
//            int counter = selectingObjectsDetail.getNumberOfSelectedObjectsByPlayer();
//            while (counter != 0) {
//                if (effectedObjects.size() == this.classOfEffectedObjects.getListOfObjects().size())
//                    break;
//                GUI.Display.printf("Please enter your targeted " + this.classOfEffectedObjects.getName() + ": ");
//                String command = GUI.Display.getString();
//                String[] nameOfObjects = command.split(" ");
//                for (String nameOfObject : nameOfObjects) {
//                    boolean isTargetRecognized = false;
//                    for (T object : (ArrayList<T>) this.classOfEffectedObjects.getListOfObjects()) {
//                        if (nameOfObject.equalsIgnoreCase(object.toString())) {
//                            if (!effectedObjects.contains(object)) {
//                                effectedObjects.add(object);
//                                counter--;
//                                isTargetRecognized = true;
//                            } else {
//                                GUI.Display.printInEachLine(object.toString() + " was selected in the past! Please enter another " + this.classOfEffectedObjects.getName());
//                            }
//                        }
//                    }
//                    if (isTargetRecognized == false) {
//                        GUI.Display.printInEachLine(nameOfObject + " was not recognized. Please enter another " + this.classOfEffectedObjects.getName());
//                    }
//                }
//            }
//        }
//        return effectedObjects;
    }

    private ArrayList<?> choosingEffectingObjects(Property property) {
        return property.getSelectingEffectingObjectsDetails().selectingObjects();
//        if (property.getClassOfEffectingObjects() == ClassName.Hero) {
//            SelectingObjectsDetail<Hero> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
//            ArrayList<Hero> effectingHeroes = new ArrayList<>();
//            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
//                effectingHeroes.addAll(GameEngine.listOfHeroes);
//                return effectingHeroes;
//            }
//            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
//                effectingHeroes.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
//            }
//            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
//                ArrayList<Hero> heroes = new ArrayList<Hero>();
//                heroes.addAll(GameEngine.listOfHeroes);
//                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
//                    Random random = new Random();
//                    int randomIndex = random.nextInt(heroes.size());
//                    effectingHeroes.add(heroes.get(randomIndex));
//                    heroes.remove(randomIndex);
//                }
//            }
//            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
//                // TODO
//            }
//            return effectingHeroes;
//        } else if (property.getClassOfEffectingObjects() == ClassName.Enemy) {
//            SelectingObjectsDetail<Enemy> selectingObjectsDetail = property.getSelectingEffectingObjectsDetails();
//            ArrayList<Enemy> effectingEnemies = new ArrayList<>();
//            if (selectingObjectsDetail.isAllRelatedObjectsInvolved()) {
//                effectingEnemies.addAll(GameEngine.listOfEnemies);
//                return effectingEnemies;
//            }
//            if (selectingObjectsDetail.isObjectsWereSelectedByDefault()) {
//                effectingEnemies.addAll(selectingObjectsDetail.getSelectedObjectsByDefault());
//            }
//            if (selectingObjectsDetail.isRandomObjectsSelecting()) {
//                ArrayList<Enemy> Enemies = new ArrayList<>();
//                Enemies.addAll(GameEngine.listOfEnemies);
//                for (int i = 0; i < selectingObjectsDetail.getNumberOfRandomSelectedObjects(); i++) {
//                    Random random = new Random();
//                    int randomIndex = random.nextInt(Enemies.size());
//                    effectingEnemies.add(Enemies.get(randomIndex));
//                    Enemies.remove(randomIndex);
//                }
//            }
//            if (selectingObjectsDetail.isSelectedObjectsDependsOnPlayer()) {
//                // TODO
//            }
//            return effectingEnemies;
//        }
//        return null;
    }

    //---------------------------------------------------- Getter && Setters


    public SubSkill getRelatedSubSkill() {
        return relatedSubSkill;
    }

    public void setRelatedSubSkill(SubSkill relatedSubSkill) {
        this.relatedSubSkill = relatedSubSkill;
    }

    public Map<T, Time> getMapOfRemainTimeOfEffect() {
        return mapOfRemainTimeOfEffect;
    }

    public void setMapOfRemainTimeOfEffect(Map<T, Time> mapOfRemainTimeOfEffect) {
        this.mapOfRemainTimeOfEffect = mapOfRemainTimeOfEffect;
    }
}
