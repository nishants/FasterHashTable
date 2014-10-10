package spoonjoy.collection;

import static java.lang.Math.abs;

public class HashTable<K extends Comparable, V> {
    private SortedBucket<K, V>[] sortedBuckets;
    private static final int DEFAULT_TABLE_SIZE = 999;
    private static final int INITIAL_BUCKET_SIZE = 2;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTable(int size) {
        sortedBuckets = new SortedBucket[size];
    }

    public boolean put(K key, V value) {
        return bucketFor(key).put(key, value);
    }

    private SortedBucket<K, V> bucketFor(K key) {
        int bucketIndex = abs(key.hashCode()) % sortedBuckets.length;
        if(sortedBuckets[bucketIndex] == null) sortedBuckets[bucketIndex]
                = new SortedBucket<K,V>(INITIAL_BUCKET_SIZE);
        return sortedBuckets[bucketIndex];
    }

    public V get(K key) {
        return bucketFor(key).get(key);
    }

    public V[] lessThan(K key) {
        Object[][] values = new Object[sortedBuckets.length][];
        for (int i = 0; i < sortedBuckets.length; i++) {
            SortedBucket<K, V> bucket = sortedBuckets[i];
            if(bucket !=null) values[i] = bucket.valuesSmallerThan(key);
        }
        return (V[])flattened(values);
    }

    public static Object[] flattened(Object[][] values) {
        int totalElements = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null && values[i].length != 0){
                totalElements += values[i].length;
            }
        }
        Object[] result = new Object[totalElements];
        int elementsAdded = 0;
        for (Object[] subArr : values) {
            if (subArr != null && subArr.length !=0) {
                for (Object value : subArr) {
                    result[elementsAdded++] = value;
                }
            }
        }
        return result;
    }
}
