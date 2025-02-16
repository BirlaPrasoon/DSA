package multithreading_practicals.forkjoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo {
    public static void main(String[] args)
    {
        // Create a pool of threads.
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100000];

        // Give nums some values
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double)i;
        }
        System.out.println("A portion of the original sequence");
        for (int i = 0; i < 9; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        SqrtTransform task
                = new SqrtTransform(nums, 0, nums.length);

        // Start the task
        fjp.invoke(task);
        System.out.println("A portion of the transformed sequence"
                + " (to four decimal places): ");
        for (int i = 0; i < 9; i++) {
            System.out.printf("%.4f ", nums[i]);
        }
        System.out.println();
    }
}

// A task that transforms the elements into their square roots
class SqrtTransform extends RecursiveAction {
    final int seqThreshold = 1000;

    double[] data;

    // Determines what part of data to process
    int start, end;

    SqrtTransform(double[] data, int start, int end)
    {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    // The method where parallel computation will occur
    @Override
    protected void compute()
    {
        // If the number of elements are less
        // than the sequential threshold
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = Math.sqrt(data[i]);
            }
        }
        else {
            // Otherwise, continue to break the data into smaller pieces
            // Find the midpoint
            int middle = (start + end) / 2;

            // Invoke new tasks, using the subdivided tasks.
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end));
        }
    }
}

