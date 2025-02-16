import java.util.*;
import java.util.Arrays;

import java.util.Arrays;

public class RonsRealEstate {
    public static int maxNetWorth(int N, int X, int Y, int[] prices) {
        PriorityQueue<Integer> priceQ = new PriorityQueue<>((a, b) -> b-a);
        for(int pri: prices) priceQ.add(pri);
        ArrayList<Integer> purchaseSequence = new ArrayList<>();
        while(X>0 && !priceQ.isEmpty()) {
            while(!priceQ.isEmpty() &&priceQ.peek() > X) priceQ.poll();
            if(priceQ.isEmpty()) break;
            int prop = priceQ.poll();
            purchaseSequence.add(prop);
            X-=prop;
            X/=2;
        }

        long totalWorth = 0;
        for(int i = 0; i<purchaseSequence.size(); i++) {
            int remainingYears = Y - i;
            int price = purchaseSequence.get(i) * (i+1);
            totalWorth += (int) (price*Math.pow(2,remainingYears));
        }
        System.out.println(totalWorth);
        return (int)totalWorth;
    }

    public static void main(String[] args) {
        int N = 4, X = 46, Y = 20;
        int[] prices = {7, 15, 11, 17};
        System.out.println(maxNetWorth(N, X, Y, prices)); // Expected output: 29360128
    }
}

