package com.kgarsjo.datastructs.tests;

import com.kgarsjo.datastructs.queue.IQueue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractQueueTest {

    protected IQueue<String> testObject;

    @Before
    public abstract void setUp();

    @Test
    public void enqueueToEmptyQueueAddsElementToQueue() {
        testObject.enqueue("foo");
        Object[] expected = {"foo"};
        Assert.assertArrayEquals(expected, testObject.toArray());
    }

    @Test
    public void enqueueToNonemptyQueueAddsElementToBackOfQueue() {
        testObject.enqueue("foo");
        testObject.enqueue("bar");
        Object[] expected = {"foo", "bar"};
        Assert.assertArrayEquals(expected, testObject.toArray());
    }

    @Test(expected = IllegalStateException.class)
    public void dequeueToEmptyQueueThrowsIllegalStateException() {
        testObject.dequeue();
    }

    @Test
    public void dequeueToSingleItemQueueEmptiesQueue() {
        testObject.enqueue("foo");
        testObject.dequeue();
        int expected = 0;
        int actual = testObject.toArray().length;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dequeueToMultiItemQueueRemovesFirstItem() {
        String[] values = new String[] {"foo", "bar", "baz", "quux"};
        Arrays.stream(values).forEach(testObject::enqueue);
        testObject.dequeue();

        Object[] expected = Arrays.stream(values)
                .filter(str -> !str.equals("foo"))
                .toArray();
        Object[] actual = testObject.toArray();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void dequeueToNonemptyQueueReturnsFirstItem() {
        String expected = "foo";
        testObject.enqueue(expected);
        String actual = testObject.dequeue();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isEmptyOnEmptyQueueReturnsTrue() {
        Assert.assertTrue(testObject.isEmpty());
    }

    @Test
    public void isEmptyOnNonemptyQueueReturnsFalse() {
        testObject.enqueue("foo");
        Assert.assertFalse(testObject.isEmpty());
    }
}
