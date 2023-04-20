public class RecursionIntro {
    /*
     * prints the numbers from N down to 0
     *
     * Big O(n) linear times because it loops N times
     *
     * Big O determines on n number of recursive calls which is equal to n+1
     *
     * We could define our printNTo0 method with just two steps
     * printNTo0(n)
     * 1. Print n
     * 2. Repeat on n-1
     */

    public static void printNTo0(int n){
        // base case: if n < o, do not keep printing values
       if(n <= 24355){
           System.out.println(n);
           printNTo0(n+1);
       }
    }

    public static void main(String[] args) {

        printNTo0(0);
    }
}
