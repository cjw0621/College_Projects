package WearhouseAssignment;
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

    public int receive(int itemCode, int itemCount){

        int count = 0;
        int leftOver = 0;

            for(int i = 0; i < size; i++){

                if(count == itemCount){
                    break;
                }
                if(warehouse[i] == itemCode){
                    count++;
                }
            }

            if(count > limitPerItem){
                leftOver = count - limitPerItem;
                return leftOver;
            }

        return leftOver;
    }

    public int ship(int itemCode, int itemCount){

        return -1;
    }

}
