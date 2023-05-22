package ClassNotes;

import java.util.NoSuchElementException;
public class PerformanceAnalysis {




        public static void main(String[] args) {

            // try out the sum method on array sizes
            // 10_000, 20_000, 40_000, 80_0000, 160_000
            // for (int size = 10_000; size <= 160_000; size = size * 2) {
            for (int size = 10_000; size <= 160_000; size = size * 2) {
                //create an array of the current size
                int[] a = new int[size];
                // initialize the elements to their indexes
                //0,0,0,0,0
                //1,2,3,4,5,...size-1
                for (int i = 0; i < a.length; i++) {
                    a[i] = i;
                }
                // for each size lets do 1000 trials
                int trials = 1000;
                // total time it took to do 1000 trials
                long totalTime = 0;

                // for loop that runs 1000 times to do 100 timing experiments for the current size
                for(int i = 0; i < trials; i++) {

                    long start = System.currentTimeMillis();
                    find(a, 1_000_000_000);
                    long end = System.currentTimeMillis();

                    totalTime = totalTime + (end-start);
                }
                //totalTime will contain how long it took to do 1000 trials
                double average = (double) totalTime / trials;

                System.out.println(size +": " + average);

            }
        }

        /*
         * sum returns the sum of all the elements in an array of integers
         *
         * the relationship between input size and time is linear
         *
         * doubling the size will double the time it takes to run
         * O(n)
         */
        public static long sum(int[] a) {
            long sum = 0;
            for(int i = 0; i < a.length; i++) {
                sum = sum + a[i];
            }
            return sum;
        }
        /*
         *find(int[] a, int x) returns the first index that x occurs at in the array a
         *
         * if x is not an element in a, return -1;
         * fi({1,2,3}, 2) => 1
         * find({1,2,3), 6) => -1
         *
         * O(n)
         *
         */

    public static int find(int[] a, int x){
        for(int i = 0; i < a.length; i++){
            if(a[i] == x) {
                return i;
            }
        }
        return -1;
    }

        /*
         * head(int[] a) returns the first element in an array
         *
         * constant time, doubling the length of the array does not increase the time
         *
         * O(1)
         */
        public static int head(int[] a) {
            if(a.length == 0)
                throw new NoSuchElementException("The head does not exist.");

            return a[0];
        }

        /*
         * returns true if at least one element occurs in the parameter array a
         * more than once
         *
         * returns false if all the elements in "a" are unique
         *
         * the relationship between input size and time is quadratic
         *
         * O(n^2) because of the nested for loops
         */
        public static boolean containsDuplicates(int[] a) {
            for(int i = 0; i < a.length; i++) {
                for(int j = i + 1; j < a.length; j++) {
                    if(a[i] == a[j]) {
                        return true;
                    }
                }
            }
            return false;
        }

        /*
         * print1to10 prints the numbers between 1 and 10 each on their own line
         *
         * constant time because the loop always runs 10 times.
         *
         * O(1)
         */
    public static void print1To10() {
        int i = 1;
        while(i <= 10){
            System.out.println(i);
            i++;
        }
    }

}
