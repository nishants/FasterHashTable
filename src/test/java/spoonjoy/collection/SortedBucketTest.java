package spoonjoy.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SortedBucketTest {

    @Test
    public void testPut() throws Exception {
        SortedBucket<String, String> bucket = bucketOfSize(3);
        assertTrue(bucket.put("one", "value"));
        assertFalse(bucket.put("one", "new-value"));
        assertTrue(bucket.put("two", "value"));
    }

    @Test
    public void testExists() throws Exception {
        SortedBucket<String, String> bucket = bucketOfSize(3);
        bucket.put("one", "value");
        assertTrue(bucket.exists("one"));
    }

    @Test
    public void shouldReturnSortedKeys(){
        SortedBucket<String, String> bucket = bucketOfSize(4);
        bucket.put("3", "three");
        bucket.put("1", "one");
        bucket.put("4", "four");
        bucket.put("2", "two");
        
        assertThat(bucket.values(), is(arrayOf("one", "two", "three", "four")));
    }

    @Test
    public void shouldReturnEmptyArayIfNoValuesAdded(){
        assertThat(bucketOfSize(4).values(), is(new Object[0]));
    }

    @Test
    public void shouldResize(){
        SortedBucket<String, String> bucket = bucketOfSize(1);
        bucket.put("3", "three");
        bucket.put("1", "one");
        bucket.put("5", "five");
        bucket.put("4", "four");
        bucket.put("2", "two");

        assertThat(bucket.values(), is(arrayOf("one","two","three", "four", "five")));

    }

    @Test
    public void shouldReturnValuesInRangeByKey() {
        SortedBucket<String, String> bucket = bucketOfSize(1);
        bucket.put("3", "three");
        bucket.put("5", "five");
        bucket.put("2", "two");
        bucket.put("6", "six");
        bucket.put("4", "four");
        assertThat(bucket.valuesBetween("3", "6"), is(arrayOf("three", "four", "five", "six")));
        assertThat(bucket.valuesBetween("1", "6"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(bucket.valuesBetween("1", "7"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(bucket.valuesBetween("5", "7"), is(arrayOf("five", "six")));
        assertThat(bucket.valuesBetween("6", "7"), is(arrayOf( "six")));
        assertThat(bucket.valuesBetween("1", "2"), is(arrayOf( "two")));
        assertThat(bucket.valuesBetween("0", "1"), is(arrayOf()));
        assertThat(bucket.valuesBetween("7", "8"), is(arrayOf()));
    }

    @Test
    public void shouldReturnValuesSmallerThanAKey() {
        SortedBucket<String, String> bucket = bucketOfSize(1);
        bucket.put("3", "three");
        bucket.put("5", "five");
        bucket.put("2", "two");
        bucket.put("6", "six");
        bucket.put("4", "four");

        assertThat(bucket.valuesSmallerThan("6"), is(arrayOf("two","three", "four", "five")));
        assertThat(bucket.valuesSmallerThan("2"), is(arrayOf()));
        assertThat(bucket.valuesSmallerThan("3"), is(arrayOf("two")));
        assertThat(bucket.valuesSmallerThan("7"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(bucket.valuesSmallerThan("1"), is(arrayOf()));
    }

    @Test
    public void shouldReturnValuesGreaterThanAKey() {
        SortedBucket<String, String> bucket = bucketOfSize(1);
        bucket.put("3", "three");
        bucket.put("5", "five");
        bucket.put("2", "two");
        bucket.put("6", "six");
        bucket.put("4", "four");

        assertThat(bucket.valuesGreaterThan("2"), is(arrayOf("three", "four", "five", "six")));
        assertThat(bucket.valuesGreaterThan("5"), is(arrayOf("six")));
        assertThat(bucket.valuesGreaterThan("6"), is(arrayOf()));
        assertThat(bucket.valuesGreaterThan("7"), is(arrayOf()));
        assertThat(bucket.valuesGreaterThan("1"), is(arrayOf("two","three", "four", "five", "six")));
    }

    @Test
    public void shouldReturnKeyValues(){
        SortedBucket<String, String> bucket = bucketOfSize(2);
        bucket.put("3", "bad-value");
        bucket.put("3", "three");
        bucket.put("5", "five");

        assertThat(bucket.get("3"), is("three"));
        assertThat(bucket.get("5"), is("five"));
        assertThat(bucket.get("6"), is(nullValue()));
    }

    private Object[] arrayOf(Object...values) {
        return values;
    }

    private SortedBucket<String, String> bucketOfSize(int size) {
        return new SortedBucket<String, String>(size);
    }
}