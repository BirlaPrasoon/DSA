import java.util.Random;

public class RandomArrayGenerator {

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] randomArray = new int[size];

        for (int i = 0; i < size; i++) {
            randomArray[i] = random.nextInt(100); // generates random number between 0 and 99
        }

        return randomArray;
    }

    public static void main(String[] args) {
        int size = 10; // specify the desired array size
        int[] randomArray = generateRandomArray(size);

        // Print the generated array
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
    }
}

