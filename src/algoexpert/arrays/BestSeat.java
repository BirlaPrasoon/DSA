package algoexpert.arrays;

public class BestSeat {
    // This Question is simple to see and solve but difficult to write code atleast for me. After wasting a lot of time,
    // I started with for loop instead of while loop only then I was able to solve it.
    public int bestSeat(int[] seats) {
        if(seats.length >=1 && seats.length <=2) return -1;
        int s = 0, e = 0;
        int maxLen = 0, ms = 1, me = 1;
        while(e < seats.length) {
            if(seats[e] == 1) {
                int len = e - s - 1;
                if(len > maxLen) {
                    ms = s;
                    me = e;
                    maxLen = len;
                }
                s = e;
            }
            e++;
        }
        return maxLen == 0 ? -1 :(ms + (me - ms)/2);
    }

    public static void main(String[] args) {
        BestSeat bs = new BestSeat();
        int[] a = new int[]{1,1,1};
        System.out.println(bs.bestSeat(a));
    }
}
