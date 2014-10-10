package spoonjoy.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SMapping<K extends Comparable, V> {
    private K key;
    private V value;

    public boolean keyEquals(K key){
        return this.key.equals(key);
    }

    public boolean hasSameKeyAs(SMapping that){
        return this.key.equals(that.key);
    }

    public boolean isLessThan(SMapping that){
        return this.key.compareTo(that.key) < 0;
    }

    public boolean keyIsSmallerTo(K key) {
        return this.key.compareTo(key) < 0;
    }
}
