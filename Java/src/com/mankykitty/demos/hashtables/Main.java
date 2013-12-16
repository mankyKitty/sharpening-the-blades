package com.mankykitty.demos.hashtables;

import com.mankykitty.demos.hashtables.tables.HashTable;

/**
 * Created by manky on 14/12/13.
 */
public class Main {

    private static HashTable<String> foo = new HashTable<String>();

    public static void main(String[] args) {
        String[] bar = new String[] {
            "Steve", "Steveaa",
            "Notes", "Notesaa",
            "Steven","Stevenaa"
        };

        for (int i = 0; i < (bar.length / 2);) {
            foo.insert(bar[i], bar[i+1]);
            i = i + 2;
        }

        for (int i = 0; i < (bar.length / 2);) {

            System.out.println("Value for Key: " + bar[i] + " is : " + foo.get(bar[i]));

            i = i + 2;
        }

    }
}
