package spoonjoy.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MappingTest {

    @Test
    public void testHasSameKeyAs() throws Exception {
        Mapping<Integer, String> one = new Mapping<Integer, String>(1,"one");
        Mapping<Integer, String> two = new Mapping<Integer, String>(1,"two");
        assertThat(one.hasSameKeyAs(two), is(true));
    }

    @Test
    public void testKeyIsSmaller() throws Exception {
        Mapping<Integer, String> one = new Mapping<Integer, String>(1,"one");
        assertThat(one.keySmallerThan(2), is(true));
    }
    @Test
    public void testHasKey() throws Exception {
        Mapping<Integer, String> one = new Mapping<Integer, String>(1,"one");
        assertThat(one.keyEquals(1), is(true));
    }

    @Test
    public void testIsLessThan() throws Exception {
        Mapping<Integer, String> one = new Mapping<Integer, String>(1,"one");
        Mapping<Integer, String> two = new Mapping<Integer, String>(2,"two");
        assertThat(one.isLessThan(two), is(true));
    }
}