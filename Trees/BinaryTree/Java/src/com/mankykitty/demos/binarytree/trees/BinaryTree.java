package com.mankykitty.demos.binarytree.trees;

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

    private class Node {

        int value;
        Node left;
        Node right;

        Node(int startingValue) {
            value = startingValue;
        }
    }

    public void insertValue(int value) {
        if (null == root) {
            root = new Node(value);
            return;
        }
        insert(value, root);
    }

    public void deleteValue(int value) {
        delete(value, root, null);
    }

    public boolean search(int value) {
        return search(value, root);
    }

    private boolean search(int value, Node root) {
        if (root.value == value) {
            return true;
        }

        if (value < root.value && null != root.left) {
            return search(value, root.left);
        }
        if (value > root.value && null != root.right) {
            return search(value, root.right);
        }
        return false;
    }

    private void insert(int newVal, Node root) {

        if (newVal > root.value) {
            if (null == root.right) {
                root.right = new Node(newVal);
            }
            else if (newVal < root.right.value){
                Node n = new Node(newVal);
                n.right = root.right;
                root.right = n;
            }
            else {
                insert(newVal, root.right);
            }
        }
        else {
            if (null == root.left) {
                root.left = new Node(newVal);
            }
            else if (newVal > root.left.value) {
                Node n = new Node(newVal);
                n.left = root.left;
                root.left = n;
            }
            else {
                insert(newVal, root.left);
            }
        }
    }

    private void delete(int del, Node current, Node previous) {
        if (del == current.value) {

            if (null == previous && null == current.left && null == current.right) {
                current = new Node(0); // I guess this resets the tree? O.o
            }
            else if (null == current.left && null == current.right) {
                deleteHelper(del, previous, null);
            }
            else if (null != current.left) {
                Node tmp = current.left;
                tmp.right = current.right;

                deleteHelper(tmp.value, previous, tmp);
            }
            else if (null != current.right) {
                Node tmp = current.right;

                deleteHelper(tmp.value, previous, tmp);
            }
        }
        else {
            if (del > current.value) {
                delete(del, current.right, current);
            }
            else {
                delete(del, current.left, current);
            }
        }
    }

    private void deleteHelper(int value, Node target, Node targetValue) {
        if (value > target.value) {
            target.right = targetValue;
        }
        else {
            target.left = targetValue;
        }
    }
}
