package leetcode.problems.google.medium.greedy;

import java.util.Arrays;

public class BoatsToSavePeople {
/**
    You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit.<br></br>
    Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.</br></br>

    Return the minimum number of boats to carry every given person.</br></br>

    Example 1:</br>
    Input: people = [1,2], limit = 3</br>
    Output: 1</br>
    Explanation: 1 boat (1, 2)</br></br>

    Example 2:</br>
    Input: people = [3,2,2,1], limit = 3</br>
    Output: 3</br>
    Explanation: 3 boats (1, 2), (2) and (3)</br></br>

    Example 3:</br>
    Input: people = [3,5,3,4], limit = 5</br>
    Output: 4</br>
    Explanation: 4 boats (3), (3), (4), (5)</br></br>

    Constraints:</br>
 </br>
    1 <= people.length <= 5 * 10<sup>4</sup></br>
    1 <= people[i] <= limit <= 3 * 10<sup>4</sup></br>

    */

    class Solution {
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int i = 0, j = people.length-1;
            int count = 0;
            // [3,5,3,4], limit = 5
            // i = 0, j = 3, count = 1;
            while(i<=j) {
                int sum = people[i] + people[j];
                if(sum <= limit) {
                    count++;
                    i++;
                    j--;
                } else {
                    count++;
                    j--;
                }
            }
            return count;
        }
    }
}
