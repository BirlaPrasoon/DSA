# Fenwick Tree (Binary Indexed Tree)

## Overview

**Fenwick Tree** (or Binary Indexed Tree) is a data structure that provides efficient methods for cumulative frequency tables, often used for range sum queries and updates. It supports operations in logarithmic time, making it ideal for scenarios where frequent updates and queries are required.

## Table of Contents

1. [Introduction](#introduction)
2. [Complexities](#complexities)
3. [Implementation](#implementation)
4. [Example Usage](#example-usage)

## Introduction

The Fenwick Tree is a binary tree-like data structure that efficiently supports the following operations:

- **Point Updates**: Update the value at a specific index.
- **Prefix Sum Queries**: Calculate the sum of elements from the start up to a specific index.
- **Range Sum Queries**: Calculate the sum of elements between two indices.

## Complexities

- **Update Operation**: `O(log n)`
    - Updating the value at a specific index requires logarithmic time.

- **Prefix Sum Query**: `O(log n)`
    - Calculating the sum of elements from the start up to a specific index requires logarithmic time.

- **Range Sum Query**: `O(log n)`
    - Calculating the sum of elements between two indices can be done using two prefix sum queries in logarithmic time.

## Implementation

Here's a Java implementation of a Fenwick Tree with well-documented complexities:

```java
public class FenwickTree {
    private int[] tree; // Fenwick Tree array
    private int n; // Size of the array

    // Constructor
    public FenwickTree(int size) {
        n = size;
        tree = new int[n + 1];
    }

    // Update the value at index i
    // Complexity: O(log n)
    public void update(int i, int delta) {
        while (i <= n) {
            tree[i] += delta;
            i += i & -i; // Move to the next index
        }
    }

    // Compute prefix sum from 1 to i
    // Complexity: O(log n)
    public int prefixSum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i; // Move to the parent index
        }
        return sum;
    }

    // Compute the range sum from left to right
    // Complexity: O(log n)
    public int rangeSum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {
        int size = 10;
        FenwickTree fenwickTree = new FenwickTree(size);

        // Updating values
        fenwickTree.update(3, 5); // Add 5 to index 3
        fenwickTree.update(5, 2); // Add 2 to index 5

        // Querying prefix sums
        System.out.println("Prefix sum up to index 5: " + fenwickTree.prefixSum(5)); // Output: 7

        // Querying range sums
        System.out.println("Range sum from index 3 to 5: " + fenwickTree.rangeSum(3, 5)); // Output: 7
    }
}
