package com.kgarsjo.datastructs;

import java.util.Arrays;

public class ArrayStack<T> implements IStack<T> {
    private final static int EXPAND_BY = 10;

    private T[] items;
    private int headIndex;

    public ArrayStack() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        items = (T[]) new Object[capacity];
        headIndex = 0;
    }

    public boolean isEmpty() {
        return headIndex <= 0;
    }

    public T peek() throws IllegalStateException {
        if (!isEmpty()) {
            return items[headIndex];
        }
        throw new IllegalStateException("Stack is empty!");
    }

    public T pop() throws IllegalStateException {
        T topValue = peek();
        items[headIndex] = null;
        headIndex -= 1;
        return topValue;
    }

    public void push(T item) {
        expandArrayIfRequired(headIndex + 1);
        headIndex += 1;
        items[headIndex] = item;
    }

    private void expandArrayIfRequired(int projectedHeadIndex) {
        if (projectedHeadIndex >= items.length) {
            int newLength = items.length + EXPAND_BY;
            items = Arrays.copyOf(items, newLength);
        }
    }
}
