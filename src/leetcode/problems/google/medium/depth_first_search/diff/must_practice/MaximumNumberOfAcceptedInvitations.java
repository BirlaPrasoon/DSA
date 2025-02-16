package leetcode.problems.google.medium.depth_first_search.diff.must_practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class MaximumNumberOfAcceptedInvitations {

/*
    There are m boys and n girls in a class attending an upcoming party.

    You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.

    Return the maximum possible number of accepted invitations.
*/

    // Maximum Bipertite matching!!!

    class Solution {
        public int maximumInvitations(int[][] grid) {
            int boys = grid.length;
            int girls = grid[0].length;
            int acceptedInvitations = 0;
            int[] match = new int[girls];
            Arrays.fill(match, -1);
            for(int boy=0; boy<boys; boy++) {
                boolean[] vis = new boolean[girls];
                if(dfs(grid,vis,match,boy)) {
                    acceptedInvitations++;
                }
            }
            return acceptedInvitations;
        }

        public boolean dfs(int[][] grid, boolean[] vis, int[] match, int boy) {
            for(int girl = 0; girl<grid[boy].length; girl++) {
                if(!vis[girl] && grid[boy][girl] == 1) {
                    vis[girl] = true;
                    // dfs call is to adjust the prev boy matching, if prev boy can be matched to a new girl
                    if(match[girl] == -1 || dfs(grid, vis, match, match[girl])) {
                        match[girl] = boy;
                        return true;
                    }
                }
            }
            return false;
        }
    }



}
