package com.mankykitty.demos.binarytree.trees;

import com.mankykitty.demos.binarytree.trees.nodes.Node;

/**
 * Super basic Binary Tree implementation that can be used for storing Ints.
 * Very useful yes? ... okay fine it isn't, but I did this from scratch without
 * having to consult my own old source code or anyone else's implementation. I
 * know it's not great, eat me. :)
 */
public class BinaryTree {

    private Node root;

    public BinaryTree() {}

    public BinaryTree(int first) {
        root = new Node(first);
    }

    public void insertValue(int value) {
        if (null == this.root) {
            this.root = new Node(value);
            return;
        }
        insert(value, this.root);
    }

    public void deleteValue(int value) {
        delete(value, this.root, null);
    }

    public boolean search(int value) {
        return search(value, this.root);
    }

    private boolean search(int value, Node root) {
        if (root.getValue() == value) {
            return true;
        }

        if (value < root.getValue() && !root.isLeftEmpty()) {
            return search(value, root.getLeft());
        }
        if (value > root.getValue() && !root.isRightEmpty()) {
            return search(value, root.getRight());
        }
        return false;
    }

    private void insert(int newVal, Node root) {

        if (newVal > root.getValue()) {
            if (root.isRightEmpty()) {
                root.setRight(new Node(newVal));
            }
            else if (newVal < root.getRight().getValue()){
                Node n = new Node(newVal);
                n.setRight(root.getRight());
                root.setRight(n);
            }
            else {
                insert(newVal, root.getRight());
            }
        }
        else {
            if (root.isLeftEmpty()) {
                root.setLeft(new Node(newVal));
            }
            else if (newVal > root.getLeft().getValue()) {
                Node n = new Node(newVal);
                n.setLeft(root.getLeft());
                root.setLeft(n);
            }
            else {
                insert(newVal, root.getLeft());
            }
        }
    }

    private void delete(int del, Node current, Node previous) {
        if (del == current.getValue()) {

            if (null == previous && current.isLeftEmpty() && current.isRightEmpty()) {
                current = new Node(0); // I guess this resets the tree? O.o
            }
            else if (current.isLeftEmpty() && current.isRightEmpty()) {
                deleteHelper(del, previous, null);
            }
            else if (!current.isLeftEmpty()) {
                Node tmp = current.getLeft();
                tmp.setRight(current.getRight());

                deleteHelper(tmp.getValue(), previous, tmp);
            }
            else if (!current.isRightEmpty()) {
                Node tmp = current.getRight();

                deleteHelper(tmp.getValue(), previous, tmp);
            }
        }
        else {
            if (del > current.getValue()) {
                delete(del, current.getRight(), current);
            }
            else {
                delete(del, current.getLeft(), current);
            }
        }
    }

    private void deleteHelper(int value, Node target, Node targetValue) {
        if (value > target.getValue()) {
            target.setRight(targetValue);
        }
        else {
            target.setLeft(targetValue);
        }
    }
}
