package leetcode.problems.google.medium.breadth_first_search;

import java.util.*;

public class OpenTheLock {

/*
    You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

    The lock initially starts at '0000', a string representing the state of the 4 wheels.

    You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

    Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.



            Example 1:

    Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
    Output: 6
    Explanation:
    A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
    Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
    because the wheels of the lock become stuck after the display becomes the dead end "0102".
    Example 2:

    Input: deadends = ["8888"], target = "0009"
    Output: 1
    Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
    Example 3:

    Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
    Output: -1
    Explanation: We cannot reach the target without getting stuck.
*/

    class Solution {
        public int openLock(String[] deadends, String target) {
            // Map the next slot digit for each current slot digit.
            Map<Character, Character> nextSlot = Map.of(
                    '0', '1',
                    '1', '2',
                    '2', '3',
                    '3', '4',
                    '4', '5',
                    '5', '6',
                    '6', '7',
                    '7', '8',
                    '8', '9',
                    '9', '0'
            );
            // Map the previous slot digit for each current slot digit.
            Map<Character, Character> prevSlot = Map.of(
                    '0', '9',
                    '1', '0',
                    '2', '1',
                    '3', '2',
                    '4', '3',
                    '5', '4',
                    '6', '5',
                    '7', '6',
                    '8', '7',
                    '9', '8'
            );

            // Set to store visited and dead-end combinations.
            Set<String> visitedCombinations = new HashSet<>(Arrays.asList(deadends));
            // Queue to store combinations generated after each turn.
            Queue<String> pendingCombinations = new LinkedList<String>();

            // Count the number of wheel turns made.
            int turns = 0;

            // If the starting combination is also a dead-end,
            // then we can't move from the starting combination.
            if (visitedCombinations.contains("0000")) {
                return -1;
            }

            // Start with the initial combination '0000'.
            pendingCombinations.add("0000");
            visitedCombinations.add("0000");

            while (!pendingCombinations.isEmpty()) {
                // Explore all the combinations of the current level.
                int currLevelNodesCount = pendingCombinations.size();
                for (int i = 0; i < currLevelNodesCount; i++) {
                    // Get the current combination from the front of the queue.
                    String currentCombination = pendingCombinations.poll();

                    // If the current combination matches the target,
                    // return the number of turns/level.
                    if (currentCombination.equals(target)) {
                        return turns;
                    }

                    // Explore all possible new combinations by turning each wheel in both directions.
                    for (int wheel = 0; wheel < 4; wheel += 1) {
                        // Generate the new combination by turning the wheel to the next digit.
                        StringBuilder newCombination = new StringBuilder(currentCombination);
                        newCombination.setCharAt(wheel, nextSlot.get(newCombination.charAt(wheel)));
                        // If the new combination is not a dead-end and was never visited,
                        // add it to the queue and mark it as visited.
                        if (!visitedCombinations.contains(newCombination.toString())) {
                            pendingCombinations.add(newCombination.toString());
                            visitedCombinations.add(newCombination.toString());
                        }

                        // Generate the new combination by turning the wheel to the previous digit.
                        newCombination = new StringBuilder(currentCombination);
                        newCombination.setCharAt(wheel, prevSlot.get(newCombination.charAt(wheel)));
                        // If the new combination is not a dead-end and is never visited,
                        // add it to the queue and mark it as visited.
                        if (!visitedCombinations.contains(newCombination.toString())) {
                            pendingCombinations.add(newCombination.toString());
                            visitedCombinations.add(newCombination.toString());
                        }
                    }
                }
                // We will visit next-level combinations.
                turns += 1;
            }
            // We never reached the target combination.
            return -1;
        }
    }
}
