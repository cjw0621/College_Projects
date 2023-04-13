package WearhouseAssignment;

import java.util.Arrays;

public class Warehouse{

    private int size;
    private int limitPerItem;
    private int[] warehouse;
    public Warehouse(int size, int limitPerItem){

        this.size = size;
        this.limitPerItem = limitPerItem;
        warehouse = new int[size];

    }
    public int getSize() {
        return size;
    }

    public int getLimitPerItem() {
        return limitPerItem;
    }

    /*
     * receive(int itemCode, int itemCount); Method checks if item code is already in the warehouse
     * and increments the counter, if the counter is greater than the limit, it adds the item to the warehouse and
     * returns the number of pallets that was not received
     */
    public int receive(int itemCode, int itemCount){

        int received = 0;

        for(int i = 0; i < size; i++) {

            if (itemCount == received) {
                break;
            }
            
            if (warehouse[i] == 0) {
                warehouse[i] = itemCode;
                received++;
            }
        }

        System.out.println(Arrays.toString(warehouse));


        return itemCount - received;
    }

    public int ship(int itemCode, int itemCount){

        return -1;
    }

    public static void main(String[] args) {
        Warehouse w = new Warehouse(47,35);
        w.receive(41,76);
        System.out.println(w.receive(64,87));
    }

}
