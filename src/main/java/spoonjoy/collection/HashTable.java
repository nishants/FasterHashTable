package spoonjoy.collection;

import static java.lang.Math.abs;

public class HashTable<K extends Comparable, V> {
    private SortedBucket<K, V>[] sortedBuckets;
    private static final int DEFAULT_TABLE_SIZE = 99999999;
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
}
