import java.util.*;

/**
 * Created by asus-pc on 7/6/2016.
 */
public class SelectingObjectsDetail<T> {
    private ClassName classOfObjects;
    private boolean isAllRelatedObjectsInvolved;
    private boolean isObjectsWereSelectedByDefault;
    private ArrayList<T> selectedObjectsByDefault;
    private boolean isRandomObjectsSelecting;
    private int numberOfRandomSelectedObjects;
    private boolean isSelectedObjectsDependsOnPlayer;
    private int numberOfSelectedObjectsByPlayer;
    private boolean isRelatedToAttackDefend;
    private boolean isHeroEffected;
    private boolean isHeroEffecting;
    private boolean isEnemyEffected;
    private boolean isEnemyEffecting;

    //---------------------------------------------------------------- Constructors



    //---------------------------------------------------------------- Functions

    public ArrayList<T> selectingObjects() {
        return selectingObjects(null, null);
    }

    public ArrayList<T> selectingObjects(Enemy enemy, Hero hero) {
        ArrayList<T> selectedObjects = new ArrayList<>();
        if (this.isAllRelatedObjectsInvolved) {
            selectedObjects.addAll((ArrayList<? extends T>) this.classOfObjects.getListOfObjects());
            return selectedObjects;
        }
        if (this.isObjectsWereSelectedByDefault()) {
            selectedObjects.addAll(this.selectedObjectsByDefault);
        }
        if (this.isRandomObjectsSelecting) {
            ArrayList<T> objects = new ArrayList<>();
            objects.addAll((ArrayList<? extends T>) this.classOfObjects.getListOfObjects());
            for (int i = 0; i < this.getNumberOfRandomSelectedObjects(); i++) {
                Random random = new Random();
                int randomIndex = random.nextInt(objects.size());
                selectedObjects.add(objects.get(randomIndex));
                objects.remove(randomIndex);
            }
        }
        if (this.isRelatedToAttackDefend()) {
            if (this.classOfObjects == ClassName.Hero) {
                if (this.isHeroEffected() || this.isHeroEffecting()) {
                    selectedObjects.add((T) hero);
                }
            } else if (this.classOfObjects == ClassName.Enemy) {
                if (this.isEnemyEffected() || this.isEnemyEffecting) {
                    selectedObjects.add((T) enemy);
                }
            }
        }
        if (this.isSelectedObjectsDependsOnPlayer()) {
            int counter = this.getNumberOfSelectedObjectsByPlayer();
            while (counter != 0) {
                if (selectedObjects.size() == this.classOfObjects.getListOfObjects().size())
                    break;
                Display.printf("Please enter your targeted " + this.classOfObjects.getName() + ": ");
                String command = Display.getString();
                String[] nameOfObjects = command.split(" ");
                for (String nameOfObject : nameOfObjects) {
                    boolean isTargetRecognized = false;
                    for (T object : (ArrayList<T>) this.classOfObjects.getListOfObjects()) {
                        if (nameOfObject.equalsIgnoreCase(object.toString())) {
                            if (!selectedObjects.contains(object)) {
                                selectedObjects.add(object);
                                counter--;
                                isTargetRecognized = true;
                            } else {
                                Display.printInEachLine(object.toString() + " was selected in the past! Please enter another " + this.classOfObjects.getName());
                            }
                        }
                    }
                    if (isTargetRecognized == false) {
                        Display.printInEachLine(nameOfObject + " was not recognized. Please enter another " + this.classOfObjects.getName());
                    }
                }
            }
        }
        return selectedObjects;
    }

    //---------------------------------------------------- Getter && Setters


    public ClassName getClassOfObjects() {
        return classOfObjects;
    }

    public void setClassOfObjects(ClassName classOfObjects) {
        this.classOfObjects = classOfObjects;
    }

    public boolean isObjectsWereSelectedByDefault() {
        return isObjectsWereSelectedByDefault;
    }

    public void setObjectsWereSelectedByDefault(boolean objectsWereSelectedByDefault) {
        isObjectsWereSelectedByDefault = objectsWereSelectedByDefault;
    }

    public ArrayList<T> getSelectedObjectsByDefault() {
        return selectedObjectsByDefault;
    }

    public void setSelectedObjectsByDefault(ArrayList<T> selectedObjectsByDefault) {
        this.selectedObjectsByDefault = selectedObjectsByDefault;
    }

    public boolean isAllRelatedObjectsInvolved() {
        return isAllRelatedObjectsInvolved;
    }

    public void setAllRelatedObjectsInvolved(boolean allRelatedObjectsInvolved) {
        isAllRelatedObjectsInvolved = allRelatedObjectsInvolved;
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

    public int getNumberOfSelectedObjectsByPlayer() {
        return numberOfSelectedObjectsByPlayer;
    }

    public void setNumberOfSelectedObjectsByPlayer(int numberOfSelectedObjectsByPlayer) {
        this.numberOfSelectedObjectsByPlayer = numberOfSelectedObjectsByPlayer;
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
