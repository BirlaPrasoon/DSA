package algoexpert.linkedlist;

public class RemoveDuplicatesFromSortedLinkedList {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList removeDuplicatesFromLinkedList(LinkedList head) {
        LinkedList cur = head;
        while(cur != null && cur.next != null) {
            // This cur.next != null in below statement will throw null pointer exception.
            while(cur.next != null && cur.value == cur.next.value) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
