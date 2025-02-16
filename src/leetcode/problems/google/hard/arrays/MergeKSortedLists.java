package leetcode.problems.google.hard.arrays;

import leetcode.ListNode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            point.next = queue.poll();
            point = point.next;
            if (point.next != null) {
                queue.add(point.next);
            }
        }
        return head.next;
    }

}
