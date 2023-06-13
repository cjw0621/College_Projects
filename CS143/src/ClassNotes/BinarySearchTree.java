package ClassNotes;

import java.util.Scanner;

/*
 * T extends ComparableT> ensures that when a BST is created
 * only objects that implement the Comparable interface can be
 * substituted for T and not other types of objects
 */
public class BinarySearchTree <T extends Comparable <T>>{
    // inner class that represents one Node of the binary search tree
    private static class Node <T>{
        private T data; // value stored in this node
        private Node <T> left; // link to the subtree to the left of this node
        private Node <T> right; // link to the subtree to the right of this

        // constructs a new leaf Node
        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // reference to the root of this BST
    private Node <T> overallRoot;

    /*
     * Constructs an empty BST
     */
    public BinarySearchTree() {
        overallRoot = null;
    }
    /*
     * add(T data) adds a new node to the BST while preserving the binary search invariant
     */
    public boolean add(T data) {
        if(overallRoot == null){
            overallRoot = new Node<>(data);
            return true;
        }
        // hand the work over to the recursive driver
        return add(data, overallRoot);
    }

    /*
     * Recursive driver method with an extra parameter that we will use
     * to tell the next recursive call to go left or right
     */
    private boolean add(T data, Node<T> subtree){

        // compare data to the data in subtrees root (overallRoot)
        int dataVSNode = data.compareTo(subtree.data);
        // if dataVsNode is less than 0, than we need to recursively add data to the node
        if(dataVSNode < 0){
            if(subtree.left == null){
                subtree.left = new Node<>(data);
                return true;
            }
            return add(data,subtree.left);

        }
        // if dataVsNode is > 0, recursively add this data to the right
        else if( dataVSNode > 0){
            // if we are abou to go to the right but there is nothing there
            if(subtree.right == null){
                subtree.right = new Node<>(data);
                return true;
            }
            return add(data, subtree.right);
        }
        // otherwise, dataVsNode must be == 0 return false
        else{
            return false;
        }
    }

    /*
     * size recursibely calculates the number of the nodes in the BST
     */
    public int size() {
        return size(overallRoot);
    }

    // recursive driver contain an extra parameter that we can use to pass in the subtree that this recursive call is
    //responsible for
    private int size(Node<T> subtree) {
        if(subtree == null){
            return 0;
        }
        else if(subtree.left == null && subtree.right == null) {
            return 1;
        }
        else {
            return size(subtree.left) + size(subtree.right) + 1;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        BinarySearchTree<String> bst1 = new BinarySearchTree<>();

    }
}
