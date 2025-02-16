package leetcode.problems.google.easy.arrays;

import java.util.Arrays;

public class FloodFill {

    public static void main(String[] args) {
        int a[][] = {{1,1,1},{1,1,0},{1,0,1}};
        FloodFill f = new FloodFill();
        System.out.println(Arrays.deepToString(f.floodFill(a, 1, 1, 2)));
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        if(initialColor == color) return image;
        solve(image, sr, sc, color, initialColor);
        return image;
    }

    private void solve(int image[][], int i, int j, int color, int initialColor) {
        if(!canGo(image, i, j, initialColor)) return;
        image[i][j] = color;
        solve(image, i-1, j, color, initialColor);
        solve(image, i+1, j, color, initialColor);
        solve(image, i, j-1, color, initialColor);
        solve(image, i, j+1, color, initialColor);
    }

    private boolean canGo(int a[][], int i, int j, int initialColor) {
        if(i<0 || j<0 || i>= a.length || j>= a[0].length) return false;
        return a[i][j] == initialColor;
    }
}
