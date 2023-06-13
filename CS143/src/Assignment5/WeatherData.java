package Assignment5;

import java.util.Scanner;
import java.util.*;

/**
 * A class representing weather data including temperatures, snowfall, and
 * precipitation over a period of time.
 * 
 * Chase Whitney
 * 
 * PLEASE DO NOT COPY THIS FILE TO OTHER STUDENTS OR TO WEBSITES LIKE CHEGG,
 * GITHUB, ETC WHERE OTHERS MAY VIEW IT!!! IT IS YOUR WORK AND YOU SHOULD BE
 * PROUD OF WHAT YOU HAVE ACCOMPLISHED! IN ADDITION, THIS FILE CONTAINS THE
 * COPYRIGHTED WORK OF MARTIN HOCK AND IS ONLY LICENSED FOR USE BY INDIVIDUAL
 * STUDENTS FOR NONPROFIT EDUCATIONAL PURPOSES.
 */
public class WeatherData {

	/**
	 * Load the data provided by the Scanner into your WeatherData class. The data
	 * should be loaded one line of text at a time using the Scanner's nextLine
	 * method. The first line of text should be discarded as it is a header. Each
	 * line of text, stored in a String, should be split using the .split(",")
	 * method. This will create an array of Strings. The relevant Strings are index
	 * 1, which indicates the date for the data on this line, index 2, which will
	 * indicate if the day contains multiple days of precipitation (or will be blank
	 * if it is just a single day of data), index 3, which indicates a multiple day
	 * precipitation total (or will be blank if it is a single day of
	 * precipitation), index 4, which indicates the single day precipitation total
	 * (or will be blank if the day contains multiple days of precipitation), index
	 * 7 which indicates the day's high temperature (and may be blank if this was
	 * not recorded), and index 8 which indicates the day's low temperature (and may
	 * be blank if this was not recorded). All other indexes may be ignored. All
	 * non-blank indexes in the array will contain a string surrounded by quotes -
	 * the substring command is the easiest way to get rid of them. Temperatures are
	 * always integers but precipitation and snowfall totals may contain decimal
	 * points. Dates are always of the form YYYY-MM-DD (a four digit year followed
	 * by a dash followed by a two digit month followed by a dash followed by a two
	 * digit day).
	 * 
	 * @param file Scanner connected to a weather data file
	 */

	private int maxTemp = Integer.MIN_VALUE;
	private int minTemp = Integer.MAX_VALUE;

	private double snowFall;

	private String dateOfMinTemp = null;
	private String dateOfMaxTemp = null;

	private String dateOfMaxSnowfall = null;

	private Map<Integer, List<Integer>> HighestHighOfLow = new HashMap<>();

	private Map<Integer, List<Integer>> highTemperatures = new HashMap<>();
	private Map<Integer, List<Integer>> lowTemperatures = new HashMap<>();

	// there should only be 12 total keys with a linked value to determine highest and lowest percipitation
	private Map<Integer, List<Double>> precipitationTotals = new HashMap<>();

	private Map<Integer, Double> averagePrecipitation = new HashMap<>();

