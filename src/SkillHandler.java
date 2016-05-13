import java.util.ArrayList;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class SkillHandler<T> {
    private ArrayList<Property<T>> propertiesOfRelatedSoldiers;
    private Property propertiesOfUser;
    private int nonTargetedEnemy;
    private boolean isRepeated;
    private Time timeOfEffecting;
    private ArrayList<String> blackList;
    private int[] cooldown;
    private boolean isDependsRelatedSoldiersSelectingOnPlayer;
    private boolean canStackUp;                                 // What is this Shit?
    private int[] requiredEnergyPoint;
    private int[] requiredMagicPoint;

    //----------------------------------------          Constructor

    public SkillHandler(ArrayList<Property<T>> propertiesOfRelatedSoldiers, Property propertiesOfUser, int nonTargetedEnemy, boolean isRepeated, Time timeOfEffecting, ArrayList<String> blackList, int[] cooldown, boolean isDependsRelatedSoldiersSelectingOnPlayer, boolean canStackUp, int[] requiredEnergyPoint, int[] requiredMagicPoint) {
        this.propertiesOfRelatedSoldiers = propertiesOfRelatedSoldiers;
        this.propertiesOfUser = propertiesOfUser;
        this.nonTargetedEnemy = nonTargetedEnemy;
        this.isRepeated = isRepeated;
        this.timeOfEffecting = timeOfEffecting;
        this.blackList = blackList;
        this.cooldown = cooldown;
        this.isDependsRelatedSoldiersSelectingOnPlayer = isDependsRelatedSoldiersSelectingOnPlayer;
        this.canStackUp = canStackUp;
        this.requiredEnergyPoint = requiredEnergyPoint;
        this.requiredMagicPoint = requiredMagicPoint;
    }


    //----------------------------------------         Getter & Setter!


    public ArrayList<Property<T>> getPropertiesOfRelatedSoldiers() {
        return propertiesOfRelatedSoldiers;
    }

    public void setPropertiesOfRelatedSoldiers(ArrayList<Property<T>> propertiesOfRelatedSoldiers) {
        this.propertiesOfRelatedSoldiers = propertiesOfRelatedSoldiers;
    }

    public Property getPropertiesOfUser() {
        return propertiesOfUser;
    }

    public void setPropertiesOfUser(Property propertiesOfUser) {
        this.propertiesOfUser = propertiesOfUser;
    }

    public int getNonTargetedEnemy() {
        return nonTargetedEnemy;
    }

    public void setNonTargetedEnemy(int nonTargetedEnemy) {
        this.nonTargetedEnemy = nonTargetedEnemy;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public Time getTimeOfEffecting() {
        return timeOfEffecting;
    }

    public void setTimeOfEffecting(Time timeOfEffecting) {
        this.timeOfEffecting = timeOfEffecting;
    }

    public ArrayList<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    public int[] getCooldown() {
        return cooldown;
    }

    public void setCooldown(int[] cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isDependsRelatedSoldiersSelectingOnPlayer() {
        return isDependsRelatedSoldiersSelectingOnPlayer;
    }

    public void setDependsRelatedSoldiersSelectingOnPlayer(boolean dependsRelatedSoldiersSelectingOnPlayer) {
        isDependsRelatedSoldiersSelectingOnPlayer = dependsRelatedSoldiersSelectingOnPlayer;
    }

    public boolean isCanStackUp() {
        return canStackUp;
    }

    public void setCanStackUp(boolean canStackUp) {
        this.canStackUp = canStackUp;
    }

    public int[] getRequiredEnergyPoint() {
        return requiredEnergyPoint;
    }

    public void setRequiredEnergyPoint(int[] requiredEnergyPoint) {
        this.requiredEnergyPoint = requiredEnergyPoint;
    }

    public int[] getRequiredMagicPoint() {
        return requiredMagicPoint;
    }

    public void setRequiredMagicPoint(int[] requiredMagicPoint) {
        this.requiredMagicPoint = requiredMagicPoint;
    }

}
