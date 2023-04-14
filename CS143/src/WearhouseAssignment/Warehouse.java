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

        //checks if an itemCode is present in the warehouse inventory, adds 1 to the inStockItem counter if true.
        for(int i = 0; i < getSize(); i++){
            if(warehouse[i]== itemCode){
                inStockItem++;
            }
        }

        for(int i = 0; i < getSize(); i++){
            //have we already received the full shipment?
            //does this index contain an empty space

            //stops adding pallets to the warehouse inventory if either the limit was reached or if there is no more
            //space available.
            if(received + inStockItem == getLimitPerItem()){
                break;
            }
            if(itemCount == received){
                break;
            }

            if(warehouse[i] == 0){
                warehouse[i] = itemCode;
                received++;
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


        //checks if an itemCode is present in the warehouse inventory, adds 1 to the numOfItem counter if true.
        for(int i = 0; i < getSize(); i++){
            if(warehouse[i] == itemCode){
                numOfItem++;
            }
        }

        //boolean checks if there are more than or equal to the number of items requested to ship to prevent negative
        // values
        if(numOfItem >= itemCount){
            for(int i = 0; i < getSize(); i++){
                if(palletsShipped == itemCount){
                    break;
                }

                //adds one to the counter if a pallet with itemCode was found and shipped
                //replaces the itemCode with a 0 to indicate that the space is empty.
                if(warehouse[i]== itemCode){
                    warehouse[i] = 0;
                    palletsShipped++;
                }
            }
            //return value indicates that the number of requested pallets has been shipped
            return itemCount;
        }

        //replaces the itemCodes of the items that were shipped to 0 to indicate an empty space.
       for(int i = 0; i < getSize(); i++){
           if(warehouse[i] == itemCode){
               warehouse[i] = 0;
           }
       }

       //return values indicates all the pallets in the warehouse inventory with the item code has been shipped.
        return numOfItem;
    }
}
