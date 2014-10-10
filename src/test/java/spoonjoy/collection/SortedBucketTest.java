package spoonjoy.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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
    public void shouldReturnSortedKeys(){
        SortedBucket<String, String> bucket = bucketOfSize(4);
        bucket.put("3", "three");
        bucket.put("1", "one");
        bucket.put("4", "four");
        bucket.put("2", "two");
        
        assertThat(bucket.values(), is(new String[]{"one","two","three", "four"}));
    }

    @Test
    public void shouldReturnEmptyArayIfNoValuesAdded(){
        assertThat(bucketOfSize(4).values(), is(new String[0]));
    }


    @Test
    public void shouldResize(){
        SortedBucket<String, String> bucket = bucketOfSize(1);
        bucket.put("3", "three");
        bucket.put("1", "one");
        bucket.put("5", "five");
        bucket.put("4", "four");
        bucket.put("2", "two");

        assertThat(bucket.values(), is(new String[]{"one","two","three", "four", "five"}));

    }
    private SortedBucket<String, String> bucketOfSize(int size) {
        return new SortedBucket<String, String>(size);
    }
}