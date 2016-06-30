package com.kgarsjo.algorithms.sorting;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

public class MergeSortStrategy<T> implements ISortStrategy<T> {

    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) {
            return list;
        }
        int split = Math.floorDiv(list.size(), 2);
        List<T> sorted1 = sort(list.subList(0, split), comparator);
        List<T> sorted2 = sort(list.subList(split, list.size()), comparator);
        return merge(sorted1, sorted2, comparator);
    }

    private List<T> merge(List<T> sorted1, List<T> sorted2, Comparator<T> comparator) {
        ListIterator<T> iter1 = sorted1.listIterator();
        ListIterator<T> iter2 = sorted2.listIterator();
        List<T> merged = new LinkedList<>();

        while (iter1.hasNext() || iter2.hasNext()) {
            merged.add(pickLowerValue(iter1, iter2, comparator));
        }
        return merged;
    }

    private T pickLowerValue(ListIterator<T> i1, ListIterator<T> i2, Comparator<T> comparator) {
        if (i1.hasNext() && i2.hasNext()) {
            int comparison = comparator.compare(peek(i1), peek(i2));
            if (comparison < 0) {
                return i1.next();
            } else {
                return i2.next();
            }
        } else if (i1.hasNext()) {
            return i1.next();
        }
        return i2.next();
    }

    private T peek(ListIterator<T> iterator) {
        T value = iterator.next();
        iterator.previous();
        return value;
    }

}
