package algoexpert.binarytrees;

public class NumberOfBinaryTreeTopologies {

    public static int numberOfBinaryTreeTopologies(int n) {
        if(n == 0) return 0;
        int ans = 0;

        for(int i = 0; i<n; i++) {
            ans += fun(i, n);
        }
        return ans;
    }

    private static int fun(int x, int n) {
        if(x == 0) return 1;
        if(x == 1) return 1;
        return fun(x, n) * fun(n-x-1, n);
    }

    public static void main(String[] args) {
        System.out.println(numberOfBinaryTreeTopologies(4));
    }
}
