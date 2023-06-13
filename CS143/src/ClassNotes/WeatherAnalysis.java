package ClassNotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WeatherAnalysis {

    // Map from each month to the list of all high temperatures for that mont
    private Map<Integer, List<Integer>> highTempsPerMonth = new HashMap<>();


    // what is the average high temp for a given month in bremerton?

    private Map<Integer, Double> averageHighTempPerMonth = new HashMap<>();

    private int maxTemp = 0; // Maximum temperature ever seen in the data
    private String dateOfMaxTemp = null; // What date had that max temp

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

            //does this row have a high temp in it?
            if(row.length >= 8 && !row[7].isEmpty()){
                // we have a high temp. lets extact it
                int highTemp = Integer.parseInt(row[7]);

                // we need to figure ou twhich list to put this high temp in based on what month this row is for

                //is this high temp hotter than the hottest we have seen?
                if(highTemp > maxTemp) {
                    maxTemp = highTemp;
                    dateOfMaxTemp = date;
                }

                // do we already have a list of high temps for this month?

                if(highTempsPerMonth.containsKey(month)){
                    // 1. get the existing list
                    List<Integer> highTemps = highTempsPerMonth.get(month);

                    //2. add this high temp to the end of the list
                    highTemps.add(highTemp);
                }
                // otherwise, we do not have a list for this month yet
                else {
                    // 1. create an empty list
                    LinkedList<Integer> highTemps = new LinkedList<>();

                    // 2. add the first high temp to the new linked list
                    highTemps.add(highTemp);

                    // 3. map from the month to that new list
                    highTempsPerMonth.put(month, highTemps);

                }
            }
        }
        // we are done with the data and have lists of high temps for each month
        // Now we can calculate average high temp for each month and put that in our averageHighTempPerMonth Map
        // loop through each month
        for(int month : highTempsPerMonth.keySet()){
            // 1. get the list of high temps for that month
            List<Integer> highTemps = highTempsPerMonth.get(month);
            // 2. add them all up
            double sum = 0;
            for(int temp : highTemps) {
                sum = sum + temp;
            }

            // 3. calculate average
            double average  = sum / highTemps.size();

            // 4. map from the month to the average
            averageHighTempPerMonth.put(month, average);

        }
        System.out.println(averageHighTempPerMonth);
    }
    /*
     * Returns the highest high temp seen in the data
     */
    public int getMaxTemp() {
        return maxTemp;
    }

    /*
     * Returns the date of the highest high temp seen in the data
     */
    public String getDateOfMaxTemp() {
        return dateOfMaxTemp;
    }

    /*
     * Given a month, returns the average high temp for that month.
     *
     * throws an exception if the month is invalid
     *
     * O(1) constant time.
     */
    public double averageHighTempPerMonth(int month) {
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("Month must be between 1 through 12");
        }
        return averageHighTempPerMonth.get(month);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner brem = new Scanner(new File ("brem100.csv"));
        WeatherAnalysis wa = new WeatherAnalysis(brem);
        System.out.println( wa.averageHighTempPerMonth(8));

        System.out.println(wa.getDateOfMaxTemp());
        System.out.println(wa.getMaxTemp());



    }

}


