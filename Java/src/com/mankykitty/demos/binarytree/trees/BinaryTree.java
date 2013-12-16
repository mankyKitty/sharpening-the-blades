package com.mankykitty.demos.binarytree.trees;

/**
 * Super basic Binary Tree implementation that can be used for storing Ints.
 * Very useful yes? ... okay fine it isn't, but I did this from scratch without
 * having to consult my own old source code or anyone else's implementation. I
 * know it's not great, eat me. :)
 */
public class BinaryTree {

    // This is, evidently, the root of the tree.
    private Node root;

    // Allow the tree to be instantiated without a initial value.
    public BinaryTree() {}

    // Sometimes you might want to kick start the tree with your first value.
    public BinaryTree(int first) {
        root = new Node(first);
    }

    /**
     * Insert a value into the Binary Tree.
     *
     * If the tree has no current root, this will create the root node and return.
     *
     * If the tree has values, then we delve into the structure of the tree in an attempt to locate
     * the correct location for our new value. Remembering that this is a bog standard binary tree.
     *
     * We make no attempts to balance the values.
     *
     * @param value int
     */
    public void insertValue(int value) {
        if (null == this.root) {
            // Empty tree, instantiate root and return.
            this.root = new Node(value);
            return;
        }
        // We're going to need a bigger boat.
        insert(value, this.root);
    }

    /**
     * Delete the given value from the tree.
     *
     * This begins the process of deleting a node from the tree and ensuring the tree structure
     * remains intact and correct.
     *
     * @param value int
     */
    public void deleteValue(int value) {
        // Start at the root and delete the value.
        delete(value, this.root, null);
    }

    /**
     * Establish if the tree contains the given value.
     *
     * Traverse the tree IN ORDER in an attempt to locate this value.
     *
     * @param value int
     * @return boolean
     */
    public boolean search(int value) {
        if (this.root == null) {
            return false;
        }
        // Start at the base and off we go!
        return search(value, this.root);
    }

    /**
     * Searches the tree recursively for our given value.
     *
     * @param value int
     * @param root Node
     * @return boolean
     */
    private boolean search(int value, Node root) {

        if (root.getValue() == value) {
            // Victory for Zim!
            return true;
        } else if (value < root.getValue()) {
            // It's just a step to left...
            return !root.isLeftEmpty() && search(value, root.getLeft());
        } else {
            // and jump the right!
            return !root.isRightEmpty() && search(value, root.getRight());
        }
    }

    /**
     * Inserts a new value into the tree structure ensuring it is inserted at
     * the correct location in the structure. Left or Right leaf of an existing
     * node.
     *
     * @param newVal int
     * @param root Node
     */
    private void insert(int newVal, Node root) {

        // Determine if we need to be looking at the right or left side
        // of this particular node.
        if (newVal > root.getValue()) {
            if (root.isRightEmpty()) {
                // Easy case, right is empty. Create a node and place it there.
                root.setRight(new Node(newVal));
            }
            else {
                // "We're going to have to go deeper"
                insert(newVal, root.getRight());
            }
        }
        else {
            if (root.isLeftEmpty()) {
                // Easy case! No left node so spin one up and place it in.
                root.setLeft(new Node(newVal));
            }
            else {
                // Wheeeeeeeeeee!
                insert(newVal, root.getLeft());
            }
        }
    }

    /**
     * Deletes the given value from the tree whilst preserving the rules of the tree.
     *
     * I'm not 100% I handle all cases correctly.
     *
     * @param del int
     * @param current Node
     * @param previous Node
     */
    private void delete(int del, Node current, Node previous) {
        // We've located the value we're going to delete.
        if (del == current.getValue()) {

            // Deleting the root node of an empty tree. -- NB 'previous' is not null for non-empty trees.
            if (null == previous && current.isLeftEmpty() && current.isRightEmpty()) {
                current = new Node(0); // I guess this resets the tree? O.o
            }
            else if (current.isLeftEmpty() && current.isRightEmpty()) {
                // Delete this Node off the parent
                previous.deleteHelper(del, null);
            }
            else if (!current.isLeftEmpty()) {
                Node tmp = current.getLeft();
                tmp.setRight(current.getRight());
                // Rearrange the left and right values after a delete
                previous.deleteHelper(tmp.getValue(), tmp);
            }
            else if (!current.isRightEmpty()) {
                // Rearrange the left and right values after a delete
                previous.deleteHelper(current.getRight().getValue(), current.getRight());
            }
        }
        else {
            // We have not found our value yet so keep going.
            if (del > current.getValue()) {
                delete(del, current.getRight(), current);
            }
            else {
                delete(del, current.getLeft(), current);
            }
        }
    }
}
