package com.kgarsjo.datastructs.common;

import java.util.Iterator;

public class Node<T> implements INode<T> {
    private T payload;
    private INode<T> next;

    public Node(T payload) {
        this.payload = payload;
    }

    public INode<T> getNext() {
        return next;
    }

    public T getPayload() {
        return payload;
    }

    public Iterator<T> iterator() {
        return new NodeIterator<>(this);
    }

    public void setNext(INode<T> next) {
        this.next = next;
    }
}
