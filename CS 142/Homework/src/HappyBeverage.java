import java.math.*;

public class HappyBeverage {

    public static void main(String[] args) {

        System.out.println(totalCost(7));

    }

    public static int pouchesOfHops(double gallons){

        double gallonsPerBatch = 5;
        double ozHopsPerBatch = 15;
        double ozHopsPerPouch = 2;

        double pouches = gallons/ gallonsPerBatch * ozHopsPerBatch / ozHopsPerPouch;
        return (int) Math.ceil(pouches);
    }

    public static double poundsOfMalt(double gallons){
        double gallonsPerBatch = 5;
        double lbsMaltPerBatch = 12.5;

        return gallons / gallonsPerBatch * lbsMaltPerBatch;
    }

    public static int packetsOfYeast(double gallons){

        double gallonsPerBatch = 5;
        int packetsYeastPerBatch = 1;

        double packets = gallons / gallonsPerBatch * packetsYeastPerBatch;

        return (int) Math.ceil(packets);
    }

    public static double totalCost(double gallons){

        double pouchesHopsTotalPrice = pouchesOfHops(gallons) * 3.99;
        double lbsMaltTotalPrice = poundsOfMalt(gallons) * 1.50;
        double packetsYeastTotalPrice = packetsOfYeast(gallons) * 2.59;

        return pouchesHopsTotalPrice + lbsMaltTotalPrice + packetsYeastTotalPrice;
    }
}
