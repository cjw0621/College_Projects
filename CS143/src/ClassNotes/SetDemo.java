package ClassNotes;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        /*
         * All sets in java support the following 4 operations
         * 1. add: adds a new member to the set
         * 2. remove: removes a member from the set
         * 3. contains: check if a value is a member of the set
         * 4. size: gives us the number of members in the set
         *
         * HashSets are a set that is implemented using a hash table
         * (more on this later)
         *
         * HashSets do not guarantee any particular ordering
         *
         * HashSets are O(1) for adding, removing and checking for membership (contains method)
         *
         * no indexes in sets
         *
         * TreeSets does order there elements in alphabetical ordering and numerical ordering.
         *
         *
         * TreeSets are O(log(n)) for adding, removing, and checking for membership(Contains method)
         *
         */

        Set<Integer> n = new TreeSet<>();
        n.add(1);
        n.add(5);
        n.add(2);
        n.add(7);
        n.add(10);
        n.add(11);
        System.out.println(n);


        Set<String> s = new HashSet<>();
        s.add("David");
        s.add("CS 143");
        s.add("TCC");
        s.add("120");
        System.out.println(s);
        s.add("David"); // will not add another david

        System.out.println(s.remove("120"));
        System.out.println(s);

        System.out.println(containsDuplicates(new int[] {1,5,7,3,5,8,9,11,10,25,2,6}));
        System.out.println(containsDuplicates(new int[] {1,5,7,3,8,9,11,10,25,2,6}));


    }
    /*
     * O(n) linear time version of our original containsDuplicates method
     *
     */
    public static boolean containsDuplicates(int[] a){
        // keeps track of which elements we have seen in the array
        Set<Integer> seenSoFar = new HashSet<>();
        // loop through each array element
        for(int i : a){
            //have we already seen i before?
            if (seenSoFar.contains(i)){//O(1)

                return true;
            }else{
                seenSoFar.add(i);//O(1)
            }
        }
        return false;
    }

}
