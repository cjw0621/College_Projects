import java.awt.*;

public class Points {
    public static void main(String[] args) {

        Point p = new Point();// (0,0)
        System.out.println(p);

        Point p1 = new Point(1,2); // (1,2)
        System.out.println(p1);

        Point p2 = new Point(p1); // different object with same point as p1
        System.out.println(p2);

        System.out.println(p1 == p2); // different objects with same values

        System.out.println(p1.getX()); // returns x coordinate as a double, getter method

        System.out.println(p1.getY()); // returns y coordinate as a double, getter method

        p.move(2,2); // change p from )0,0) to (2,2)

        System.out.println("("+p.getX() + ", " + p.getY() + ")");



    }


}
