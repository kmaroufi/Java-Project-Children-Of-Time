import java.util.*;

/**
 * Created by asus-pc on 7/6/2016.
 */
public class SelectingObjectsDetail<T> {
    private String classOfObjects;
    private boolean isAllRelatedObjectsInvolved;
    private boolean isObjectsWereSelectedByDefault;
    private ArrayList<T> SelectedObjectsByDefault;
    private int numberOfSelectedObjects;
    private boolean isRandomObjectsSelecting;
    private int numberOfRandomSelectedObjects;
    private boolean isSelectedObjectsDependsOnPlayer;
    private boolean isRelatedToAttackDefend;
    private boolean isHeroEffected;
    private boolean isHeroEffecting;
    private boolean isEnemyEffected;
    private boolean isEnemyEffecting;

    public String getClassOfObjects() {
        return classOfObjects;
    }

    public void setClassOfObjects(String classOfObjects) {
        this.classOfObjects = classOfObjects;
    }

    public boolean isObjectsWereSelectedByDefault() {
        return isObjectsWereSelectedByDefault;
    }

    public void setObjectsWereSelectedByDefault(boolean objectsWereSelectedByDefault) {
        isObjectsWereSelectedByDefault = objectsWereSelectedByDefault;
    }

    public ArrayList<T> getSelectedObjectsByDefault() {
        return SelectedObjectsByDefault;
    }

    public void setSelectedObjectsByDefault(ArrayList<T> selectedObjectsByDefault) {
        SelectedObjectsByDefault = selectedObjectsByDefault;
    }

    public boolean isAllRelatedObjectsInvolved() {
        return isAllRelatedObjectsInvolved;
    }

    public void setAllRelatedObjectsInvolved(boolean allRelatedObjectsInvolved) {
        isAllRelatedObjectsInvolved = allRelatedObjectsInvolved;
    }

    public int getNumberOfSelectedObjects() {
        return numberOfSelectedObjects;
    }

    public void setNumberOfSelectedObjects(int numberOfSelectedObjects) {
        this.numberOfSelectedObjects = numberOfSelectedObjects;
    }

    public boolean isRandomObjectsSelecting() {
        return isRandomObjectsSelecting;
    }

    public void setRandomObjectsSelecting(boolean randomObjectsSelecting) {
        isRandomObjectsSelecting = randomObjectsSelecting;
    }

    public boolean isSelectedObjectsDependsOnPlayer() {
        return isSelectedObjectsDependsOnPlayer;
    }

    public void setSelectedObjectsDependsOnPlayer(boolean selectedObjectsDependsOnPlayer) {
        isSelectedObjectsDependsOnPlayer = selectedObjectsDependsOnPlayer;
    }

    public int getNumberOfRandomSelectedObjects() {
        return numberOfRandomSelectedObjects;
    }

    public void setNumberOfRandomSelectedObjects(int numberOfRandomSelectedObjects) {
        this.numberOfRandomSelectedObjects = numberOfRandomSelectedObjects;
    }

    public boolean isRelatedToAttackDefend() {
        return isRelatedToAttackDefend;
    }

    public void setRelatedToAttackDefend(boolean relatedToAttackDefend) {
        isRelatedToAttackDefend = relatedToAttackDefend;
    }

    public boolean isHeroEffected() {
        return isHeroEffected;
    }

    public void setHeroEffected(boolean heroEffected) {
        isHeroEffected = heroEffected;
    }

    public boolean isHeroEffecting() {
        return isHeroEffecting;
    }

    public void setHeroEffecting(boolean heroEffecting) {
        isHeroEffecting = heroEffecting;
    }

    public boolean isEnemyEffected() {
        return isEnemyEffected;
    }

    public void setEnemyEffected(boolean enemyEffected) {
        isEnemyEffected = enemyEffected;
    }

    public boolean isEnemyEffecting() {
        return isEnemyEffecting;
    }

    public void setEnemyEffecting(boolean enemyEffecting) {
        isEnemyEffecting = enemyEffecting;
    }
}
