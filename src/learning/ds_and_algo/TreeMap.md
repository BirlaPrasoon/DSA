# TreeMap Java Class Documentation

## Overview

`TreeMap` is part of Java's Collections Framework and implements the `NavigableMap` interface. It uses a Red-Black tree to store key-value pairs in a sorted order. The map provides efficient operations for querying and modifying sorted data.

## Methods Overview

- `TreeMap` provides operations to manage and access key-value pairs, support range queries, and handle sorted entries.

## Entry Methods

- **`Map.Entry<K, V> firstEntry()`**
    - Returns the entry for the least (first) key.

- **`Map.Entry<K, V> lastEntry()`**
    - Returns the entry for the greatest (last) key.

- **`Map.Entry<K, V> floorEntry(K key)`**
    - Returns the greatest entry less than or equal to the given key, or `null` if none exists.

- **`Map.Entry<K, V> ceilingEntry(K key)`**
    - Returns the smallest entry greater than or equal to the given key, or `null` if none exists.

- **`Map.Entry<K, V> lowerEntry(K key)`**
    - Returns the entry strictly less than the given key, or `null` if none exists.

- **`Map.Entry<K, V> higherEntry(K key)`**
    - Returns the entry strictly greater than the given key, or `null` if none exists.

## Key Methods

- **`K firstKey()`**
    - Returns the first (lowest) key currently in the map.

- **`K lastKey()`**
    - Returns the last (highest) key currently in the map.

- **`K floorKey(K key)`**
    - Returns the greatest key less than or equal to the given key, or `null` if none exists.

- **`K ceilingKey(K key)`**
    - Returns the least key greater than or equal to the given key, or `null` if none exists.

- **`K lowerKey(K key)`**
    - Returns the greatest key strictly less than the given key, or `null` if none exists.

- **`K higherKey(K key)`**
    - Returns the least key strictly greater than the given key, or `null` if none exists.

## SubMap Methods

- **`SortedMap<K, V> headMap(K toKey)`**
    - Returns a view of the portion of the map whose keys are strictly less than `toKey`.

- **`SortedMap<K, V> tailMap(K fromKey)`**
    - Returns a view of the portion of the map whose keys are greater than or equal to `fromKey`.

- **`SortedMap<K, V> subMap(K fromKey, K toKey)`**
    - Returns a view of the portion of the map whose keys range from `fromKey` to `toKey` (exclusive).

- **`NavigableMap<K, V> descendingMap()`**
    - Returns a reverse-order view of the map.

## Navigable Methods

- **`Map.Entry<K, V> pollFirstEntry()`**
    - Removes and returns the first entry, or returns `null` if the map is empty.

- **`Map.Entry<K, V> pollLastEntry()`**
    - Removes and returns the last entry, or returns `null` if the map is empty.

## General Methods

- **`V put(K key, V value)`**
    - Associates the specified value with the specified key.

- **`V remove(Object key)`**
    - Removes the mapping for the specified key if present.

- **`V get(Object key)`**
    - Returns the value to which the specified key is mapped, or `null` if no mapping exists.

- **`boolean containsKey(Object key)`**
    - Returns `true` if this map contains a mapping for the specified key.

- **`boolean containsValue(Object value)`**
    - Returns `true` if this map maps one or more keys to the specified value.

- **`void clear()`**
    - Removes all the mappings from the map.

- **`int size()`**
    - Returns the number of key-value mappings in the map.

- **`boolean isEmpty()`**
    - Returns `true` if the map contains no key-value mappings.

## Collection Views

- **`Set<K> keySet()`**
    - Returns a set view of the keys contained in this map.

- **`Collection<V> values()`**
    - Returns a collection view of the values contained in this map.

- **`Set<Map.Entry<K, V>> entrySet()`**
    - Returns a set view of the mappings contained in this map.

## Comparator Methods

- **`Comparator<? super K> comparator()`**
    - Returns the comparator used to order the keys in this map, or `null` if the map uses the natural ordering of its keys.

## Example Usage

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        // Adding elements
        treeMap.put(10, "A");
        treeMap.put(20, "B");
        treeMap.put(30, "C");
        treeMap.put(40, "D");

        // Access methods
        System.out.println("First entry: " + treeMap.firstEntry()); // First entry
        System.out.println("Last entry: " + treeMap.lastEntry());   // Last entry
        System.out.println("Floor entry for 25: " + treeMap.floorEntry(25)); // Entry <= 25
        System.out.println("Ceiling entry for 25: " + treeMap.ceilingEntry(25)); // Entry >= 25

        // Query key existence
        System.out.println("Contains key 20: " + treeMap.containsKey(20)); // true
        System.out.println("Value for key 10: " + treeMap.get(10)); // "A"

        // SubMap
        System.out.println("SubMap(10, 30): " + treeMap.subMap(10, 30)); // {10=A, 20=B}

        // Removing an entry
        treeMap.remove(20);
        System.out.println("After removing key 20: " + treeMap);
    }
}
