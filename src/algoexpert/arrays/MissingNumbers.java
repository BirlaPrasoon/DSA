package algoexpert.arrays;

import java.util.Arrays;

// This is a good question with an important trick. Not sure where it might be useful but a good trick.
// The question becomes complex when we have to go for O(n) time and O(1) space.
public class MissingNumbers {

    public int[] missingNumbers(int[] nums) {
        int n = nums.length + 2;
        int sum = (n * (n+1))/2;
        int givenSum = Arrays.stream(nums).sum();
        int aplusb = sum - givenSum;
        int average = aplusb/2;
        // first number will be less than or equal to average and second will be greater or equal.
        int leftSum= 0, rightSum = 0;
        int sumRangeSmallerInclude = (average * (average+1)) / 2;
        for(int N: nums) {
            if(N <= average) {
                leftSum += N;
            }
        }

        int missingLesser = sumRangeSmallerInclude - leftSum;
        int missingRighter = aplusb - missingLesser;

        return new int[] {missingLesser, missingRighter};
    }

    public static void main(String[] args) {
        int a[] = {1,3,5};
        MissingNumbers mn = new MissingNumbers();
        System.out.println(Arrays.toString(mn.missingNumbers(a)));
    }
}
