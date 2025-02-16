package leetcode.problems.google.medium.dynamicprogramming.different;

public class EggDrop {

    // Java program for the above approach

    public class GFG {

        /* Function to get minimum number of
        trials needed in worst case with e
        eggs and k floors */
        static int eggDrop(int e, int f)
        {
            // If there are no floors, then
            // no trials needed. OR if there
            // is one floor, one trial needed.
            if (f == 1 || f == 0)
                return f;

            // We need k trials for one egg
            // and k floors
            if (e == 1)
                return f;

            int min = Integer.MAX_VALUE;
            int x, res;

            // Consider all droppings from
            // 1st floor to kth floor and
            // return the minimum of these
            // values plus 1.
            for (x = 1; x <= f; x++) {
                res = Math.max(eggDrop(e - 1, x - 1),
                        eggDrop(e, f - x));
                if (res < min)
                    min = res;
            }

            return min + 1;
        }

        // Driver code
        public static void main(String args[])
        {
            int n = 2, k = 10;
            System.out.print("Minimum number of "
                    + "trials in worst case with " + n
                    + " eggs and " + k + " floors is "
                    + eggDrop(n, k));
        }
        // This code is contributed by Ryuga.
    }

}
