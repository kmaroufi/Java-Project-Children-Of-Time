import java.util.ArrayList;

/**
 * Created by asus-pc on 5/5/2016.
 */
public class PerkMode {
    private ArrayList<Property> properties;
    private int nonTargetedEnemy; //it isn't clear that this field has int type!

    public <T> void effect(T relatedSoldiers, Hero owner) {
        for (Property property: this.properties) {
            property.effect(relatedSoldiers, owner);
        }
    }

    public <T> void removeEffect(T relatedSoldiers) {
        for (Property property: this.properties) {
            property.removeEffect(relatedSoldiers);
        }
    }
}
