package leetcode.problems.google.hard.union_find;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands2 {

    /**
     * <h1>Problem Statement</h1>
     * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).</br>
     * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.</br>
     * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.</br>
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</br>
     * <h3>Example 1</h3>
     * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]</br>
     * Output: [1,1,2,3]</br>
     * <b>Explanation:</b></br>
     * Initially, the 2d grid is filled with water.</br>
     * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.</br>
     * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.</br>
     * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.</br>
     * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.</br></br>
     * <b>Example 2:</b></br>
     * <p>
     * Input: m = 1, n = 1, positions = [[0,0]]</br>
     * Output: [1]</br>
     */

    class UnionFind {
        int[] parent;
        int[] rank;
        int count;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = -1;
            count = 0;
        }

        public void addLand(int x) {
            if (parent[x] >= 0)
                return;
            parent[x] = x;
            count++;
        }

        public boolean isLand(int x) {
            if (parent[x] >= 0) {
                return true;
            } else {
                return false;
            }
        }

        int numberOfIslands() {
            return count;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int xset = find(x), yset = find(y);
            if (xset == yset) {
                return;
            } else if (rank[xset] < rank[yset]) {
                parent[xset] = yset;
            } else if (rank[xset] > rank[yset]) {
                parent[yset] = xset;
            } else {
                parent[yset] = xset;
                rank[xset]++;
            }
            count--;
        }
    }

    class Solution {
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int x[] = {-1, 1, 0, 0};
            int y[] = {0, 0, -1, 1};
            UnionFind dsu = new UnionFind(m * n);
            List<Integer> answer = new ArrayList<>();

            for (int[] position : positions) {
                int landPosition = position[0] * n + position[1];
                dsu.addLand(landPosition);

                for (int i = 0; i < 4; i++) {
                    int neighborX = position[0] + x[i];
                    int neighborY = position[1] + y[i];
                    int neighborPosition = neighborX * n + neighborY;
                    // If neighborX and neighborY correspond to a point in the grid and there is a
                    // land at that point, then merge it with the current land.
                    if (neighborX >= 0 && neighborX < m && neighborY >= 0 && neighborY < n &&
                            dsu.isLand(neighborPosition)) {
                        dsu.union(landPosition, neighborPosition);
                    }
                }
                answer.add(dsu.numberOfIslands());
            }
            return answer;
        }
    }
}
