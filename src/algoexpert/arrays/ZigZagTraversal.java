package algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ZigZagTraversal {

    public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        List<Integer> ans = new ArrayList<>();
        if(array.size() == 0) return ans;
        if(array.get(0).size() == 0) return ans;
        int i = 0; int j = 0;
        String dir = "DD";
        while(true) {
            ans.add(array.get(i).get(j));
            if(i == array.size()-1 && j == array.get(0).size()-1) {
                break;
            }
            if(dir.equals("DU")) {
                if(upperWallHit(i, j, dir, array)) {
                    if(canGoRight(i, j, array)) {
                        j++;
                    } else if(canGoDown(i, j, array)) {
                        i++;
                    } else {
                        break;
                    }
                    dir = "DD";
                } else if(rightWallHit(i, j, dir, array)) {
                    if(canGoDown(i, j, array)) {
                        i++;
                    } else {
                        break;
                    }
                    dir = "DD";
                } else {
                    j++;
                    i--;
                }
            }
            else {
                if(leftWallHit(i, j, dir, array)) {
                    if(canGoDown(i, j, array)) {
                        i++;
                    } else if(canGoRight(i, j, array)) {
                        j++;
                    } else {
                        break;
                    }
                    dir = "DU";
                } else if(bottomWallHit(i, j, dir, array)) {
                    if(canGoRight(i, j, array)) {
                        j++;
                    }
                    dir = "DU";
                } else {
                    i++;
                    j--;
                }
            }
        }
        return ans;
    }

    private static boolean canGoRight(int i, int j, List<List<Integer>> mat) {
        return j <mat.get(0).size() - 1;
    }

    private static boolean canGoDown(int i, int j, List<List<Integer>> mat) {
        return i < mat.size()-1;
    }

    private static boolean canGoUp(int i, int j, List<List<Integer>> mat) {
        return i > 0;
    }

    private static boolean canGoLeft(int i, int j, List<List<Integer>> mat) {
        return j>0;
    }

    public static boolean upperWallHit(int i, int j, String dir, List<List<Integer>> mat) {
        return (i == 0 && Objects.equals(dir, "DU"));
    }

    public static boolean rightWallHit(int i, int j, String dir, List<List<Integer>> mat) {
        // make sure you compare with length - 1 not length, it will be out of wall otherwise.
        return j == mat.get(0).size() -1  && dir.equals("DU");
    }

    public static boolean leftWallHit(int i, int j, String dir, List<List<Integer>> mat) {
        return j == 0 && dir.equals("DD");
    }

    public static boolean bottomWallHit(int i, int j, String dir, List<List<Integer>> mat) {
        // make sure you compare with length - 1 not length, it will be out of wall otherwise.
        return i == mat.size() - 1 && dir.equals("DD");
    }

    public static void main(String[] args) {
        List<List<Integer>> array = new ArrayList<>(Arrays.asList(
                Arrays.asList(1,3)
//                Arrays.asList(2,4)
//                Arrays.asList(6,8,12,15),
//                Arrays.asList(7,13,14,16)
        ));
        System.out.println(zigzagTraverse(array));
    }
}
