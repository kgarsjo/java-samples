package com.kgarsjo.datastructs.queue;

import java.util.LinkedList;
import java.util.List;

public class EasyQueue<T> implements IQueue<T> {

    private List<T> items;

    public EasyQueue() {
        items = new LinkedList<>();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void enqueue(T item) {
        items.add(item);
    }

    public T dequeue () {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return items.remove(0);
    }

    public Object[] toArray() {
        return items.toArray();
    }

}
