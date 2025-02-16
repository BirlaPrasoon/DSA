# Suffix Tree

## Overview

A **Suffix Tree** is a compressed trie (or prefix tree) that represents all suffixes of a given string. It is a powerful data structure used for various string processing problems, such as substring search, longest common substring, and pattern matching. The Suffix Tree allows efficient querying and is especially useful in bioinformatics and text processing.

## Key Concepts

- **Suffix Tree**: A tree that contains all suffixes of a given string as paths from the root.
- **Edge Labels**: Represent substrings of the original string.
- **Nodes**: Represent common prefixes among suffixes.

## Complexities

- **Construction Time**: `O(n)` for a string of length `n` using Ukkonen’s algorithm.
- **Space Complexity**: `O(n)`.

## Operations

### 1. Construction

To construct a Suffix Tree for a string `S` of length `n`, you can use Ukkonen’s algorithm, which is efficient and builds the tree in linear time.

### 2. Searching

You can search for a substring or pattern within the Suffix Tree efficiently. Each search operation typically runs in `O(m)` time, where `m` is the length of the pattern.

## Example

Let’s walk through an example of constructing and querying a Suffix Tree.

### Example String

**String**: `banana$

The `$` symbol is used as a unique end marker for the string.

### Construction

1. **Suffixes**: Generate all suffixes of the string `banana$`:
    - `banana$`
    - `anana$`
    - `nana$`
    - `ana$`
    - `na$`
    - `a$`
    - `$`

2. **Build the Tree**:
    - Construct the tree such that each suffix corresponds to a path from the root to a leaf.

### Example Tree

Below is a simplified visual representation of the Suffix Tree for `banana$`:

     root
     /|\
    / | \
   b  a  $
  /   |   
 a    n  


### Searching for a Pattern

**Pattern**: `ana`

1. Start at the root and follow the path corresponding to the pattern.
2. For `ana`, follow `a -> n -> a`.

**Search Process**:
- Start at the root.
- Follow the edge labeled `a` to the next node.
- Continue with the edge labeled `n` to the next node.
- Follow the edge labeled `a` to find the matches.

### Code Example (Java)

Here is a basic outline for constructing a Suffix Tree using a simplified approach. For a full implementation, you would typically use Ukkonen’s algorithm.

```java
import java.util.HashMap;
import java.util.Map;

class SuffixTree {
    class Node {
        Map<Character, Node> children = new HashMap<>();
        int start, end;
        
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private final String text;
    private final Node root;

    public SuffixTree(String text) {
        this.text = text;
        this.root = new Node(-1, -1);
        buildSuffixTree();
    }

    private void buildSuffixTree() {
        int n = text.length();
        for (int i = 0; i < n; i++) {
            addSuffix(text.substring(i));
        }
    }

    private void addSuffix(String suffix) {
        Node currentNode = root;
        for (char c : suffix.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(c, k -> new Node(-1, -1));
        }
    }

    // Basic search function (not optimized)
    public boolean search(String pattern) {
        Node currentNode = root;
        for (char c : pattern.toCharArray()) {
            currentNode = currentNode.children.get(c);
            if (currentNode == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SuffixTree tree = new SuffixTree("banana$");
        System.out.println(tree.search("ana"));  // true
        System.out.println(tree.search("nan"));  // true
        System.out.println(tree.search("ban"));  // true
        System.out.println(tree.search("xyz"));  // false
    }
}
```
