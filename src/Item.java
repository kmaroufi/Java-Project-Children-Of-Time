import java.util.ArrayList;
import java.util.Random;

/**
 * Created by asus-pc on 5/6/2016.
 */
public class Item {

    private String name;
    private String ownerName;
    private int size;
    private boolean isInstantlyUsed;
    private int maximumTimeOfUsed;
    private int remainingTimeOfUsed;
    private int requiredEnergyPoint;
    private int requiredMagicPoint;
    private String Description;
    private ArrayList<Hero> effectedSoldiers = new ArrayList<>();
    private ArrayList<Hero> relatedSoldiers = new ArrayList<>();
    private boolean isDependsRelatedSoldiersSelectingOnPlayer;
    private int numberOfRelatedSoldiers;
    private boolean isRandomSoldierSelecting;
    private ArrayList<Property<Hero>> properties;
    private boolean isTemporary;
    private int cooldown;
    private int remainingCooldown;
    private double worth;
    private boolean isDisappearAfterFullUse;
    private boolean isAlive;

    //--------------------------------------------------- Constructors
    public Item(){}

    public Item(String name, String ownerName, int size, boolean isInstantlyUsed, int maximumTimeOfUsed, int requiredEnergyPoint, int requiredMagicPoint, String description, boolean isDependsRelatedSoldiersSelectingOnPlayer, int numberOfRelatedSoldiers, boolean isRandomSoldierSelecting, ArrayList<Property<Hero>> properties, boolean isTemporary, int cooldown, boolean isDisappearAfterFullUse) {
        this.name = name;
        this.ownerName = ownerName;
        this.size = size;
        this.isInstantlyUsed = isInstantlyUsed;
        this.maximumTimeOfUsed = maximumTimeOfUsed;
        this.requiredEnergyPoint = requiredEnergyPoint;
        this.requiredMagicPoint = requiredMagicPoint;
        Description = description;
        this.isDependsRelatedSoldiersSelectingOnPlayer = isDependsRelatedSoldiersSelectingOnPlayer;
        this.numberOfRelatedSoldiers = numberOfRelatedSoldiers;
        this.isRandomSoldierSelecting = isRandomSoldierSelecting;
        this.properties = properties;
        this.isTemporary = isTemporary;
        this.cooldown = cooldown;
        this.remainingCooldown = 0;
        this.worth = 0; // in field bayad dar moghe tahvile item be hero meghdardehi shavad.
        this.isDisappearAfterFullUse = isDisappearAfterFullUse;
        this.isAlive = true;
    }

    //--------------------------------------------------- Functions

    public void isActivated() {
        //TODO
    }

    public void useItem() {
        if (this.remainingCooldown != 0)
            return;
        for (Hero hero: this.relatedSoldiers) {
            for (Property property: this.properties) {
                property.effect(relatedSoldiers, Hero.mapOfHeroes.get(this.ownerName), Hero.mapOfHeroes.get(this.ownerName));
            }
            this.effectedSoldiers.add(hero);
        }
        this.remainingTimeOfUsed--;
        if (this.remainingTimeOfUsed == 0) {
            if (this.isDisappearAfterFullUse) {
                if (this.effectedSoldiers.size() == 0)
                    this.isAlive = false;
            }
        }
    }

    public void removeEffect() {
        ArrayList<Integer> indexOfRemovedSoldiers = new ArrayList<Integer>();
        for (int i = 0; i < effectedSoldiers.size(); i++) {
            Hero hero = effectedSoldiers.get(i);
            for (Property property : this.properties) {
                property.removeEffect(hero);
            }
            indexOfRemovedSoldiers.add(new Integer(i));
        }
        for (int index: indexOfRemovedSoldiers) {
            this.effectedSoldiers.remove(index);
        }

        if (this.remainingTimeOfUsed == 0) {
            if (this.isDisappearAfterFullUse) {
                if (this.effectedSoldiers.size() == 0)
                    this.isAlive = false;
            }
        }
    }

    public boolean equals(Item item){
        if(this.name.equals(item.getName())){
            return true;
        }
        return false;
    }

    public void choosingRelatedSoldiers() {
        this.relatedSoldiers.clear();
        if (this.numberOfRelatedSoldiers == GameEngine.listOfHeroes.size())
            this.relatedSoldiers.addAll(GameEngine.listOfHeroes);
        else if (this.isRandomSoldierSelecting) {
            ArrayList<Hero> heroes = new ArrayList<Hero>();
            heroes.addAll(GameEngine.listOfHeroes);
            for (int i = 0; i < this.numberOfRelatedSoldiers; i++) {
                Random random = new Random();
                int randomIndex = random.nextInt(heroes.size());
                this.relatedSoldiers.add(heroes.get(randomIndex));
                heroes.remove(randomIndex);
            }
        }
        else if (this.isDependsRelatedSoldiersSelectingOnPlayer){
            ArrayList<String> nameOfHeroes = Display.getAbilityDetailsBeforeUsing(null);
            for (String nameOfHero: nameOfHeroes) {
                this.relatedSoldiers.add(Hero.mapOfHeroes.get(nameOfHero));
            }
        }
        else {
            this.relatedSoldiers.add(Hero.mapOfHeroes.get(this.ownerName));
        }
    }

    //--------------------------------------------------- Getter && Setters


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isDisappearAfterFullUse() {
        return isDisappearAfterFullUse;
    }

    public void setDisappearAfterFullUse(boolean disappearAfterFullUse) {
        isDisappearAfterFullUse = disappearAfterFullUse;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
    }

    public ArrayList<Hero> getRelatedSoldiers() {
        return relatedSoldiers;
    }

    public void setRelatedSoldiers(ArrayList<Hero> relatedSoldiers) {
        this.relatedSoldiers = relatedSoldiers;
    }

    public boolean isDependsRelatedSoldiersSelectingOnPlayer() {
        return isDependsRelatedSoldiersSelectingOnPlayer;
    }

    public void setDependsRelatedSoldiersSelectingOnPlayer(boolean dependsRelatedSoldiersSelectingOnPlayer) {
        isDependsRelatedSoldiersSelectingOnPlayer = dependsRelatedSoldiersSelectingOnPlayer;
    }

    public int getNumberOfRelatedSoldiers() {
        return numberOfRelatedSoldiers;
    }

    public void setNumberOfRelatedSoldiers(int numberOfRelatedSoldiers) {
        this.numberOfRelatedSoldiers = numberOfRelatedSoldiers;
    }

    public boolean isRandomSoldierSelecting() {
        return isRandomSoldierSelecting;
    }

    public void setRandomSoldierSelecting(boolean randomSoldierSelecting) {
        isRandomSoldierSelecting = randomSoldierSelecting;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public ArrayList<Property<Hero>> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property<Hero>> properties) {
        this.properties = properties;
    }

}
