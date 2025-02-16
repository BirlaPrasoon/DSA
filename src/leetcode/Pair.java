package leetcode;

import java.util.Objects;

public class Pair<U, V> {
    public U key;
    public V value;

    public Pair(U key, V value) {
        this.key = key;
        this.value = value;
    }

    public U getKey() {
        return key;
    }

    public void setKey(U key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;

        return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(key);
        result = 31 * result + Objects.hashCode(value);
        return result;
    }
}
