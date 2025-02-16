package algoexpert.stacks;

import java.util.ArrayList;
import java.util.Arrays;

public class SortStackInPlace {

    public ArrayList<Integer> sortStack(ArrayList<Integer> stack) {
        sortStack(stack, 0);
        return stack;
    }

    public void sortStack(ArrayList<Integer> stack, int index) {
        if(index >= stack.size()) return ;
        int smallest = index;
        for(int i = index; i<stack.size(); i++) {
            if(stack.get(i) < stack.get(smallest)){
                smallest = i;
            }
        }
        // swap with starting index
        int temp = stack.get(index);
        stack.set(index, stack.get(smallest));
        stack.set(smallest, temp);
        // recursively sort remaining stack
        sortStack(stack, index+1);
    }

    public static void main(String[] args) {
        ArrayList<Integer> stack = new ArrayList<>(Arrays.asList(-1,-1,-1, -3));
        SortStackInPlace si = new SortStackInPlace();
        System.out.println(si.sortStack(stack));
    }
}
