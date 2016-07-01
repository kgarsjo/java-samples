package com.kgarsjo.datastructs.list;

import org.assertj.core.data.Index;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractListTest {

    private IList<String> testObject;

    @Before
    public void setUp() {
        testObject = createList();
    }

    protected abstract IList<String> createList();

    @Test
    public void addItemAddsToEndOfList() {
        String[] expected = new String[] {"foo", "bar", "baz"};
        Arrays.stream(expected).forEach(testObject::add);
        Assert.assertArrayEquals(expected, testObject.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addItemAtNegativeIndexThrowsIndexOutOfBounds() {
        testObject.add(-1, "foo");
    }

    @Test
    public void addItemAtStartIndexInsertsBeforeItemAtIndex() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.add(0, "quux");

        Object[] expected = new String[] {"quux", "foo", "bar", "baz"};
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void addItemAtMidIndexInsertsBeforeItemAtIndex() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.add(1, "quux");

        Object[] expected = new String[] {"foo", "quux", "bar", "baz"};
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void addItemAtEndIndexInsertsBeforeItemAtIndex() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.add(2, "quux");

        Object[] expected = new String[] {"foo", "bar", "quux", "baz"};
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addItemAtOverIndexThrowsIndexOutOfBounds() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.add(4, "quux");
    }

    @Test
    public void copyListResultsInListsHavingDeepEquality() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        IList<String> copy = testObject.copy();

        Assert.assertTrue(testObject.equals(copy));
        Assert.assertTrue(copy.equals(testObject));
    }

    @Test
    public void copyListResultsInListsNotHavingReferenceEquality() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        IList<String> copy = testObject.copy();

        Assert.assertFalse(testObject == copy);
    }

    @Test
    public void equalsReturnsTrueIfAllIndexValuesAreEqual() {
        IList<String> other = createList();
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(value -> {
            testObject.add(value);
            other.add(value);
        });

        Assert.assertTrue(testObject.equals(other));
        Assert.assertTrue(other.equals(testObject));
    }

    @Test
    public void equalsReturnsFalseIfNotAllIndexValuesAreEqual() {
        IList<String> other = createList();
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        Arrays.stream(new String[] {"foo", "quux", "baz"}).forEach(other::add);

        Assert.assertFalse(testObject.equals(other));
        Assert.assertFalse(other.equals(testObject));
    }

    @Test
    public void equalsReturnsFalseIfOtherListIsLonger() {
        IList<String> other = createList();
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        Arrays.stream(new String[] {"foo", "bar", "baz", "quux"}).forEach(other::add);

        Assert.assertFalse(testObject.equals(other));
        Assert.assertFalse(other.equals(testObject));
    }

    @Test
    public void equalsReturnsFalseIfOtherListIsShorter() {
        IList<String> other = createList();
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        Arrays.stream(new String[] {"foo", "bar"}).forEach(other::add);

        Assert.assertFalse(testObject.equals(other));
        Assert.assertFalse(other.equals(testObject));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexLessThanZeroThrowsIndexOutOfBounds() {
        testObject.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexGreaterThanLastIndexThrowsIndexOutOfBounds() {
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        testObject.get(3);
    }

    @Test
    public void getIndexWithinBoundsReturnsValueAtIndex() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        String expected = values[2];
        String actual = testObject.get(2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmptyReturnsTrueWithEmptyList() {
        Assert.assertTrue(testObject.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseWithNonemptyList() {
        testObject.add("foo");
        Assert.assertFalse(testObject.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexLessThanZeroThrowsIndexOutOfBounds() {
        testObject.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIndexGreaterThanLastIndexThrowsIndexOutOfBounds() {
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        testObject.remove(3);
    }

    @Test
    public void removeIndexWithinBoundsReturnsValueAtIndex() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        String expected = values[2];
        String actual = testObject.remove(2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeStartIndexWithinBoundsRemovesIndexValueFromList() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.remove(0);

        Object[] expected = Arrays.stream(values)
                .filter(str -> !str.equals("foo"))
                .toArray();
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeMidIndexWithinBoundsRemovesIndexValueFromList() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.remove(1);

        Object[] expected = Arrays.stream(values)
                .filter(str -> !str.equals("bar"))
                .toArray();
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeEndIndexWithinBoundsRemovesIndexValueFromList() {
        String[] values = new String[] {"foo", "bar", "baz"};
        Arrays.stream(values).forEach(testObject::add);
        testObject.remove(2);

        Object[] expected = Arrays.stream(values)
                .filter(str -> !str.equals("baz"))
                .toArray();
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void reverseEmptyListReturnsEmptyList() {
        IList<String> reversed = testObject.reverse();
        Assert.assertTrue(reversed.isEmpty());
    }

    @Test
    public void reverseSingleItemListReturnsSingleItemList() {
        testObject.add("foo");
        IList<String> reversed = testObject.reverse();

        Object[] expected = {"foo"};
        Object[] actual = reversed.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void reverseMultiItemListReturnsReversedMultiItemList() {
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        IList<String> reversed = testObject.reverse();

        Object[] expected = {"baz", "bar", "foo"};
        Object[] actual = reversed.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void reverseMultiItemListDoesNotModifyOriginalList() {
        String[] expected = new String[] {"foo", "bar", "baz"};
        Arrays.stream(expected).forEach(testObject::add);
        testObject.reverse();

        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void reverseInPlaceEmptyListReturnsEmptyList() {
        testObject.reverseInPlace();
        Assert.assertTrue(testObject.isEmpty());
    }

    @Test
    public void reverseInPlaceSingleItemListReturnsSingleItemList() {
        testObject.add("foo");
        testObject.reverseInPlace();

        Object[] expected = {"foo"};
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void reverseInPlaceMultiItemListReturnsReversedMultiItemList() {
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        testObject.reverseInPlace();

        Object[] expected = {"baz", "bar", "foo"};
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void sizeForEmptyListShouldBeZero() {
        Assert.assertEquals(0, testObject.size());
    }

    @Test
    public void sizeForSingleItemListShouldBeOne() {
        testObject.add("foo");
        Assert.assertEquals(1, testObject.size());
    }

    @Test
    public void sizeForNItemListShouldBeN() {
        Arrays.stream(new String[] {"foo", "bar", "baz"}).forEach(testObject::add);
        Assert.assertEquals(3, testObject.size());
    }
}
