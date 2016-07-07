import javafx.util.Pair;

import java.util.*;
import java.util.Map;

/**
 * Created by asus-pc on 7/5/2016.
 */
public class Tree<T> {
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
        }
        return (T) node.data;
    }

    public static class Node<T> {
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
