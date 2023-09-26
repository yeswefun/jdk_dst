package com.z.mk11_avl;

import java.util.TreeMap;

/*
K 必须实现 hashCode 方法
泛型必须是引用类型
所有引用类型的父类 Object
Object 中存在 hashCode
 */
class HashTable<K, V> {

    //TreeMap数组
    private TreeMap<K, V>[] hashTable;

    //哈希表有多少个位置
    private int m;

    private int size;

    public HashTable(int m) {
        this.m = m;
        this.size = 0;
        //Unchecked assignment:
        //'java.util.TreeMap[]' to 'java.util.TreeMap<K,V>[]'
        hashTable = new TreeMap[m];
        for (int i = 0; i < m; i++) {
            //Construction of sorted collection with non-comparable elements
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    private int hash(K key) {
        return key.hashCode() & 0x7fffffff % m;
    }

    public int getSize() {
        return size;
    }

    //------------------------------ CRUD
    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value); // 覆盖
        } else {
            map.put(key, value);
            ++size;
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            --size;
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException("key doesn't exist");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }
}
