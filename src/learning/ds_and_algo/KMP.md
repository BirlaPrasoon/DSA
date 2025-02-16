# Knuth-Morris-Pratt (KMP) Algorithm

## Overview

The **Knuth-Morris-Pratt (KMP)** algorithm is a string matching algorithm that efficiently searches for occurrences of a "pattern" string within a "text" string. It improves upon naive string matching algorithms by avoiding redundant comparisons.

## Key Concepts

- **Prefix Function**: The prefix function (also known as the "partial match" table) is used to preprocess the pattern. It indicates the longest proper prefix of the pattern that is also a suffix for each substring of the pattern.
- **Matching Process**: The KMP algorithm uses the prefix function to skip unnecessary comparisons during the search phase, resulting in an efficient pattern search.

## Complexities

- **Preprocessing Time**: `O(m)`
    - Where `m` is the length of the pattern. This is the time required to build the prefix function table.

- **Search Time**: `O(n)`
    - Where `n` is the length of the text. This is the time required to search for the pattern within the text using the prefix function.

- **Space Complexity**: `O(m)`
    - This is the space required to store the prefix function table.

## Implementation

Here's a Java implementation of the KMP algorithm with well-documented complexities:

```java
public class KMPAlgorithm {

    // Compute the prefix function table
    // Complexity: O(2m)
    private static int[] longestPrefixSuffix(String s) {
        int[] prefixTable = new int[s.length()];
        int length = 0;
        for (int i = 1; i < s.length(); i++) {
            while (length > 0 && s.charAt(i) != s.charAt(length)) {
                length = prefixTable[length - 1];
            }
            if (s.charAt(i) == s.charAt(length)) {
                length++;
            }
            prefixTable[i] = length;
        }
        return prefixTable;
    }

    // KMP search algorithm
    // Complexity: O(n)
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = longestPrefixSuffix(pattern);
        int i = 0; // index for text
        int prevLPS = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(prevLPS) == text.charAt(i)) {
                i++;
                prevLPS++;
            }

            if (prevLPS == m) {
                System.out.println("Pattern found at index " + (i - prevLPS));
                prevLPS = lps[prevLPS - 1];
            } else if (i < n && pattern.charAt(prevLPS) != text.charAt(i)) {
                if (prevLPS != 0) {
                    prevLPS = lps[prevLPS - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        // Search for the pattern in the text
        KMPSearch(text, pattern);
    }
}
```

# Knuth-Morris-Pratt (KMP) Algorithm

## Overview

The **Knuth-Morris-Pratt (KMP)** algorithm is an efficient string searching algorithm used to find occurrences of a "pattern" within a "text". It preprocesses the pattern to build a prefix table (or failure function) to avoid redundant comparisons during the search process.

## Key Concepts

- **Prefix Table (π Table)**: Contains the length of the longest proper prefix of the pattern which is also a suffix.
- **Search Process**: Utilizes the prefix table to skip unnecessary comparisons.

## Complexities

- **Time Complexity**: `O(n + m)`, where `n` is the length of the text and `m` is the length of the pattern.
- **Space Complexity**: `O(m)` for the prefix table.

## Algorithm Steps

### 1. Build the Prefix Table

1. **Initialize**:
  - `π[0] = 0` (single character has no proper prefix).

2. **Compute the Prefix Table**:

   For a pattern `P = ABAB`:
  - `π = [0, 0, 1, 2]`

### 2. Search the Text

1. **Initialize Pointers**:
  - `i = 0` (pointer for text)
  - `j = 0` (pointer for pattern)

2. **Search Process**:
  - Compare `text[i]` with `pattern[j]`.
  - If `text[i] == pattern[j]`, move both pointers forward (`i++`, `j++`).
  - If `j == m` (pattern length), a match is found at `i - m`. Reset `j` using the prefix table: `j = π[j - 1]`.
  - If `text[i] != pattern[j]`:
    - If `j != 0`, reset `j` using the prefix table: `j = π[j - 1]`.
    - If `j == 0`, move `i` forward (`i++`).

## Example

**Text**: `ABABABABABABABAB`

**Pattern**: `ABAB`

### Prefix Table Construction

1. **Pattern**: `ABAB`
  - **Prefix Table**:
    - `π[0] = 0`
    - `π[1] = 0`
    - `π[2] = 1`
    - `π[3] = 2`

### Search Execution

1. **Initialize**:
  - `i = 0`, `j = 0`

2. **Step-by-Step**:

  - **Step 1**:
    - Compare `text[0]` with `pattern[0]`: Match (`A == A`)
    - Move to `i = 1`, `j = 1`

  - **Step 2**:
    - Compare `text[1]` with `pattern[1]`: Match (`B == B`)
    - Move to `i = 2`, `j = 2`

  - **Step 3**:
    - Compare `text[2]` with `pattern[2]`: Match (`A == A`)
    - Move to `i = 3`, `j = 3`

  - **Step 4**:
    - Compare `text[3]` with `pattern[3]`: Match (`B == B`)
    - `j == 4` (length of the pattern), match found at index `i - j = 0`
    - Reset `j = π[j - 1] = 2`

  - **Step 5**:
    - Continue with `i = 4`, `j = 2`
    - Compare `text[4]` with `pattern[2]`: Match (`A == A`)
    - Move to `i = 5`, `j = 3`

  - **Step 6**:
    - Compare `text[5]` with `pattern[3]`: Match (`B == B`)
    - `j == 4`, match found at index `i - j = 1`
    - Reset `j = π[j - 1] = 2`

  - **Repeat**:
    - Continue with `i = 6`, `j = 2` and so on.
    - Matches are found at indices `0`, `1`, `2`, `3`, `4`, `5`, `6`, etc.

## Summary

- The KMP algorithm efficiently finds all occurrences of the pattern `ABAB` in the text `ABABABABABABABAB`.
- Matches are found at indices `0`, `1`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, etc.
- The prefix table allows the algorithm to skip unnecessary comparisons.

---

Feel free to use this README to understand and implement the KMP algorithm effectively.
