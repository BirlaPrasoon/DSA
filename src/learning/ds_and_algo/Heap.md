# Heap Data Structure

## Overview

A **Heap** is a specialized tree-based data structure that satisfies the heap property. It is commonly used to implement priority queues, where the highest (or lowest) priority element is always accessible in constant time. Heaps are typically represented as binary trees, but can be efficiently implemented using arrays.

## Types of Heaps

- **Min-Heap**: The key at the root node is the smallest among all keys in the heap. Every parent node has a value less than or equal to its children.
- **Max-Heap**: The key at the root node is the largest among all keys in the heap. Every parent node has a value greater than or equal to its children.

## Complexities

- **Insertion**: `O(log n)`
    - Adding a new element to the heap requires logarithmic time to maintain the heap property.

- **Deletion of Min/Max**: `O(log n)`
    - Removing the root (minimum in a min-heap or maximum in a max-heap) requires logarithmic time to restore the heap property.

- **Peek Min/Max**: `O(1)`
    - Accessing the root element (the minimum or maximum) takes constant time.

- **Heapify**: `O(n)`
    - Building a heap from an unordered array can be done in linear time.

## Implementation

Here's a Java implementation of a Min-Heap with well-documented complexities:

```java
import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1]; // Index 0 is unused for convenience
    }

    // Insert a new element into the heap
    // Complexity: O(log n)
    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[++size] = value;
        int current = size;

        // Heapify up
        while (current > 1 && heap[current] < heap[current / 2]) {
            swap(current, current / 2);
            current /= 2;
        }
    }

    // Remove and return the minimum element from the heap
    // Complexity: O(log n)
    public int removeMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return min;
    }

    // Peek the minimum element without removing it
    // Complexity: O(1)
    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[1];
    }

    // Restore the heap property after removal
    private void heapify(int index) {
        int smallest = index;
        int leftChild = 2 * index;
        int rightChild = 2 * index + 1;

        if (leftChild <= size && heap[leftChild] < heap[smallest]) {
            smallest = leftChild;
        }

        if (rightChild <= size && heap[rightChild] < heap[smallest]) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        // Inserting elements
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(15);

        // Peeking the minimum element
        System.out.println("Min element: " + minHeap.peekMin()); // Output: 5

        // Removing and printing the minimum element
        System.out.println("Removed min: " + minHeap.removeMin()); // Output: 5

        // Peeking the new minimum element
        System.out.println("New min element: " + minHeap.peekMin()); // Output: 10
    }
}
