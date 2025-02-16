package leetcode.problems.google.medium.dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MaximizeTotalTastinessOfPurchasedFruits {

    /*
    *
    * You are given two non-negative integer arrays price and tastiness, both arrays have the same length n. You are also given two non-negative integers maxAmount and maxCoupons.

For every integer i in range [0, n - 1]:

price[i] describes the price of ith fruit.
tastiness[i] describes the tastiness of ith fruit.
You want to purchase some fruits such that total tastiness is maximized and the total price does not exceed maxAmount.

Additionally, you can use a coupon to purchase fruit for half of its price (rounded down to the closest integer). You can use at most maxCoupons of such coupons.

Return the maximum total tastiness that can be purchased.

Note that:

You can purchase each fruit at most once.
You can use coupons on some fruit at most once.
*
*
* Example 1:

Input: price = [10,20,20], tastiness = [5,8,8], maxAmount = 20, maxCoupons = 1
Output: 13
Explanation: It is possible to make total tastiness 13 in following way:
- Buy first fruit without coupon, so that total price = 0 + 10 and total tastiness = 0 + 5.
- Buy second fruit with coupon, so that total price = 10 + 10 and total tastiness = 5 + 8.
- Do not buy third fruit, so that total price = 20 and total tastiness = 13.
It can be proven that 13 is the maximum total tastiness that can be obtained.
Example 2:

Input: price = [10,15,7], tastiness = [5,8,20], maxAmount = 10, maxCoupons = 2
Output: 28
Explanation: It is possible to make total tastiness 20 in following way:
- Do not buy first fruit, so that total price = 0 and total tastiness = 0.
- Buy second fruit with coupon, so that total price = 0 + 7 and total tastiness = 0 + 8.
- Buy third fruit with coupon, so that total price = 7 + 3 and total tastiness = 8 + 20.
It can be proven that 28 is the maximum total tastiness that can be obtained.

    * */

    class Solution {
        public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
            Map<String, Integer> cache = new HashMap<>();
            HashMap<Node, Integer> map = new HashMap<>();
            return findMax(price, tastiness, maxAmount, maxCoupons, 0, cache, map);
        }

        private int findMax(int[] price, int[] tastiness, int availAmount, int availCoupons, int idx, Map<String, Integer> cache, HashMap<Node, Integer> map) {
            if(idx >= price.length) {
                return 0;
            }
            String key = idx + "-" + availAmount + "-" + availCoupons;
            Node node = new Node(idx, availAmount, availCoupons);

            if(cache.containsKey(key)) return cache.get(key);
            if(map.containsKey(node)) {return map.get(node);}
            int maxTastiness = findMax(price, tastiness, availAmount, availCoupons, idx + 1, cache, map);

            if(availAmount >= price[idx]) {
                maxTastiness = Math.max(maxTastiness, tastiness[idx] + findMax(price, tastiness, availAmount - price[idx], availCoupons, idx + 1, cache, map));
            }

            if(availAmount >= price[idx] / 2  && availCoupons > 0) {
                maxTastiness = Math.max(maxTastiness, tastiness[idx] + findMax(price, tastiness, availAmount - (price[idx] / 2), availCoupons - 1, idx + 1, cache, map));
            }

            cache.put(key, maxTastiness);
            map.put(node, maxTastiness);
            return maxTastiness;
        }
    }
    static class Node {
        int index, amt, cpns;
        public Node(int index, int amt, int cpns) {
            this.index = index;
            this.amt = amt;
            this.cpns = cpns;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;

            return index == node.index && amt == node.amt && cpns == node.cpns;
        }

        @Override
        public int hashCode() {
            int result = index;
            result = 31 * result + amt;
            result = 31 * result + cpns;
            return result;
        }
    }

    class MoreOptimizedSolution {
        class Solution {
            public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
                int n = price.length;
                int[][] dp = new int[maxAmount + 1][maxCoupons + 1];
                for (int i = 0; i < price.length; i++) {
                    for (int j = maxAmount; j >= price[i] / 2; j--) {
                        for (int k = maxCoupons; k >= 0; k--) {
                            int p = price[i];
                            int t = tastiness[i];
                            if (j >= p) {
                                dp[j][k] = Math.max(dp[j][k], dp[j - p][k] + t);
                            }
                            if (j >= p / 2 && k > 0) {
                                dp[j][k] = Math.max(dp[j][k], dp[j - p / 2][k - 1] + t);
                            }
                        }
                    }
                }
                return dp[maxAmount][maxCoupons];
            }
        }
    }
}
