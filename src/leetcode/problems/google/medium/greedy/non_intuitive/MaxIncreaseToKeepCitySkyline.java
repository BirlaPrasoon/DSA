package leetcode.problems.google.medium.greedy.non_intuitive;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MaxIncreaseToKeepCitySkyline {

/**
    There is a city composed of n x n blocks, where each block contains a single building shaped like a vertical square prism. You are given a 0-indexed n x n integer matrix grid where grid[r][c] represents the height of the building located in the block at row r and column c.<br></br>

    A city's skyline is the outer contour formed by all the building when viewing the side of the city from a distance. The skyline from each cardinal direction north, east, south, and west may be different.</br></br>

    We are allowed to increase the height of any number of buildings by any amount (the amount can be different per building). The height of a 0-height building can also be increased. However, increasing the height of a building should not affect the city's skyline from any cardinal direction.</br></br>

    Return the maximum total sum that the height of the buildings can be increased by without changing the city's skyline from any cardinal direction.</br></br>

    Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]</br>
    Output: 35</br>
    Explanation: The building heights are shown in the center of the above image.
    The skylines when viewed from each cardinal direction are drawn in red.
    The grid after increasing the height of buildings without affecting skylines is:</br>
    gridNew = [</br>
    [8, 4, 8, 7],</br>
    [7, 4, 7, 7],</br>
    [9, 4, 8, 7],</br>
    [3, 3, 3, 3] </br>
    ]</br></br>
    Example 2:</br>

    Input: grid = [[0,0,0],[0,0,0],[0,0,0]]</br>
    Output: 0</br>
    Explanation: Increasing the height of any building will result in the skyline changing.</br>

 </br>
    n == grid.length</br>
    n == grid[r].length</br>
    2 <= n <= 50</br>
    0 <= grid[r][c] <= 100</br>
    */

class Solution {

    class SolutionMostOptimalGreedy {

        public boolean isNStraightHand(int[] hand, int groupSize) {
            if (hand.length % groupSize != 0) {
                return false;
            }

            // HashMap to store the count of each card value
            HashMap<Integer, Integer> cardCount = new HashMap<>();
            for (int card : hand) {
                int count = cardCount.getOrDefault(card, 0);
                cardCount.put(card, count + 1);
            }

            for (int card : hand) {
                int startCard = card;
                // Find the start of the potential straight sequence
                while (cardCount.getOrDefault(startCard - 1, 0) > 0) {
                    startCard--;
                }

                // Process the sequence starting from startCard
                while (startCard <= card) {
                    while (cardCount.getOrDefault(startCard, 0) > 0) {
                        // Check if we can form a consecutive sequence
                        // of groupSize cards
                        for (
                                int nextCard = startCard;
                                nextCard < startCard + groupSize;
                                nextCard++
                        ) {
                            if (cardCount.getOrDefault(nextCard, 0) == 0) {
                                return false;
                            }
                            cardCount.put(nextCard, cardCount.get(nextCard) - 1);
                        }
                    }
                    startCard++;
                }
            }

            return true;
        }
    }

    // Onlogn
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int handSize = hand.length;
        if (handSize % groupSize != 0) {
            return false;
        }

        // TreeMap to store the count of each card value
        Map<Integer, Integer> cardCount = new TreeMap<>();
        for (int ele : hand) {
            cardCount.put(ele, cardCount.getOrDefault(ele, 0) + 1);
        }

        // Process the cards until the map is empty
        while (!cardCount.isEmpty()) {
            // Get the smallest card value
            int current_card = cardCount.entrySet().iterator().next().getKey();
            // Check each consecutive sequence of groupSize cards
            for (int i = 0; i < groupSize; i++) {
                // If a card is missing or has exhausted its occurrences
                if (!cardCount.containsKey(current_card + i)) return false;
                cardCount.put(
                        current_card + i,
                        cardCount.get(current_card + i) - 1
                );
                // Remove the card value if its occurrences are exhausted
                if (cardCount.get(current_card + i) == 0) cardCount.remove(
                        current_card + i
                );
            }
        }

        return true;
    }
}

}
