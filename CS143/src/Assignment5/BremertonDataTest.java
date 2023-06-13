package Assignment5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * UNOFFICIAL Test program for CS 143 Assignment 6 (Version of 11/15/2020 2:45
 * PM)
 * 
 * You may only use this program as a student of Martin Hock or David Anderson, CS 143 Fall 2021.
 * You may not give this program to any other people or companies (aside from
 * private storage).
 * 
 * Directions: Run this program from the same project source folder as your
 * WeatherData.java file.
 */
public class BremertonDataTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		int highPoints = 0, lowPoints = 0, snowfallPoints = 0, precipPoints = 0, lmchPoints = 0, hihiloPoints = 0;
		System.out.println(
				"**** THIS TEST IS MEANT TO HELP YOU DEBUG YOUR PROGRAM AND IS NOT AN OFFICIAL ASSIGNMENT SCORE ***");
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

			byte[] hash = { -1, 105, 6, 94, 68, -78, -118, -96, -54, -61, -42, 107, -108, 68, 53, 90, 112, -38, -47,
					-110, -123, 50, -37, -71, 87, -23, -94, 37, -47, -49, 4, -10 };
			DigestInputStream stream = new DigestInputStream(Files.newInputStream(Paths.get("brem100.csv")), sha256);
			Scanner s = new Scanner(stream);
			long startC = System.currentTimeMillis();
			WeatherData wd = new WeatherData(s);
			long endC = System.currentTimeMillis();
			if (endC - startC > 60001) {
				System.out.println("Your constructor took too long (" + (endC - startC) / 1000.0 + " seconds)!");
				return;
			}
			if (s.hasNext()) {
				System.out.println("*** WARNING *** Your WeatherData constructor is not reading all of the lines!");
				while (s.hasNextLine()) {
					s.nextLine(); // hint, this reads all the lines!
				}
			}

			stream.close();
			byte[] digest = sha256.digest();
			if (!Arrays.equals(digest, hash)) {
				System.out.println("*** WARNING *** Your brem100.csv file does not have the expected contents!");
			}

			boolean[] lows = { false, true, false, false, true, false, true, false, true, false, false, true, false,
					true, false, true, false, true, true, false, true, false, true, false, false, true, false, true,
					false, true, false, true, true, false, true, false, true, false, true, true, false, true, false,
					true, false, true, true, false, true, false, true, false, true, true, false, true, false, true,
					false, true, true, false, true, false, true, false, true, true, false, true, false, true, false,
					true, true, false, true, false, true, false, true, true, false, true, false, true, false, true,
					true, false, true, false, true, false, true, true, false, true, false, true, false, true, false,
					false, true, false, true, false, true, false, false, true, false, true, false, true, false, true,
					true, false, true, false, true, false, false, true, false, true };
			{
				System.out.println("Testing highTemp() and lowTemp()...");
				boolean[] his = { false, true, true, true, true, false, true, false, true, true, true, true, false,
						true, false, true, false, true, true, false, true, false, true, false, true, true, false, true,
						false, true, false, true, true, false, true, false, true, false, true, true, false, true, false,
						true, false, true, true, false, true, false, true, false, true, true, false, true, false, true,
						false, true, true, true, true, false, true, false, true, true, false, true, false, true, false,
						true, true, true, true, false, true, false, true, true, false, true, false, true, false, true,
						true, true, true, false, true, false, true, true, true, true, false, true, false, true, true,
						true, true, false, true, false, true, true, true, true, false, true, false, true, true, true,
						true, false, true, false, true, true, true, true, false, true };

				int i = 0;
				int j = 0;
				int hiErrors = 0, lowErrors = 0;
				long totalHiTime = 0, totalLowTime = 0;
				do {
					int hi = i, lo = i - 16;
					try {
						long start = System.nanoTime();
						boolean hit = wd.highTemp(hi);
						long end = System.nanoTime();
						totalHiTime += end - start;
						if (end - start > 1_000_000_001) {
							System.out.println("highTemp(" + hi
									+ ") executed too slowly (over 1 second)! No credit will be awarded.");
							hiErrors = 10;
						}
						if (hit != his[j]) {
							if (hiErrors < 10) {
								System.out.println(
										"highTemp(" + hi + ") returned " + hit + " but should have returned " + his[j]);
							}
							hiErrors++;
							if (hiErrors == 10) {
								System.out.println("Further highTemp errors suppressed.");
							}
						}
					} catch (Throwable e) {
						if (hiErrors < 10) {
							System.out.println("highTemp(" + hi + ") should have returned " + his[j]
									+ " but instead caused the following error:");
							e.printStackTrace(System.out);
						}
						hiErrors++;
						if (hiErrors == 10) {
							System.out.println("Further highTemp errors suppressed.");
						}
					}
					try {
						long start = System.nanoTime();
						boolean lowt = wd.lowTemp(lo);
						long end = System.nanoTime();
						totalLowTime += end - start;
						if (end - start > 1_000_000_001) {
							System.out.println("lowTemp(" + lo
									+ ") executed too slowly (over 1 second)! No credit will be awarded.");
							lowErrors = 10;
						}
						lows[j] = lowt; // FIXME REMOVE
						if (lowt != lows[j]) {
							if (lowErrors < 10) {
								System.out.println("lowTemp(" + lo + ") returned " + lowt + " but should have returned "
										+ lows[j]);
							}
							lowErrors++;
							if (lowErrors == 10) {
								System.out.println("Further lowTemp errors suppressed.");
							}
						}
					} catch (Throwable e) {
						if (lowErrors < 10) {
							System.out.println("lowTemp(" + lo + ") should have returned " + lows[j]
									+ " but instead caused the following error: ");
							e.printStackTrace(System.out);
						}
						lowErrors++;
						if (lowErrors == 10) {
							System.out.println("Further lowTemp errors suppressed.");
						}
					}
					i = (i + 73) & 127;
					j++;
				} while (i != 0);
				if (i == 0 && j > 0) {
					highPoints = Math.max(10 - hiErrors, 0);
					if (totalHiTime > 1_000_001) {
						System.out.println(
								"10% penalty for slow highTemp() method (over 1 microsecond total time to perform 128 tests).");
						highPoints--;
					}
					lowPoints = Math.max(10 - lowErrors, 0);
					if (totalLowTime > 1_000_001) {
						System.out.println(
								"10% penalty for slow lowTemp() method (over 1 microsecond total time to perform 128 tests)");
						lowPoints--;
					}
				} else {
					System.out.println("No highTemp or lowTemp points earned due to above errors.");
				}
			}
			{
				System.out.println("Testing totalSnowfallForYear()...");
				int i = 0;
				int j = 0;
				int[] snowfalls = { 0, 20, 0, 0, 101, 72, 164, 0, 58, 0, 131, 150, 105, 0, 0, 390, 0, 78, 0, 0, 0, 0,
						51, 0, 0, 76, 40, 0, 0, 360, 0, 0, 93, 0, 385, 0, 170, 0, 3, 35, 0, 285, 0, 145, 0, 201, 90, 0,
						40, 0, 0, 0, 20, 170, 0, 70, 0, 118, 0, 11, 0, 100, 18, 211, 3, 0, 28, 108, 10, 5, 17, 0, 0, 0,
						55, 0, 0, 20, 5, 0, 170, 0, 10, 159, 25, 38, 0, 0, 10, 0, 10, 0, 15, 0, 0, 203, 177, 0, 20, 0,
						0, 30, 87, 0, 20, 10, 0, 0, 0, 119, 8, 14, 5, 66, 0, 20, 0, 15, 18, 2, 74, 0, 65, 0, 75, 304,
						20, 250 };
				int snowfallErrors = 0;
				long totalSnowTime = 0;
				do {
					int year = i + 1900;
					try {
						long start = System.nanoTime();
						double snowfall = wd.totalSnowfallForYear(year);
						long end = System.nanoTime();
						if (end - start > 1_000_000_001) {
							System.out.println("totalSnowfallForYear(" + year
									+ ") executed too slowly (over 1 second)! No credit will be awarded.");
							snowfallErrors = 10;
						}
						totalSnowTime += end - start;
						if ((int) Math.round(snowfall * 10) != snowfalls[j]) { // prevent calculation
																				// rounding errors
							if (snowfallErrors < 10) {
								System.out.println("totalSnowfallForYear(" + year + ") returned " + snowfall
										+ " but it should have returned " + snowfalls[j] / 10.0);
							}
							if (snowfallErrors == 0) {
								System.out.println(
										"It's OK if the calculation is off by 0.01 or less but yours is off by too much.");
							}
							snowfallErrors++;
							if (snowfallErrors == 10) {
								System.out.println("Further totalSnowfallForYear errors suppressed.");
							}
						}
					} catch (Throwable e) {
						if (snowfallErrors < 10) {
							System.out.println("snowfall(" + year
									+ ") should have returned, but instead caused the following error:");
							e.printStackTrace(System.out);
						}
						snowfallErrors++;
						if (snowfallErrors == 10) {
							System.out.println("Further totalSnowfallForYear errors suppressed.");
						}
					}
					i = (i + 73) & 127;
					j++;
				} while (i != 0);
				if (i == 0 && j > 0) {
					snowfallPoints = Math.max(20 - 2 * snowfallErrors, 0);
					if (totalSnowTime > 1_000_001) {
						System.out.println(
								"10% penalty for slow totalSnowfallForYear() method (over 1 microsecond total time to perform 128 tests).");
						snowfallPoints -= 2;
					}
				}
			}
			{
				int[] precips = { 0, 69895, 53847, 47278, 28559, 17626, 14049, 6771, 8920, 17972, 41661, 71440, 80365,
						0 };
				System.out.println("Testing averagePrecipitationForMonth()...");
				int precipErrors = 0;
				int i = 0;
				long totalPrecipTime = 0;
				for (i = 0; i <= 13; i++) {
					try {
						long start = System.nanoTime();
						double precip = wd.averagePrecipitationForMonth(i);
						long end = System.nanoTime();
						if (end - start > 1_000_000_001) {
							System.out.println("averagePrecipitationForMonth(" + i
									+ ") executed too slowly (over 1 second)! No credit will be awarded.");
							precipErrors = 10;
						}
						totalPrecipTime += end - start;
						if ((int) Math.round(precip * 10000) != precips[i]) {
							if (precipErrors < 10) {
								System.out.println("averagePrecipitationForMonth(" + i + ") returned " + precip
										+ " but it should have returned " + precips[i] / 10000.0);
							}
							precipErrors++;
							if (precipErrors == 10) {
								System.out.println("Further averagePrecipitationForMonth errors suppressed.");
							}
						}
					} catch (Throwable e) {
						if (precipErrors < 10) {
							System.out.println("averagePrecipitationForMonth(" + i
									+ ") should have returned, but instead caused the following error: ");
							e.printStackTrace(System.out);
						}
						precipErrors++;
						if (precipErrors == 10) {
							System.out.println("Further averagePrecipitationForMonth errors suppressed.");
						}
					}
				}
				if (i == 14) {
					precipPoints = 20 - 2 * precipErrors;
					if (totalPrecipTime > 100_001) {
						System.out.println(
								"10% penalty for slow averagePrecipitationForMonth() method (over .1 microsecond total time to perform 14 tests).");
						precipPoints -= 2;
					}
				}
			}
			{
				System.out.println("Testing lowestMostCommonHighForMonth()...");
				int[] lmch = { 48, 50, 52, 55, 62, 65, 74, 71, 66, 58, 52, 46 };
				int lmchErrors = 0;
				int i = 0;
				long lmchTime = 0;
				for (i = 1; i <= 12; i++) {
					try {
						long start = System.nanoTime();
						int hi = wd.lowestMostCommonHighForMonth(i);
						long end = System.nanoTime();
						if (end - start > 1_000_000_001) {
							System.out.println("lowestMostCommonHighForMonth(" + i
									+ ") executed too slowly (over 1 second)! No credit will be awarded.");
							lmchErrors = 10;
						}
						lmchTime += end - start;
						if (hi != lmch[i - 1]) {
							if (lmchErrors < 10) {
								System.out.println("lowestMostCommonHighForMonth(" + i + ") returned " + hi
										+ " but it should have returned " + lmch[i - 1]);
							}
							lmchErrors++;
							if (lmchErrors == 10) {
								System.out.println("Further lowestMostCommonHighForMonth errors suppressed.");
							}
						}
					} catch (Throwable e) {
						if (lmchErrors < 10) {
							System.out.println("lowestMostCommonHighForMonth(" + i
									+ ") should have returned, but instead caused the following error: ");
							e.printStackTrace(System.out);
						}
						lmchErrors++;
						if (lmchErrors == 10) {
							System.out.println("Further lowestMostCommonHighForMonth errors suppressed.");
						}
					}
				}
				if (i == 13) {
					lmchPoints = 20 - 2 * lmchErrors;
					if (lmchTime > 100_001) {
						System.out.println(
								"10% penalty for slow lowestMostCommonHighForMonth() method (over .1 microsecond total time to perform 14 tests).");
						lmchPoints -= 2;
					}
				}
			}
			{
				int[] hihilo = { 0, 96, 0, 0, 49, 0, 74, 0, 94, 0, 0, 50, 0, 77, 0, 95, 0, 77, 48, 0, 72, 0, 95, 0, 0,
						39, 0, 76, 0, 89, 0, 95, 43, 0, 66, 0, 92, 0, 97, 65, 0, 70, 0, 96, 0, 75, 33, 0, 69, 0, 87, 0,
						85, 29, 0, 64, 0, 85, 0, 84, 33, 0, 67, 0, 87, 0, 101, 27, 0, 62, 0, 85, 0, 99, 24, 0, 73, 0,
						82, 0, 96, 17, 0, 65, 0, 88, 0, 97, 32, 0, 58, 0, 83, 0, 97, 21, 0, 53, 0, 83, 0, 97, 0, 0, 58,
						0, 80, 0, 99, 0, 0, 58, 0, 89, 0, 97, 0, 81, 45, 0, 75, 0, 97, 0, 0, 46, 0, 83 };
				System.out.println("Testing highestHighForLow()...");
				int i = 0;
				int j = 0;
				int lowErrors = 0;
				long totalLowTime = 0;
				do {
					if (lows[j]) {
						int lo = i - 16;
						try {
							long start = System.nanoTime();
							int hit = wd.highestHighForLow(lo);
							long end = System.nanoTime();
							totalLowTime += end - start;
							if (end - start > 1_000_000_001) {
								System.out.println("highestHighForLow(" + lo
										+ ") executed too slowly (over 1 second)! No credit will be awarded.");
								lowErrors = 10;
							}
							if (hit != hihilo[j]) {
								if (lowErrors < 10) {
									System.out.println("highestHighForLow(" + lo + ") returned " + hit
											+ " but it should have returned " + hihilo[j]);
								}
								lowErrors++;
								if (lowErrors == 10) {
									System.out.println("Further highestHighForLow errors suppressed.");
								}
							}
						} catch (Throwable e) {
							if (lowErrors < 10) {
								System.out.println("highestHighForLow(" + lo + ") should have returned " + lows[j]
										+ " but instead caused the following error: ");
								e.printStackTrace(System.out);
							}
							lowErrors++;
							if (lowErrors == 10) {
								System.out.println("Further highestHighForLow errors suppressed.");
							}
						}
					}
					i = (i + 73) & 127;
					j++;
				} while (i != 0);
				if (i == 0 && j > 0) {
					hihiloPoints = 20 - 2 * lowErrors;
					if (totalLowTime > 1_000_001) {
						System.out.println(
								"10% penalty for slow highestHighForLow() method (over 1 microsecond total time to perform 128 tests)");
						lowPoints--;
					}
				} else {
					System.out.println("No highestHighForLow points earned due to above errors.");
				}
			}
		} finally {
			highPoints = Math.max(highPoints, 0);
			lowPoints = Math.max(lowPoints, 0);
			snowfallPoints = Math.max(snowfallPoints, 0);
			precipPoints = Math.max(precipPoints, 0);
			lmchPoints = Math.max(lmchPoints, 0);
			hihiloPoints = Math.max(hihiloPoints, 0);
			System.out.println(
					"**** THIS TEST IS MEANT TO HELP YOU DEBUG YOUR PROGRAM AND IS NOT AN OFFICIAL ASSIGNMENT SCORE ***");
			System.out.println("highTemp: " + highPoints + " / 10");
			System.out.println("lowTemp: " + lowPoints + " / 10");
			System.out.println("totalSnowfallForYear: " + snowfallPoints + " / 20");
			System.out.println("averagePrecipitationForMonth: " + precipPoints + " / 20");
			System.out.println("lowestMostCommonHighForMonth: " + lmchPoints + " / 20");
			System.out.println("highestHighForLow: " + hihiloPoints + " / 20");
			if ((highPoints + lowPoints + snowfallPoints + precipPoints + lmchPoints + hihiloPoints) == 100) {
				System.out.println("Speed bonus!");
				System.out.println("Total: 102% for this test (NOT YOUR OFFICIAL ASSIGNMENT SCORE)");
			} else {
				System.out.println(
						"Total: " + (highPoints + lowPoints + snowfallPoints + precipPoints + lmchPoints + hihiloPoints)
								+ "% (NOT YOUR OFFICIAL ASSIGNMENT SCORE)");
			}
		}

	}

}
