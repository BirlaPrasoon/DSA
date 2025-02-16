package leetcode.problems.google.hard.graphs.dijkstra;

import java.util.*;

public class PathWithMinimumEffort {

    /**
     * You are a hiker preparing for an upcoming hike. </br>
     * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).<br>
     * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
     * <br>You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.<br>
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.<br>
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.<br>
     */


    class SolutionDijkstra {
        int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            int[][] differenceMatrix = new int[row][col];
            for (int[] eachRow : differenceMatrix)
                Arrays.fill(eachRow, Integer.MAX_VALUE);
            differenceMatrix[0][0] = 0;
            PriorityQueue<Cell> queue = new PriorityQueue<Cell>(Comparator.comparing(a -> a.difference));
            boolean[][] visited = new boolean[row][col];
            queue.add(new Cell(0, 0, differenceMatrix[0][0]));

            while (!queue.isEmpty()) {
                Cell curr = queue.poll();
                visited[curr.x][curr.y] = true;

                if (curr.x == row - 1 && curr.y == col - 1)
                    return curr.difference;

                for (int[] direction : directions) {

                    int adjacentX = curr.x + direction[0];
                    int adjacentY = curr.y + direction[1];

                    if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                        int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                        int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                        if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                            differenceMatrix[adjacentX][adjacentY] = maxDifference;
                            queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                        }
                    }
                }
            }
            return differenceMatrix[row - 1][col - 1];
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }
    }

    record Cell(int x, int y, Integer difference) { }

    class Solution {
        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            if (row == 1 && col == 1) return 0;
            UnionFind unionFind = new UnionFind(heights);
            List<Edge> edgeList = unionFind.edgeList;
            edgeList.sort(Comparator.comparingInt(e -> e.difference));

            for (Edge edge : edgeList) {
                int x = edge.x;
                int y = edge.y;
                unionFind.union(x, y);
                if (unionFind.find(0) == unionFind.find(row * col - 1)) return edge.difference;
            }
            return -1;
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        List<Edge> edgeList;

        public UnionFind(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            parent = new int[row * col];
            edgeList = new ArrayList<>();
            rank = new int[row * col];
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) {
                    if (currentRow > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                (currentRow - 1) * col + currentCol,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol]))
                        );
                    }
                    if (currentCol > 0) {
                        edgeList.add(new Edge(currentRow * col + currentCol,
                                currentRow * col + currentCol - 1,
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1]))
                        );
                    }
                    parent[currentRow * col + currentCol] = currentRow * col + currentCol;
                }
            }
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                if (rank[parentX] > rank[parentY]) parent[parentY] = parentX;
                else if (rank[parentX] < rank[parentY]) parent[parentX] = parentY;
                else {
                    parent[parentY] = parentX;
                    rank[parentX] += 1;
                }
            }
        }
    }

    class Edge {
        int x;
        int y;
        int difference;

        Edge(int x, int y, int difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }

}
