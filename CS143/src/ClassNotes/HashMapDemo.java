package ClassNotes;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static int mode(int[] nums){
        Map<Integer, Integer> frequencies = new HashMap<>();
        for(int n : nums){
            //if we have not seen in before, create an initial mapping
            // from n to 1
            if(!frequencies.containsKey(n)){
                frequencies.put(n, 1);
            }
            else{
                // get the current frequency of n
                int frequency = frequencies.get(n);
                // add one to it since we are seeing it again
                frequency++;
                // update the value of n frequency.
                frequencies.put(n, frequency);
            }
        }
        // the current mode so far
        int mode = 0;
        // the frequency of the current mode
        int modeFrequency = 0;
        // second loop that goes through the pairs and find the mode
        // (the key with the highest frequency)
        for(int key : frequencies.keySet()){
            // get the frequency for this key
            int frequency = frequencies.get(key);
            // compare it to the current mode's frequency
            if(frequency > modeFrequency){
                mode = key;
                modeFrequency = frequency;
            } else if(frequency == modeFrequency && key > mode){
                mode = key;
            }
        }

        return mode;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,1,1,2,3,3};
        System.out.println(mode(a));
    }
}
