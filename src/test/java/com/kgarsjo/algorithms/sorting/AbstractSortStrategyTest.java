package com.kgarsjo.algorithms.sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractSortStrategyTest {

    protected ISortStrategy<String> testObject;
    private Comparator<String> comparator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    };

    @Before
    public abstract void setUp();

    @Test
    public void sortingEmptyArrayReturnsEmptyArray() {
        List<String> unsorted = asList();
        List<String> expected = asList();
        Assert.assertEquals(expected, sort(unsorted));
    }

    @Test
    public void sortingSingleItemArrayReturnsSingleItemArray() {
        List<String> unsorted = asList("a");
        List<String> expected = asList("a");
        Assert.assertEquals(expected, sort(unsorted));
    }

    @Test
    public void sortingMultiItemUnsortedArrayReturnsSortedMultiItemArray() {
        List<String> unsorted = asList("b", "c", "a");
        List<String> expected = asList("a", "b", "c");
        Assert.assertEquals(expected, sort(unsorted));
    }

    @Test
    public void sortingMultiItemLongUnsortedArrayReturnsSortedMultiItemArray() {
        List<String> unsorted = asList("g", "f", "e", "b", "c", "a", "d", "a");
        List<String> expected = asList("a", "a", "b", "c", "d", "e", "f", "g");
        Assert.assertEquals(expected, sort(unsorted));
    }

    @Test
    public void sortingMultiItemSortedArrayReturnsSortedMultiItemArray() {
        List<String> unsorted = asList("a", "b", "c");
        List<String> expected = asList("a", "b", "c");
        Assert.assertEquals(expected, sort(unsorted));
    }

    private List<String> sort(List<String> unsorted) {
        return testObject.sort(unsorted, comparator);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> asList(T... elements) {
        return new ArrayList<T>(Arrays.asList(elements));
    }
}
