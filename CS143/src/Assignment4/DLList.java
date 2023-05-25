package Assignment4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing a doubly linked list.
 * 
 * Chase Whitney
 * 
 * PLEASE DO NOT COPY THIS FILE TO OTHER STUDENTS OR TO WEBSITES LIKE CHEGG,
 * GITHUB, ETC WHERE OTHERS MAY VIEW IT!!! IT IS YOUR WORK AND YOU SHOULD BE
 * PROUD OF WHAT YOU HAVE ACCOMPLISHED! IN ADDITION, THIS FILE CONTAINS THE
 * COPYRIGHTED WORK OF MARTIN HOCK AND IS ONLY LICENSED FOR USE BY INDIVIDUAL
 * STUDENTS FOR NONPROFIT EDUCATIONAL PURPOSES.
 */
public class DLList<T> implements Iterable<T> {
	// head is beginning node (no prev), tail is end node (no next)
	// They can both reference the same node if the list is one element long
	// The can both reference null if the list is empty
	public Node<T> head, tail;
	private int size; // how many elements are in the list

	private static class Node<T> {
		// prev is reference to adjacent node closer to head (or null if this node is
		// the head)
		// next is reference to adjacent node closer to tail (or null if this node is
		// the tail)
		public Node<T> prev, next;
		// data is the information stored in the node of type T (T could be String,
		// Integer, etc.)
		public T data;

		public Node(Node<T> before, T data, Node<T> after) {
			this.prev = before;
			this.next = after;
			this.data = data;
		}
	}



	/**
	 * Forward iterator class (conductor).
	 */
	private static class Conductor<T> implements Iterator<T> {
		public Node<T> car; // Next node to visit

		public Conductor(DLList<T> list) {
			car = list.head; // Begin at head
		}

		public boolean hasNext() {
			return car != null; // No more to visit
		}

		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = car.data; // Remember current
			car = car.next; // Advance to after car
			return data; // Return old car data
		}
	}

	private static class BackwardsConductor<T> implements Iterator<T> {
		private Node<T> car;

		public BackwardsConductor(DLList<T> list){
			car = list.tail;
		}

		public boolean hasNext() {
			return car != null; // No more to visit
		}

		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = car.data;
			car = car.prev; // Advance to the prior car
			return data;// return old car data
		}
	}



	public DLList() {
		head = tail = null; // Empty list
		size = 0; // Sets size to 0 when a new list is created
	}

	/**
	 * Add data to the end (tail) of the list.
	 */
	public void add(T data) {
		if (tail == null) {
			// Empty list: one node is head and tail
			head = new Node<>(null, data, null);
			tail = head;
		} else {
			tail.next = new Node<>(tail, data, null);
			tail = tail.next;
		}
		size++;// increases the list size when an element is added
	}

	/**
	 * Add data to a new node at index i, causing the nodes at that index and after
	 * to be one place ahead of where they were in the list. (Do nothing if i was
	 * not a valid index in the list.)
	 *
	 * @param i    existing index in the list
	 * @param data information to add into a new node
	 * @return false if i is not an index in the list, true otherwise
	 */
	public boolean add(int i, T data) {

		if (i >= this.size || i < 0) {
			return false;
		}

		Node<T> newNode = new Node<>(null, data,null);

		if (i == 0 ) {

			newNode.next = head;
			head.prev = newNode;
			head = newNode;

		} else {
			Node<T> current = head;
			for (int j = 0; j < i - 1; j++) {
				current = current.next;
			}

			newNode.prev = current;
			newNode.next = current.next;
			current.next = newNode;
			newNode.next.prev = newNode;

		}

		size++;
		return true;
	}


	/**
	 * Retrieve an element from middle of list.
	 * 
	 * @param i Zero-based index of element
	 * @return The element at that index
	 * @throws IndexOutOfBoundsException if i is invalid
	 */
	public T get(int i) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;

		if(i >= this.size/2) {
			current = tail;
			for (int j = size-1; j > i; j--) {
				// Count our way down to desired element
				current = current.prev;
			}
		} else{

			for(int j = 0;j < i; j++){
				// Count our way up to desired element
				current = current.next;
			}
		}
		return current.data;


	}

	/**
	 * Get and remove element i from the list.
	 *
	 * @param i Zero-based index of element
	 * @return The element at that index
	 * @throws IndexOutOfBoundsException if i is invalid
	 */
	public T remove(int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<T> current = head;
		for (int j = 0; current != null && j < i; j++) {
			// Count our way up to desired element
			current = current.next;
		}
		if (current == null)
			throw new IndexOutOfBoundsException();
		if (current.prev != null) {
			// Link before's after to new after
			// (The node after the node before the current node
			// becomes the node after the current node)
			current.prev.next = current.next;
		} else {
			// current must be head, so head should refer to
			// the node after it to stop referencing current
			head = head.next;
		}
		if (current.next != null) {
			// Link after's before to new before
			// (The node before the node after the current node
			// becomes the node before the current node)
			current.next.prev = current.prev;
		} else {
			// current must be tail, so tail should refer to
			// the node before it to stop referencing current
			tail = tail.prev;
		}
		this.size--;
		return current.data;
	}

	/**
	 * Create a forward iterator for this list.
	 * 
	 * @return iterator that walks from head to tail
	 */

	@Override
	public Iterator<T> iterator() {
		// The Conductor object can walk this list
		// forward, front to back. Each time
		// .next() is called, the Conductor
		// produces one more piece of data,
		// starting with head and ending with tail
		return new Conductor<T>(this);
	}


	/**
	 * Create a reverse iterator for this list.
	 *
	 * @return iterator that walks from tail to head
	 */


	public Iterator<T> descendingIterator() {
		return new BackwardsConductor<>(this);
	}

	/**
	 * Retrieve the number of nodes of this list in O(1) time.
	 * 
	 * @return number of nodes
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Reverse the list, so that what was the tail is now the head, and so forth. A
	 * list going A <-> B <-> C <-> D would now go D <-> C <-> B <-> A.
	 */
	public void reverse() {

		Node<T> previous = null;
		Node<T> current = head;
		Node<T> next;

		while (current != null) {
			// Store the next node
			next = current.next;

			// Reverse the link
			current.next = previous;

			// Move previous and current one step forward

			current.prev = next;
			previous = current;
			current = next;

		}
		Node<T> oldHead = head;
		head = tail;
		tail = oldHead;
	}
}
