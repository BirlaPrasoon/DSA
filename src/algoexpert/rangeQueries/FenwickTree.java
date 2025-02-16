package algoexpert.rangeQueries;

public class FenwickTree {

    // Max tree size
    final static int MAX = 1000;

    static int BITree[] = new int[MAX];


    int getSum(int index) {
        int sum = 0;
        index = index + 1;
        while(index > 0) {
            sum += BITree[index];
            // Move index to parent node in
            // getSum View
            index -= index & (-index);
        }
        return sum;
    }

    void update(int n, int index, int val) {
        // index in BITree[] is 1 more than
        // the index in arr[]
        index = index + 1;

        // Traverse all ancestors and add 'val'
        while(index <= n)
        {
            // Add 'val' to current node of BIT Tree
            BITree[index] += val;

            // Update index to that of parent
            // in update View
            index += index & (-index);
        }
    }

    void constructBITree(int arr[], int n)
    {
        // Initialize BITree[] as 0
        for(int i=1; i<=n; i++)
            BITree[i] = 0;

        // Store the actual values in BITree[]
        // using update()
        for(int i = 0; i < n; i++)
            update(n, i, arr[i]);
    }

    public static void main(String[] args) {
        int[] freq = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = freq.length;
        FenwickTree tree = new FenwickTree();

        // Build fenwick tree from given array
        tree.constructBITree(freq, n);

        System.out.println("Sum of elements in arr[0..5]"+
                " is "+ tree.getSum(5));

        // Let use test the update operation
        freq[3] += 6;

        // Update BIT for above change in arr[]
        tree.update(n, 3, 6);

        // Find sum after the value is updated
        System.out.println("Sum of elements in arr[0..5]"+
                " after update is " + tree.getSum(5));
    }
}
