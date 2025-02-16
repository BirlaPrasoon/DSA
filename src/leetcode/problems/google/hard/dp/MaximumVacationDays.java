package leetcode.problems.google.hard.dp;

import java.util.Arrays;

public class MaximumVacationDays {

/*

    LeetCode wants to give one of its best employees the option to travel among n cities to collect algorithm problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there are certain rules and restrictions you need to follow.

    Rules and restrictions:

    You can only travel among n cities, represented by indexes from 0 to n - 1. Initially, you are in the city indexed 0 on Monday.
    The cities are connected by flights. The flights are represented as an n x n matrix (not necessarily symmetrical), called flights representing the airline status from the city i to the city j. If there is no flight from the city i to the city j, flights[i][j] == 0; Otherwise, flights[i][j] == 1. Also, flights[i][i] == 0 for all i.
    You totally have k weeks (each week has seven days) to travel. You can only take flights at most once per day and can only take flights on each week's Monday morning. Since flight time is so short, we do not consider the impact of flight time.
    For each city, you can only have restricted vacation days in different weeks, given an n x k matrix called days representing this relationship. For the value of days[i][j], it represents the maximum days you could take a vacation in the city i in the week j.
    You could stay in a city beyond the number of vacation days, but you should work on the extra days, which will not be counted as vacation days.
    If you fly from city A to city B and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city B in that week.
    We do not consider the impact of flight hours on the calculation of vacation days.
    Given the two matrices flights and days, return the maximum vacation days you could take during k weeks.

*/

/*
    Example 1:

    Input: flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
    Output: 12
    Explanation:
    One of the best strategies is:
            1st week : fly from city 0 to city 1 on Monday, and play 6 days and work 1 day.
            (Although you start at city 0, we could also fly to and start at other cities since it is Monday.)
            2nd week : fly from city 1 to city 2 on Monday, and play 3 days and work 4 days.
            3rd week : stay at city 2, and play 3 days and work 4 days.
            Ans = 6 + 3 + 3 = 12.
    Example 2:

    Input: flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
    Output: 3
    Explanation:
    Since there are no flights that enable you to move to another city, you have to stay at city 0 for the whole 3 weeks.
    For each week, you only have one day to play and six days to work.
    So the maximum number of vacation days is 3.
    Ans = 1 + 1 + 1 = 3.
    Example 3:

    Input: flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
    Output: 21
    Explanation:
    One of the best strategies is:
            1st week : stay at city 0, and play 7 days.
2nd week : fly from city 0 to city 1 on Monday, and play 7 days.
3rd week : fly from city 1 to city 2 on Monday, and play 7 days.
            Ans = 7 + 7 + 7 = 21


    Constraints:

    n == flights.length
    n == flights[i].length
    n == days.length
    k == days[i].length
1 <= n, k <= 100
    flights[i][j] is either 0 or 1.
            0 <= days[i][j] <= 7
    */

    class SolutionTopDown {
        private Integer[][] memo;
        private int[][] flights;
        private int[][] days;
        private int N, K;

        public int maxVacationDays(int[][] flights, int[][] days) {
            if (flights.length == 0 || days.length == 0) return 0;

            this.flights = flights;
            this.days = days;
            this.N = flights.length;    // number of cities
            this.K = days[0].length;    // number of weeks
            this.memo = new Integer[N][K];

            // Start from city 0, week 0
            return dfs(0, 0);
        }

        private int dfs(int currCity, int week) {
            // Base case: if we've used all weeks
            if (week == K) {
                return 0;
            }

            // If we've already computed this state
            if (memo[currCity][week] != null) {
                return memo[currCity][week];
            }

            int maxVacation = 0;

            // Try all possible cities we can fly to (including staying in current city)
            for (int nextCity = 0; nextCity < N; nextCity++) {
                // We can go to nextCity if:
                // 1. It's the same city (staying) OR
                // 2. There's a flight from currCity to nextCity
                if (nextCity == currCity || flights[currCity][nextCity] == 1) {
                    // Add vacation days for nextCity in current week
                    // and recursively calculate for remaining weeks
                    int vacationDays = days[nextCity][week] + dfs(nextCity, week + 1);
                    maxVacation = Math.max(maxVacation, vacationDays);
                }
            }

            // Memoize and return
            return memo[currCity][week] = maxVacation;
        }
    }

    class SolutionBottomUp {
        public int maxVacationDays(int[][] flights, int[][] days) {
            if (flights.length == 0 || days.length == 0) return 0;

            int N = flights.length;    // number of cities
            int K = days[0].length;    // number of weeks

            // dp[i][j] represents max vacation days we can get
            // when we stay at city i in week j
            int[][] dp = new int[N][K];

            // Initialize dp array with minimum value
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MIN_VALUE);
            }

            // Start from city 0
            dp[0][0] = days[0][0];

            // If we can fly to city i from city 0, we can start from there
            for (int i = 1; i < N; i++) {
                if (flights[0][i] == 1) {
                    dp[i][0] = days[i][0];
                }
            }

            // For each week
            for (int week = 1; week < K; week++) {
                // For each city we could stay in current week
                for (int curr = 0; curr < N; curr++) {
                    // Skip if we can't be at current city
                    if (dp[curr][week-1] == Integer.MIN_VALUE &&
                            !canReachCity(flights, curr, week-1, dp)) {
                        continue;
                    }

                    // Stay in current city
                    if (dp[curr][week-1] != Integer.MIN_VALUE) {
                        dp[curr][week] = dp[curr][week-1] + days[curr][week];
                    }

                    // Or fly from other cities
                    for (int prev = 0; prev < N; prev++) {
                        if (dp[prev][week-1] != Integer.MIN_VALUE && flights[prev][curr] == 1) {
                            dp[curr][week] = Math.max(dp[curr][week],
                                    dp[prev][week-1] + days[curr][week]);
                        }
                    }
                }
            }

            // Find maximum vacation days among all cities in last week
            int maxVacation = 0;
            for (int i = 0; i < N; i++) {
                maxVacation = Math.max(maxVacation, dp[i][K-1]);
            }

            return maxVacation == Integer.MIN_VALUE ? 0 : maxVacation;
        }

        // Helper method to check if we can reach current city
        private boolean canReachCity(int[][] flights, int curr, int week, int[][] dp) {
            for (int prev = 0; prev < flights.length; prev++) {
                if (flights[prev][curr] == 1 && dp[prev][week] != Integer.MIN_VALUE) {
                    return true;
                }
            }
            return false;
        }
    }
}
