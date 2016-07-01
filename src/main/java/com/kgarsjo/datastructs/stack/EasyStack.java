package com.kgarsjo.datastructs.stack;

import java.util.ArrayList;
import java.util.List;

public class EasyStack<T> implements IStack<T> {

    private final List<T> items;

    public EasyStack() {
        items = new ArrayList<>();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public T peek() throws IllegalStateException {
        if (!isEmpty()) {
            return items.get(0);
        }
        throw new IllegalStateException("Stack is empty!");
    }

    public T pop() throws IllegalStateException {
        if (!isEmpty()) {
            return items.remove(0);
        }
        throw new IllegalStateException("Stack is empty!");
    }

    public void push(T item) {
        items.add(0, item);
    }
}
