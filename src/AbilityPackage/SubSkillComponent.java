package AbilityPackage;
import GUI.*;
import SoldierPackage.*;
import Structure.*;

import java.io.Serializable;
import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubSkillComponent<T> extends SubAbilityComponent<T> implements Cloneable, Serializable {

    private SubSkill relatedSubSkill;

    private Map<T, Time> mapOfRemainTimeOfEffect = new HashMap<>(); // Key = className, Value = { Key = object, Value = Time of how much this effect will be remain

    //---------------------------------------------------------------- Constructors

    public SubSkillComponent(SubAbilityComponentHandler subAbilityComponentHandler) {
        super(subAbilityComponentHandler);

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

    public <E extends Soldier> boolean effect(E user) {

        boolean isEffectedOnAtLeastOnObject = false;

        ArrayList<T> effectedObjects = this.selectingEffectedObjectsDetails.selectingObjects(null, null, (T)user);
        for (int i = 0; i < effectedObjects.size(); i++) {
            if ((this.listOfEffectedObjects.contains(effectedObjects.get(i))) && (relatedSubSkill.isCanStackUp() == false)) {
                continue;
            }
            isEffectedOnAtLeastOnObject = true;
            this.mapOfEffectedProperties.put(effectedObjects.get(i), this.trieConditions.findCorrectNode(effectedObjects.get(i)));
            for (Property property : this.trieConditions.findCorrectNode(effectedObjects.get(i))) {
                ArrayList<?> effectingObjects = property.getSelectingEffectingObjectsDetails().selectingObjects(null, null, user);
                double effectValue = property.effect(effectedObjects.get(i), effectingObjects);
                Display.printInEachLine(user.getName() + " just used " + this.relatedSubSkill.getRelatedSkill().getName() + " on " + effectedObjects.get(i).toString() + " and effecting on " + property.getName() + " with " + Math.abs(effectValue));
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

    public int numberOfEffectedObjects() {
        return this.listOfEffectedObjects.size();
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
