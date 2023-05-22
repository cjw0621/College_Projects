package ClassNotes;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {

        int[] a = new int[1_000_000];

//        int[] a = {-11, -8, 2, 3, 5, 7, 14};
//        System.out.println(binarySearch(a, 3));
//
//        System.out.println(binarySearch(a, -11));
//
//        System.out.println(binarySearch(a, 7));
//        System.out.println(binarySearch(a, 14));
//        System.out.println(binarySearch(a, 4));

        /*
         * Experiment results:
         *
         * Unoptimized find: StackOverflowError
         * Recursive find method (with small optimization) takes 90ms-98ms
         *
         * Binary search method: 1- 4ms
         */

        for(int i = 1; i < a.length; i++){
            // makea a[i] be equal to a[i-1] + either 1 2 or 3

            a[i] = a[i-1] + (int) (Math.random() * 3) + 1;

        }
//        System.out.println(Arrays.toString(a));

        long start = System.currentTimeMillis();
        // search for the numbers 0,1,...., 9999
        for(int i = 0; i < 10_000; i++){
           binarySearch(a,i);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
    /*
     * binarySearch(int[] a, int x, int start, int end) returns an index where x occurs at in array a
     *
     * "a" is the given int array, and int x is the number being searched, start and end is the start and end index
     *
     * array will have to be sorted in order to work
     *
     * faster approach than "find"  using the binary search algorithm
     *
     * O(log(n))
     */
    public static int binarySearch(int[] a, int x){
        return binarySearch(a, x, 0, a.length - 1);
    }

    //private "recursive driver" method that has extra parameters start and end that represent the begin and end indexes
    // of the current range for the recursive call
    private static int binarySearch(int[] a, int x, int start, int end){
        // case for an invalid range
        if(end < start){
            return -1;
        }
        // 1. calculate the middle index between start and end
        int mid = (start + end) / 2;
        // 2. give the middle value a[mid] and alias for convenience
        int midVal = a[mid];

        // is x what we are looking for) < midVal?
        if(x < midVal){
            return binarySearch(a, x, start, mid - 1);// go left and excluded the right half of the current range

            // is x what we are looking for) > midVal?
        }else if(x > midVal){
            return binarySearch(a, x, mid + 1, end);// go right and exclude the left half of the current range

            // is x == midVal?
        } else {
            return mid;
        }
    }
}
