package com.kgarsjo.datastructs.list;

public interface IList<T> extends Iterable<T> {

    public void add(T item);

    public void add(int index, T item);

    public IList<T> copy();

    public boolean equals(IList<T> other);

    public T get(int index);

    public boolean isEmpty();

    public T remove(int index);

    public T remove(T element);

    public IList<T> reverse();

    public void reverseInPlace();

    public int size();

    public Object[] toArray();

}
