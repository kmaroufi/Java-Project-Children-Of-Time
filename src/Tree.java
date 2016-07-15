import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/5/2016.
 */
public class Tree<T> implements Cloneable{
    private Node<T> root;
    private ArrayList<T> dataSet = new ArrayList<>();

    public Tree() {
        root = new Node<T>(null, this);
    }

    public <U> T findCorrectNode(U object) {
        Node<T> node = this.root;
        while (node.conditions != null) {
            boolean isOnOfConditionsTrue = false;
            for (Condition condition: node.conditions) {
                if (condition.checkCondition(object)) {
                    node = node.mapOfConditions.get(condition);
                    isOnOfConditionsTrue = true;
                    break;
                }
            }
            if (isOnOfConditionsTrue == false) {
                break;
            }
//            try {
//                throw new Exception();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        return (T) node.data;
    }

    public Tree<T> clone() {
        Tree<T> newTree = null;
        try {
            newTree = (Tree<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        newTree.root = new Node<>(null, newTree);
        newTree.dataSet = new ArrayList<>();
        cloneHelper(newTree, this.root, newTree.root);
        return newTree;
    }

    public void cloneHelper(Tree<T> newTree, Node<T> node, Node<T> newNode) {
        if (node.conditions == null) {
            return;
        }
        for (Condition condition: node.conditions) {
            T childData = node.mapOfConditions.get(condition).data;
            if (childData instanceof ArrayList) {
                ArrayList<Property> array = (ArrayList<Property>) childData;
                ArrayList<Property> newArray = new ArrayList<>();
                for (Property property: array) {
                    newArray.add(property.clone());
                }
                newNode.addChild((T) newArray, condition);
                newTree.dataSet.add((T) newArray);
            } else if (childData instanceof SubSkill) {
                SubSkill subSkill = (SubSkill) childData;
                subSkill = subSkill.clone();
                newNode.addChild((T) subSkill, condition);
                newTree.dataSet.add((T) subSkill);
            } else if (childData instanceof SubPerk) {
                SubPerk subPerk = (SubPerk) childData;
                subPerk = subPerk.clone();
                newNode.addChild((T) subPerk, condition);
                newTree.dataSet.add((T) subPerk);
            } else {
                newNode.addChild(node.mapOfConditions.get(condition).data, condition);
                newTree.dataSet.add(node.mapOfConditions.get(condition).data);
            }
        }
        for (Condition condition: node.conditions) {
            cloneHelper(newTree, node.mapOfConditions.get(condition), newNode.mapOfConditions.get(condition));
        }
    }

    public ArrayList<T> getDataSet() {
        return dataSet;
    }

    public Node<T> getRoot() {
        return root;
    }

    public static class Node<E> implements Cloneable{
        private E data;
        private Tree<E> relatedTree;
        private ArrayList<Condition> conditions = null;
        private Map<Condition, Node<E>> mapOfConditions = null;
        private Node<E> parent = null;
        private ArrayList<Node<E>> children = null;

        Node(E data, Tree<E> relatedTree) {
            this.data = data;
            this.relatedTree = relatedTree;
            this.relatedTree.dataSet.add(data);
        }

        public void addChild(E data, Condition condition) {
            Node<E> child = new Node<>(data, this.relatedTree);
            child.parent = this;
            if (this.conditions == null) {
                this.conditions = new ArrayList<>();
            }
            if (this.mapOfConditions == null) {
                this.mapOfConditions = new HashMap<>();
            }
            if (this.children == null) {
                this.children = new ArrayList<>();
            }
            this.conditions.add(condition);
            this.mapOfConditions.put(condition, child);
            this.children.add(child);
        }

        public Condition hasThisCondition(Condition condition) {
            for (int i = 0; i < this.conditions.size(); i++) {
                if (this.conditions.get(i).equals(condition)) {
                    return this.conditions.get(i);
                }
            }
            return null;
        }

        public ArrayList<Node<E>> getChildren() {
            return children;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }
    }
}
