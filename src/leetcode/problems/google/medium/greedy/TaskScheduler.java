package leetcode.problems.google.medium.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {
/*
    You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to be a gap of at least n intervals between two tasks with the same label.

    Return the minimum number of CPU intervals required to complete all tasks.



    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
    After completing task A, you must wait two intervals before doing A again. The same applies to task B.
    In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.

    Example 2:
    Input: tasks = ["A","C","A","B","D","B"], n = 1
    Output: 6
    Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
    With a cooling interval of 1, you can repeat a task after just one other task.

    Example 3:
    Input: tasks = ["A","A","A", "B","B","B"], n = 3
    Output: 10
    Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
    There are only two types of tasks, A and B, which need to be separated by 3 intervals.
    This leads to idling twice between repetitions of these tasks.



    Constraints:

            1 <= tasks.length <= 104
    tasks[i] is an uppercase English letter.
0 <= n <= 100
    */


    class Solution {
        public int leastInterval(char[] tasks, int n) {
            // Build frequency map
            int[] freq = new int[26];
            for (char ch : tasks) {
                freq[ch - 'A']++;
            }

            // Max heap to store frequencies
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    pq.offer(freq[i]);
                }
            }

            int time = 0;
            // Process tasks until the heap is empty
            while (!pq.isEmpty()) {
                int cycle = n + 1;
                List<Integer> store = new ArrayList<>();
                int taskCount = 0;
                // Execute tasks in each cycle
                while (cycle-- > 0 && !pq.isEmpty()) {
                    int currentFreq = pq.poll();
                    if (currentFreq > 1) {
                        store.add(currentFreq - 1);
                    }
                    taskCount++;
                }
                // Restore updated frequencies to the heap
                store.forEach(pq::offer);
                // Add time for the completed cycle
                time += (pq.isEmpty() ? taskCount : n + 1);
            }
            return time;
        }
    }
}
