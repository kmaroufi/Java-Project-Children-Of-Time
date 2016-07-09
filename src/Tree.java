import javafx.util.Pair;

import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/5/2016.
 */
public class Tree<T> implements Cloneable{
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>(rootData);
    }

    public <U> T findCorrectNode(U object) {
        Node<T> node = this.root;
        while (node.conditions != null) {
            for (Condition condition: node.conditions) {
                if (condition.checkCondition(object)) {
                    node = node.mapOfConditions.get(condition);
                    break;
                }
            }
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) node.data;
    }

    public Tree<T> clone() {
        Tree<T> tree = null;
        try {
            tree = (Tree<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        tree.root = new Node<>(this.root.data);
        cloneHelper(this.root, tree.root);
        return tree;
    }

    public void cloneHelper(Node<T> node, Node<T> newNode) {
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
            }
            else {
                newNode.addChild(node.mapOfConditions.get(condition).data, condition);
            }
        }
        for (Condition condition: node.conditions) {
            cloneHelper(node.mapOfConditions.get(condition), newNode.mapOfConditions.get(condition));
        }
    }

    public static class Node<T> implements Cloneable{
        private T data;
        private ArrayList<Condition> conditions = null;
        private Map<Condition, Node<T>> mapOfConditions = null;
        private Node<T> parent = null;
        private ArrayList<Node<T>> children = null;

        Node(T data) {
            this.data = data;
        }

        public void addChild(T data, Condition condition) {
            Node<T> child = new Node<>(data);
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
    }
}
