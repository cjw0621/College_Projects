import java.util.Arrays;

public class intArrayList {
    // the underlying array that stores the data
    // added to this intArraylist
    // Our class will handle resizing the array automatically behind the scenes when it reaches capacity

    private int[] a;
    // length keeps track of how much data we have added to the list
    private int length;

    //constant time O(1)
    public intArrayList() {
        // give the underlying array space for 4 elements
        a = new int[4];
        // no data has been added yet
        length = 0;

    }

    /*
     *size() returns how many elements are in the list this does not count the empty spaces
     *
     * constant time method O(1)
     */
    public int size(){
        return  length;
    }

    /*
     *get(int i) returns the ith element of the list
     *
     * indexes are between 0 and size() - 1
     *
     * constant time O(1)
     */

    public int get(int i){
        //does index i exist?
        if(i < 0 || i >= length){
            throw new IndexOutOfBoundsException();
        }
        return a[i];
    }

    /*
     *set(int i, int x) set the ith elements of the list to x
     *
     * index are between 0 and size()-1
     *
     * constant time O(1)
     */
    public void set(int i, int x){
        if(i < 0 || i >= length){
            throw new IndexOutOfBoundsException();
        }
        a[i] = x;
    }

    /*
     * add(int a) returns nothing, only adds an element to array a at n length
     *
     * non-constant time O(N) worst case
     *
     * best case: O(1)
     *
     * Overall, we could classify our add method as being no worse than O(n)
     */
    public void add(int a){
//      Non-constant time of the add method
        if(this.a.length == length){
            int[] b = new int[this.a.length * 2];
            for(int i = 0; i < this.a.length; i++){
                b[i] = this. a[i];
            }
            this.a = b;
        }
        //This is the constant time part of the add method
        this.a[length] = a;
        length++;
    }

    // non-constant time because Arrays.toString() is non-constant time
    // O(N)
    @Override
    public String toString() {
        return "intArrayList{" +
                "a=" + Arrays.toString(a) +
                ", length=" + length +
                '}';
    }

    /*
     * addToBegining(int a) adds an int to the begining of the array
     */

    public int[] addToBeginning(int[] a, int b) {
        int indexStartPos = 0;
        int[] c = new int[a.length];
        for(int i = 0; i < a.length; i++){
            if(a[i] == 0){
                indexStartPos++;
            }
        }

        if(indexStartPos == 0) {
          c = new int[a.length * 2];
        }

        c[a.length-1] = b;
        for(int i = 0; i< a.length; i++){
            c[a.length + i] = a[i];
        }

        System.out.println(Arrays.toString(c));

        return c;
    }

    public static void main(String[] args) {
        intArrayList ial = new intArrayList();

        ial.add(1);
        ial.add(10);
        ial.add(12);
        ial.add(20);
        ial.add(25);
        ial.add(30);
        System.out.println(ial);

        intArrayList a = new intArrayList();



    }
}
