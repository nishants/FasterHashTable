package spoonjoy.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SMappingTest {

    @Test
    public void testHasSameKeyAs() throws Exception {
        SMapping<Integer, String> one = new SMapping<Integer, String>(1,"one");
        SMapping<Integer, String> two = new SMapping<Integer, String>(1,"two");
        assertThat(one.hasSameKeyAs(two), is(true));
    }

    @Test
    public void testKeyIsSmaller() throws Exception {
        SMapping<Integer, String> one = new SMapping<Integer, String>(1,"one");
        assertThat(one.keyIsSmallerTo(2), is(true));
    }
    @Test
    public void testHasKey() throws Exception {
        SMapping<Integer, String> one = new SMapping<Integer, String>(1,"one");
        assertThat(one.keyEquals(1), is(true));
    }

    @Test
    public void testIsLessThan() throws Exception {
        SMapping<Integer, String> one = new SMapping<Integer, String>(1,"one");
        SMapping<Integer, String> two = new SMapping<Integer, String>(2,"two");
        assertThat(one.isLessThan(two), is(true));
    }
}