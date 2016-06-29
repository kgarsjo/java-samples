import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.sample.Hello;

public class ExampleTest {
    private Hello testObject;

    @Before
    public void setUp() {
        testObject = new Hello();
    }

    @Test
    public void testCallingHelloReturnsHello() {
        String expected = "Hello!";
        String actual = testObject.hello();
        Assert.assertEquals(expected, actual);
    }
}
