import java.util.ArrayList;

/**
 * Created by asus-pc on 5/7/2016.
 */
public class SkillHandler {
    private boolean isRepeated;
    private Time timeOfEffecting;
    private int[] cooldown;
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


    public SkillHandler() {

    }

    //----------------------------------------         Getter & Setter!

}