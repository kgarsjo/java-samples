package com.kgarsjo.datastructs.common;

import java.util.Iterator;

public class NodeIterator<T> implements Iterator<T> {

    private INode<T> head;

    public NodeIterator(INode<T> head) {
        this.head = head;
    }

    public boolean hasNext() {
        return head != null;
    }

    public T next() {
        if (hasNext()) {
            T payload = head.getPayload();
            head = head.getNext();
            return payload;
        }
        throw new IllegalStateException("Iterator has no next value");
    }

}
