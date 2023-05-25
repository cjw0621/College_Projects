package Assignment3;

/*
 * Chase Whitney
 *
 * Examples of different recursive methods
 *
 * Assignment 3
 *
 * All methods do not use loops that are non-recursive
 */
public class RecursionIntro {

    /* Assignment 3 part 1
     * eduodd(long n) returns a long
     *
     * method increases all even digits in long n by 1 and decreases all odd digits in long n by 1
     */
    public static long eduodd(long n){

         if (n < 0) {
                return -eduodd(-n);
            }
         else if(n < 10 ) {

             if (n % 2 == 0) {
                 return (n + 1) % 10;
             } else {
                 return (n - 1) % 10;
             }
         }

       else {
           long lastDigit = n % 10; // saves the last digit of N into a variable lastDigit
           long remaining = n/10; // Saves the rest of the number minus the last digit into a variable remaining

           return eduodd(lastDigit,remaining);
       }
    }

    /*
     * helper method that takes the last digit and checks if even and returns by calling the eduodd(long n) method
     * with the remaining plus the last digit that was changed
     */
    private static long eduodd( long last, long remaining){
       if(last % 2 == 0){ // checks if the last digit is even if true add one to it
           last = last + 1;
       }else{ // if last is odd subtract 1 from it
           last = last -1;
       }
       return eduodd(remaining) * 10 + last;
    }

    /* Assignment 3 Part 2A
     * fibby(int n) returns the output that is required  assignment 3
     */

    public static int fibby(int n){
        if(n < 0){ // returns 0 if n is negative to
            return 0;
        }
        else if (n == 0) { // base case, returns 1 if n is 0

            return 1;
        }else{ // recursive call to fibby(int n)  appropriate output
            return fibby(n/3) + fibby(2*n/3);
        }
    }

    /*Assignment 3 Part 2B
     * printSparseTable(int start, int end) returns void
     *
     * prints table ensuring to skip over previously printed fibby value
     */

    public static void printSparseTable(int start, int end) {
        if(start <= end){ // base case
            System.out.println(start + " " + fibby(start)); // prints table line by line
            printSparseTable(printSparseTable(start), end); // calls on helper method as a parameter  start
        }
    }

    /* Assignment 3 Part 3A
     * printSparseTable(int i) returns an int
     *
     * checks if fibby(i) == fibby(i+1) if true than add 1 to i and recursively call printSparseTable(int i)
     *
     * else returns just i + 1
     */

    private static int printSparseTable(int i){
        if(fibby(i) == fibby(i+1)){ // checks if i and i + 1 are equal in the fibby sequence
            return printSparseTable(i + 1); // calls on own method adding one until fibby(i) && fibby(i+1)
                                              // are not equal
        }else{
            return i + 1;
        }
    }


    /*
     * lp2lt(int n) returns int
     *
     * calculates and returns the largest integer power of 2 less than n
     *
     * may assume n is greater than 1
     *
     * if n is 10 the largest power of 2 less than n is 8 (8 is 2 cubed)
     * if n is 8 the answer is 4
     *
     * if n is 2 the answer is 1
     */
    public static int lp2lt(int n) {

        int powerOfTwo = 2;
        if (n <= powerOfTwo) {
            return 1;
        } else {
            return lp2lt((n + 1) / 2) * powerOfTwo;
        }
    }

    public static int champion(boolean[] a) {
        if (a.length == 1) {
            return 0;
        } else if (a.length == 2) {
            if (a[0] != a[1]) {
                return 0;
            } else {
                return 1;
            }
        } else if(a.length % 2 == 1){
            boolean endValue = a[a.length - 1];
            if(a[0] != a[1]){
                return championHelper(a, 0, 1);
            } else {
                return 1;
            }

        }else {
            return championHelper(a, 0, a.length - 1);
        }
    }

    private static int championHelper(boolean[] a, int start, int end) {
        if (start == end) {
            return start;  // Base case: Only one participant remains, return its index
        }

        int mid = start + lp2lt(end - start + 1) / 2;  // Calculate the midpoint of the range

        int leftWinner = championHelper(a, start, mid);  // Recurse on the left half
        int rightWinner = championHelper(a, mid + 1, end);  // Recurse on the right half

        // Determine the winner based on the values at the left and right indices
        if (a[leftWinner] != a[rightWinner]) {
            return a[leftWinner] ? leftWinner : rightWinner;
        } else {
            return rightWinner;  // If both values are the same, the right participant wins
        }
    }
}
