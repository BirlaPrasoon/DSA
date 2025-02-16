package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class PutBoxesIntoWareHouses1 {
/*

    You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit width and the heights of n rooms in a warehouse respectively. The warehouse's rooms are labelled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.

    Boxes are put into the warehouse by the following rules:

    Boxes cannot be stacked.
    You can rearrange the insertion order of the boxes.
    Boxes can only be pushed into the warehouse from left to right only.
    If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
    Return the maximum number of boxes you can put into the warehouse.

*/

    class Solution {
        public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
            // Preprocess the height of the warehouse rooms to get usable heights
            for (int i = 1; i < warehouse.length; i++) {
                warehouse[i] = Math.min(warehouse[i - 1], warehouse[i]);
            }

            // Iterate through boxes from smallest to largest
            Arrays.sort(boxes);

            int count = 0;

            for (int i = warehouse.length - 1; i >= 0; i--) {
                // Count the boxes that can fit in the current warehouse room
                if (count < boxes.length && boxes[count] <= warehouse[i]) {
                    count++;
                }
            }

            return count;
        }
    }

}
