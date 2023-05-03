package Assignment2;

import java.util.Arrays;

public class IAList {
    private int[] a; // Underlying array
    private int length; // Number of added elements in a
    private int emptySpace; // counts the number of empty spaces in the array
    public IAList() {
        length = 0; // Start with no added elements in a
        a = new int[4]; // A little room to grow
        emptySpace = 0;
    }
    public int get(int i) { // Retrieve an added element, O(1)
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(i);
        }
        return a[i + emptySpace]; // Retrieve the element at position i
    }
    public int size() { // Number of added elements, O(1)
        return length; // The number of added elements
    }
    public void set(int i, int x) { // Modify an existing element, O(1)
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException(i);
        }
        a[i + emptySpace] = x; // Change the existing element at position i + zeros to x
    }

    /*
     * add(int x) adds x into array
     *
     * if length with the added empty space is greater than
     *
     * checks if there is any empty spaces and creates a new array that is doubled in size to prevent running out of space
     *too quickly
     *
     */
    public void add(int x) { // Add an element to the end, O(n) for n
        if (length + emptySpace >= a.length) {
// Create new array of double the length
            int[] b = new int[a.length * 2];
// Copy the elements of "a" to the corresponding indexes of b
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i];
            }
// Reassign a reference to b
            a = b;
        }
// Checks if the array has any empty spaces, if not than the new array doubles in length and copies the elements from a
        //into the new array
        if (emptySpace == 0) {

            emptySpace = a.length;// converts the value of the number of empty spaces to the size of array A

            int[] b = new int[emptySpace * 2];// initializes a new array B with length double array A length

            // copies the values of array a into be starting at the index after all the zeros that has been tracked
            // globally
            for (int i = 0; i < a.length; i++) {
                b[i + emptySpace] = a[i];//copies the elements from array A into array b starting at index i + the number
                                            //of empty spaces available.
            }

            a = b;
        }
// Place x at the end of the IAList
        a[length + emptySpace] = x;
// Increase length by 1
        length++;

    }

    /*
     *addBefore(int x)
     *
     * calls add(x) to add int x into array
     *
     * everytime method is called, emptySpace is decremented to track the remaining empty space
     * adds x using empty space int as an index.
     */
    public void addBefore(int x) {
        add(x); //
        emptySpace--;// Decrement the number of empty spaces so that x can be stored in the index before
        a[emptySpace] = x;// Stored in the index designated by the number that is empty spaces inside the array
    }




    @Override
    public String toString() {
        return "IAList{" +
                "a=" + Arrays.toString(a) +
                ", length=" + length +
                '}';
    }

    public static void main(String[] args) {
        IAList a = new IAList();
        for(int i = 1; i <= 5; i++){

            a.addBefore(i);
        }



        System.out.println(a);

    }
}

