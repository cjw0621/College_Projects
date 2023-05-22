package ClassNotes;

import java.util.EmptyStackException;

public class StringStack {
    //inner class that represents a single node of our stack
    // Each node will have
    //1. a string
    //2. a reference to the Node below it in the stack
    private class Node{
        private String data; // (pancake flavor)
        private Node below; // link to the node underneath this node

    }
    // what node is at the very top of the stack
    private Node overallTop;

    //define an empty stack as being one where there is nothing on top
    // constructor constructs an empty stack
    public StringStack(){
        overallTop = null;
    }

    /*
     * isEmpty() returns true if the stack has no data. Otherwise, return false
     *
     * O(1) constant time
     */
    public boolean isEmpty(){
        return overallTop == null;
    }

    /*
     * push(String data) pushes new String to the top of the stack
     * The new string will become the top of the stack
     *
     * O(1) constant time
     */
    public void push(String data){
        // 1. create a new node with "data" as its data
        Node newTop = new Node();
        newTop.data = data;
        // 2. the current overallTop should be below the newTop
        newTop.below = overallTop;
        // 3. the newTop should become the overallTop
        overallTop = newTop;
    }

    /*
     * pop() removes the top of the stack and returns the data that was removed
     * O(1) constant time
     */
    public String pop(){
        // 0. check for an empty stack
        if(isEmpty()){
            throw new EmptyStackException();
        }
        // 1. save the overall tops data, so we don't lose access to it
        String result = overallTop.data;
        // 2. move the overallTop down bellow
        overallTop = overallTop.below;
        // 3. return what used to be on top
        return result;
    }

    /*
     * peek() returns but does not remove the top of the stack
     *
     * O(1) constant time
     */
    public String peek(){
        // 0. check for an empty stack
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return overallTop.data;
    }

    public static void main(String[] args) {
        StringStack stack = new StringStack();
        stack.push("Strawberry");
        stack.push("banana");
        stack.push( "chocolate chip");
        stack.push("Blueberry");
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());

    }
}
