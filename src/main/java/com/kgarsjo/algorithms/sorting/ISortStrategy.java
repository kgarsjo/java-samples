package com.kgarsjo.algorithms.sorting;

import java.util.Comparator;
import java.util.List;

public interface ISortStrategy<T> {
    public List<T> sort(List<T> list, Comparator<T> comparator);
}
