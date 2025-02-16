# Segment Trees and Lazy Propagation

## Overview

**Segment Trees** are a data structure used for efficiently answering range queries and updates on an array. They are particularly useful for problems involving dynamic intervals or ranges, such as range sum queries, range minimum queries, and range updates.

**Lazy Propagation** is an optimization technique used in segment trees to handle range updates efficiently. It allows you to delay updates to segments until they are needed, thereby reducing the number of operations required.

## Table of Contents

1. [Segment Tree Basics](#segment-tree-basics)
2. [Lazy Propagation](#lazy-propagation)
3. [Implementation](#implementation)
4. [Example Usage](#example-usage)

## Segment Tree Basics

### What is a Segment Tree?

A Segment Tree is a binary tree used for storing intervals or segments. It allows querying and updating of segments or intervals efficiently. The tree is constructed in such a way that each node represents an interval and stores aggregate information for that interval.

### Key Operations

1. **Build Tree**: Construct the segment tree from the given array.
2. **Range Query**: Query aggregate information (sum, minimum, maximum, etc.) for a given range.
3. **Point Update**: Update a single element in the array and propagate the change to the segment tree.
4. **Range Update**: Update all elements in a given range and propagate the change to the segment tree.

## Lazy Propagation

### What is Lazy Propagation?

Lazy Propagation is a technique used to optimize range updates in a segment tree. Instead of updating all elements immediately, you mark the nodes that need updating and perform the updates only when necessary. This helps in reducing the time complexity of range updates.

### Key Concepts

- **Lazy Array**: An array used to store pending updates for each segment of the tree.
- **Propagation**: The process of applying updates to child nodes when they are needed.

## Implementation

Here is a Java implementation of a Segment Tree with Lazy Propagation:

```java
public class SegmentTreeLazyPropagation {

    private int[] tree;       // Segment tree array
    private int[] lazy;       // Lazy propagation array
    private int n;            // Size of the input array

    // Constructor
    public SegmentTreeLazyPropagation(int size) {
        n = size;
        tree = new int[4 * n];
        lazy = new int[4 * n];
    }

    // Build the segment tree from the given array
    public void build(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, start, mid, 2 * node + 1);
            build(arr, mid + 1, end, 2 * node + 2);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Propagate pending updates
    private void propagate(int start, int end, int node) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node]; // Apply the pending update

            if (start != end) {
                // Not a leaf node
                lazy[2 * node + 1] += lazy[node]; // Mark left child
                lazy[2 * node + 2] += lazy[node]; // Mark right child
            }
            lazy[node] = 0; // Clear the lazy value
        }
    }

    // Range update
    public void updateRange(int l, int r, int value, int start, int end, int node) {
        propagate(start, end, node); // Ensure all pending updates are applied

        if (start > r || end < l) {
            return; // Out of range
        }

        if (start >= l && end <= r) {
            tree[node] += (end - start + 1) * value; // Apply the update
            if (start != end) {
                // Not a leaf node
                lazy[2 * node + 1] += value; // Mark left child
                lazy[2 * node + 2] += value; // Mark right child
            }
            return;
        }

        int mid = (start + end) / 2;
        updateRange(l, r, value, start, mid, 2 * node + 1);
        updateRange(l, r, value, mid + 1, end, 2 * node + 2);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    // Range query
    public int queryRange(int l, int r, int start, int end, int node) {
        propagate(start, end, node); // Ensure all pending updates are applied

        if (start > r || end < l) {
            return 0; // Out of range
        }

        if (start >= l && end <= r) {
            return tree[node]; // Fully within range
        }

        int mid = (start + end) / 2;
        int leftQuery = queryRange(l, r, start, mid, 2 * node + 1);
        int rightQuery = queryRange(l, r, mid + 1, end, 2 * node + 2);
        return leftQuery + rightQuery;
    }

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        SegmentTreeLazyPropagation segmentTree = new SegmentTreeLazyPropagation(size);
        
        // Initialize array with values
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        
        segmentTree.build(arr, 0, size - 1, 0);

        // Range query
        System.out.println("Sum of range [1, 5]: " + segmentTree.queryRange(1, 5, 0, size - 1, 0));

        // Range update
        segmentTree.updateRange(2, 4, 10, 0, size - 1, 0);
        
        // Query again after update
        System.out.println("Sum of range [1, 5] after update: " + segmentTree.queryRange(1, 5, 0, size - 1, 0));
    }
}
