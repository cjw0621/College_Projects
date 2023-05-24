package ClassNotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CovidData {
    public static void main(String[] args) throws FileNotFoundException {

        //creates a new scanner object with a file parameter
        Scanner file = new Scanner(new File("us-counties.csv"));
        // set to store the name of all the states
        Set<String> states = new TreeSet<>();

        while(file.hasNextLine()){
            String line = file.nextLine();
            String[] row = line.split(",");
            if(row.length == 6){
                /*
                 *0 -> date
                 * 1 -> count
                 * 2 -> state
                 * 3 -> fips (ignore)
                 * 4 -> cases (cumulative)
                 * 5 - > deaths (cumulative)
                 */
                String state = row[2];
                states.add(state);
            }
            System.out.println(states);
        }




    }
}
