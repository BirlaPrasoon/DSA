# Digit DP (Dynamic Programming)

## Overview

Digit DP is a dynamic programming technique used to solve problems involving counting or summing up numbers with certain digit-based properties within a given range. It's particularly effective for large ranges where brute force methods would be inefficient.

## Key Aspects

1. **Problem Type**: Counting numbers with specific digit properties in a range [L, R].
2. **Approach**: Breaks down the problem into subproblems based on digit positions and constraints.
3. **State Representation**: Typically includes current position, tight bound flag, and problem-specific states.
4. **Transitions**: Consider all possible digits for each position, updating the state accordingly.
5. **Base Case**: Usually when all digits in the number have been processed.
6. **Implementation**: Often uses recursion with memoization (top-down DP) or iterative methods (bottom-up DP).

## Example Problem

Count numbers in range [L, R] where the sum of digits is divisible by a given number D.

## Solution

```java
class DigitDP {
    private long[][][] dp;
    private String num;
    private int D;

    public long count(int L, int R, int D) {
        this.D = D;
        return solve(String.valueOf(R), D) - solve(String.valueOf(L - 1), D);
    }

    private long solve(String upperBound, int D) {
        num = upperBound;
        int n = num.length();
        dp = new long[n][D][2];
        for (long[][] layer : dp)
            for (long[] row : layer)
                Arrays.fill(row, -1);
        return dfs(0, 0, true);
    }

    private long dfs(int pos, int sum, boolean tight) {
        if (pos == num.length()) return sum == 0 ? 1 : 0;

        if (dp[pos][sum][tight ? 1 : 0] != -1)
            return dp[pos][sum][tight ? 1 : 0];

        long result = 0;
        int limit = tight ? num.charAt(pos) - '0' : 9;

        for (int digit = 0; digit <= limit; digit++) {
            boolean newTight = tight && (digit == limit);
            result += dfs(pos + 1, (sum + digit) % D, newTight);
        }

        return dp[pos][sum][tight ? 1 : 0] = result;
    }
}
```

## Code Explanation

- `count(L, R, D)`: Main function to count numbers in range L, R with digit sum divisible by D.
- `solve(upperBound, D)`: Sets up the DP array and initiates the DFS.
- `dfs(pos, sum, tight)`: Recursive function implementing the Digit DP logic.
  - `pos`: Current position in the number.
  - `sum`: Current sum of digits (modulo D).
  - `tight`: Boolean flag indicating if we're at the upper bound.

The DP array `dp[pos][sum][tight]` memoizes results to avoid redundant calculations.

## Usage

```java
    DigitDP solver = new DigitDP();
    long result = solver.count(1, 100, 3);
    System.out.println("Numbers between 1 and 100 with digit sum divisible by 3: " + result);
```

This Digit DP solution efficiently solves the problem with a time complexity of O(n * D * 10), where n is the number of digits in the upper bound.

# LeetCode 233: Number of Digit One

## Problem Description

Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

## Example

Input: n = 13
Output: 6
Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

## Solution using Digit DP

This problem can be efficiently solved using Digit DP. Here's the implementation:

```java
class Solution {
    private int[][] dp;
    private String num;

    public int countDigitOne(int n) {
        num = String.valueOf(n);
        int len = num.length();
        dp = new int[len][len];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true);
    }

    private int dfs(int pos, int count, boolean tight) {
        if (pos == num.length()) {
            return count;
        }

        if (!tight && dp[pos][count] != -1) {
            return dp[pos][count];
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        int result = 0;

        for (int digit = 0; digit <= limit; digit++) {
            boolean newTight = tight && (digit == limit);
            int newCount = count + (digit == 1 ? 1 : 0);
            result += dfs(pos + 1, newCount, newTight);
        }

        if (!tight) {
            dp[pos][count] = result;
        }

        return result;
    }
}
```

## Explanation

1. We convert the input number to a string for easy digit access.
2. The `dp` array is used for memoization: `dp[pos][count]` stores the result for a given position and count of 1s.
3. The `dfs` function is our recursive Digit DP implementation:
   - `pos`: Current position in the number.
   - `count`: Current count of 1s.
   - `tight`: Boolean flag indicating if we're at the upper bound.
