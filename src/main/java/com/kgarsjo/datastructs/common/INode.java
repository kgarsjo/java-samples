package com.kgarsjo.datastructs.common;

import java.util.Iterator;

public interface INode<T> extends Iterable {

    public INode<T> getNext();

    public T getPayload();

    public void setNext(INode<T> next);
}
