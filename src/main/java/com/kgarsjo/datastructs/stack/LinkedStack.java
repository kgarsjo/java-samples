package com.kgarsjo.datastructs.stack;

import com.kgarsjo.datastructs.common.INode;
import com.kgarsjo.datastructs.common.Node;

public class LinkedStack<T> implements IStack<T> {

    private INode<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    public T peek() throws IllegalStateException {
        if (!isEmpty()) {
            return head.getPayload();
        }
        throw new IllegalStateException("Stack is empty!");
    }

    public T pop() throws IllegalStateException {
        T topValue = peek();
        head = head.getNext();
        return topValue;
    }

    public void push(T item) {
        INode<T> current = new Node<>(item);
        current.setNext(head);
        head = current;
    }
}
