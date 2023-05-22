package ClassNotes;

public class Factorial {

    /*
     * Iterative (loop) approach to computing N!
     *
     * only works for small values
     */
    public static long fact(int n){
        long product = 1;
        for(int x = n; x >=1; x--){
            product = product * x;
        }
        return product;
    }

    public static long factR(int n){
        // base case: 1! = 1
        if(n == 1){
            return 1;
        }
        return n * factR(n-1);
    }



    public static void main(String[] args) {


        System.out.println(factR(5));
    }
}
