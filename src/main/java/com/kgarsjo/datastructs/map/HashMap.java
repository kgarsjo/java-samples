package com.kgarsjo.datastructs.map;

import com.kgarsjo.datastructs.list.IList;
import com.kgarsjo.datastructs.list.LinkedList;

public class HashMap<K,V> implements IMap<K,V> {

    private static class Entry<K,V> {
        public final K key;
        public final V value;

        public Entry(K key, V value) {
            if (key == null || value == null) {
                throw new IllegalArgumentException();
            }
            this.key = key;
            this.value = value;
        }
    }

    private IList<Entry<K,V>>[] table;

    public HashMap() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        table = new IList[capacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        Entry<K,V> entry = getEntry(key);
        return (entry == null) ? null : entry.value;
    }

    public void put(K key, V value) {
        Entry<K,V> entry = new Entry<>(key, value);
        IList<Entry<K,V>> chain = getEntryChain(key);
        chain.add(0, entry);
    }

    public V remove(K key) {
        return null;
    }

    private Entry<K,V> getEntry(K key) {
        IList<Entry<K,V>> chain = getEntryChain(key);
        for (Entry<K,V> entry : chain) {
            if (entry.key.hashCode() == key.hashCode()) {
                return entry;
            }
        }
        return null;
    }

    private IList<Entry<K,V>> getEntryChain(K key) {
        if (key == null) {
            return null;
        }
        int index = hashToIndex(key);
        return table[index];
    }

    private int hashToIndex(K key) {
        return key.hashCode() % table.length;
    }
}
