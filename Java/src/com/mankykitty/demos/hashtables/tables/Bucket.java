package com.mankykitty.demos.hashtables.tables;

/**
 * Created by manky on 16/12/13.
 */
public class Bucket<KeyType,ValueType> {
    KeyType key;
    ValueType contents;

    public boolean keyEquals(KeyType otherKey) {
        return otherKey != null && key == otherKey;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getContents() {
        return contents;
    }

    public Bucket<KeyType, ValueType> setKey(KeyType key) {
        this.key = key;
        return this;
    }

    public Bucket<KeyType, ValueType> setContents(ValueType contents) {
        this.contents = contents;
        return this;
    }
}
