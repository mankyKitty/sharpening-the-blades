package com.mankykitty.demos.hashtables;

import com.mankykitty.demos.hashtables.tables.HashTable;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by manky on 14/12/13.
 */
public class Main {

    private static HashTable<String> foo = new HashTable<String>();

    public static void main(String[] args) {
        HashMap<String, String> bar = new HashMap<String, String>();

        bar.put("Steve", "STOOOVE");
        bar.put("Notes", "NOOOTES");
        bar.put("Foo", "FOOOOO");
        bar.put("Bar", "BARRRRR");
        bar.put("Bee", "BEEEEEEE");

        Set<String> keys = bar.keySet();

        int insertCount = 0;

        for (String key : keys) {
            foo.insert(key, bar.get(key));
            insertCount++;
        }

        System.out.println("Inserted " + insertCount + " records.");

        int containsCount = 0;

        for (String key : keys) {
            if (foo.contains(key)) {
                containsCount++;
            }
        }

        System.out.println("HashTable contains " + containsCount + " records.");

        int count = 0;

        for (String key : keys) {
            if (foo.get(key).equals(bar.get(key))) {
                count++;
            }
        }
        if (count == 5) {
            System.out.println("HashTable contains all matching records after table extension.");
        }
    }
}
