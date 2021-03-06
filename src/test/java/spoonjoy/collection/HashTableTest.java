package spoonjoy.collection;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
    public void testExists() throws Exception {
        hashTable.put("one", "value");
        assertTrue(hashTable.exists("one"));
    }

    @Test
    public void testDeleteKeys(){
        hashTable.put("3", "three");
        hashTable.put("1", "one");
        hashTable.put("4", "four");
        hashTable.put("2", "two");

        assertThat(hashTable.delete("2"), is(true));
        assertThat(hashTable.get("2"), is(nullValue()));
        assertThat(hashTable.get("1"), is("one"));
        assertThat(hashTable.get("3"), is("three"));
        assertThat(hashTable.get("4"), is("four"));

        assertThat(hashTable.delete("invalid key"), is(false));
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
    public void testBetween() {
        hashTable.put("3", "three");
        hashTable.put("5", "five");
        hashTable.put("2", "two");
        hashTable.put("6", "six");
        hashTable.put("4", "four");
        assertThat(hashTable.valuesBetween("3", "6"), is(arrayOf("three", "four", "five", "six")));
        assertThat(hashTable.valuesBetween("1", "6"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(hashTable.valuesBetween("1", "7"), is(arrayOf("two","three", "four", "five", "six")));
        assertThat(hashTable.valuesBetween("5", "7"), is(arrayOf("five", "six")));
        assertThat(hashTable.valuesBetween("6", "7"), is(arrayOf( "six")));
        assertThat(hashTable.valuesBetween("1", "2"), is(arrayOf( "two")));
        assertThat(hashTable.valuesBetween("0", "1"), is(arrayOf()));
        assertThat(hashTable.valuesBetween("7", "8"), is(arrayOf()));
    }

    @Test
    public void testLessThan() {
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

    @Test
    public void testGreaterThan() {
        hashTable.put("3", "three");
        hashTable.put("5", "five");
        hashTable.put("2", "two");
        hashTable.put("6", "six");
        hashTable.put("4", "four");

        assertThat(hashTable.greaterThan("2"), is(arrayOf("three", "four", "five", "six")));
        assertThat(hashTable.greaterThan("5"), is(arrayOf("six")));
        assertThat(hashTable.greaterThan("6"), is(arrayOf()));
        assertThat(hashTable.greaterThan("7"), is(arrayOf()));
        assertThat(hashTable.greaterThan("1"), is(arrayOf("two","three", "four", "five", "six")));
    }

    private <T> T[] arrayOf(T...values) {
        return values;
    }
}