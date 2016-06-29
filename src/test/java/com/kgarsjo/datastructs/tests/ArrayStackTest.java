package com.kgarsjo.datastructs.tests;

import com.kgarsjo.datastructs.ArrayStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayStackTest extends AbstractStackTest {

    private int initialCapacity = 10;

    @Before
    public void setUp() {
        testObject = new ArrayStack<>(initialCapacity);
    }

    @Test
    public void itemsCanBePushedBeyondInitialCapacity() {
        int beyondInitialCapactiy = 2 * initialCapacity;
        for (int i = 1; i <= beyondInitialCapactiy; i++) {
            testObject.push("item " + i);
        }

        for (int i = beyondInitialCapactiy; i > 0; i--) {
            Assert.assertEquals("item " + i, testObject.pop());
        }
    }
}
