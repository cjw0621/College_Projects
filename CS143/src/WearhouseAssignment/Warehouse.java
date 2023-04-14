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
     * receive(int itemCode, int itemCount) adds the pallets to the warehouse if space is available and returns the
     * number of pallets that was not received due to insufficient space.
     */
    public int receive(int itemCode, int itemCount){
        int received = 0;
        int inStockItem = 0;

        for(int i = 0; i < warehouse.length; i++){
            if(warehouse[i]== itemCode){
                inStockItem++;
            }
        }


        for(int i = 0; i < getSize(); i++){
            //have we already received the full shipment?
            //does this index contain an empty space
            if(itemCount == received || (itemCount - inStockItem) == received){
                break;
            }
            if(warehouse[i] == 0){
                warehouse[i] = itemCode;
                received++;
                //stops adding pallets to the warehouse array if either the limit was reached or if there is no more
                //space available.
                if(received == getLimitPerItem()){
                    break;
                }
            }
        }
        return itemCount - received;

    }

    /*
     *ship(int itemCode, int itemCount) if itemCode is in the ware warehouse array, the itemCount designates how many
     * items of the itemCode will be shipped, and the method will return the number of pallets that was shipped based
     * on how many of those pallets are available to ship
     *
     * returns 0 if no pallets was shipped due to the item code not being in the warehouse.
     */

    public int ship(int itemCode, int itemCount){

        //Is the pallet itemCode in the warehouse?
        //When the pallet is shipped need the inventory to reflect the remaining number of pallets after shipping
        //numOfItem gives you how many pallets of the itemCode is in the inventory
        //itemShipped gives you how many pallets were shipped.

        int numOfItem = 0;
        int palletsShipped = 0;

        for(int i = 0; i < warehouse.length; i++){
            if(warehouse[i] == itemCode){
                numOfItem++;
            }
        }

        if(numOfItem >= itemCount){
            for(int i = 0; i < warehouse.length; i++){
                if(palletsShipped == itemCount){
                    break;
                }

                if(warehouse[i]== itemCode){
                    warehouse[i] = 0;
                    palletsShipped++;
                }
            }
            return itemCount;
        }

       for(int i = 0; i < warehouse.length; i++){
           if(warehouse[i] == itemCode){
               warehouse[i] = 0;
           }
       }
        return numOfItem;
    }
}
