package com.kgarsjo.datastructs.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractMapTest {

    protected IMap<String, String> testObject;

    @Before
    public abstract void setUp();

    @Test
    public void emptyMapContainsKeyReturnsFalse() {
        Assert.assertFalse(testObject.contains("foo"));
    }

    @Test
    public void mapMissingKeyContainsKeyReturnsFalse() {
        testObject.put("foo", "foo-val");
        Assert.assertFalse(testObject.contains("bar"));
    }

    @Test
    public void mapContainingKeyContainsKeyReturnsTrue() {
        testObject.put("foo", "foo-val");
        Assert.assertTrue(testObject.contains("foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapGetNullKeyThrowsIllegalArgument() {
        testObject.get(null);
    }

    @Test
    public void emptyMapGetKeyReturnsNull() {
        Assert.assertNull(testObject.get("foo"));
    }

    @Test
    public void mapMissingKeyGetKeyReturnsNull() {
        testObject.put("foo", "foo-val");
        Assert.assertNull(testObject.get("bar"));
    }

    @Test
    public void mapContainingKeyGetKeyReturnsValue() {
        testObject.put("foo", "foo-val");
        Assert.assertEquals("foo-val", testObject.get("foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapPutNullKeyThrowsIllegalArgument() {
        testObject.put(null, "null-value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapRemoveNullKeyThrowsIllegalArgument() {
        testObject.remove(null);
    }

    @Test
    public void emptyMapRemoveKeyReturnsNull() {
        Assert.assertNull(testObject.remove("foo"));
    }

    @Test
    public void mapMissingKeyRemoveKeyReturnsNull() {
        testObject.put("foo", "foo-val");
        Assert.assertNull(testObject.remove("bar"));
    }

    @Test
    public void mapContainingKeyRemoveKeyReturnsValue() {
        testObject.put("foo", "foo-val");
        Assert.assertEquals("foo-val", testObject.remove("foo"));
    }

    @Test
    public void mapContainingKeyRemoveKeyTwiceShouldReturnValueThenNull() {
        testObject.put("foo", "foo-val");
        testObject.remove("foo");
        Assert.assertNull(testObject.remove("foo"));
    }

    @Test
    public void mapContainingKeyRemoveKeyThenContainsShouldReturnFalse() {
        testObject.put("foo", "foo-val");
        testObject.remove("foo");
        Assert.assertFalse(testObject.contains("foo"));
    }

    @Test
    public void mapContainingKeyRemoveKeyThenGetShouldReturnNull() {
        testObject.put("foo", "foo-val");
        testObject.remove("foo");
        Assert.assertNull(testObject.get("foo"));
    }

}
