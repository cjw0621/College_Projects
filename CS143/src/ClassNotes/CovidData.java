package ClassNotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CovidData {
    public static void main(String[] args) throws FileNotFoundException {
        // attach a Scanner to the us-counties.csv
        Scanner file = new Scanner(new File("us-counties.csv"));
        // set to store the names of all the states
        Set<String> states = new TreeSet<>();
        // Map from state names to the set of all counties in that state
        Map<String, Set<String>> countiesPerState = new HashMap<>();
        // Map from each date to the cumulative nationwide case
        // total as of that date
        Map<String, Integer> casesAsOfDate = new TreeMap<>();
        // latestDate is the most recent date observed in the data
        String latestDate = "2020-01-21";
        // sum of all the counties case data as of the latestDate
        // a nationwide case total as of latestDate
        int caseCount = 0;
        // move the scanner past the header to the first row of data
        file.nextLine();
        // loop through each row from the data
        while(file.hasNextLine()) {
            String line = file.nextLine();
            // use split to convert from a single string
            // to an array of strings
            String[] row = line.split(",");
            // only consider rows with 6 columns
            if(row.length == 6) {
                /*
                 * 0 -> date
                 * 1 -> county
                 * 2 -> state
                 * 3 -> fips (ignore)
                 * 4 -> cases (cumulative)
                 * 5 -> deaths (cumulative)
                 */
                String county = row[1];
                String state = row[2];
                // added the state to the set of all states
                states.add(state);
                // does the map not have a Set of counties
                // for this state name yet?
                if(!countiesPerState.containsKey(state)) {
                    // 1. create a new set for the counties in this state
                    Set<String> counties = new TreeSet<>();
                    // 2. add this county to that set
                    counties.add(county);
                    // 3. map from the state name to that set
                    countiesPerState.put(state, counties);
                }
                else {
                    // we already have a set of counties for this state
                    // 1. get it from the map
                    Set<String> counties = countiesPerState.get(state);
                    // 2. add this county to the set
                    counties.add(county);
                }

                String date = row[0];
                int cases = Integer.parseInt(row[4]);
                // ask the scanner if it is on the last line
                if(!file.hasNextLine()) {
                    // we are done with the last date
                    // add a mapping for it
                    casesAsOfDate.put(latestDate, caseCount);
                }
                // if we are seeing another entry for the same date
                else if(date.equals(latestDate)) {
                    // add this row's case data to our nationwide total
                    caseCount = caseCount + cases;
                }
                else {
                    // this row is the first row for the next date
                    // we are done with the previous date and can
                    // add a pair to the map
                    casesAsOfDate.put(latestDate, caseCount);
                    // update the latestDate
                    latestDate = date;
                    // reset the caseCount
                    caseCount = cases;
                }
            }

        }

        // the date with the highest number of new covid cases
        String dateOfMostCases = "2020-01-21";
        // how many new covid cases were there on that date
        int mostCases = 1;


        // using out casesAsOfDate map, construct a new map from each date to the number of new cases on that date
        Map<String, Integer> newCasesPerDate = new TreeMap<>();
        // variable to track the previous date's cumulative total
        int prevCumulativeCases = 0;
        // loop through each date in our original map
        for(String date : casesAsOfDate.keySet()){
            // retrieve the cumulative case total as of this date
            int cumulativeCases = casesAsOfDate.get(date);
            // calculate new cases to be this day's total - the previous day total
            int newCases = cumulativeCases - prevCumulativeCases;
            // is this date's new cases larger than the highest single day new case total we have seen?
            if(newCases > mostCases){
                dateOfMostCases = date;
                mostCases = newCases;
            }
            // map from this date to the number of new cases
            newCasesPerDate.put(date, newCases);
            // before moving on to the next date, we need to update preCumulativeCases
            prevCumulativeCases = cumulativeCases;
        }

        System.out.println(states);
        System.out.println(countiesPerState.get("Washington"));
        System.out.println(newCasesPerDate);
        // asking the map to tell us how many cumulative cases
        // nationwide had there been as of June 29, 2021
        System.out.println(newCasesPerDate.get("2021-06-29"));

        System.out.println("Highest single day case total was: " + dateOfMostCases + ": " + mostCases);
    }
}
