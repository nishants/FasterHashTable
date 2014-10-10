package spoonjoy.collection;

import java.util.Arrays;

public class SortedBucket<K extends Comparable, V> {
    private Mapping[] mappings;

    protected SortedBucket(int size) {
        mappings = new Mapping[size];
    }

    public boolean put(K key, V value) {
        //TODO use binary tree to store mappings
        if (mappings[mappings.length - 1] != null) resize();
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

    private void resize() {
        mappings = Arrays.copyOf(mappings, mappings.length * 2);
    }

    private void insert(Mapping item, int atIndex) {
        for (int i = size(); i > atIndex; i--) {
            mappings[i] = mappings[i - 1];
        }
        mappings[atIndex] = item;
    }

    private Mapping mappingOf(K key, V value) {
        return new Mapping(key, value);
    }

    public Object[] values() {
        Object[] keys = new Object[size()];
        for (int i = 0; i < size(); i++) {
            if (mappings[i] != null) keys[i] = (mappings[i].getValue());
        }
        return keys;
    }

    private int size() {
        //TODO : update size on adding element, don't calculate
        for (int i = 0; i < mappings.length; i++) {
            if (mappings[i] == null) return i;
        }
        return mappings.length;
    }

    public Object[] valuesBetween(K fromKey, K toKey) {
        int fromIndex = mappings.length;
        int toIndex = mappings.length;
        for (int i = 0; i < size(); i++) {
            if (!mappings[i].keyIsSmallerTo(fromKey) && i < fromIndex) {
                fromIndex = i;
            }
            if (mappings[i].keyIsSmallerTo(toKey) || mappings[i].keyEquals(toKey)) {
                toIndex = i;
            }
        }
        return copyOfRange(mappings, fromIndex, toIndex);
    }

    private Object[] copyOfRange(Mapping[] mappings, int fromIndex, int toIndex) {
        if (toIndex == mappings.length) return new Object[0];
        if (toIndex < fromIndex) return new Object[0];

        Object[] result = new Object[toIndex - fromIndex + 1];
        for (int i = fromIndex; i <= toIndex; i++) {
            result[i - fromIndex] = mappings[i].getValue();
        }
        return result;
    }
}
