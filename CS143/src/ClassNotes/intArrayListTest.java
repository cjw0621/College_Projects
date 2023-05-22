package ClassNotes;

public class intArrayListTest {
    public static void main(String[] args) {
        // time how long it takes to add 1000 vs 2000 vs 4000 ... 1_000_000
        // n represents how many adds we are doing for the current trial
        for(int n = 1000; n < 10_000_000; n = n * 2){
            // create an empty list
            intArrayList list = new intArrayList();
            // get the computer's time before adding n elements
            long start = System.currentTimeMillis();
            // add the numbers 1 to n to the list
            for(int i = 1; i <= n; i++){
                list.add(i);
            }
            // get the computer's time before adding n elements
            long end = System.currentTimeMillis();

            System.out.println( n + ": " + (end - start));

        }
    }
}
