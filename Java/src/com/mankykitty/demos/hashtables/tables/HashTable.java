package com.mankykitty.demos.hashtables.tables;

import java.math.BigInteger;

/**
 * Created by manky on 14/12/13.
 */
public class HashTable<ValueType> {

    enum BucketOps {
        DELETE_BUCKET,
        UPDATE_BUCKET,
        CONTAINS_BUCKET
    }

    private int increment = 4;
    private int tableLength = 7;
    private int tableDepth = 3;

    private int storageCount = 0;

    // TODO: Handle the extension of the array when more values arrive.
    private Bucket<String, ValueType>[][] storage = new Bucket[tableLength][tableDepth];

    public void insert(String key, ValueType value) {
        int hash = hashFunction(key);
        Bucket newBucket = new Bucket<String, ValueType>()
                .setKey(key)
                .setContents(value);

        int i = 0;

        while (null != storage[hash][i]) {
            i++;
        }
        storage[hash][i] = newBucket;
        storageCount++;

        if (storageCount >= (storage.length - increment)) {
            System.out.println("Threshold exceeded... extending table.");
            extendTable();
            System.out.println("Table extension completed.");
        }
    }

    /**
     * Extend the size of the storage table, length ways.
     */
    private void extendTable() {
        Bucket<String, ValueType>[][] tmpStorage = storage.clone();
        int tmpStorageCount = storageCount;

        storage = new Bucket[storage.length + increment][tableDepth];

        for (int i = 0; i < tmpStorage.length; i++) {
            for (int j = 0; j < tableDepth; j++) {
                if (tmpStorage[i][j] != null) {
                    insert(tmpStorage[i][j].getKey(), tmpStorage[i][j].getContents());
                }
            }
        }
        storageCount = tmpStorageCount;
    }

    public boolean delete(String key) {
        return key != null && performOperation(BucketOps.DELETE_BUCKET, key, null);
    }

    public boolean update(String key, ValueType newValue) {
        return key != null && performOperation(BucketOps.UPDATE_BUCKET, key, newValue);
    }

    public boolean contains(String key) {
        return key != null && performOperation(BucketOps.CONTAINS_BUCKET, key, null);
    }

    public ValueType get(String key) {
        int hashed = hashFunction(key);

        for (int i = 0; i < tableDepth; i++) {
            if (storage[hashed][i].keyEquals(key)) {
                return storage[hashed][i].getContents();
            }
        }
        return null;
    }

    private boolean performOperation(BucketOps op, String key, ValueType newValue) {
        int hashed = hashFunction(key);

        for (int i = 0; i < tableDepth; i++) {
            if (storage[hashed][i].keyEquals(key)) {

                switch (op) {
                    case DELETE_BUCKET:
                        storage[hashed][i] = null;
                        return true;
                    case UPDATE_BUCKET:
                        storage[hashed][i].setContents(newValue);
                        return true;
                    case CONTAINS_BUCKET:
                        return true;
                }
            }
        }
        return false;
    }


    public int hashFunction(String keyValue) {
        int hashed = 0;

        for (char c : keyValue.toCharArray()) {
            hashed += (int)c;
        }
        return hashed % (storage.length);
    }
}
