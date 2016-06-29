package com.kgarsjo.datastructs.tests;

import com.kgarsjo.datastructs.IStack;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStackTest {

    protected IStack<String> testObject;

    @Before
    public abstract void setUp();

    @Test
    public void isEmptyOnEmptyStackReturnsTrue() {
        boolean actual = testObject.isEmpty();
        Assert.assertTrue(actual);
    }

    @Test
    public void isEmptyOnEmptyStackReturnsFalse() {
        testObject.push("foo");
        boolean actual = testObject.isEmpty();
        Assert.assertFalse(actual);
    }

    @Test(expected = IllegalStateException.class)
    public void peekOnEmptyStackThrowsIllegalStateException() {
        testObject.peek();
    }

    @Test
    public void peekOnNonemptyStackReturnsTopValueWithoutModification() {
        testObject.push("foo");
        String actual = testObject.peek();
        Assert.assertEquals("foo", actual);
        Assert.assertFalse(testObject.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void popOnEmptyStackThrowsIllegalStateException() {
        testObject.pop();
    }

    @Test
    public void popOnNonemptyStackReturnsTopValueAndRemovesFromStack() {
        testObject.push("foo");
        String actual = testObject.pop();
        Assert.assertEquals("foo", actual);
        Assert.assertTrue(testObject.isEmpty());
    }

    @Test
    public void pushAddsValueToTopOfStack() {
        testObject.push("foo");
        Assert.assertEquals("foo", testObject.peek());
    }

}
