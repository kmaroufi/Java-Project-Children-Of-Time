import java.util.*;

/**
 * Created by asus-pc on 7/6/2016.
 */
public class SelectingObjectsDetail<T> {
    private String classOfObjects;
    private boolean isObjectsWereSelectedByDefault;
    private ArrayList<T> SelectedObjectsByDefault;
    private boolean isAllRelatedObjectsInvolved;
    private int numberOfSelectedObjects;
    private boolean isRandomObjectsSelecting;
    private boolean isSelectedObjectsDependsOnPlayer;

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
}
