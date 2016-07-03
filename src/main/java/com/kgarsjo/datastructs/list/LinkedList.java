package com.kgarsjo.datastructs.list;

import com.kgarsjo.datastructs.common.INode;
import com.kgarsjo.datastructs.common.Node;
import com.kgarsjo.datastructs.common.NodeIterator;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedList<T> implements IList<T> {

    private INode<T> head;

    public void add(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()) {
            head = node;
        } else {
            add(size(), item);
        }
    }

    public void add(int index, T item) {
        INode<T> nodeToInsert = new Node<>(item);
        if (index == 0) {
            nodeToInsert.setNext(head);
            head = nodeToInsert;
        } else {
            INode<T> nodeBefore = getNodeBefore(index);
            nodeToInsert.setNext(nodeBefore.getNext());
            nodeBefore.setNext(nodeToInsert);
        }
    }

    public IList<T> copy() {
        IList<T> copy = new LinkedList<>();
        stream().forEach(copy::add);
        return copy;
    }

    public boolean equals(IList<T> other) {
        if (size() != other.size()) {
            return false;
        }
        Iterator<T> thisIter = iterator();
        Iterator<T> otherIter = other.iterator();
        while (thisIter.hasNext() && otherIter.hasNext()) {
            if (!thisIter.next().equals(otherIter.next())) {
                return false;
            }
        }
        return true;
    }

    public T get(int index) {
        return getNodeAt(index).getPayload();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }

    public T remove(int index) {
        INode<T> nodeAt = getNodeAt(index);
        if (index == 0) {
            head = head.getNext();
        } else {
            INode<T> nodeBefore = getNodeBefore(index);
            nodeBefore.setNext(nodeAt.getNext());
        }
        return nodeAt.getPayload();
    }

    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            return remove(index);
        }
        return null;
    }

    public IList<T> reverse() {
        IList<T> reversed = new LinkedList<>();
        stream().forEach(t -> reversed.add(0, t));
        return reversed;
    }

    public void reverseInPlace() {
        INode<T> prev = head;
        INode<T> current = head;
        head = null;
        while (current != null) {
            current = current.getNext();
            prev.setNext(head);
            head = prev;
            prev = current;
        }

    }

    public int size() {
        return stream().collect(Collectors.summingInt(t -> 1));
    }

    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        return stream().toArray();
    }

    private INode<T> getNodeAt(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        INode<T> current = head;
        for (int i = 0; i < index; i++) {
            if (current == null || current.getNext() == null) {
                throw new IndexOutOfBoundsException();
            }
            current = current.getNext();
        }
        return current;
    }

    private INode<T> getNodeBefore (int index) {
        return getNodeAt(index - 1);
    }

    private int indexOf(T element) {
        int idx = 0;
        for (T current : this) {
            if (current.equals(element)) {
                return idx;
            }
            ++idx;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private Stream<T> stream() {
        if (isEmpty()) {
            return Stream.empty();
        }
        return StreamSupport.stream(head.spliterator(), false);
    }
}
