package ClassNotes;

public class Pow {

    /*
     *pow(int a, int b) recursively computes a to the power of b
     *
     * assume a is positive (1, inf)
     * Assume b is non-negative(0,inf)
     */
    public static int pow(int a, int b) throws Exception {


        if(a == 0 && b == 0){
            throw new Exception("Undefined");
        }
        if(b == 0){
            return 1;
        }
        if(b == 1){
            return a;
        }

       return a * pow(a, b-1);
    }
    public static void main(String[] args) throws Exception {
//        System.out.println(Math.pow(2,4));//16.0
//        System.out.println(Math.pow(2,.5));//4.0
        System.out.println(pow(4, 3));
        System.out.println(Math.pow(4,3));
        System.out.println(pow(3,1));
        System.out.println(pow(0,0));
    }
}
