package com.kgarsjo.datastructs.map;

public interface IMap<K,V> {

    public boolean contains(K key);

    public V get(K key);

    public void put(K key, V value);

    public V remove(K key);
}
