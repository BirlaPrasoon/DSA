package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class VideoStitching {

/*
    You are given a series of video clips from a sporting event that lasted time seconds. These video clips can be overlapping with each other and have varying lengths.

    Each video clip is described by an array clips where clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.

    We can cut these clips into segments freely.

    For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
    Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event [0, time]. If the task is impossible, return -1.



    Example 1:

    Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
    Output: 3
    Explanation: We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
            Then, we can reconstruct the sporting event as follows:
    We cut [1,9] into segments [1,2] + [2,8] + [8,9].
    Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
    Example 2:

    Input: clips = [[0,1],[1,2]], time = 5
    Output: -1
    Explanation: We cannot cover [0,5] with only [0,1] and [1,2].
    Example 3:

    Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
    Output: 3
    Explanation: We can take clips [0,4], [4,7], and [6,9].


    Constraints:

            1 <= clips.length <= 100
            0 <= starti <= endi <= 100
            1 <= time <= 100
    */

    static class Solution {
        public int videoStitching(int[][] clips, int targetTime) {
            // Sort clips by start time, for equal starts sort by end time in descending order
            Arrays.sort(clips, (clipA, clipB) ->
                    clipA[0] == clipB[0] ? clipB[1] - clipA[1] : clipA[0] - clipB[0]
            );

            // If first clip doesn't start at beginning, it's impossible
            if (clips[0][0] > 0) return -1;

            int clipsUsed = 1;  // Count of clips in final solution
            int currentCoverage = clips[0][1];  // How far we've covered so far
            int maxReachable = currentCoverage;  // Farthest we can reach with next clip
            int clipIndex = 1;  // Current clip being considered

            // If first clip alone covers the target duration
            if (currentCoverage >= targetTime) return 1;

            while (clipIndex < clips.length) {
                // If there's a gap we can't cover
                if (clips[clipIndex][0] > currentCoverage) return -1;

                // Find the clip that extends our coverage the furthest
                while (clipIndex < clips.length && clips[clipIndex][0] <= currentCoverage) {
                    maxReachable = Math.max(maxReachable, clips[clipIndex][1]);
                    clipIndex++;
                }

                // If we couldn't extend coverage further
                if (maxReachable == currentCoverage) return -1;

                clipsUsed++;
                currentCoverage = maxReachable;

                // If we've reached or exceeded target
                if (currentCoverage >= targetTime) return clipsUsed;
            }

            // If we've used all clips but haven't reached target
            return currentCoverage >= targetTime ? clipsUsed : -1;
        }
    }

    // Test class with descriptive test cases
    class Main {
        public static void main(String[] args) {
            Solution solution = new Solution();

            // Test cases with descriptive names
            int[][] overlappingClips = {{0,2}, {4,6}, {8,10}, {1,9}, {1,5}, {5,9}};
            int[][] consecutiveClips = {{0,1}, {1,2}, {2,3}, {3,4}};
            int[][] simpleOverlap = {{0,4}, {2,8}};

            // Test scenarios
            testScenario(solution, overlappingClips, 10, "Overlapping clips");
            testScenario(solution, consecutiveClips, 5, "Consecutive clips");
            testScenario(solution, simpleOverlap, 5, "Simple overlap");
        }

        private static void testScenario(Solution solution, int[][] clips, int targetTime, String description) {
            System.out.println("Test case: " + description);
            System.out.println("Input clips: " + Arrays.deepToString(clips));
            System.out.println("Target time: " + targetTime);
            System.out.println("Minimum clips needed: " + solution.videoStitching(clips, targetTime));
            System.out.println();
        }
    }

}
