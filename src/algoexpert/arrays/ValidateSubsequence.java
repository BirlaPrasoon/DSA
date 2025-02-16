package algoexpert.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidateSubsequence {

    public static boolean isValidSubsequence(
            List<Integer> array, List<Integer> sequence
    ) {
        if(sequence.size() == 0) return true;
        if(array.size() < sequence.size()) return false;
        int i = 0, j = 0;
        for(; i<array.size(); i++) {
            if(Objects.equals(array.get(i), sequence.get(j))) {
                j++;
                // This is where I missed, I can't afford such mistakes in interview. once J reaches end of sequence, we need to return true.
                if(j == sequence.size()) return true;
            }
        }
        return j == sequence.size();
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10));
        List<Integer> sequence = new ArrayList<>(Arrays.asList(22, 25, 6));
        System.out.println(isValidSubsequence(array, sequence));
    }
}
