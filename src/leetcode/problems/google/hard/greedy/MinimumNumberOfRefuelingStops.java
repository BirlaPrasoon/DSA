package leetcode.problems.google.hard.greedy;

import java.util.PriorityQueue;

public class MinimumNumberOfRefuelingStops {
/*
    A car travels from a starting position to a destination which is target miles east of the starting position.

    There are gas stations along the way. The gas stations are represented as an array stations where stations[i] = [positioni, fueli] indicates that the ith gas station is positioni miles east of the starting position and has fueli liters of gas.

    The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it. It uses one liter of gas per one mile that it drives. When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.

    Return the minimum number of refueling stops the car must make in order to reach its destination. If it cannot reach the destination, return -1.

    Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there. If the car reaches the destination with 0 fuel left, it is still considered to have arrived.



    Example 1:

    Input: target = 1, startFuel = 1, stations = []
    Output: 0
    Explanation: We can reach the target without refueling.
            Example 2:

    Input: target = 100, startFuel = 1, stations = [[10,100]]
    Output: -1
    Explanation: We can not reach the target (or even the first gas station).
    Example 3:

    Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
    Output: 2
    Explanation: We start with 10 liters of fuel.
    We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
            Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
    and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
    We made 2 refueling stops along the way, so we return 2.


    Constraints:

            1 <= target, startFuel <= 109
            0 <= stations.length <= 500
            1 <= positioni < positioni+1 < target
1 <= fueli < 109
    */

    // O(nLogN)
    class SolutionGreedy {
        public int minRefuelStops(int target, int startFuel, int[][] positions) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int fuel = startFuel;
            if (positions.length == 0) {
                if (startFuel < target) return -1;
                return 0;
            }

            if (startFuel < positions[0][0]) {
                return -1;
            }
            int count = 0;
            // i = 1
            // target = 100, fuel = 40, stations [[10,60],[20,30],[30,30],[60,40]], count = 1
            // pq = [30]
            int distance = 0;
            for (int i = 0; i < positions.length; i++) {
                pq.add(positions[i][1]);
                int distanceFromPreviousPosition = positions[i][0] - distance;
                fuel -= distanceFromPreviousPosition;
                distance = positions[i][0];

                if (i + 1 < positions.length) {
                    int distanceFromNextPosition = positions[i + 1][0] - positions[i][0];
                    // System.out.println("i: " + i + " Distance from next position:" + distanceFromNextPosition);
                    // System.out.println("i: "+i +" FUEL: " + fuel +" Remaining: " + pq + " distance: " + distance);
                    while (fuel < distanceFromNextPosition && !pq.isEmpty()) {
                        int extraFuelTaken = pq.poll();
                        fuel += extraFuelTaken;
                        // System.out.println("USING1: "+ extraFuelTaken);
                        count++;
                    }
                    if (fuel < distanceFromNextPosition) {
                        return -1;
                    }
                } else {
                    int distanceFromTarget = target - distance;
                    // System.out.println("Distance from target: " + distanceFromTarget);
                    // System.out.println("FUEL: " + fuel);
                    while (fuel < distanceFromTarget && !pq.isEmpty()) {
                        int extraFuelTaken = pq.poll();
                        fuel += extraFuelTaken;
                        // System.out.println("USING: "+ extraFuelTaken);
                        count++;
                    }
                    // System.out.println("i: "+i +" FUEL: " + fuel +" Remaining: " + pq + " distance from target:"+ (target - distance));

                    if (fuel < distanceFromTarget) return -1;
                    return count;
                }

            }
            // System.out.println("Returning from here...");
            return -1;
        }
    }

    // Integer Knapsack...
    class SolutionDP {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            int N = stations.length;
            long[] dp = new long[N + 1];
            dp[0] = startFuel;
            for (int i = 0; i < N; ++i) {
                for (int t = i; t >= 0; --t) {
                    if (dp[t] >= stations[i][0])
                        dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long) stations[i][1]);
                }
            }

            for (int i = 0; i <= N; ++i)
                if (dp[i] >= target) return i;
            return -1;
        }
    }

}
