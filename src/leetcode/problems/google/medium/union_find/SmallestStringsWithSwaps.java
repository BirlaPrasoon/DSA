package leetcode.problems.google.medium.union_find;

import datastructures.UnionFind;

import java.util.*;

public class SmallestStringsWithSwaps {

    /*
    * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b]
    * indicates 2 indices(0-indexed) of the string.
    *
    * You can swap the characters at any pair of indices in the given pairs any number of times.
    *
    * Return the lexicographically smallest string that s can be changed to after using the swaps.
    *
    *
    *

    Example 1:
    Input: s = "dcab", pairs = [[0,3],[1,2]]
    Output: "bacd"
    Explanation:
    Swap s[0] and s[3], s = "bcad"
    Swap s[1] and s[2], s = "bacd"

    Example 2:
    Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    Output: "abcd"
    Explanation:
    Swap s[0] and s[3], s = "bcad"
    Swap s[0] and s[2], s = "acbd"
    Swap s[1] and s[2], s = "abcd"

    Example 3:
    Input: s = "cba", pairs = [[0,1],[1,2]]
    Output: "abc"
    Explanation:
    Swap s[0] and s[1], s = "bca"
    Swap s[1] and s[2], s = "bac"
    Swap s[0] and s[1], s = "abc"

    * */

    class SolutionDFS {
        // Maximum number of vertices
        final static int N = 100001;
        boolean[] visited = new boolean[N];
        List<Integer>[] adj = new ArrayList[N];

        private void DFS(String s, int vertex, List<Character> characters, List<Integer> indices) {
            // Add the character and index to the list
            characters.add(s.charAt(vertex));
            indices.add(vertex);

            visited[vertex] = true;

            // Traverse the adjacents
            for (int adjacent : adj[vertex]) {
                if (!visited[adjacent]) {
                    DFS(s, adjacent, characters, indices);
                }
            }
        }

        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            for (int i = 0; i < s.length(); i++) {
                adj[i] = new ArrayList<Integer>();
            }

            // Build the adjacency list
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);

                // Undirected edge
                adj[source].add(destination);
                adj[destination].add(source);
            }

            char[] answer = new char[s.length()];
            for (int vertex = 0; vertex < s.length(); vertex++) {
                // If not covered in the DFS yet
                if (!visited[vertex]) {
                    List<Character> characters = new ArrayList<>();
                    List<Integer> indices = new ArrayList<>();

                    DFS(s, vertex, characters, indices);
                    // Sort the list of characters and indices
                    Collections.sort(characters);
                    Collections.sort(indices);

                    // Store the sorted characters corresponding to the index
                    for (int index = 0; index < characters.size(); index++) {
                        answer[indices.get(index)] = characters.get(index);
                    }
                }
            }
            return new String(answer);
        }
    }

    class SolutionUF {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
            UnionFind uf = new UnionFind(s.length());

            // Iterate over the edges
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);

                // Perform the union of end points
                uf.union(source, destination);
            }

            Map<Integer, List<Integer>> rootToComponent = new HashMap<>();
            // Group the vertices that are in the same component
            for (int vertex = 0; vertex < s.length(); vertex++) {
                int root = uf.find(vertex);
                // Add the vertices corresponding to the component root
                rootToComponent.putIfAbsent(root, new ArrayList<>());
                rootToComponent.get(root).add(vertex);
            }

            // String to store the answer
            char[] smallestString = new char[s.length()];
            // Iterate over each component
            for (List<Integer> indices : rootToComponent.values()) {
                // Sort the characters in the group
                List<Character> characters = new ArrayList<>();
                for (int index : indices) {
                    characters.add(s.charAt(index));
                }
                Collections.sort(characters);

                // Store the sorted characters
                for (int index = 0; index < indices.size(); index++) {
                    smallestString[indices.get(index)] = characters.get(index);
                }
            }

            return new String(smallestString);
        }
    }

}
