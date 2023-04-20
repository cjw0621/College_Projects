public class CallStackDemo {
    public static void main(String[] args) {

        /*
         * Call stack
         * Main calls system.out.prinln and system.out.println calls Math.ceil
         *
         * Math.Ceil returns the value to 9.0 and returns it to system.out.println
         * when that happens java removes that method from the call stack when its finished because its done
         * The call stack is a fixed size, so if the call stack goes beyond the fixed size, java throws an error call
         * StackOverFlow
         */
        System.out.println(Math.ceil(8.5));
    }
}
