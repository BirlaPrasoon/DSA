package leetcode.problems.google.medium.implementation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightedSum2 {

    /*
    * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.

The weight of an integer is maxDepth - (the depth of the integer) + 1.

Return the sum of each integer in nestedList multiplied by its weight.
    * */

    public interface NestedInteger {
        // Constructor initializes an empty nested list.
        // public NestedInteger();

        // Constructor initializes a single integer.
        // public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    class Solution {
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

    class WeightedSumTriplet {
        int maxDepth;
        int sumOfElements;
        int sumOfProducts;

        WeightedSumTriplet(int maxDepth, int sumOfElements, int sumOfProducts) {
            this.maxDepth = maxDepth;
            this.sumOfElements = sumOfElements;
            this.sumOfProducts = sumOfProducts;
        }
    }

    class SolutionSinglePassDFS {
        public int depthSumInverse(List<NestedInteger> nestedList) {
            WeightedSumTriplet weightedSumTriplet = getWeightedSumTriplet(nestedList, 1);
            int maxDepth = weightedSumTriplet.maxDepth;
            int sumOfElements = weightedSumTriplet.sumOfElements;
            int sumOfProducts = weightedSumTriplet.sumOfProducts;

            return (maxDepth + 1) * sumOfElements - sumOfProducts;
        }

        private WeightedSumTriplet getWeightedSumTriplet(List<NestedInteger> list, int depth) {
            int sumOfProducts = 0;
            int sumOfElements = 0;
            int maxDepth = 0;

            for (NestedInteger nested : list) {
                if (nested.isInteger()) {
                    sumOfProducts += nested.getInteger() * depth;
                    sumOfElements += nested.getInteger();
                    maxDepth = Math.max(maxDepth, depth);
                } else {
                    WeightedSumTriplet result = getWeightedSumTriplet(nested.getList(), depth + 1);
                    sumOfProducts += result.sumOfProducts;
                    sumOfElements += result.sumOfElements;
                    maxDepth = Math.max(maxDepth, result.maxDepth);
                }
            }

            return new WeightedSumTriplet(maxDepth, sumOfElements, sumOfProducts);
        }
    }
}