	//key = year
	private Map<Integer, List<Double>> snowfallTotals = new HashMap<>();
	public WeatherData(Scanner file) {
		file.nextLine(); // skips first line
		// members. Remember that only the following methods are used to retrieve data,
		// so you can save the data using specialized collections for efficiency.
		// The constructor will probably do the hard work of setting up all the
		// collections so that the later methods can run efficiently.
		// Each method will probably have its own collection!

		while(file.hasNextLine()){
			String[] row = file.nextLine().split(",");

			for(int i = 0; i < row.length; i++){

				if(!row[i].isEmpty()) {
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



			int year = Integer.parseInt(dateArray[0]);
			int month = Integer.parseInt(dateArray[1]);


			//High/low temp
			if(row.length >= 9 && !row[7].isEmpty() && !row[8].isEmpty()) {
				int highTemp = Integer.parseInt(row[7]);
				int lowTemp = Integer.parseInt(row[8]);

				if(highTemp > maxTemp) {
					maxTemp = highTemp;
					dateOfMaxTemp = date;
				}

				if(lowTemp < minTemp) {
					minTemp = lowTemp;
					dateOfMinTemp = date;
				}

				if(highTemperatures.containsKey(month)){
					List<Integer> highTemps = highTemperatures.get(month);

					highTemps.add(highTemp);
				}

				if(lowTemperatures.containsKey(month)){
					List<Integer> lowTemps = lowTemperatures.get(month);

					lowTemps.add(lowTemp);
				}

				else {
					LinkedList<Integer> highTemps = new LinkedList<>();
					LinkedList<Integer> lowTemps = new LinkedList<>();

					highTemps.add(highTemp);

					lowTemps.add(lowTemp);

					lowTemperatures.put(month, lowTemps);

					highTemperatures.put(month, highTemps);
				}
			}

			//snowfall
			if(row.length >= 6 && !row[5].isEmpty()){
				double snowfall = Double.parseDouble(row[5]);

				if(snowfallTotals.containsKey(year)){
					List<Double> snowfalls = snowfallTotals.get(year);

					if(snowfall > snowFall) {
						snowfalls.add(snowfall);
					}
				}

				else {
					LinkedList<Double> snowfalls = new LinkedList<>();

					if(snowfall > snowFall) {
						snowfalls.add(snowfall);
					}

					snowfallTotals.put(year, snowfalls);
				}
			}

			//precipitation


			if(row.length >= 5) {
				double singleRainFall = 0;
				double multiRainFall = 0;
				List<Double> precipitationTotal = precipitationTotals.get(month);

				if (!row[3].isEmpty()) {
					if (precipitationTotals.containsKey(month)) {
						precipitationTotal = precipitationTotals.get(month);
						multiRainFall = Double.parseDouble(row[3]);
						if (precipitationTotal != null) {
							precipitationTotal.add(multiRainFall);
						}
					} else {

						precipitationTotal = precipitationTotals.get(month);

						if (precipitationTotal != null) {
							precipitationTotal.add(multiRainFall);
						}
					}

				}

				if (!row[4].isEmpty()) {
					if (precipitationTotals.containsKey(month)) {
						precipitationTotal = precipitationTotals.get(month);
						singleRainFall = Double.parseDouble(row[4]);
						if (precipitationTotal != null) {
							precipitationTotal.add(singleRainFall);
						}
					} else {

						precipitationTotal = precipitationTotals.get(month);

						if (precipitationTotal != null) {
							precipitationTotal.add(singleRainFall);
						}
					}
					precipitationTotals.put(month, precipitationTotal);

				}
			}

			//HighestHighofLow
			if(row.length >= 9 && !row[7].isEmpty() && !row[8].isEmpty()) {
				int highTemp = Integer.parseInt(row[7]);
				int lowTemp = Integer.parseInt(row[8]);

				if(HighestHighOfLow.containsKey(lowTemp)){
					List<Integer> highTemps = HighestHighOfLow.get(lowTemp);

					highTemps.add(highTemp);
				}

				else {

					LinkedList<Integer> highTemps = new LinkedList<>();

					highTemps.add(highTemp);

					HighestHighOfLow.put(lowTemp, highTemps);
				}
			}

		}
	}

	/**
	 * Determine whether the given temperature was ever seen as a high temperature
	 * in the data provided to the constructor. (10 points)
	 * 
	 * (HINT: This is a membership question. What data structure have we seen that
	 * can help us answer this question?)
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return true if high temp, false otherwise
	 */
	public boolean highTemp(int degrees) {

		for(int i : highTemperatures.keySet()){
			if((highTemperatures.get(i).contains(degrees))){
				return true;
			}
		}

		return false;
	}

	/**
	 * Determine whether the given temperature was ever seen as a low temperature in
	 * the data provided to the constructor. (10 points)
	 * 
	 * @param degrees Temperature (same units as data file)
	 * @return true if low temp, false otherwise
	 */
	public boolean lowTemp(int degrees) {

		for(int i : lowTemperatures.keySet()){
			if((lowTemperatures.get(i).contains(degrees))){
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine the total amount of snowfall recorded in the given year. (20
	 * points)
	 * 
	 * (HINT: What data structure would allow us to correspond an amount of snowfall
	 * with a year? How much snowfall is recorded in a year not found in the file?)
	 * 
	 * @param year
	 * @return
	 */
	public double totalSnowfallForYear(int year) {

		if(snowfallTotals.get(year)==null){
			return 0.0;
		}else{
			List<Double> totalSnow = snowfallTotals.get(year);

			double sum = 0.0;
			for(double i : totalSnow){
				sum+=i;
			}

			return sum;
		}

	}

	/**
	 * Determine the average (mean) total precipitation recorded for the given
	 * month. Be sure to include multi-day precipitation amounts. (Assume that all
	 * of the precipitation occurs on the date of the multi-date range - never
	 * divide it across months.) (20 points)
	 * 
	 * Hint: Since there are 100 years of precipitation data, average precipitation
	 * can be calculated by adding up the total precipitation for each month then
	 * dividing by 100 to derive the average.
	 * 
	 * @param month
	 * @return
	 */
	public double averagePrecipitationForMonth(int month) {

		double sum = 0;

		if(precipitationTotals.get(month)!= null){
			double average = sum / precipitationTotals.get(month).size();
			for(double i : precipitationTotals.get(month)){
				sum += i;
			}

			return average;
		}
		else {
			return 0.0;
		}
	}

	/**
	 * Return the most common (most often observed) high temperature seen in the
	 * given month. If there are two or more temperatures that are both seen the
	 * most number of times, return the lowest high temperature. (20 points)
	 * 
	 * @param month Month
	 * @return highest most common high temperature seen in that month
	 */
	public int lowestMostCommonHighForMonth(int month) {

		//key = temp, value = frequency of the temp
		Map<Integer, Integer> tempHighTempStorage = new HashMap<>();
		List<Integer> temp = highTemperatures.get(month);

		//iterate through the temp list to use as key for temporary map
		for(int i : temp){
			if(!tempHighTempStorage.containsKey(i)){
				tempHighTempStorage.put(i, 1);
			}else{
				int tempStorage = tempHighTempStorage.get(i); //create a temp storage vairable to update map value

				tempHighTempStorage.replace(i, tempStorage + 1);
			}
		}

		//set highestFrequency to the lowest possible value to guarantee it to change on first if statement.
		int highestFrequency = Integer.MIN_VALUE;
		int highestTemp = Integer.MIN_VALUE;

		// Checks if highestFrequency is less than the frequency value in map
		// if so set highestTemp to i and highestFrequency to map value
		for(int i : temp){
			if(tempHighTempStorage.get(i) > highestFrequency){
				highestFrequency = tempHighTempStorage.get(i);
				highestTemp = i;

				// checks if the temps frequency are equal, if so it takes the lowest temp
			} else if(tempHighTempStorage.get(i) == highestFrequency){
				if(i < highestTemp){
					highestTemp = i;
				}
			}
		}

		return highestTemp;
	}
	/**
	 * For the given low temperature, find the highest high temperature seen with
	 * that low. (20 points)
	 *
	 * @param degrees Low temperature
	 * @return Highest high ever seen for that low temperature
	 */
	public int highestHighForLow(int degrees) {
		try {
			List<Integer> temp = highTemperatures.get(degrees);

			int biggerTemp = 0;

			for (int i : temp) {
				if (i > biggerTemp) {
					biggerTemp = i;
				}
			}
			return biggerTemp;
		} catch (Exception e) {

			return 0;

		}
	}


}
