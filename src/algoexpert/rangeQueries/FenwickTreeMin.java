package algoexpert.rangeQueries;

import java.util.Arrays;

public class FenwickTreeMin {
    private final int[] bit;
    private final int n;
    private final int INF = Integer.MAX_VALUE;  // A large value for minimum initialization

    public FenwickTreeMin(int n) {
        this.n = n;
        bit = new int[n + 1];
        Arrays.fill(bit, INF);  // Initialize with infinity for minimum queries
    }

    // Update the value at index idx
    public void update(int idx, int value) {
        while (idx <= n) {
            bit[idx] = Math.min(bit[idx], value);  // Store the minimum value at idx
            idx += idx & -idx;
        }
    }

    // Query minimum in the range [1, idx]
    public int query(int idx) {
        int minValue = INF;
        while (idx > 0) {
            minValue = Math.min(minValue, bit[idx]);
            idx -= idx & -idx;
        }
        return minValue;
    }

    public static void main(String[] args) {
        int n = 10;  // Size of the array
        FenwickTreeMin fenwickTree = new FenwickTreeMin(n);

        int[] values = {0, 5, 3, 7, 9, 2, 1, 6, 8, 4};  // Example values

        // Populate Fenwick Tree
        for (int i = 1; i <= values.length; i++) {
            fenwickTree.update(i, values[i - 1]);
        }

        // Query minimum in range [1, 5]
        System.out.println(fenwickTree.query(5));  // Output should be the minimum in [1, 5]
    }
}
