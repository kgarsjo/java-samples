package com.kgarsjo.datastructs.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NodeIteratorTest {

    private INode<String> n1;
    private INode<String> n2;
    private INode<String> n3;

    @Before
    public void setUp() {
        n1 = new Node<>("foo");
        n2 = new Node<>("bar");
        n3 = new Node<>("baz");
    }

    @Test
    public void iteratorOverNullReferenceDoesNotHaveNext() {
        NodeIterator<String> testObject = new NodeIterator<>(null);
        Assert.assertFalse(testObject.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorOverNullReferenceThrowsOnNext() {
        NodeIterator<String> testObject = new NodeIterator<>(null);
        testObject.next();
    }

    @Test
    public void iteratorOverSingleNodeReferenceHasNext() {
        NodeIterator<String> testObject = new NodeIterator<>(n1);
        Assert.assertTrue(testObject.hasNext());
    }

    @Test
    public void iteratorOverSingleNodeReferenceReturnsPayloadOnNext() {
        NodeIterator<String> testObject = new NodeIterator<>(n1);
        String expected = n1.getPayload();
        String actual = testObject.next();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void iteratorOverSingleNodeReferenceAdvancesIteratorToEmptyAfterNext() {
        NodeIterator<String> testObject = new NodeIterator<>(n1);
        testObject.next();
        Assert.assertFalse(testObject.hasNext());
    }

    @Test
    public void iteratorOverMultiNodeReferenceAdvancesIteratorToNextValueAfterNext() {
        n1.setNext(n2);
        n2.setNext(n3);
        NodeIterator<String> testObject = new NodeIterator<>(n1);
        testObject.next();

        String expected = n2.getPayload();
        String secondValue = testObject.next();
        Assert.assertEquals(expected, secondValue);
    }
}
