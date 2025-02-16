package algoexpert.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Very good question, should definitely retry.
public class RearrangeLinkedList {

    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        // Its about pass by reference, why do we need to take output from first then pass to second instead of keeping local variables here.
        Output out = rearrange(head, null, k, input -> input.cur.value < input.x);
        out = rearrange(out.head, out.newHead, k, input -> input.cur.value == input.x);
        out = rearrange(out.head, out.newHead, k, input -> input.cur.value > input.x);
        return out.newHead;
    }

    static class Output {
        LinkedList head;
        LinkedList newHead;

        public Output(LinkedList head, LinkedList newHead) {
            this.head = head;
            this.newHead = newHead;
        }
    }

    public static Output rearrange(LinkedList head, LinkedList newHead, int k, Function<Input, Boolean> fun) {
        LinkedList newCur = newHead;
        while(newCur != null && newCur.next != null) {
            newCur = newCur.next;
        }
        LinkedList prev = null;
        LinkedList cur = head;
        while(cur!= null) {
            if(fun.apply(new Input(k, cur))) {
                if(newHead == null) {
                    newHead = cur;
                    newCur = newHead;
                } else {
                    newCur.next = cur;
                    newCur = newCur.next;
                }
                if(prev == null) {
                    head = head.next;
                } else {
                    prev.next = cur.next;
                }
                cur = cur.next;
                continue;
            }
            prev = cur;
            cur = cur.next;
        }
        if (newCur != null) {
            newCur.next = null;
        }
        return new Output(head, newHead);
    }

    static class Input {
        int x;
        LinkedList cur;

        public Input(int x, LinkedList cur) {
            this.x = x;
            this.cur = cur;
        }
    }


    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }

    public static List<Integer> linkedListToArray(LinkedList head) {
        ArrayList<Integer> array = new ArrayList<>();
        LinkedList current = head;
        while (current != null) {
            array.add(current.value);
            current = current.next;
        }
        return array;
    }

    public static void main(String[] args) {
        LinkedList head = new LinkedList(3);
        head.next = new LinkedList(0);
        head.next.next = new LinkedList(5);
        head.next.next.next = new LinkedList(2);
        head.next.next.next.next = new LinkedList(1);
        head.next.next.next.next.next = new LinkedList(4);
        LinkedList result = rearrangeLinkedList(head, 3);
        List<Integer> array = linkedListToArray(result);
        System.out.println(array);
    }

}
