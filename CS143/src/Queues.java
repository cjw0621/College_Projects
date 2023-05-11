import java.util.NoSuchElementException;

public class Queues {

    private class Node{
        private String data;
        private Node next;

    }
    private Node front;
    // a reference to the last node in the queue
    private Node end;
    public Queues(){
        front = end = null;

    }

    /*
     * is empty returns true when the queue is empty
     */
    public boolean isEmpty(){
        return front == null && end == null;
    }


    /*
     *add(String data) inserts a new String at the end of the queue
     *
     * with the extra end property: O(1) constant time
     */
    public void add(String data){
        //1. create a new node with the parameter data in it
        Node newEnd = new Node();
        newEnd.data = data;

        // special case: is the queue empty?
        // if so, the new node should be the front and end
        if(isEmpty()){
            front = end = newEnd;
            return;
        }
        // 2. put the new node after the current end
        end.next = newEnd;
        // 3. update the end to the new node
        end = newEnd;

//        2. starting at the front walk to the end of the queue
//        Node walker = front;
//
//
//          while (walker.next != null){
//              walker = walker.next;
//          }
//
//        // 3. now wlaker refers to the last node in the queue
//        // put the newEnd after that
//        walker.next = newEnd;

    }

    /*
     *remove() removes and returns the data from the front of the queue;
     * O(1) constant time.
     */
    public String remove(){
        // 0. check if the queue is empty. If so, nothing to remove
        // and throw an exception
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        // 1.save the front's data
        String oldFront = front.data;
        //2. move the front forwards
        front = front.next;
       // 2a. if this made the front null, end should also be null since we are removing the last element
        if(front == null){
            end = null;
        }
        // 3. return the old front
        return oldFront;

    }

    /*
     *peek() returns but does not remove the front of the queue
     *
     * O(1) constant time.
     */
    public String peek(){
        //if the queue is empty there is nothing to peek at
        if(isEmpty()){
            throw new NoSuchElementException();
        }else{
            return front.data;
        }
    }

    public static void main(String[] args) {
        Queues queues = new Queues();
        queues.add("allan");
        queues.add("Chase");
        queues.add("Tiger");
        queues.add("David");
        queues.add("Steve");

        queues.remove();
        queues.remove();
        queues.remove();
        queues.remove();
        queues.remove();
    }

}
