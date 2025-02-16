package algoexpert.linkedlist;

public class Palindrome {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public boolean linkedListPalindrome(LinkedList head) {
        if(head == null) return false;
        if(head.next == null) return true;
        LinkedList first = head;
        LinkedList second = head;
        // This condition is important, when second.next becomes null || second.next.next becomes null. We stop. We have our middle point
        while(second.next != null && second.next.next != null) {
            first = first.next;
            second = second.next.next;
        }
        LinkedList secondStart = first.next;
        secondStart = reverse(secondStart);
        first.next = null;
        while (secondStart != null && head!= null) {
            if(secondStart.value != head.value) return false;
            secondStart = secondStart.next;
            head = head.next;
        }
        return true;
    }

    private LinkedList reverse(LinkedList head) {
        if(head == null  || head.next == null) return head;
        LinkedList secondLast = head;
        LinkedList last = head;
        while (last.next != null) {
            secondLast = last;
            last = last.next;
        }
        secondLast.next = null;
        last.next = reverse(head);
        return last;
    }

    public static void main(String[] args) {
        LinkedList head = new LinkedList(0);
        head.next = new LinkedList(1);
        head.next.next = new LinkedList(2);
        head.next.next.next = new LinkedList(3);
        head.next.next.next.next = new LinkedList(2);
        head.next.next.next.next.next = new LinkedList(1);
        head.next.next.next.next.next.next = new LinkedList(0);
        boolean actual = new Palindrome().linkedListPalindrome(head);
        System.out.println(actual);
    }
}
