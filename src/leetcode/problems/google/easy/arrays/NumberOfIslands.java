package leetcode.problems.google.easy.arrays;

public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands n = new NumberOfIslands();
        char a[][] = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(n.numIslands(a));
    }

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
