package com.example.employee.data.adapter;

import java.util.HashMap;

/**
 * Адаптер HashMap
 */
public class HashMapAdapter<E, T> {
    protected HashMap<E, T> hashMap;

    public HashMapAdapter() {
        this.hashMap = new HashMap<E, T>();
    }

    public HashMap<E, T> get() {
        return this.hashMap;
    }

    public HashMapAdapter<E, T> append(E key, T value) {
        this.hashMap.put(key, value);
        return this;
    }
}
