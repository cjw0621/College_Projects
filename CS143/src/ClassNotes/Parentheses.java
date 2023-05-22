package ClassNotes;

import java.util.Stack;

public class Parentheses {

    /*
     * isBalanaced(String expr) return true if the parenthesis in expr are balanaced. Every opening parenthesis has a
     * matching closing parenthesis.
     */

    public static boolean isBalanced(String expr){
        // create a stack of characters to keep track of the opening parens in the expr
        Stack<Character> stack = new Stack<>();

        // loops through each character in the string
        for(int i = 0; i < expr.length(); i++){
            char c = expr.charAt(i);
           if(c == '('){
               // push it to the stack
               stack.push(c);
           } else if(c == ')'){
               //is the stack empty?
               if(stack.empty()){
                   //we have closing paren with no opening one to match it to
                   return false;
               }
               //pop the top of the stack
               stack.pop();
           }
        }

        // after the for loop, if the stack is empty, we did the same number of pushes as pops. this means every opening
        //paren had a matching closing paren after it.
        //
        if(stack.empty()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("(1+2) * (3+4)")); // true
        System.out.println(isBalanced("((1+2) * (3+4)")); // false
        System.out.println(isBalanced("(1+2) * 3+4)))))))))))))))))))")); // false
        System.out.println(isBalanced("((1+2)*3) + (4-(3)*2)")); // true
    }
}
