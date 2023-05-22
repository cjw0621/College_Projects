package ClassNotes;

import java.util.Arrays;

public class SimpleWarehouse {
    private int size; // max number of items the warehouse can store
    private int[] warehouse; // codes of each item stored in the warehouse

    public SimpleWarehouse(int size){
        this.size = size;
        this.warehouse = new int[size]; //make all the elements are zero. Zero represents an empty space we can fill in
    }

    public boolean shipItem(int itemCode){
        //loop through the warehouse looking for the item code

        for(int i = 0; i < size; i++) {
            //does index i contain the itemCode we are shipping?
            if(warehouse[i] == itemCode){
                warehouse[i]= 0;
                return true;
            }
        }
        //if we never found itemCode, a box could not be shipped
        return false;
    }

    /*
     * receive(int itemCode, int itemCount)
     * itemCode: What type of item the warehouse is receiving
     * itemCount: how many of them
     *
     * receive(101, 4) => receiving 4 boxes of item type 101
     *
     * return how many boxes were left over due to the capacity of the warehouse
     * if receive returned 0 that means the warehouse was able to store all the boxes.
     */

    public int receive(int itemCode, int itemCount){
        // loop through the warehouse searching for empty spaces (0)
        int received = 0;
        for(int i = 0; i < size; i++){
            //have we already received the full shipment?
            //does this index contain an empty space
            if(itemCount == received){
                break;
            }
            if(warehouse[i]== 0){
                warehouse[i] = itemCode;
              received++;

            }
        }

        return itemCount - received;
    }
    /*
     *stock(int itemCode) returns how many items of a particular code exists in the warehouse (0 if none)
     *
     */
    public int stock(int itemCode){
        //how many items with code itemCode have we found?
        int stock = 0;
        //loop through the array searching for the itemcode
        for(int i = 0; i < size; i++){
            //does index i contain itemCode
            if(warehouse[i]==itemCode){
                stock++;
            }
        }
        return stock;
    }

    /*
     *contains returns true if the warehouse has at least one box of a certain itemCode. Returns false if that code does
     * not exist.
     */

    public boolean contains(int itemCode){
        return stock(itemCode) != 0;
    }


    @Override
    public String toString() {
        return "ClassNotes.SimpleWarehouse{" +
                "size=" + size +
                ", warehouse=" + Arrays.toString(warehouse) +
                '}';
    }

    public static void main(String[] args) {
        SimpleWarehouse sw = new SimpleWarehouse(10);
        System.out.println(sw);
        sw.receive(101, 3);
        System.out.println(sw.receive(99, 5));
        System.out.println(sw.receive(102,6));
        System.out.println(sw);

        System.out.println(sw.stock(101));
        System.out.println(sw.stock(99));
        System.out.println(sw.stock(102));
        System.out.println(sw.stock(103));

        System.out.println(sw.contains(101));
        System.out.println(sw.contains(105));


    }

}
