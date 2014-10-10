package spoonjoy.collection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable<String, String> hashTable;

    @Before
    public void setUp() throws Exception {
        hashTable = new HashTable<String, String>();
    }

    @Test
    public void shouldReturnTrueIfNewKeyAdded() {
        assertThat(hashTable.put("newKey", "someValue"), is(true));
        assertThat(hashTable.put("newKey", "someValue"), is(false));
        assertThat(hashTable.put("newKey", "someOtherValue"), is(false));
    }

    @Test
    public void shouldGetValue() {
        hashTable.put("1", "bad-value");
        hashTable.put("1", "one");
        hashTable.put("2", "two");

        assertThat(hashTable.get("1"), is("one"));
        assertThat(hashTable.get("2"), is("two"));
    }

    @Test
    public void shouldGetValueInRange() {
        hashTable.put("3", "three");
        hashTable.put("5", "five");
        hashTable.put("2", "two");
        hashTable.put("6", "six");
        hashTable.put("4", "four");

        assertThat(hashTable.lessThan("7"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(hashTable.lessThan("5"), is(arrayOf("two","three", "four")));
        assertThat(hashTable.lessThan("2"), is(arrayOf()));
        assertThat(hashTable.lessThan("1"), is(arrayOf()));
    }

    private <T> T[] arrayOf(T...values) {
        return values;
    }
}