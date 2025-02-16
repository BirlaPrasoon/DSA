package algoexpert.arrays;

import java.util.Arrays;

public class ArrayOfProducts {

    public int[] arrayOfProducts(int[] array) {
        int product = 1;
        int zeroCount = 0;
        int nonZeroProduct = 1;
        for(int a: array) {
            product *= a;
            if(a == 0){
                zeroCount++;
            } else {
                nonZeroProduct*= a;
            }
        }
        if(zeroCount == 0) {
            for (int i = 0; i < array.length; i++) {
                // division by zero will cause java.lang.ArithmeticException
                array[i] = product / array[i];
            }
        } else if (zeroCount == 1) {
            for(int i = 0; i < array.length; i++) {
                if(array[i] != 0) {
                    array[i] = 0;
                } else {
                    array[i] = nonZeroProduct;
                }
            }
        } else {
            Arrays.fill(array, 0);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayOfProducts ap = new ArrayOfProducts();
        System.out.println(Arrays.toString(ap.arrayOfProducts(a)));
    }
}
