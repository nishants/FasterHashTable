package spoonjoy.collection;

public class SortedBucket<K extends Comparable, V> {
    private final Mapping[] mappings;

    protected SortedBucket(int size) {
        mappings = new Mapping[size];
    }

    public boolean put(K key, V value) {
        int index = 0;
        while (mappings[index] != null) {
            if (mappings[index].keyEquals(key)) {
                mappings[index] = mappingOf(key, value);
                return false;
            }
            if (!mappings[index].keyIsSmallerTo(key)) {
                insert(mappingOf(key, value), index);
                return true;
            }
            index++;
        }
        mappings[index] = mappingOf(key, value);
        return true;
    }

    private void insert(Mapping item, int atIndex) {
        for (int i = mappings.length - 1; i > atIndex; i--) {
            mappings[i] = mappings[i - 1];
        }
        mappings[atIndex] = item;
    }

    private Mapping mappingOf(K key, V value) {
        return new Mapping(key, value);
    }

    public V[] values() {
        Object[] keys = new Object[mappings.length];
        for (int i = 0; i < mappings.length; i++) {
            keys[i] = (mappings[i].getValue());
        }
        return (V[])keys;
    }
}
