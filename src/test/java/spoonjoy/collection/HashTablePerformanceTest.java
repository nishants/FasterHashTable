package spoonjoy.collection;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HashTablePerformanceTest {
    @Test
    public void testGetPut() {
        perform(9999);
        perform(9999);
        perform(9999);
        perform(9999);
    }

    private void perform(int count) {
        long timeForJavaMap = Calendar.getInstance().getTimeInMillis();
        toMap(count);
        timeForJavaMap = Calendar.getInstance().getTimeInMillis() - timeForJavaMap;

        long timeForHashtable = Calendar.getInstance().getTimeInMillis();
        toTable(9999);
        timeForHashtable = Calendar.getInstance().getTimeInMillis() - timeForHashtable;

        System.out.printf("######## For %s gets and puts #########################%n", count);
        System.out.println("map   : " + timeForJavaMap);
        System.out.println("table : "+timeForHashtable);
        System.out.println("#################################");
        assertThat(timeForHashtable < timeForJavaMap, is(true));
    }

    private void toTable(int count) {
        Map<String, String> map = new HashMap<String, String>();
        for(int l = 0; l < count; l++) {
            map.put("" + l, "" + l);
        }
        for(int l = 0; l < count; l++) {
            map.get("" + l);
        }

    }

    private void toMap(int count) {
        HashTable<String, String> table = new HashTable<String, String>();
        for (int l = 0; l < count; l++) {
            table.put("" + l, "" + l);
        }
        for (int l = 0; l < count; l++) {
            table.get("" + l);
        }

    }

}