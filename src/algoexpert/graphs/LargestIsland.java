package algoexpert.graphs;

import java.util.*;

public class LargestIsland {

    static class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (a != pair.a) return false;
            return b == pair.b;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }

    public int largestIsland(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) matrix[i][j] = 1;
                else if(matrix[i][j] == 1) matrix[i][j] = 0;
            }
        }
        HashSet<Pair> visited = new HashSet<>();
        HashMap<Pair, Integer> parents = new HashMap<>();
        int parentNumber = 0;
        for (int i=0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                Pair p = new Pair(i, j);
                if(matrix[i][j] == 1 && !visited.contains(p)) {
                    HashSet<Pair> path = new HashSet<>();
                    dfs(i, j, matrix, visited, path);
                    int size = path.size();
                    for(Pair x: path) {
                        matrix[x.a][x.b] = size;
                        // Note: Make sure you assign the right parentNumber to every pair. This was a good thought.
                        parents.put(x, parentNumber);
                    }
                    parentNumber++;
                }
            }
        }
        int maxSize  = 0;
        for (int i=0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                Pair p = new Pair(i, j);
                int sum = 0;
                if(matrix[i][j] == 0) {
                    for(Pair dir: getNonZeroDirsWithDiffParents(i,j, parents, matrix)){
                        sum += matrix[dir.a][dir.b];
                    }
                    sum+=1;
                    if(sum > maxSize) {
                        maxSize = sum;
                    }
                }
            }
        }

        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
        return maxSize;
    }

    private List<Pair> getNonZeroDirsWithDiffParents(int i, int j, HashMap<Pair, Integer> parents, int[][] matrix) {
        List<Pair> dirs = getAllPossibleDirections(i,j);
        HashSet<Integer> existingParents = new HashSet<>();
        ArrayList<Pair> finalDirs = new ArrayList<>();
        for(Pair dir: dirs) {
            if(!isOutOfBound(matrix, dir.a, dir.b) && !(matrix[dir.a][dir.b] == 0) && !existingParents.contains(parents.get(dir))) {
                existingParents.add(parents.get(dir));
                finalDirs.add(dir);
            }
        }
        return finalDirs;
    }

    private void dfs(int i, int j, int[][] matrix, HashSet<Pair> visited, HashSet<Pair> path) {
        if(isOutOfBound(matrix, i, j)|| matrix[i][j] == 0) {
            return;
        }
        Pair p = new Pair(i, j);
        if(visited.contains(p)) {
            return;
        }
        visited.add(p);
        path.add(p);
        for(Pair dir: getAllPossibleDirections(i, j)) {
            dfs(dir.a, dir.b, matrix, visited, path);
        }
    }


    List<Pair> getAllPossibleDirections(int i, int j) {
        ArrayList<Pair> dirs = new ArrayList<>();
        dirs.add(new Pair(i+1, j));
        dirs.add(new Pair(i-1, j));
        dirs.add(new Pair(i, j+1));
        dirs.add(new Pair(i, j-1));
        return dirs;
    }

    boolean isOutOfBound(int[][] board, int i, int j) {
        return i<0 || j< 0 || i>= board.length || j >= board[0].length;
    }

    public static void main(String[] args) {
        LargestIsland ls = new LargestIsland();
//        int[][] mat = {
//                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
//                {1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
//                {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
//                {0, 1, 1, 0, 0, 0, 1, 0, 1, 0},
//                {1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 1, 1, 1, 1, 0, 1, 0, 1},
//                {1, 0, 1, 0, 0, 0, 0, 1, 1, 1}
//        };
        int[][] mat = {
                {0,1,1},
                {0,0,1},
                {1,1,0}
        };
        System.out.println(ls.largestIsland(mat));
    }
}
