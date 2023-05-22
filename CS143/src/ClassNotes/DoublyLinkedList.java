package ClassNotes;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>{
    /*
     * Inner Node class that represents one node
     * of a doubly linked list.
     *
     * Each Node has 3 things
     * 1. some data (a T)
     * 2. a reference to the Node before this Node in the list
     * 3. a reference to the Node after this Node in the list
     */
    private static class Node<T> {

        private Node<T> prev; // a link to the previous node in the list
        private T data;
        private Node<T> next; // a link to the next node in the list


        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }
    /*
     *Iterator class for iterating through the list in O(n) linear time
     *
     * methods:
     * 1. next() returns the T in a particular node and move the iterator forwards to the next node
     * 2. hasNext() returns true if the iterator has more nodes to iterate through
     * (returns false if the iterator is at the end)
     */
    private class Conductor implements Iterator<T>{

        // current represents the node that the iterator
        // is currently on in the iteration of the list
        private Node<T> current;

        // constructor initializes the Iterator to start at a particular node

        public Conductor(Node<T> start){
            current = start;
        }

        // Checking if the conductor is at the end of the train
        @Override
        public boolean hasNext(){
            return current != null;
        }
         @Override
        public T next(){
            //1. save current's data
             T data = current.data;
             //2. move current forward to the next node in the lsit
             current = current.next;
             //3. return the data that's in current's previous location
            return data;
         }
    }
    // eventually this method will return an Iterator
    // attached to this list that other classes can use
    // to iterate the list in O(n) time
    @Override
    public Iterator<T> iterator() {

        return new Conductor(head);
    }

    //=====Doubly linked list properties

    private Node<T> head; // the first element in the list
    private Node<T> tail; // the last element in the list
    private int size; // how many elements are in the list

    /*
     * Constructs an empty ClassNotes.DoublyLinkedList with no data
     */
    public DoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /*
     * isEmpty returns true if there is no data in the list
     *
     * O(1)
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /*
     * Size returns the number of elements in the list
     *
     * O(1)
     */
    public int size() {
        return size;
    }

    /*
     * add(T e) adds the T e to the end of the list
     * 		e goes after the current tail
     * 		e becomes the tail
     *
     * O(1) constant time
     */
    public void add(T e) {
        // special case: if the list is empty, the head
        // and the tail should become the new node
        if(isEmpty()) {
            Node<T> newNode = new Node<>(null, e, null);
            head = tail = newNode;
        }
        else {
            // 1. create a new node with
            // 		the current tail before it
            //		e inside of it
            //      nothing (null) after it
            Node<T> newNode = new Node<>(tail, e, null);
            // 2. the new node should come after the current tail
            tail.next = newNode;
            // 3. the new node should become the tail
            tail = newNode;
        }
        // increase size
        size++;
    }

    /*
     * remove(int i) removes the data at position i from the doubly linked list
     * return the data that was removed
     *
     * O(n) liner time
     */
    public T remove(int i){
        // is "i" out of bounds?
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }else {
            Node<T> walker = head;
            // move walker forwards "i" times
            for (int j = 0; j < i; j++) {
                walker = walker.next;
            }
            if(head == tail){
                head = tail = null;
            }
            // case 2: removing the head of the list
            else if(walker == head){
                // 1. move the head forwards
                head = head.next;
                //2. make nothing come before the new head
                head.prev = null;
            }else if(walker == tail){
                //1. set tail.prev to head
                tail = tail.prev;
                //2. set tail.next to null to signify the end of the list
                tail.next = null;
            }else{
                //1. The node after walker should be after the node before walker
                (walker.prev).next = walker.next;
                //2. The node before walker should be before the node after walker
                (walker.next).prev = walker.prev;
            }
            size--;
            return walker.data;
        }
    }

    /*
     * get(int i) returns the ith T in the list
     * 		throws an IndexOutOfBoundsException if i is undefined
     *
     * O(n) linear time
     */
    public T get(int i) {
        // is "i" out of bounds?
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        // start at the head of the list
        Node<T> walker = head;
        // move walker forwards "i" times
        for(int j = 0; j < i; j++) {
            walker = walker.next;
        }
        // walker is pointing to the ith Node, so return its data
        return walker.data;
    }

    public static void main(String[] args) {
//		ClassNotes.DoublyLinkedList list = new ClassNotes.DoublyLinkedList();
//		list.add("Cheese"); // 0
//		list.add("Crackers"); // 1
//		list.add("Ham"); // 2
//		list.add("M&Ms"); // 3
//		list.add("Bologna"); // 4
//
//		System.out.println(list.get(2));

        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        for(int i = 0; i < 1_000_000; i++) {
            list.add("" + i);
        }

        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
        for(int i = 0; i <= 100; i++){
            list2.add(i + (i * 2));
        }

        DoublyLinkedList<Double> list3 = new DoublyLinkedList<>();
        for(int i = 0; i <= 100; i++){
            list3.add(i+.02 + i + 2.032);
        }

        DoublyLinkedList<Boolean> list4 = new DoublyLinkedList<>();
        for(int i = 0; i < 100; i++){
            if(Math.ceil(i/9.0)==.0+i){
                list4.add(true);
            }else{
                list4.add(false);
            }
        }


//
//        // print out all 1 million values in the list
//        for(int i = 0; i < list.size(); i++) {
//            list.get(i);
//        }

//        Iterator<T> iter = list.iterator();
//        while(iter.hasNext()){
//            System.out.println(iter.next());
//        }

        for(String s : list){
            System.out.println(s);
        }

        for(Integer s : list2){
            System.out.println(s);
        }

        for(Double s : list3){
            System.out.println(s);
        }

        for(Boolean s : list4){
            System.out.println(s);
        }
    }
}

