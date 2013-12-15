package com.mankykitty.demos.binarytree;

import com.mankykitty.demos.binarytree.trees.BinaryTree;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Creating Binary Tree Object.");
        BinaryTree tree;
        tree = new BinaryTree(99);

        System.out.println("Populating Tree");
        for (int i = 0; i < 20; i++) {
            tree.insertValue(getRandomInt());
        }

        // Erm...
        System.out.print(tree.toString());
    }

    /**
     * Retrieve a random int between 1 and 1000.
     *
     * I'm sure there is a built-in for this..
     *
     * @return int
     */
    private static int getRandomInt() {
        int min = 1;
        int max = 1000;

        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }
}