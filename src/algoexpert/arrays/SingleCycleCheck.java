package algoexpert.arrays;

import java.util.HashSet;

public class SingleCycleCheck {
    public static boolean hasSingleCycle(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        int i = 0;
        while(true){
            if(set.contains(i)) {
                return count == array.length;
            }
            int jumps = array[i];
            set.add(i);
            count++;
            i = getValidIndex(i, jumps, array.length);
        }
    }

    static int getValidIndex(int i, int b, int n) {
        int finalIndex = (i+b)%n;
        if(finalIndex <0) {
            finalIndex +=n;
        }
        return finalIndex;
    }

    public static void main(String[] args) {
        int[] a = {2, 1};
        System.out.println(hasSingleCycle(a));
    }
}
