package leetcode.problems.google.medium.union_find;

import java.util.ArrayList;
import java.util.Collections;

public class EarliestMomentWhenEveryoneBecomeFriends {

    /*
    *
    * There are n people in a social group labeled from 0 to n - 1.
    * You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
    * Friendship is symmetric. That means if a is friends with b, then b is friends with a.
    * Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
    * Return the earliest time for which every person became acquainted with every other person.
    * If there is no such earliest time, return -1.
    *
    * */

    class Solution {
        public int earliestAcq(int[][] logs, int n) {
            ArrayList<Point> points = new ArrayList<>();
            for(int[] log: logs) {
                points.add(new Point(log[0], log[1], log[2]));
            }
            Collections.sort(points);
            UnionFind uf = new UnionFind(n);
            int count = 0;
            for(Point p: points) {
                int iP = uf.find(p.i);
                int jP = uf.find(p.j);
                if(jP!= iP) {
                    count++;
                    uf.union(p.i, p.j);
                }
                if(count == n-1) return p.time;
            }
            return -1;
        }

        class Point implements Comparable<Point>{
            int time, i, j;
            Point(int time, int i, int j) {
                this.time = time;
                this.i = i;
                this.j = j;
            }

            public int compareTo(Point p) {
                return this.time - p.time;
            }
        }

        static class UnionFind {
            int rank[], parent[];
            UnionFind(int n) {
                rank = new int[n];
                parent = new int[n];
                for(int i = 0; i<n; i++) parent[i] = i;
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if(rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if(rank[rootY] < rank[rootY]) {
                    parent[rootY] = rootY;
                } else {
                    parent[rootX]=rootY;
                    rank[rootY]++;
                }
            }

            public int find(int x) {
                if(parent[x] != x) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }
        }

    }
}
