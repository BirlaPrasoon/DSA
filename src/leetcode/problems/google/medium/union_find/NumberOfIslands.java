package leetcode.problems.google.medium.union_find;

public class NumberOfIslands {
    /*
    *
    * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    * You may assume all four edges of the grid are all surrounded by water.
    *
    * */
    class SolutionUF {
        class UnionFind {
            int count; // # of connected components
            int[] parent;
            int[] rank;

            public UnionFind(char[][] grid) { // for problem 200
                count = 0;
                int m = grid.length;
                int n = grid[0].length;
                parent = new int[m * n];
                rank = new int[m * n];
                for (int i = 0; i < m; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (grid[i][j] == '1') {
                            parent[i * n + j] = i * n + j;
                            ++count;
                        }
                        rank[i * n + j] = 0;
                    }
                }
            }

            public int find(int i) { // path compression
                if (parent[i] != i) parent[i] = find(parent[i]);
                return parent[i];
            }

            public void union(int x, int y) { // union with rank
                int rootx = find(x);
                int rooty = find(y);
                if (rootx != rooty) {
                    if (rank[rootx] > rank[rooty]) {
                        parent[rooty] = rootx;
                    } else if (rank[rootx] < rank[rooty]) {
                        parent[rootx] = rooty;
                    } else {
                        parent[rooty] = rootx;
                        rank[rootx] += 1;
                    }
                    --count;
                }
            }

            public int getCount() {
                return count;
            }
        }

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int nr = grid.length;
            int nc = grid[0].length;
            UnionFind uf = new UnionFind(grid);
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    if (grid[r][c] == '1') {
                        grid[r][c] = '0';
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            uf.union(r * nc + c, (r - 1) * nc + c);
                        }
                        if (r + 1 < nr && grid[r + 1][c] == '1') {
                            uf.union(r * nc + c, (r + 1) * nc + c);
                        }
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            uf.union(r * nc + c, r * nc + c - 1);
                        }
                        if (c + 1 < nc && grid[r][c + 1] == '1') {
                            uf.union(r * nc + c, r * nc + c + 1);
                        }
                    }
                }
            }

            return uf.getCount();
        }
    }

    class SolutioDFS {
        public int numIslands(char[][] grid) {
            int count =0;
            for(int i = 0; i<grid.length; i++) {
                for(int j=0; j<grid[i].length; j++) {
                    if(grid[i][j] == '1') {
                        count++;
                        dfs(grid, i, j);
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int i, int j) {
            if(!canGo(grid, i, j)) return;
            grid[i][j] = '2';
            dfs(grid, i+1, j);
            dfs(grid, i-1, j);
            dfs(grid, i, j-1);
            dfs(grid, i, j+1);
        }

        private boolean canGo(char[][] grid, int i, int j) {
            if(i<0 || j<0 || i>= grid.length || j>= grid[0].length) return false;
            if(grid[i][j] == '1') return true;
            return false;
        }
    }
}
