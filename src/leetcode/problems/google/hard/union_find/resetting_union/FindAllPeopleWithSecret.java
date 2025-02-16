package leetcode.problems.google.hard.union_find.resetting_union;

import java.util.*;

public class FindAllPeopleWithSecret {
    /**
     * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.<br>
     * <br>
     * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.<br><br>
     * <p>
     * The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.<br><br>
     * <p>
     * Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.<br><br>
     * <p>
     * <p>
     * <p>
     * Example 1:<br>
     * <p>
     * Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1<br>
     * Output: [0,1,2,3,5]<br><br>
     * Explanation:<br>
     * At time 0, person 0 shares the secret with person 1.<br>
     * At time 5, person 1 shares the secret with person 2.<br>
     * At time 8, person 2 shares the secret with person 3.<br>
     * At time 10, person 1 shares the secret with person 5.<br>
     * Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.<br><br>
     * Example 2:<br>
     * <p>
     * Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3<br>
     * Output: [0,1,3]<br><br>
     * Explanation:<br>
     * At time 0, person 0 shares the secret with person 3.<br>
     * At time 2, neither person 1 nor person 2 know the secret.<br>
     * At time 3, person 3 shares the secret with person 0 and person 1.<br>
     * Thus, people 0, 1, and 3 know the secret after all the meetings.<br><br>
     * Example 3:<br>
     * <p>
     * Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1<br>
     * Output: [0,1,2,3,4]<br><br>
     * Explanation:<br>
     * At time 0, person 0 shares the secret with person 1.<br>
     * At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.<br>
     * Note that person 2 can share the secret at the same time as receiving it.<br>
     * At time 2, person 3 shares the secret with person 4.<br>
     * Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.<br>
     */

    class SolutionBFS {

        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            // For every person, we store the meeting time and label of the person met.
            Map<Integer, List<int[]>> graph = new HashMap<>();
            for (int[] meeting : meetings) {
                int x = meeting[0], y = meeting[1], t = meeting[2];
                graph.computeIfAbsent(x, k -> new ArrayList<>()).add(new int[]{t, y});
                graph.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{t, x});
            }

            // Earliest time at which a person learned the secret
            // as per current state of knowledge. If due to some new information,
            // the earliest time of knowing the secret changes, we will update it
            // and again process all the people whom he/she meets after the time
            // at which he/she learned the secret.
            int[] earliest = new int[n];
            Arrays.fill(earliest, Integer.MAX_VALUE);
            earliest[0] = 0;
            earliest[firstPerson] = 0;

            // Queue for BFS. It will store (person, time of knowing the secret)
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, 0});
            q.offer(new int[]{firstPerson, 0});

            // Do BFS
            while (!q.isEmpty()) {
                int[] personTime = q.poll();
                int person = personTime[0], time = personTime[1];
                for (int[] nextPersonTime : graph.getOrDefault(person, new ArrayList<>())) {
                    int t = nextPersonTime[0], nextPerson = nextPersonTime[1];
                    if (t >= time && earliest[nextPerson] > t) {
                        earliest[nextPerson] = t;
                        q.offer(new int[]{nextPerson, t});
                    }
                }
            }

            // Since we visited only those people who know the secret,
            // we need to return indices of all visited people.
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (earliest[i] != Integer.MAX_VALUE) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }

    class SolutionUFWithReset {

        public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
            // Sort meetings in increasing order of time
            Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

            // Group Meetings in increasing order of time
            Map<Integer, List<int[]>> sameTimeMeetings = new TreeMap<>();
            for (int[] meeting : meetings) {
                int x = meeting[0];
                int y = meeting[1];
                int t = meeting[2];
                sameTimeMeetings.computeIfAbsent(t, k -> new ArrayList<>()).add(new int[]{x, y});
            }

            // Create graph
            UnionFind graph = new UnionFind(n);
            graph.unite(firstPerson, 0);

            // Process in increasing order of time
            for (int t : sameTimeMeetings.keySet()) {
                // Unite two persons taking part in a meeting
                for (int[] meeting : sameTimeMeetings.get(t)) {
                    int x = meeting[0];
                    int y = meeting[1];
                    graph.unite(x, y);
                }

                // If any one knows the secret, both will be connected to 0.
                // If no one knows the secret, then reset.
                for (int[] meeting : sameTimeMeetings.get(t)) {
                    int x = meeting[0], y = meeting[1];
                    if (!graph.connected(x, 0)) {
                        // No need to check for y since x and y were united
                        graph.reset(x);
                        graph.reset(y);
                    }
                }
            }

            // Al those who are connected to 0 will know the secret
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (graph.connected(i, 0)) {
                    ans.add(i);
                }
            }
            return ans;
        }
    }

    class UnionFind {

        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            // Initialize parent and rank arrays
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            // Find parent of node x. Use Path Compression
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void unite(int x, int y) {
            // Unite two nodes x and y, if they are not already united
            int px = find(x);
            int py = find(y);
            if (px != py) {
                // Union by Rank Heuristic
                if (rank[px] > rank[py]) {
                    parent[py] = px;
                } else if (rank[px] < rank[py]) {
                    parent[px] = py;
                } else {
                    parent[py] = px;
                    rank[px] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            // Check if two nodes x and y are connected or not
            return find(x) == find(y);
        }

        public void reset(int x) {
            // Reset the initial properties of node x
            parent[x] = x;
            rank[x] = 0;
        }
    }
}
