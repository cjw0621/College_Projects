// Chase Whitney
public class BakingCalculator {

    public static void main(String[] args) {
            int[] cookieCounts = { 24, 39, 80, 96, 97, 9000 };
            int[] loafCounts = { 1, 1, 8, 10, 10, 100 };
            int[][] ingredientCounts = { { 1, 1, 1, 1, 1, 1, 1, 3, 1 }, { 1, 1, 1,
                    1, 1, 1, 1, 3, 2 },
                    { 1, 1, 1, 2, 1, 5, 3, 24, 3 }, { 2, 1, 1, 2, 2, 6, 3, 30,
                    3 }, { 2, 1, 1, 3, 2, 6, 3, 30, 3 }, { 35, 2, 3, 48, 40, 179, 111, 300, 235 } };
            double[] finalPrices = { 20.88, 24.17, 55.11, 63.00, 66.99, 2070.97};
            int[] scores = { 0, 60, 70, 80, 90, 95 };
            int i = 0;
            int j = 0;
            for (; i < cookieCounts.length; i++) {
                j = 0;
                int count;
                System.out.println("Testing cookieCount = "+cookieCounts[i]+" and loafCount = "+loafCounts[i]+".");
                        count = BakingCalculator.bagsOfFlour(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][0] != count) {
                    System.out.println("Incorrect bagsOfFlour calculation! Should be "+ingredientCounts[i][0]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.containersOfSalt(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][1] != count) {
                    System.out.println("Incorrect containersOfSalt calculation! Should be "+ingredientCounts[i][1]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.boxesOfBakingSoda(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][2] != count) {
                    System.out.println("Incorrect boxesOfBakingSoda calculation! Should be "+ingredientCounts[i][2]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.bottlesOfVanilla(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][3] != count) {
                    System.out.println("Incorrect bottlesOfVanilla calculation! Should be "+ingredientCounts[i][3]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.cartonsOfEggs(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][4] != count) {
                    System.out.println("Incorrect cartonsOfEggs calculation! Should be "+ingredientCounts[i][4]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.bagsOfSugar(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][5] != count) {
                    System.out.println("Incorrect bagsOfSugar calculation! Should be "+ingredientCounts[i][5]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.packagesOfButter(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][6] != count) {
                    System.out.println("Incorrect packagesOfButter calculation! Should be "+ingredientCounts[i][6]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.bananas(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][7] != count) {
                    System.out.println("Incorrect bananas calculation! Should be "+ingredientCounts[i][7]+", got "+count);
                    break;
                }
                j++;
                count = BakingCalculator.bagsOfChocolateChips(cookieCounts[i], loafCounts[i]);
                if (ingredientCounts[i][8] != count) {
                    System.out.println("Incorrect bagsOfChocolateChips calculation! Should be "+ingredientCounts[i][8]+", got "+count);
                    break;
                }
                j++;
                if (Math.abs(finalPrices[i] -
                        BakingCalculator.totalCost(cookieCounts[i], loafCounts[i])) > 0.001) {
                    System.out.println("Incorrect totalCost calculation! Should be "+finalPrices[i]+", got "+BakingCalculator.totalCost(cookieCounts[i], loafCounts[i]));
                    break;
                }
            }
            if (i < cookieCounts.length) {
                int score = scores[i];
                if (score == 0) score += 2*j;
                else score += j/2;
                System.out.println("You only earned "+score+" points out of 100.");
                System.out.println("Please fix the above error in your code and run this tester again - there may be other problems!");
            } else {
                System.out.println("You earned 100 / 100 points!");
            }
            System.out.println(
                    "NOTE: This program doesn't check for lateness or academic dishonesty which could affect your score. Please make sure you know the syllabus policy.");

    }

    public static int bagsOfFlour(int cookieCount, int loafCount) {

        double cupsFlourCookies = 2.25;
        double cupsFlourLoaf = 1.5;
        double batchOfCookies = 48.0;
        double cupsOfFlourPerLbs = 3+1.0/3.0;
        double lbsOfFlourPerBag = 5.0;
        double bagsOfFlourNeeded = (((cupsFlourCookies / batchOfCookies * cookieCount) +
                (cupsFlourLoaf * loafCount)) / cupsOfFlourPerLbs) / lbsOfFlourPerBag;

        return  (int) Math.ceil(bagsOfFlourNeeded);
    }
    public static int containersOfSalt(int cookieCount, int loafCount) {

        double numOfTspCookies = 1.0;
        double numOfTspLoaf = 1.0/8.0;
        double batchOfCookies = 48.0;

        double ozOfSaltPerContainer = 26.0;
        double numberOfTspPerOz = 6.0;

        double numOfContainersNeeded = (((numOfTspCookies / batchOfCookies * cookieCount) +
                (numOfTspLoaf * loafCount)) / numberOfTspPerOz) / ozOfSaltPerContainer;


        return (int) Math.ceil(numOfContainersNeeded);
    }

    public static int boxesOfBakingSoda(int cookieCount, int loafCount) {

        double numOfTspCookies = 1.0;
        double numTspLoaf = 1.0;
        double batchOfCookies = 48.0;

        double numOfTspPerOz = 6.0;
        double ozOfBakingSodaPerBox = 16.0;

        double numOfBoxesNeeded = (((numOfTspCookies / batchOfCookies * cookieCount) +
                (numTspLoaf * loafCount)) / numOfTspPerOz) / ozOfBakingSodaPerBox;

        return (int) Math.ceil(numOfBoxesNeeded);
    }

    public static int bottlesOfVanilla(int cookieCount, int loafCount) {

        double tspVanillaCookies = 1.0;
        double tspVanillaLoaf = 1.0;
        double batchOfCookies = 48.0;

        double tspPerOz = 6.0;
        double ozPerBottle = 1.0;

        double numOfBottlesNeeded = (((tspVanillaCookies / batchOfCookies * cookieCount) +
                (tspVanillaLoaf * loafCount)) / tspPerOz) / ozPerBottle;

        return (int) Math.ceil(numOfBottlesNeeded);
    }
    public static int cartonsOfEggs(int cookieCount, int loafCount) {

        double numEggsCookies = 2.0;
        double numEggsLoaf = 1.0;
        double batchOfCookies = 48.0;

        double numEggsPerContainer = 12;

        double numOfContainers = ((numEggsCookies / batchOfCookies * cookieCount) +
                (numEggsLoaf * loafCount)) / numEggsPerContainer;

        return (int) Math.ceil(numOfContainers);
    }

    public static int bagsOfSugar(int cookieCount, int loafCount) {

        double numCupsCookies = 1 + 1.0/2.0;
        double numCupsLoaf = 3.0/4.0;
        double batchOfCookies = 48.0;

        double cupsPerBag = 2.0;

        double numOfBagsNeeded = (((numCupsCookies / batchOfCookies * cookieCount) +
                (numCupsLoaf * loafCount)) / cupsPerBag);

        return (int) Math.ceil(numOfBagsNeeded);
    }

    public static int packagesOfButter(int cookieCount, int loafCount) {

        double numCupsCookies = 1.0;
        double numCupsLoaf = 1.0/3.0;
        double batchOfCookies = 48.0;

        double cupsPerStick = 1.0/2.0;
        double numSticksPerPackage = 4.0;

        double numOfPackagesNeeded = (((numCupsCookies / batchOfCookies * cookieCount) +
                (numCupsLoaf * loafCount)) / cupsPerStick ) / numSticksPerPackage;


        return (int) Math.ceil(numOfPackagesNeeded);
    }
    public static int bananas(int cookieCount, int loafCount) {

        double numOfBananasCookies = 0;
        double numOfBananasLoaf = 3.0;
        double batchOfCookies = 48.0;

        return (int) ((numOfBananasLoaf * loafCount) +
                (numOfBananasCookies / batchOfCookies * cookieCount));
    }
    public static int bagsOfChocolateChips(int cookieCount, int loafCount) {

        double numCupsCookies = (2.5);
        double numCupsLoaf = 0.0;
        double batchOfCookies = 48.0;

        double cupsPerOz = 1.0/6.0;
        double ozPerBag = 12.0;

        double numOfBagsNeeded = (((numCupsCookies / batchOfCookies * cookieCount) +
                (numCupsLoaf * loafCount))/ cupsPerOz / ozPerBag);


        return (int) Math.ceil(numOfBagsNeeded);
    }
    public static double totalCost(int cookieCount, int loafCount) {
        double bagsOfFlourCost = bagsOfFlour(cookieCount,loafCount) * 1.79;
        double containersOfSaltCost = containersOfSalt(cookieCount, loafCount) * 1.09;
        double boxesOfBakingSodaCost = boxesOfBakingSoda(cookieCount, loafCount) * 1.09;
        double bottlesOfVanillaCost = bottlesOfVanilla(cookieCount, loafCount) * 3.99;
        double cartonsOfEggsCost = cartonsOfEggs(cookieCount, loafCount) * 2.19;
        double bagsOfSugarCost = bagsOfSugar(cookieCount, loafCount) * 1.99;
        double packagesOfButterCost = packagesOfButter(cookieCount, loafCount) * 4.49;
        double bananasCost = bananas(cookieCount, loafCount) * 0.32;
        double bagsOfChocolateChipsCost = bagsOfChocolateChips(cookieCount, loafCount) * 3.29;

        return bagsOfFlourCost + containersOfSaltCost + boxesOfBakingSodaCost + bottlesOfVanillaCost + cartonsOfEggsCost
                + bagsOfSugarCost + packagesOfButterCost + bananasCost + bagsOfChocolateChipsCost;
    }
}
