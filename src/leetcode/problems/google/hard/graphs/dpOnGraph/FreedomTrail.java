package leetcode.problems.google.hard.graphs.dpOnGraph;

import leetcode.Pair;

import java.util.*;

public class FreedomTrail {

/*
    In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring"
    and use the dial to spell a specific keyword to open the door.

    Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword
    that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.

    Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key
    one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction
    and then by pressing the center button.

    At the stage of rotating the ring to spell the key character key[i]:

    You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation
    is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
    If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step.
    After the pressing, you could begin to spell the next character in the key (next stage).
    Otherwise, you have finished all the spelling.


    Example 1:


    Input: ring = "godding", key = "gd"
    Output: 4
    Explanation:
    For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
    For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
    Also, we need 1 more step for spelling.
    So the final output is 4.
    Example 2:

    Input: ring = "godding", key = "godding"
    Output: 13


    Constraints:

    1 <= ring.length, key.length <= 100
    ring and key consist of only lower case English letters.
    It is guaranteed that key could always be spelled by rotating ring.
    */

    // Look in notes for better understandin.
    class Solution {
        private static int RING_LENGTH;

        public int findRotateSteps(String ring, String key) {
            Integer[][] dp = new Integer[101][101];
            RING_LENGTH = ring.length();
            return dfs(0, 0, ring, key, dp);
        }

        // Find the minimum steps between two indexes of ring
        private int countSteps(int curr, int next) {
            int clockWiseDistance = Math.abs(curr - next);  // clockwise distance between cur and next
            int antiClockWiseDistance = RING_LENGTH - clockWiseDistance; // anticlockwise distance between cur and next
            return Math.min(clockWiseDistance, antiClockWiseDistance);
        }

        public int dfs(int ringIndex, int keyIndex, String ring, String key, Integer[][] dp) {
            // If we have already calculated this sub-problem, return the result
            if (dp[ringIndex][keyIndex] != null) {
                return dp[ringIndex][keyIndex];
            }
            // If we reach the end of the key, it has been spelled
            if (keyIndex == key.length()) {
                return 0;
            }
            int minSteps = Integer.MAX_VALUE;
            // For each occurrence of the character at keyIndex of key in ring
            // Calculate and save the minimum steps to that character from the ringIndex of ring
            for (int nextPositionInRing = 0; nextPositionInRing < ring.length(); nextPositionInRing++) {

                if (ring.charAt(nextPositionInRing) == key.charAt(keyIndex)) {
                    int totalSteps =
                            // count the distance between cur element and selected element.
                            countSteps(ringIndex, nextPositionInRing)
                                    // 1 for button press.
                                    + 1
                                    // now recur for completing the ring via this way.
                                    + dfs(nextPositionInRing, keyIndex + 1, ring, key, dp);
                    minSteps = Math.min(minSteps, totalSteps);
                }
            }
            dp[ringIndex][keyIndex] = minSteps;
            return minSteps;
        }
    }

    class SolutionMine {
        public int findRotateSteps(String ring, String key) {
            Integer dp[][] = new Integer[ring.length()][key.length()];
            return dfs(0, 0, ring, key, dp);
        }

        private int countSteps(int next, int cur, int ringLength) {
            int clockwiseDistance = Math.abs(next - cur);
            int anticlockwiseDistance = ringLength - clockwiseDistance;
            return Math.min(clockwiseDistance, anticlockwiseDistance);
        }

        private int dfs(int ringIndex, int keyIndex, String ring, String key, Integer[][] dp) {
            if (keyIndex == key.length()) return 0;
            if (dp[ringIndex][keyIndex] != null) return dp[ringIndex][keyIndex];
            int minSteps = Integer.MAX_VALUE;
            for (int next = 0; next < ring.length(); next++) {
                if (ring.charAt(next) == key.charAt(keyIndex)) {
                    int totalSteps = countSteps(ringIndex, next, ring.length()) + 1 + dfs(next, keyIndex + 1, ring, key, dp);
                    if (totalSteps < minSteps) minSteps = totalSteps;
                }
            }
            dp[ringIndex][keyIndex] = minSteps;
            return minSteps;
        }
    }

    class SolutionDijkstra {
        record State(int ringIndex, int keyIndex, int steps) {
            @Override
            public boolean equals(Object o) {
                if(this == o) return true;
                if(!(o instanceof State state)) return false;
                return state.ringIndex == this.ringIndex && state.keyIndex == this.keyIndex;
            }

            @Override
            public int hashCode() {
                int result = this.ringIndex;
                result = result*31 + this.keyIndex;
                return result;
            }

            public String toString() {
                return "STATE: {" + this.ringIndex +", " + this.keyIndex+", step:" + this.steps +"}";
            }
        }


        public int findRotateSteps(String ring, String key) {
            int ringLen = ring.length();
            int keyLen = key.length();

            // HashMap to store the indices of occurrences of each character in the ring
            Map<Character, List<Integer>> characterIndices = new HashMap<>();
            for (int i = 0; i < ring.length(); i++) {
                char ch = ring.charAt(i);
                characterIndices.computeIfAbsent(ch, k -> new ArrayList<>()).add(i);
            }

            // Initialize the min heap (priority queue) with the starting point
            // Each element of the heap is an array of integers representing:
            // totalSteps, ringIndex, keyIndex
            PriorityQueue<State> heap = new PriorityQueue<>(Comparator.comparingInt(State::steps));

            heap.offer(new State(0,0,0));

            // HashSet to keep track of visited states
            Set<State> seen = new HashSet<>();

            // Spell the keyword using the metal dial
            int totalSteps = 0;
            while (!heap.isEmpty()) {
                // Pop the element with the smallest total steps from the heap
                State cur = heap.poll();
                totalSteps = cur.steps;
                int ringIndex = cur.ringIndex;
                int keyIndex = cur.keyIndex;

                // We have spelled the keyword
                if (keyIndex == keyLen) {
                    break;
                }
                if (seen.contains(cur)) {
                    continue;
                }

                // Otherwise, add this pair to the visited list
                seen.add(cur);

                // Add the rest of the occurrences of this character in ring to the heap
                for (int nextIndex : characterIndices.get(key.charAt(keyIndex))) {
                    heap.offer(new State(nextIndex, keyIndex+1,
                            cur.steps +
                                    countSteps(ringIndex, nextIndex, ringLen)
                                    + 1
                    ));
                }
            }

            // Return the total steps and add keyLen to account for
            // pressing the center button for each character in the keyword
            return totalSteps;
        }

        // Find the minimum steps between two indexes of ring
        private int countSteps(int curr, int next, int ringLen) {
            int stepsBetween = Math.abs(curr - next);
            int stepsAround = ringLen - stepsBetween;
            return Math.min(stepsBetween, stepsAround);
        }
    }


}
