package algoexpert.linkedlist;

public class RemoveKthNodeFromEnd {
    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        int size= size(head);
        if(k == size) return;
        LinkedList cur = head;
        while (cur!= null && k>0) {
            cur = cur.next;
            k--;
        }
        if(k > 0) return;
        LinkedList first = head;
        LinkedList second = cur;
        while (second != null && second.next != null) {
            second = second.next;
            first = first.next;
        }
        first.next = first.next.next;
    }

    private static int size(LinkedList linkedListOne) {
        int size = 0;
        while (linkedListOne !=null) {
            size++;
            linkedListOne = linkedListOne.next;
        }
        return size;
    }

    public static void main(String[] args) {

    }
}
