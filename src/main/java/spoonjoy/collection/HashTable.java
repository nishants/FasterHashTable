package spoonjoy.collection;

import static java.lang.Math.abs;

public class HashTable<K extends Comparable, V> {
    private SortedSet<K, V>[] buckets;
    private static final int DEFAULT_TABLE_SIZE = 999;
    private static final int INITIAL_BUCKET_SIZE = 2;

    public HashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public HashTable(int size) {
        buckets = new SortedSet[size];
    }

    public boolean put(K key, V value) {
        return bucketFor(key).put(key, value);
    }

    public V get(K key) {
        return bucketFor(key).get(key);
    }

    public V[] valuesBetween(K fromKey, K toKey) {
        Object[][] values = new Object[buckets.length][];
        for (int i = 0; i < buckets.length; i++) {
            SortedSet<K, V> bucket = buckets[i];
            if(bucket !=null) values[i] = bucket.valuesBetween(fromKey, toKey);
        }
        return (V[])flattened(values);    }

    public V[] lessThan(K key) {
        Object[][] values = new Object[buckets.length][];
        for (int i = 0; i < buckets.length; i++) {
            SortedSet<K, V> bucket = buckets[i];
            if(bucket !=null) values[i] = bucket.valuesSmallerThan(key);
        }
        return (V[])flattened(values);
    }
    public V[] greaterThan(K key) {
        Object[][] values = new Object[buckets.length][];
        for (int i = 0; i < buckets.length; i++) {
            SortedSet<K, V> bucket = buckets[i];
            if(bucket !=null) values[i] = bucket.valuesGreaterThan(key);
        }
        return (V[])flattened(values);    }

    private SortedSet<K, V> bucketFor(K key) {
        int bucketIndex = abs(key.hashCode()) % buckets.length;
        if(buckets[bucketIndex] == null) buckets[bucketIndex]
                = new SortedSet<K,V>(INITIAL_BUCKET_SIZE);
        return buckets[bucketIndex];
    }

    private static Object[] flattened(Object[][] values) {
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

    public boolean exists(K key) {
        return get(key) != null;
    }

    public boolean delete(K key) {
        SortedSet<K, V> bucket = buckets[abs(key.hashCode()) % buckets.length];
        return bucket == null ? false : bucket.delete(key);
    }
}
