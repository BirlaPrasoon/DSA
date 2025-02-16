package leetcode.problems.google.easy.linkedlist;

import java.math.BigInteger;

public class AddTwoLists {
    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    static void print(ListNode list) {
        System.out.print("[");
        while(list != null) {
            System.out.print(list.val +", ");
            list = list.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        BigInteger i1 = new BigInteger("1123");
        BigInteger i2 = i1.add(new BigInteger("123"));
        String num2 = new StringBuilder(i2.toString()).reverse().toString();
        ListNode head = new ListNode();
        ListNode cur = head;
        for(char c: num2.toCharArray()) {
            int num = c - '0';
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        print(head.next);
    }
}
