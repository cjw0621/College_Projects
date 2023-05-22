package ClassNotes;

public class Find {
    /*
     *find (int[] a, int b) finds the integer in the array
     *
     * will do it recursively
     * if x is not an element in a, return -1
     *
     */

    public static int find(int[] a, int b){
        int i = 0;
        return -1;
    }
    //second version of the method with an extra parameter that keeps track of the index that the current recursive call
    // is examining find(a, x, 0) looks for the value x in the array a at index 0
    // find(a,x,2) looks for the value x in the array a at index 2

    private static int find(int[] a, int x, int i){
        //base case: if x occurs at index i, the result is i
        if(a[i] == x){
            return i;
        }
        //base case: what if index i is undefined?
        if(i >= a.length){
            return -1;
        }
        // base case: if a[i] is bigger than x, x does not exisit
        if(a[i] > x){
            return -1;
        }
        return find(a, x, i +1);
    }
}
