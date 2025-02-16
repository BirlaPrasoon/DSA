package leetcode.problems.google.medium.union_find;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumNumberOfFishInAGrid {

    /*
    *
    * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.


    *
    * */

    class SolutionDFS {
        public int findMaxFish(int[][] grid) {
            int sum=0;
            for(int i=0;i<grid.length;i++)
            {
                for(int j=0;j<grid[0].length;j++)
                {
                    if(grid[i][j]!=0)
                    {
                        int[] a =new int[1];
                        task(grid,i,j,a);
                        sum=Math.max(sum,a[0]);
                    }
                }
            }
            return sum;
        }
        public void task(int[][] grid, int i, int j, int[] a)
        {
            if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0)
            {
                return;
            }
            a[0]+=grid[i][j];
            grid[i][j]=0;
            task(grid,i+1,j,a);
            task(grid,i-1,j,a);
            task(grid,i,j+1,a);
            task(grid,i,j-1,a);
        }
    }

    class SolutionUF {
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,1,0,-1};
        public int findMaxFish(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            boolean[][] vis = new boolean[n][m];
            Disjoint dis = new Disjoint(n * m);
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    if(grid[i][j] != 0 && !vis[i][j]){
                        int node = getNode(m,i,j);
                        dis.setCount(node,grid[i][j]);
                        bfs(m,node,grid,vis,i,j,dis);
                    }
                }
            }

            int res = -1;
            for(int i=0;i<(m*n);i++){
                if(i == dis.findParent(i)){
                    res = Math.max(res,dis.getCount(i));
                }
            }
            return res;
        }

        void bfs(int m, int node, int[][] grid, boolean[][] vis, int row, int col, Disjoint dis) {
            Queue<pair> queue = new LinkedList<>();
            queue.add(new pair(row, col));
            vis[row][col] = true;
            while(!queue.isEmpty()){
                pair p = queue.poll();
                int r = p.i;
                int c = p.j;
                for(int k=0;k<4;k++){
                    int nr = drow[k]+r;
                    int nc = dcol[k]+c;
                    if(nr>=0 && nc>=0 && nr < vis.length && nc < vis[0].length &&
                            !vis[nr][nc] && grid[nr][nc] != 0){
                        int n1 = getNode(m,nr,nc);
                        dis.unioBySize(node,n1,grid[nr][nc]);
                        vis[nr][nc] = true;
                        queue.add(new pair(nr, nc));
                    }
                }
            }
        }

        int getNode(int n,int row,int col){
            return (n*row)+col;
        }
    }
    static class pair{
        int i;
        int j;
        public pair(int i,int j){
            this.i = i;
            this.j = j;
        }
    }
    static class Disjoint{
        List<Integer> parent = new ArrayList<Integer>();
        List<Integer> size = new ArrayList<Integer>();
        List<Integer> count = new ArrayList<Integer>();
        public Disjoint(int v){
            for(int i=0;i<=v;i++){
                parent.add(i);
                size.add(1);
                count.add(0);
            }
        }

        public void setCount(int u,int wt){
            count.set(u,wt);
        }

        public int getCount(int i){
            return count.get(i);
        }

        public int findParent(int u){
            if(u == parent.get(u)) return u;
            int ulp = findParent(parent.get(u));
            parent.set(u,ulp);
            return ulp;
        }

        public void unioBySize(int x,int y,int wt){
            int u_x = findParent(x);
            int u_y = findParent(y);

            if(u_x == u_y) return;

            if(size.get(u_x) < size.get(u_y)){
                parent.set(u_x,u_y);
                count.set(u_y,count.get(u_y)+wt);
                size.set(u_y,size.get(u_x)+size.get(u_y));
            } else {
                parent.set(u_y,u_x);
                count.set(u_x,count.get(u_x)+wt);
                size.set(u_x,size.get(u_x)+size.get(u_y));
            }
        }
    }

}
