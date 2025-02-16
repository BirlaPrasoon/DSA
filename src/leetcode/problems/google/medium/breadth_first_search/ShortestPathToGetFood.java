package leetcode.problems.google.medium.breadth_first_search;
import java.util.*;
public class ShortestPathToGetFood {

/*
    You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

    You are given an m x n character matrix, grid, of these different types of cells:

            '*' is your location. There is exactly one '*' cell.
'#' is a food cell. There may be multiple food cells.
            'O' is free space, and you can travel through these cells.
'X' is an obstacle, and you cannot travel through these cells.
    You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.

    Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
*/

    class Solution {
        int[][] dirs = new int[][] {{0,1}, {0, -1}, {1,0}, {-1, 0}};

        public int getFood(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            boolean[][] visited = new boolean[m][n];
            int step = 0;

            Queue<int[]> q = new LinkedList<>();

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(grid[i][j]=='*'){
                        q.offer(new int[]{i,j});
                        break;
                    }
                }
            }

            while(!q.isEmpty()){
                int size = q.size();

                for(int i=0;i<size;i++){
                    int[] info=q.poll();
                    int r = info[0];
                    int c = info[1];

                    if(grid[r][c]=='#'){
                        return step;
                    }

                    for(int[] dir:dirs)  {
                        int nextR = r + dir[0];
                        int nextC = c + dir[1];

                        if(nextR>=0 && nextR<m && nextC>=0 && nextC<n && grid[nextR][nextC]!='X' &&!visited[nextR][nextC]){
                            visited[nextR][nextC] = true;
                            q.offer(new int[]{nextR,nextC});
                        }
                    }
                }

                step++;
            }

            return -1;
        }
    }


}
