package algoexpert.rangeQueries;

import java.util.Arrays;

public class IterativeSegmentTreeMax {
    private final int[] segTree;
    private final int n;

    IterativeSegmentTreeMax(int[] a) {
        this.n = a.length;

        segTree = new int[2*n];
        Arrays.fill(segTree, Integer.MIN_VALUE);
        constructSegmentTree(a);
    }

    private void constructSegmentTree(int[] a) {
        System.arraycopy(a, 0, segTree, n, a.length);
        for(int i = n-1; i>=1; i--){
            segTree[i] = Math.max(segTree[2*i], segTree[2*i+1]);
        }
    }

    public void update(int pos, int val) {
        pos += n;
        segTree[pos] = val;
        for (int i = pos / 2; i > 0; i /= 2) {
            segTree[i] = Math.max(segTree[2 * i] , segTree[2 * i + 1]);  // Each parent is updated to the sum of its children
        }
    }

    public int rangeQuery(int left, int right) {
        int ma = Integer.MIN_VALUE;
        left += n;
        right += n;

        while (left <= right) {
            if (left % 2 == 1) {
                ma = Math.max(ma, segTree[left]);
                left++;
            }
            if (right % 2 == 0) {
                ma = Math.max(ma, segTree[right]);
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return ma;
    }

    public static void main(String[] args)
    {
        int[] a = { 2, 6, 10, 4, 7, 28, 9, 11, 6, 33 };
        IterativeSegmentTreeMax tree
                = new IterativeSegmentTreeMax(a);

        // compute maximum in the range left to right
        int left = 1, right = 3;
        System.out.println(
                "Maximum in range " + left + " to " + right
                        + " is " + tree.rangeQuery(left, right + 1));

        // update the value of index 5 to 32
        int index = 5, value = 32;

        // a[5] = 32;
        // Contents of array : {2, 6, 10, 4, 7, 32, 9, 11,
        // 6, 33}
        tree.update(index, value);

        // compute maximum in the range left to right
        left = 2;
        right = 8;
        System.out.println(
                "Maximum in range " + left + " to " + right
                        + " is " + tree.rangeQuery(left, right + 1));
    }
}
