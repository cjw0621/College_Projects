package ClassNotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeatherAnalysis {

    // what is the average high temp for a given month in bremerton?

    private Map<Integer, Double> averageHighTempPerMonth = new HashMap<>();

    public WeatherAnalysis(Scanner file){
        // discard the header
        file.nextLine();

        // while loop that processes each line of  data from the csv file
        while(file.hasNextLine()) {
            String[] row = file.nextLine().split(",");
//            System.out.println(Arrays.toString(row));


            //use subString to get rid of the quotation marks.
            // extracts the characters from index 1-11

//            String date = "\"2019-10-18\"";
//            System.out.println( date.substring(1, date.length() - 1));
            // use a for loop that loops through each column in this row
            // uses substring to strip out the double quotes
            for(int i = 0; i < row.length; i++) {

                // make sure column i is non-empty
                if (!row[i].isEmpty()) {
                    row[i] = row[i].substring(1, row[i].length() - 1);
                }
            }
            /*
             * Columns that we care about:
             *
             * 1 -> date
             * 3 -> multi day precipitation total (inches)
             * 4 -> single day precipitation total (inches)
             * 5 -> snow fall total (inches)
             * 7 -> high temp for that day (Fahrenheit)
             * 8 -> low temp for that day (Fahrenheit)
             */

            String date = row[1];
            String[] dateArray = date.split("-");
            int month = Integer.parseInt(dateArray[1]);
            System.out.println(month);

//            System.out.println(Arrays.toString(row));
        }
    }

    public double averageHighTempPerMonth(int month) {
        //TODO: implement
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner brem = new Scanner(new File ("brem100.csv"));
        WeatherAnalysis wa = new WeatherAnalysis(brem);


    }

}


