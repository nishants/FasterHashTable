package spoonjoy.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Mapping<K extends Comparable, V> {
    private K key;
    private V value;

    public boolean keyEquals(K key){
        return this.key.equals(key);
    }

    public boolean hasSameKeyAs(Mapping that){
        return this.key.equals(that.key);
    }

    public boolean isLessThan(Mapping that){
        return this.key.compareTo(that.key) < 0;
    }

    public boolean keySmallerThan(K key) {
        return this.key.compareTo(key) < 0;
    }
}
