# LRU Cache Implementation

## Overview

An **LRU Cache** (Least Recently Used Cache) is a data structure that supports efficient operations to cache a limited number of items, evicting the least recently used item when the cache reaches its capacity. This implementation uses a doubly linked list and a hash map to achieve O(1) time complexity for both `get` and `put` operations.

## Key Concepts

- **Doubly Linked List**: Maintains the order of items with the most recently used item at the head and the least recently used item at the tail.
- **Hash Map**: Provides O(1) access to cache items based on keys.

## Data Structures

1. **Doubly Linked List Node**:
    - **Key**: The key of the cache entry.
    - **Value**: The value of the cache entry.
    - **Prev**: Pointer to the previous node.
    - **Next**: Pointer to the next node.

2. **Hash Map**:
    - **Key**: The key of the cache entry.
    - **Value**: Pointer to the corresponding node in the doubly linked list.

## Complexities

- **Get Operation**: `O(1)`
    - Retrieve the value for a given key and move the corresponding node to the head of the list.

- **Put Operation**: `O(1)`
    - Insert a new key-value pair or update an existing pair. If the cache exceeds its capacity, remove the least recently used item.

- **Space Complexity**: `O(capacity)`
    - The space required for the cache is proportional to its capacity.

## Implementation

Here's a Java implementation of an LRU Cache using a doubly linked list and hash map:

```java
import java.util.HashMap;

public class LRUCache {

    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head, tail;

    // Initialize the LRU Cache
    // Complexity: O(1)
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node(-1, -1); // Dummy head
        this.tail = new Node(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    // Get the value of the key if the key exists, otherwise return -1
    // Complexity: O(1)
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;

        // Move the accessed node to the head of the list
        remove(node);
        insert(node);
        return node.value;
    }

    // Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache
    // Complexity: O(1)
    public void put(int key, int value) {
        Node node = map.get(key);

        if (node != null) {
            // Update the value of the existing node
            node.value = value;
            remove(node);
            insert(node);
        } else {
            // If the cache is at capacity, remove the least recently used item
            if (map.size() >= capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            // Insert the new node
            node = new Node(key, value);
            insert(node);
            map.put(key, node);
        }
    }

    // Remove a node from the doubly linked list
    // Complexity: O(1)
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert a node right after the head of the doubly linked list
    // Complexity: O(1)
    private void insert(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // returns 1
        lruCache.put(3, 3); // evicts key 2
        System.out.println(lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4); // evicts key 1
        System.out.println(lruCache.get(1)); // returns -1 (not found)
        System.out.println(lruCache.get(3)); // returns 3
        System.out.println(lruCache.get(4)); // returns 4
    }
}
