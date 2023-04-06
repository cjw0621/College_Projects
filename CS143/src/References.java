import java.awt.*;

public class References {
    public static void main(String[] args) {
        int x = 5;
        int y = x; // copy x's value to y
        y = 6; // change y to 6

        System.out.println(x);
        System.out.println(y);
        System.out.println( x == y);

        Point p1 = new Point(6,7);
        Point p2 = p1; // p2 object points to the same location as p1, so if the primitive values change in this location
                        // p2 values will change as well
        p1.move(7,8);

        System.out.println(p1);
        System.out.println(p2);


    }
}
