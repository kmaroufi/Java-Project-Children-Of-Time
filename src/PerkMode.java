import java.util.ArrayList;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class PerkMode<E> {
    private ArrayList<Property<E>> properties;
    private int nonTargetedEnemy; //it isn't clear that this field has int type!


    public PerkMode(ArrayList<Property<E>> properties, int nonTargetedEnemy) {
        this.properties = properties;
        this.nonTargetedEnemy = nonTargetedEnemy;
    }

    public ArrayList<Property<E>> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property<E>> properties) {
        this.properties = properties;
    }

    public int getNonTargetedEnemy() {
        return nonTargetedEnemy;
    }

    public void setNonTargetedEnemy(int nonTargetedEnemy) {
        this.nonTargetedEnemy = nonTargetedEnemy;
    }

    public <T> void effect(T relatedSoldiers, Hero owner, Hero userHero) {
        for (Property property: this.properties) {
            property.effect(relatedSoldiers, owner, userHero);
        }
    }

    public <T> void removeEffect(T relatedSoldiers) {
        for (Property property: this.properties) {
            property.removeEffect(relatedSoldiers);
        }
    }
}
