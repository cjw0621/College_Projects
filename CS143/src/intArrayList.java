import java.util.Arrays;

public class intArrayList {
    // the underlying array that stores the data
    // added to this intArraylist
    // Out class will handle resizing the array automatically behind the scenes when it reaches capacity

    private int[] a;
    // length keeps track of how much data we have added to the list
    private int length;

    public intArrayList() {
        // give the underlying array space for 4 elements
        a = new int[4];
        // no data has been added yet
        length = 0;

    }

    /*
     *size() returns how many elements are in the list this does not count the empty spaces
     *
     * constant time method
     */
    public int size(){
        return  length;
    }

    /*
     *get(int i) returns the ith element of the list
     *
     * indexes are between 0 and size() - 1
     *
     * constant time
     */

    public int get(int i){
        //does index i exist?
        if(i < 0 || i >= length){
            throw new IndexOutOfBoundsException();
        }
        return a[i];
    }

    /*
     *set(int i, int x) set the ith elemtn of the list to x
     *
     * index are between 0 and size()-1
     *
     * constant time
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
     * non-constant time
     */
    public void add(int a){
//        System.out.println(a);
        if(this.a.length == length){
            int[] b = new int[this.a.length * 2];
            for(int i = 0; i < this.a.length; i++){
                b[i] = this. a[i];
            }
            this.a = b;
        }
        this.a[length] = a;
        length++;
    }

    @Override
    public String toString() {
        return "intArrayList{" +
                "a=" + Arrays.toString(a) +
                ", length=" + length +
                '}';
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

    }
}
