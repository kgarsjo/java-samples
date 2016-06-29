package com.kgarsjo.datastructs;

public interface IStack<T> {

    public boolean isEmpty();

    public T peek() throws IllegalStateException;

    public T pop() throws IllegalStateException;

    public void push(T item);
}
