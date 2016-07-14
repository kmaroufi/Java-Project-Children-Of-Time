import GUI.Display;

import java.util.*;

/**
 * Created by asus-pc on 7/6/2016.
 */
public class SelectingObjectsDetail<T> {
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

    public SelectingObjectsDetail(SelectingObjectsDetailHandler selectingObjectsDetailHandler) {
        setClassOfObjects(selectingObjectsDetailHandler.getClassOfObjects());
        setAllRelatedObjectsInvolved(selectingObjectsDetailHandler.isAllRelatedObjectsInvolved());
        setUserSelected(selectingObjectsDetailHandler.isUserSelected());
        setDependsOnCondition(selectingObjectsDetailHandler.isDependsOnCondition());
        setTrieConditions(selectingObjectsDetailHandler.getTrieConditions());
        setNumberOfSelectedObjectsByConditions(selectingObjectsDetailHandler.getNumberOfSelectedObjectsByConditions());
        setRandomObjectsSelecting(selectingObjectsDetailHandler.isRandomObjectsSelecting());
        setNumberOfRandomSelectedObjects(selectingObjectsDetailHandler.getNumberOfRandomSelectedObjects());
        setSelectedObjectsDependsOnPlayer(selectingObjectsDetailHandler.isSelectedObjectsDependsOnPlayer());
        setNumberOfSelectedObjectsByPlayer(selectingObjectsDetailHandler.getNumberOfSelectedObjectsByPlayer());
        setRelatedToAttackDefend(selectingObjectsDetailHandler.isRelatedToAttackDefend());
        setHeroSelected(selectingObjectsDetailHandler.isHeroSelected());
        setEnemySelected(selectingObjectsDetailHandler.isEnemySelected());
    }

    public SelectingObjectsDetail() {
        this.classOfObjects = null;
        this.isAllRelatedObjectsInvolved = false;
        this.isUserSelected = false;
        this.isDependsOnCondition = false;
        this.trieConditions = null;
        this.numberOfSelectedObjectsByConditions = 0;
        this.isRandomObjectsSelecting = false;
        this.numberOfRandomSelectedObjects = 0;
        this.isSelectedObjectsDependsOnPlayer = false;
        this.numberOfSelectedObjectsByPlayer = 0;
        this.isRelatedToAttackDefend = false;
        this.isHeroSelected = false;
        this.isEnemySelected = false;
    }

    //---------------------------------------------------------------- Functions

    public ArrayList<T> selectingObjects() {
        return selectingObjects(null, null, null);
    }

    public ArrayList<T> selectingObjects(Enemy enemy, Hero hero, T user) {
        ArrayList<T> selectedObjects = new ArrayList<>();
        if (this.isAllRelatedObjectsInvolved) {
            selectedObjects.addAll((ArrayList<T>) this.classOfObjects.getListOfObjects());
            return selectedObjects;
        }
        if (isUserSelected) {
            selectedObjects.add(user);
        }
        if (this.isDependsOnCondition) {
            ArrayList<T> listOfAllObjects = (ArrayList<T>) this.classOfObjects.getListOfObjects();
            int counter = 0;
            for (T object: listOfAllObjects) {
                if (this.trieConditions.findCorrectNode(object)) {
                    selectedObjects.add(object);
                    counter++;
                }
                if (counter == this.numberOfSelectedObjectsByConditions) {
                    break;
                }
            }
        }
        if (this.isRandomObjectsSelecting) {
            ArrayList<T> objects = new ArrayList<>();
            objects.addAll((ArrayList<T>) this.classOfObjects.getListOfObjects());
            for (int i = 0; i < this.getNumberOfRandomSelectedObjects(); i++) {
                Random random = new Random();
                int randomIndex = random.nextInt(objects.size());
                selectedObjects.add(objects.get(randomIndex));
                objects.remove(randomIndex);
            }
        }
        if (this.isRelatedToAttackDefend()) {
            if (this.classOfObjects == ClassName.Hero) {
                if (this.isHeroSelected) {
                    selectedObjects.add((T) hero);
                }
            } else if (this.classOfObjects == ClassName.Enemy) {
                if (this.isEnemySelected) {
                    selectedObjects.add((T) enemy);
                }
            }
        }
        if (this.isSelectedObjectsDependsOnPlayer()) {
            int counter = this.getNumberOfSelectedObjectsByPlayer();
            while (counter != 0) {
                if (selectedObjects.size() == this.classOfObjects.getListOfObjects().size())
                    break;
                Display.printInEachLine("Please enter your targeted " + this.classOfObjects.getName() + ".Please split them with ',' .");
                String command = Display.getString();
                String[] nameOfObjects = command.split(",");
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

    public boolean isDependsOnCondition() {
        return isDependsOnCondition;
    }

    public void setDependsOnCondition(boolean dependsOnCondition) {
        isDependsOnCondition = dependsOnCondition;
    }

    public Tree<Boolean> getTrieConditions() {
        return trieConditions;
    }

    public void setTrieConditions(Tree<Boolean> trieConditions) {
        this.trieConditions = trieConditions;
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

    public boolean isHeroSelected() {
        return isHeroSelected;
    }

    public void setHeroSelected(boolean heroSelected) {
        isHeroSelected = heroSelected;
    }

    public boolean isEnemySelected() {
        return isEnemySelected;
    }

    public void setEnemySelected(boolean enemySelected) {
        isEnemySelected = enemySelected;
    }

    public boolean isUserSelected() {
        return isUserSelected;
    }

    public void setUserSelected(boolean userSelected) {
        isUserSelected = userSelected;
    }

    public int getNumberOfSelectedObjectsByConditions() {
        return numberOfSelectedObjectsByConditions;
    }

    public void setNumberOfSelectedObjectsByConditions(int numberOfSelectedObjectsByConditions) {
        this.numberOfSelectedObjectsByConditions = numberOfSelectedObjectsByConditions;
    }
}
