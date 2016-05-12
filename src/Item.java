import java.util.ArrayList;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Item {

    private String name;
    private String owner; // is this necessary?!
    private ArrayList<String> effectedHero;
    private int size;
    private boolean isGlobal;
    private boolean hasEffectedOnEnemy;
    private int time;
    private int remainingTime;
    private boolean isInstantlyUsed;
    private int maximumTimeOfUsed;
    private int remainingTimeOfUsed;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;
    private int cooldown;

    private ArrayList<Property> properties;

    public void isActivated() {
        //TODO
    }

    public void useItem() {
        //TODO
    }
    //--------------------------------------------------- Getter && Setters


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

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getEffectedHero() {
        return effectedHero;
    }

    public void setEffectedHero(ArrayList<String> effectedHero) {
        this.effectedHero = effectedHero;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

    public boolean isHasEffectedOnEnemy() {
        return hasEffectedOnEnemy;
    }

    public void setHasEffectedOnEnemy(boolean hasEffectedOnEnemy) {
        this.hasEffectedOnEnemy = hasEffectedOnEnemy;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public boolean isInstantlyUsed() {
        return isInstantlyUsed;
    }

    public void setInstantlyUsed(boolean instantlyUsed) {
        isInstantlyUsed = instantlyUsed;
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

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

}