4. At each step, we consider all possible digits (0 to 9, or up to the current digit if we're at a tight bound).
5. We update the count of 1s if the current digit is 1.
6. The base case is when we've processed all digits.
7. We use memoization to avoid redundant calculations when not in a tight bound.

## Time and Space Complexity

- Time Complexity: O(10 * n * n), where n is the number of digits in the input. In practice, it's much faster due to memoization.
- Space Complexity: O(n * n) for the memoization array.

## Usage

```java
Solution solution = new Solution();
int result = solution.countDigitOne(13);
System.out.println("Number of digit 1 in numbers up to 13: " + result);
```

This Digit DP solution efficiently solves the problem of counting the number of digit 1s for large inputs, where a brute force approach would be impractical.

# Understanding the "tight" Variable in Digit DP

## Purpose

The "tight" variable is a crucial concept in Digit DP problems. It keeps track of whether we are still bound by the digits of the original number at the current position.

## Detailed Explanation

1. **Meaning**:
   - `tight = true`: We are still matching the prefix of the original number up to the current digit.
   - `tight = false`: We have deviated from the original number and have more freedom in choosing digits.

2. **How it works**:
   - Initially set to true when we start processing from the most significant digit.
   - As we move through digits, we check if we can "break free" from the tight bound.

3. **Transition**:
   - If tight and we choose a smaller digit: becomes false for subsequent digits.
   - If tight and we choose the same digit: remains true for the next position.
   - Once false, it stays false for all subsequent positions.

4. **Impact on digit choice**:
   - `tight = true`: Can only choose digits up to the current digit of the original number.
   - `tight = false`: Can choose any digit from 0 to 9.

5. **In the code**:

```java
int limit = tight ? num.charAt(pos) - '0' : 9;
for (int digit = 0; digit <= limit; digit++) {
    boolean newTight = tight && (digit == limit);
    // ... rest of the code
}
```

   - If tight, we can only go up to the digit in the original number.
   - If not tight, we can go up to 9.
   - `newTight` is true only if we're already tight and chose the maximum allowed digit.

6. **Importance in DP**:
   - Allows correct counting without exceeding the given upper bound.
   - Crucial for memoization:
     - States with `tight = true` are unique to each number and can't be reused.
     - States with `tight = false` can be memoized and reused.

## Example

Consider the number 1234 and we're processing the second digit:
- If we've chosen 1 for the first digit (tight = true):
  - We can only choose 0, 1, or 2 for the second digit.
- If we've chosen 0 for the first digit (tight = false):
  - We can choose any digit from 0 to 9 for the second digit.

## Conclusion

The "tight" variable ensures that our counting process respects the upper bound of the given number while allowing efficient reuse of calculations for lower numbers.

# Explanation of: int newCount = count + (digit == 1 ? 1 : 0);

This line is a key part of counting the occurrences of the digit 1 in a Digit DP problem, specifically for LeetCode 233 (Number of Digit One).

## Breakdown

```java
int newCount = count + (digit == 1 ? 1 : 0);
```

1. `count`: This is the current count of 1s we've seen so far in the number we're constructing.

2. `(digit == 1 ? 1 : 0)`: This is a ternary operator that checks if the current digit is 1.
   - If `digit == 1` is true, it returns 1
   - If `digit == 1` is false, it returns 0

3. `count + ...`: We're adding the result of the ternary operation to our current count.

## Purpose

This line increments the count of 1s if and only if the current digit we're considering is 1. It's a concise way of updating our count as we build numbers digit by digit.

## Example

Let's say we're processing the number 1234:

1. For the first digit (1):
   - `digit == 1` is true
   - `newCount = 0 + 1 = 1`

2. For the second digit (2):
   - `digit == 1` is false
   - `newCount = 1 + 0 = 1` (count doesn't change)

3. For the third digit (3):
   - `digit == 1` is false
   - `newCount = 1 + 0 = 1` (count doesn't change)

4. For the fourth digit (4):
   - `digit == 1` is false
   - `newCount = 1 + 0 = 1` (count doesn't change)

## In Context

This line is typically used in the recursive function of a Digit DP solution. As we explore different number combinations, we update the count each time we encounter a 1. This allows us to keep a running total of 1s for each path in our recursive exploration.

## Efficiency

Using this concise update method is more efficient than an if-else statement, especially in recursive calls where we want to minimize the number of operations.
