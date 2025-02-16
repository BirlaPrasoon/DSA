package algoexpert.linkedlist;

public class SumOfLinikedLists {

    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList sumOfLinkedLists(
            LinkedList linkedListOne, LinkedList linkedListTwo
    ) {
        int sizeOne = size(linkedListOne);
        int sizeTwo = size(linkedListTwo);
        LinkedList first = sizeOne >= sizeTwo ? linkedListOne : linkedListTwo;
        LinkedList second = sizeOne < sizeTwo ? linkedListOne : linkedListTwo;
        LinkedList head = first;
        LinkedList head1 = head;
        int carry = 0;
        while (first!= null && second != null) {
            head.value = first.value + second.value + carry;
            carry = head.value/10;
            head.value %= 10;
            head = head.next;
            first = first.next;
            second = second.next;
        }
        if(carry > 0) {
            if(head != null) {
                head.value = head.value + carry;
                carry = head.value/10;
                head.value %= 10;
            }
            LinkedList last = head1;
            while (last.next != null) last = last.next;
            if(carry > 0) {
                last.next = new LinkedList(carry);
            }
        }
        return head1;
    }

    private int size(LinkedList linkedListOne) {
        int size = 0;
        while (linkedListOne !=null) {
            size++;
            linkedListOne = linkedListOne.next;
        }
        return size;
    }

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList(2);
        print(ll1);
        LinkedList ll2 = new LinkedList(9);
        print(ll2);
        LinkedList expected =
                addMany(new LinkedList(1), new int[] {1});
        System.out.println("Expected: ");
        print(expected);
        LinkedList actual = new SumOfLinikedLists().sumOfLinkedLists(ll1, ll2);
        System.out.println("Actual:");
        print(actual);
    }

    private static void print(LinkedList head) {
        while (head != null) {
            System.out.print(head.value);
            head = head.next;
        }
        System.out.println();
    }

    private static LinkedList addMany(LinkedList linkedList, int[] ints) {
        LinkedList head = linkedList;
        for(int a : ints) {
            linkedList.next = new LinkedList(a);
            linkedList = linkedList.next;
        }
        return head;
    }
}
