import java.util.ArrayList;

/**
 * Created by asus-pc on 7/10/2016.
 */
public class SelectingObjectsDetailHandler<T> {
    private ClassName classOfObjects;
    private boolean isAllRelatedObjectsInvolved;
    private boolean isUserSelected;
    private boolean isDependsOnCondition;
    private Tree<Boolean> trieConditions;
    private int numberOfSelectedObjectsByConditions;
    private boolean isRandomObjectsSelecting;
    private int numberOfRandomSelectedObjects;
    private boolean isSelectedObjectsDependsOnPlayer;
    private int numberOfSelectedObjectsByPlayer;
    private boolean isRelatedToAttackDefend;
    private boolean isHeroSelected;
    private boolean isEnemySelected;

    //---------------------------------------------------------------- Constructors

    public SelectingObjectsDetailHandler(ClassName classOfObjects, boolean isAllRelatedObjectsInvolved, boolean isUserSelected, boolean isDependsOnCondition, Tree<Boolean> trieConditions, int numberOfSelectedObjectsByConditions, boolean isRandomObjectsSelecting, int numberOfRandomSelectedObjects, boolean isSelectedObjectsDependsOnPlayer, int numberOfSelectedObjectsByPlayer, boolean isRelatedToAttackDefend, boolean isHeroSelected, boolean isEnemySelected) {
        this.classOfObjects = classOfObjects;
        this.isAllRelatedObjectsInvolved = isAllRelatedObjectsInvolved;
        this.isUserSelected = isUserSelected;
        this.isDependsOnCondition = isDependsOnCondition;
        this.trieConditions = trieConditions;
        this.numberOfSelectedObjectsByConditions = numberOfSelectedObjectsByConditions;
        this.isRandomObjectsSelecting = isRandomObjectsSelecting;
        this.numberOfRandomSelectedObjects = numberOfRandomSelectedObjects;
        this.isSelectedObjectsDependsOnPlayer = isSelectedObjectsDependsOnPlayer;
        this.numberOfSelectedObjectsByPlayer = numberOfSelectedObjectsByPlayer;
        this.isRelatedToAttackDefend = isRelatedToAttackDefend;
        this.isHeroSelected = isHeroSelected;
        this.isEnemySelected = isEnemySelected;
    }

    //---------------------------------------------------- Getter && Setters

    public ClassName getClassOfObjects() {
        return classOfObjects;
    }

    public boolean isAllRelatedObjectsInvolved() {
        return isAllRelatedObjectsInvolved;
    }

    public boolean isDependsOnCondition() {
        return isDependsOnCondition;
    }

    public Tree<Boolean> getTrieConditions() {
        return trieConditions;
    }

    public boolean isRandomObjectsSelecting() {
        return isRandomObjectsSelecting;
    }

    public int getNumberOfRandomSelectedObjects() {
        return numberOfRandomSelectedObjects;
    }

    public boolean isSelectedObjectsDependsOnPlayer() {
        return isSelectedObjectsDependsOnPlayer;
    }

    public int getNumberOfSelectedObjectsByPlayer() {
        return numberOfSelectedObjectsByPlayer;
    }

    public boolean isRelatedToAttackDefend() {
        return isRelatedToAttackDefend;
    }

    public boolean isHeroSelected() {
        return isHeroSelected;
    }

    public boolean isEnemySelected() {
        return isEnemySelected;
    }

    public boolean isUserSelected() {
        return isUserSelected;
    }

    public int getNumberOfSelectedObjectsByConditions() {
        return numberOfSelectedObjectsByConditions;
    }
}
