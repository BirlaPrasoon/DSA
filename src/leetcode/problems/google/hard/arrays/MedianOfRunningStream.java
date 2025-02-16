package leetcode.problems.google.hard.arrays;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfRunningStream {
    class MedianFinder {
        PriorityQueue<Integer> pq1;
        PriorityQueue<Integer> pq2;

        public MedianFinder() {
            this.pq1 = new PriorityQueue<>(Collections.reverseOrder());
            this.pq2 = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (pq1.isEmpty() || pq1.peek() >= num) {
                pq1.add(num);
            } else {
                pq2.add(num);
            }

            if (pq1.size() - pq2.size() > 1) {
                pq2.add(pq1.remove());
            } else if (pq2.size() - pq1.size() >= 1) {
                pq1.add(pq2.remove());
            }
        }

        public double findMedian() {
            if (pq1.size() > pq2.size()) {
                return (double) pq1.peek();
            }
            return ((double) pq1.peek() + (double) pq2.peek()) / (double) 2;
        }
    }

}
