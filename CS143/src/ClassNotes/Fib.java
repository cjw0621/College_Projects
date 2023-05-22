package ClassNotes;

public class Fib {
    public static void main(String[] args) {
//
//        System.out.println(fib(100));
        for(int i = 0; i <= 20; i++){
            fibA(i);
            System.out.println(i + " : "  + count);
            count = 0;
            fibA = new long[101];
        }

//        System.out.println(fibA(100));

    }

    private static int count = 0;

    /*
     *compute the Nth term of the fibonacci sequence recursively
     *
     * assume non-negative n
     *
     * O(2^n)
     */

    public static long fib(int n) {
        count++;
        // base case: fib(0) = fib(1) = 1
        if(n == 0 || n == 1){
            return 1;
        }
        // recursive case: fib(n-2) + fib(n-1)
        return fib(n-2) + fib(n-1);
    }

    /*
     *compute the Nth term of the fibonacci sequence recursively
     *
     * assume non-negative n
     *
     * Alternate version that uses an array to stroe previously computed values
     */

    // If there is a 0 at index i that means the ith term has not yet been computed
    // If there is something > 0 at index i, then the ith term has been computed

    private static long[] fibA = new long[101];

    public static long fibA(int n) {
        count++;
        if(n == 0 || n == 1){
            return 1;
        }

        // has the nth term already been computed?
        else if(fibA[n] > 0){
            //if fibA at n is greater than 0, we can return that value at index n
            return fibA[n];
        }else{
            //1. compute the nth term, and store it in the array
            fibA[n] = fibA(n-2) + fibA(n-1);
            // 2. return the store values
            return fibA[n];
        }
    }


}
