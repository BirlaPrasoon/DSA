package leetcode.problems.google.medium.breadth_first_search;

import leetcode.problems.google.medium.implementation.NestedListWeightedSum.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightedSum2 {

/*    You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

    The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.

    The weight of an integer is maxDepth - (the depth of the integer) + 1.

    Return the sum of each integer in nestedList multiplied by its weight.*/


/*
    Input: nestedList = [[1,1],2,[1,1]]
    Output: 8
    Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
            1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
*/

 /*   Input: nestedList = [1,[4,[6]]]
    Output: 17
    Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
            1*3 + 4*2 + 6*1 = 17*/


    class Solution {
        public int depthSumInverse(List<NestedInteger> nestedList) {
            int maxDepth = findMaxDepth(nestedList);
            return weightedSum(nestedList, 1, maxDepth);
        }

        private int findMaxDepth(List<NestedInteger> list) {
            int maxDepth = 1;

            for (NestedInteger nested : list) {
                if (!nested.isInteger() && nested.getList().size() > 0) {
                    maxDepth = Math.max(maxDepth, 1 + findMaxDepth(nested.getList()));
                }
            }

            return maxDepth;
        }

        private int weightedSum(List<NestedInteger> list, int depth, int maxDepth) {
            int answer = 0;
            for (NestedInteger nested : list) {
                if (nested.isInteger()) {
                    answer += nested.getInteger() * (maxDepth - depth + 1);
                } else {
                    answer += weightedSum(nested.getList(), depth + 1, maxDepth);
                }
            }
            return answer;
        }
    }
}

class SolutionBFS {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> Q = new LinkedList<>();
        Q.addAll(nestedList);

        int depth = 1;
        int maxDepth = 0;
        int sumOfElements = 0;
        int sumOfProducts = 0;

        while (!Q.isEmpty()) {
            int size = Q.size();
            maxDepth = Math.max(maxDepth, depth);

            for (int i = 0; i < size; i++) {
                NestedInteger nested = Q.poll();

                if (nested.isInteger()) {
                    sumOfElements += nested.getInteger();
                    sumOfProducts += nested.getInteger() * depth;
                } else {
                    Q.addAll(nested.getList());
                }
            }
            depth++;
        }
        return (maxDepth + 1) * sumOfElements - sumOfProducts;
    }
}

