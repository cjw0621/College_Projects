public class LineSegment {
    //Properties go here
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    // we could replace x1, y1 with a point object and call it p1
    // we could replace x2, y2 with a point object and call it p2

    public LineSegment(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }



    public int getX2() {
        return x2;
    }



    public int getY2() {
        return y2;
    }

    /*
     * setEndPoints() changes the endpoints of the LineSegment to new values
     */

    public void setEndPoints(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }



    /*
     * slope() calculates the slope of this line segment object
     */
    public double slope(){
        //is the slope undefined?
        if(x2 == x1){
            throw new ArithmeticException("The slope is undefined for a vertical line");
        }
        return (double) (y2 - y1) / (double) (x2 - x1);
    }

    /*
     * length() calculates the distance between (x1, y1) and (x2, y2)
     */
    public double length() {
       double dx = x2 - x1;
       double dy = y2 - y1;
       return Math.sqrt((dx*dx)+(dy*dy));
    }

    @Override
    public String toString() {
        return "First point: ("+x1+","+y1+")" + " Second point: (" + x2 + "," + y2+")";
    }
}
