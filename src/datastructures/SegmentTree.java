package datastructures;

public class SegmentTree {

    int[] tree;
    int size;
    int[] arr;

    SegmentTree(int[] arr) {
        size = arr.length;
        tree = new int[size * 4];
        this.arr = arr;
        buildTree(arr, 0, 0, size - 1);
    }

    // O(n)
    private void buildTree(int[] array, int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = array[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildTree(array, 2 * treeIndex + 1, left, mid);
        buildTree(array, 2 * treeIndex + 2, mid + 1, right);
        tree[treeIndex] = Math.min(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    // O(log(n))
    private int query(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft <= left && queryRight >= right)
            return tree[treeIndex];
        int mid = left + (right - left) / 2;
        int minValue = Integer.MAX_VALUE;
        if (queryLeft <= mid)
            minValue = Math.min(minValue, query(2 * treeIndex + 1, left, mid, queryLeft, queryRight));
        if (queryRight > mid)
            minValue = Math.min(minValue, query(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight));
        return minValue;
    }

    int query(int left, int right) {
        return query(0, 0, size - 1, left, right);
    }

    private void update(int treeIndex, int arrIndex, int val, int left, int right) {
        if (left == right) {
            tree[treeIndex] = val;
            arr[arrIndex] = val;
            return;
        }
        int mid = left + (right - left) / 2;
        if (arrIndex <= mid) {
            update(2 * treeIndex + 1, arrIndex, val, left, mid);
        } else {
            update(2 * treeIndex + 1, arrIndex, val, mid + 1, right);
        }
        tree[treeIndex] = Math.min(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }
}
