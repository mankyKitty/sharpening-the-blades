package com.mankykitty.demos.binarytree;

import com.mankykitty.demos.binarytree.trees.BinaryTree;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println("Creating Binary Tree Object.");
        BinaryTree tree;
        tree = new BinaryTree(99);

        for (int i = 0; i < 20; i++) {
            tree.insertValue(getRandomInt());
        }

        System.out.print(tree.toString());
    }

    private static int getRandomInt() {
        int min = 1;
        int max = 1000;

        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }
}