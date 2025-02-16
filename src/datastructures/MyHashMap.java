package datastructures;

public class MyHashMap<Key, Value> {
    private static final int INITIAL_SIZE=1<<4;// 16
    private static final int MAXIMUM_CAPACITY = 1<<30;

    private Entry[] hashTable;
    private class Entry<Key, Value> {
        Key key;
        Value value;
        Entry<Key, Value> next;

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }
    MyHashMap(int capacity){
        hashTable = new Entry[tableSizeFor(capacity)];
    }

    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n>>>1;
        n |= n>>>2;
        n |= n>>>4;
        n |= n>>>8;
        n |= n>>>16;
        return n < 0 ? 1 : (n >= MAXIMUM_CAPACITY) ?MAXIMUM_CAPACITY : n + 1;
    }

    public void put(Key key, Value value) {
        int hashCode = key.hashCode() % hashTable.length;
        Entry node = hashTable[hashCode];
        if(node == null) {
            Entry newNode = new Entry(key, value);
            hashTable[hashCode] = newNode;
        } else {
            Entry previousNode = node;
            while(node != null) {
                previousNode = node;
                if (node.key == key) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }
            previousNode.next = new Entry(key, value);
        }
    }

    public Value get(Key key) {
        int hashCode = key.hashCode() % hashTable.length;
        Entry<Key, Value> node = hashTable[hashCode];
        while(node!= null) {
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>(4);
        map.put(1, "hi");
        map.put(2, "my");
        map.put(3, "name");
        map.put(4, "is");
        map.put(5, "Shrayansh");
        map.put(6, "how");
        map.put(7, "are");
        map.put(8, "you");
        map.put(9, "friends");
        map.put(10, "?");
        map.put(11, "how");
        map.put(12, "are");
        map.put(13, "you");
        map.put(14, "friends");
        map.put(15, "?");
        String value = map.get(15);
        System.out.println(value);

    }
}
