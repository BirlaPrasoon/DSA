package algoexpert.arrays;
import java.util.*;

public class SpiralTraversal {

    public static List<Integer> spiralTraverse(int[][] array) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(array.length == 0) return ans;
        if(array[0].length == 0) return ans;
        int i = 0, j = 0;
        int maxSteps = array.length * array[0].length;
        boolean[][] dp = new boolean[array.length][array[0].length];
        int count = 0;
        char dir = 'R';
        while(true) {
            count++;
            dp[i][j] = true;
            ans.add(array[i][j]);
            if(count == maxSteps) {
                break;
            }
            if(isWallHit(i,j,dir, dp)) {
                dir = getNextDirection(dir);
            }
            int[] nextCoords = nextCoords(i, j, dir);
            i = nextCoords[0];
            j = nextCoords[1];
        }
        return ans;
    }

    public static int[] nextCoords(int i, int j, char dir) {
        switch (dir) {
            case 'R': return new int[]{i, j+1};
            case 'D': return new int[]{i+1, j};
            case 'L': return new int[]{i, j-1};
            case 'U': return new int[]{i-1, j};
            default: throw new InputMismatchException("Invalid coordinates");
        }
    }

    public static boolean isOutOfBoundary(int i, int j, boolean[][] a) {
        if(a.length == 0) return true;
        return i<0 || i>=a.length || j<0 || j>= a[0].length;
    }

    public static char getNextDirection(char dir) {
        switch (dir) {
            case 'R': return 'D';
            case 'D': return 'L';
            case 'L': return 'U';
            case 'U': return 'R';
            default: throw new InputMismatchException("Invalid direction");
        }
    }

    public static boolean isWallHit(int i, int j, char dir, boolean[][] dp) {
        switch (dir) {
            case 'R':{
                if(isOutOfBoundary(i,j+1, dp)) return true;
                if(dp[i][j+1]) return true;
                break;
            }
            case 'D': {
                if(isOutOfBoundary(i+1,j, dp)) return true;
                if(dp[i+1][j]) return true;
                break;
            }
            case 'L':{
                if(isOutOfBoundary(i,j-1, dp)) return true;
                if(dp[i][j-1]) return true;
                break;
            }
            case 'U': {
                if(isOutOfBoundary(i-1, j, dp)) return true;
                if(dp[i-1][j]) return true;
                break;
            }
            default:{
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][]a = {
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };
        System.out.println(spiralTraverse(a));
    }
}
