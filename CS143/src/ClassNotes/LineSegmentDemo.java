package ClassNotes;

public class LineSegmentDemo {
    public static void main(String[] args) {

        LineSegment ls = new LineSegment(1,2,3,4);
       // No longer possible because the properties are private
        // System.out.println(ls.x1);

        System.out.println(ls.getX1());
        System.out.println(ls.getY1());
        System.out.println(ls.getX2());
        System.out.println(ls.getY2());
        System.out.println(ls.slope());

        LineSegment ls1 = new LineSegment(2,5,2,10);

        //Now this causes crash due to undefined slope
        //System.out.println(ls1.slope());
        System.out.println(ls1.length());
        System.out.println(ls.length());

        LineSegment ls2 = new LineSegment(4,4, 9, 11);

        System.out.println(ls2);

        ls2.setEndPoints(5,5,10,12);
        System.out.println(ls2);
        
    }
}
