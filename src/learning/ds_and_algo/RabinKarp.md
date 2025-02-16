# Rabin-Karp Algorithm

## Overview

The **Rabin-Karp** algorithm is a string matching algorithm that uses hashing to find occurrences of a "pattern" string
within a "text" string. It is particularly useful when searching for multiple patterns simultaneously. The algorithm
hashes substrings of the text and compares these hashes to the hash of the pattern to find matches.

## Key Concepts

- **Hashing**: Rabin-Karp uses a rolling hash function to compute hash values of substrings in the text.
- **Rolling Hash**: This technique allows the algorithm to compute the hash of the next substring using the hash of the
  previous substring efficiently.
- **Hash Comparison**: By comparing hash values, the algorithm can quickly check if the current substring matches the
  pattern.

## Complexities

- **Preprocessing Time**: `O(m)`
    - Where `m` is the length of the pattern. This is the time required to compute the hash of the pattern.

- **Search Time**: `O(n)`
    - Where `n` is the length of the text. This is the time required to search for the pattern using the rolling hash.

- **Space Complexity**: `O(1)`
    - The space required is constant, aside from the input size.

## Implementation

Here's a Java implementation of the Rabin-Karp algorithm with well-documented complexities:

```java
public class RabinKarpAlgorithm {

    private static final int PRIME = 101; // A prime number for hashing

    // Compute the hash of a string
    // Complexity: O(m)
    private static long computeHash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash * PRIME + str.charAt(i)) % Integer.MAX_VALUE;
        }
        return hash;
    }

    // Update the hash value using rolling hash
    // Complexity: O(1)
    private static long rollHash(long oldHash, char oldChar, char newChar, long primePow) {
        long newHash = (oldHash - oldChar * primePow) * PRIME + newChar;
        newHash %= Integer.MAX_VALUE;
        if (newHash < 0) {
            newHash += Integer.MAX_VALUE;
        }
        return newHash;
    }

    // Rabin-Karp search algorithm
    // Complexity: O(n)
    public static void rabinKarpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) {
            return;
        }

        long patternHash = computeHash(pattern, m);
        long textHash = computeHash(text, m);
        long primePow = 1;

        // Compute primePow = PRIME^(m-1)
        for (int i = 0; i < m - 1; i++) {
            primePow = (primePow * PRIME) % Integer.MAX_VALUE;
        }

        for (int i = 0; i+m <= n; i++) {
            if (patternHash == textHash && text.substring(i, i + m).equals(pattern)) {
                System.out.println("Pattern found at index " + i);
            }

            if (i + m < n) {
                textHash = rollHash(textHash, text.charAt(i), text.charAt(i + m), primePow);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABABA";
        String pattern = "ABA";

        // Search for the pattern in the text
        rabinKarpSearch(text, pattern);
    }
}
