package com.kgarsjo.datastructs.queue;

import com.kgarsjo.datastructs.common.INode;
import com.kgarsjo.datastructs.common.Node;

import java.util.stream.StreamSupport;

public class LinkedQueue<T> implements IQueue<T> {

    private INode<T> head;
    private INode<T> tail;

    public LinkedQueue() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null || tail == null;
    }

    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = tail.getNext();
        }
    }

    public T dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T payload = head.getPayload();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return payload;
    }

    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        return StreamSupport.stream(head.spliterator(), false)
                .toArray();
    }

}
