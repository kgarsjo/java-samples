package com.kgarsjo.datastructs;

public class LinkedStack<T> implements IStack<T> {

    private static class Node<T> {
        private T payload;
        private Node<T> next;

        Node(T payload) {
            this.payload = payload;
        }

        Node<T> getNext() {
            return next;
        }

        T getPayload() {
            return payload;
        }

        void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> head;

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
        Node<T> current = new Node<>(item);
        current.setNext(head);
        head = current;
    }
}
