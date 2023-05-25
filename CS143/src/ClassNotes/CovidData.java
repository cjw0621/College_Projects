package ClassNotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CovidData {
    public static void main(String[] args) throws FileNotFoundException {

        //creates a new scanner object with a file parameter
        Scanner file = new Scanner(new File("us-counties.csv"));
        // set to store the name of all the states
        Set<String> states = new TreeSet<>();
        Map<String, Set<String>> countiesPerState = new HashMap<>();

        while(file.hasNextLine()){
            String line = file.nextLine();
            String[] row = line.split(",");
            if(row.length == 6){
                /*
                 *0 -> date
                 * 1 -> county
                 * 2 -> state
                 * 3 -> fips (ignore)
                 * 4 -> cases (cumulative)
                 * 5 - > deaths (cumulative)
                 */

                String county = row[1];
                String state = row[2];
                states.add(state);
                // does the mpa not have a set of counties for this state name yet?

                if(!countiesPerState.containsKey(state)){
                    // create a new set for the counties in this state
                    Set<String> counties = new TreeSet<>();
                    // add this county to that set
                    counties.add(county);

                    countiesPerState.put(state, counties);

                }
            }
//            System.out.println(states);

        }
        System.out.println(countiesPerState.get("Washington"));






    }
}
