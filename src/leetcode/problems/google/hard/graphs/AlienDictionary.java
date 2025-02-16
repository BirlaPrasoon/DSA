package leetcode.problems.google.hard.graphs;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words.length == 0) return "";
        if (words.length == 1) return words[0];

        Map<Character, Integer> degree = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();

        for (String word : words) {
            for (int j = 0; j < word.length(); j++) {
                degree.put(word.charAt(j), 0);
                // adding this character as a vertex in group so that we don't get
                // null pointer exception while running Kahn's algorithm on graph.
                graph.put(word.charAt(j), new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            if (cur.length() > next.length() && cur.startsWith(next)) {
                // Verifying order to be sorted, if not, return empty string.
                return "";
            }
            int len = Math.min(cur.length(), next.length());
            for (int j = 0; j < len; j++) {
                if (cur.charAt(j) != next.charAt(j)) {
                    graph.putIfAbsent(cur.charAt(j), new HashSet<>());
                    // To avoid duplicate edges, either calculate indegrees after graph is formed,
                    // or check if the edge is created, then only increase the degree of j'th vertex.
                    if (graph.get(cur.charAt(j)).add(next.charAt(j))) {
                        degree.put(next.charAt(j), degree.get(next.charAt(j)) + 1);
                    }
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) {
                q.offer(c);
            }
        }
        while (!q.isEmpty()) {
            Character c = q.poll();
            sb.append(c);
            // This is a very good edge case. but we can handle that at the time of creating the group
            // and maintain each and every vertex to be part of the graph even if it doesn't have any edge.
            if (!graph.containsKey(c)) {
                continue;
            }

            for (Character nei : graph.get(c)) {
                degree.put(nei, degree.get(nei) - 1);
                if (degree.get(nei) == 0) {
                    q.offer(nei);
                }
            }
        }

        if (sb.toString().length() != degree.size()) {
            return "";
        }
        return sb.toString();
    }
}
