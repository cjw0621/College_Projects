
public class DoublyLinkedList {
    /*
     * Inner Node class that represents one node
     * of a doubly linked list.
     *
     * Each Node has 3 things
     * 1. some data (a String)
     * 2. a reference to the Node before this Node in the list
     * 3. a reference to the Node after this Node in the list
     */
    private static class Node {

        private Node prev; // a link to the previous node in the list
        private String data;
        private Node next; // a link to the next node in the list


        public Node(Node prev, String data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private Node head; // the first element in the list
    private Node tail; // the last element in the list
    private int size; // how many elements are in the list

    /*
     * Constructs an empty DoublyLinkedList with no data
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
     * add(String e) adds the String e to the end of the list
     * 		e goes after the current tail
     * 		e becomes the tail
     *
     * O(1) constant time
     */
    public void add(String e) {
        // special case: if the list is empty, the head
        // and the tail should become the new node
        if(isEmpty()) {
            Node newNode = new Node(null, e, null);
            head = tail = newNode;
        }
        else {
            // 1. create a new node with
            // 		the current tail before it
            //		e inside of it
            //      nothing (null) after it
            Node newNode = new Node(tail, e, null);
            // 2. the new node should come after the current tail
            tail.next = newNode;
            // 3. the new node should become the tail
            tail = newNode;
        }
        // increase size
        size++;
    }

    /*
     * get(int i) returns the ith String in the list
     * 		throws an IndexOutOfBoundsException if i is undefined
     *
     * O(n) linear time
     */
    public String get(int i) {
        // is "i" out of bounds?
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        // start at the head of the list
        Node walker = head;
        // move walker forwards "i" times
        for(int j = 0; j < i; j++) {
            walker = walker.next;
        }
        // walker is pointing to the ith Node, so return its data
        return walker.data;
    }

    public static void main(String[] args) {
//		DoublyLinkedList list = new DoublyLinkedList();
//		list.add("Cheese"); // 0
//		list.add("Crackers"); // 1
//		list.add("Ham"); // 2
//		list.add("M&Ms"); // 3
//		list.add("Bologna"); // 4
//
//		System.out.println(list.get(2));

        DoublyLinkedList list = new DoublyLinkedList();
        for(int i = 0; i < 1_000_000; i++) {
            list.add("" + i);
        }

        // print out all 1 million values in the list
        for(int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    }
}

