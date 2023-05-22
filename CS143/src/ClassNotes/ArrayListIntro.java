package ClassNotes;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListIntro {


    public static void main(String[] args) {

        // indexes: 0 1 2 3 4
        int[] a = {-8, 4 ,3, 7, 1};
        // what if we wanted to add a 15 after the 1 in the array
        // we cannot, because arrays are fixed size
        // we could, however, create a larger array
        int[] b = new int[a.length+1];
        //copy everything from a to b
        for(int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        // then, set index 6 to a 15;
        b[5] = 15;
        a = b;
        System.out.println(Arrays.toString(a));

        // arrayLists are like resizable arrays that automatically grow when they run out of space

        // Only objects can go in angle brackets
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(-8);
        a1.add(4);
        a1.add(3);
        a1.add(7);
        a1.add(1);
        a1.add(2);
        System.out.println(a1);
        a1.sort(null);
        System.out.println(a1.size());
        System.out.println(a1);

    }


}
