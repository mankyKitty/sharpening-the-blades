package com.mankykitty.demos.binarytree.trees.nodes;

/**
 * Created by manky on 15/12/13.
 */
public class Node {

    int value;
    Node left;
    Node right;

    public Node(int startingValue) {
        this.setValue(startingValue);
    }

    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isLeftEmpty() {
        return this.left == null;
    }

    public boolean isRightEmpty() {
        return this.right == null;
    }

    /**
     * Ensure the correct child is set during a delete step.
     *
     * @param value int
     * @param newChild Node
     */
    public void deleteHelper(int value, Node newChild) {
        if (value > this.getValue()) {
            this.setRight(newChild);
        }
        else {
            this.setLeft(newChild);
        }
    }
}
