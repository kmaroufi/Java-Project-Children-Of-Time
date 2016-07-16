package AbilityPackage;
import SoldierPackage.*;
import Structure.*;
import java.util.*;

/**
 * Created by asus-pc on 7/9/2016.
 */
public class SubPerkComponent<T> extends SubAbilityComponent<T> implements Cloneable {

    private SubPerk relatedSubPerk;

    //---------------------------------------------------------------- Constructors

    public SubPerkComponent(SubAbilityComponentHandler subAbilityComponentHandler) {
        super(subAbilityComponentHandler);
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

    public <E extends Soldier> void effect(Enemy enemy, Hero hero, E user) {
        ArrayList<T> effectedObjects = this.selectingEffectedObjectsDetails.selectingObjects(enemy, hero, (T) user);
        for (int i = 0; i < effectedObjects.size(); i++) {
            this.mapOfEffectedProperties.put((T) effectedObjects.get(i), this.trieConditions.findCorrectNode(effectedObjects.get(i)));
            for (Property property : this.trieConditions.findCorrectNode(effectedObjects.get(i))) {
                ArrayList<?> effectingObjects = property.getSelectingEffectingObjectsDetails().selectingObjects(enemy, hero, user);
                property.effect(effectedObjects.get(i), effectingObjects);
            }
            this.listOfEffectedObjects.add(effectedObjects.get(i));
        }
    }

    //---------------------------------------------------- Getter && Setters


    public SubPerk getRelatedSubPerk() {
        return relatedSubPerk;
    }

    public void setRelatedSubPerk(SubPerk relatedSubPerk) {
        this.relatedSubPerk = relatedSubPerk;
    }
}

