package com.kgarsjo.datastructs.queue;

public interface IQueue<T> {

    public boolean isEmpty();

    public void enqueue(T item);

    public T dequeue();

    public Object[] toArray();

}