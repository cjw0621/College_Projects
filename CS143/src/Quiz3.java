import java.util.Arrays;

public class Quiz3 {
    public static int sum(int[] a) {
        int s = 0; // This should change for multiply - if you multiply with 0, you get 0!
        for (int i = 0; i < a.length; i++) {
            s = s +a[i]; // This should change for multiply!
        }
        return s;
    }

    public static void main(String[] args) {
        int trials = 20;
        for (int size = 100000; size <= 10000000; size += 100000) {
            long totalTime = -1;
            int[] a = new int[size];
            Arrays.fill(a, size); // Make sure to import java.util.Arrays
            for (int t = 0; t < trials; t++) {
                System.gc(); // Force garbage collection to happen
                long start = System.nanoTime();
                sum(a);
                long end = System.nanoTime();
                totalTime += end - start;
            }
            System.out.println(size + " " + totalTime/trials);
        }
    }
}
