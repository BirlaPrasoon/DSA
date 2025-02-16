package leetcode.problems.google.hard.union_find.trick;

import java.util.HashSet;
import java.util.Set;

public class CouplesHoldingHands {

    // Bruteforce TLE O(n.2^n)
    class Solution {
        int N;
        int[][] pairs;

        public int minSwapsCouples(int[] row) {
            N = row.length / 2;
            pairs = new int[N][2];
            for (int i = 0; i < N; ++i) {
                pairs[i][0] = row[2*i] / 2;
                pairs[i][1] = row[2*i+1] / 2;
            }

            return solve(0);
        }

        public void swap(int a, int b, int c, int d) {
            int t = pairs[a][b];
            pairs[a][b] = pairs[c][d];
            pairs[c][d] = t;
        }

        public int solve(int i) {
            if (i == N) return 0;
            int x = pairs[i][0], y = pairs[i][1];
            if (x == y) return solve(i+1);

            int jx=0, kx=0, jy=0, ky=0; // Always gets set later
            for (int j = i+1; j < N; ++j) {
                for (int k = 0; k <= 1; ++k) {
                    if (pairs[j][k] == x) {jx = j; kx = k;}
                    if (pairs[j][k] == y) {jy = j; ky = k;}
                }
            }

            swap(i, 1, jx, kx);
            int ans1 = 1 + solve(i+1);
            swap(i, 1, jx, kx);

            swap(i, 0, jy, ky);
            int ans2 = 1 + solve(i+1);
            swap(i, 0, jy, ky);

            return Math.min(ans1, ans2);
        }
    }

    // Greedy O(N^2)
    class SolutionGreedy {
        public int minSwapsCouples(int[] row) {
            int ans = 0;

            // Bitwise XOR will simply add 1 to even and subtract 1 from odd numbers
            for (int i = 0; i < row.length; i += 2) {
                int x = row[i];
                int lookingFor = x%2 == 0 ? x+1 : x-1;
                if (row[i+1] == lookingFor) continue;
                ans++;
                for (int j = i+1; j < row.length; ++j) {
                    if (row[j] == lookingFor) {
                        row[j] = row[i+1];
                        row[i+1] = lookingFor;
                        break;
                    }
                }
            }
            return ans;
        }
    }

    // O(N)
    class SolutionUF {

        public int minSwapsCouples(int[] row) {
            int n = row.length / 2;
            UnionFind uf = new UnionFind(n);
            // Initialize parent array where each couple has same parent

            // Connect couples who are sitting together (might not be correct pairs)
            for (int i = 0; i < row.length; i += 2) {
                uf.union(row[i]/2, row[i+1]/2);
            }

            // Count number of connected components
            // Each component will need (size - 1) swaps to fix
            // Count size of each component
            int anotherSwaps = 0;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int root = uf.find(i);
                set.add(root);
            }
            for(int x: set) {
                int root = uf.find(x);
                anotherSwaps += uf.size[root]-1;
            }
            return anotherSwaps;
        }
    }

    class UnionFind {
        int parent[], size[], componentSize;
        UnionFind(int n) {
            this.componentSize = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if(parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        private boolean union(int x, int y) {
            int rX = find(x);
            int rY = find(y);
            if(rX == rY) return false;
            if(size[rX] > size[rY]) {
                parent[rY] = rX;
                size[rX] += size[rY];
            } else {
                parent[rX] = rY;
                size[rY] += size[rX];
            }
            return true;
        }
    }

}
