//323871723 Roni Brikman

/**
 * The type Point.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x value
     * @param y the y value
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Point.
     *
     * @param oldPoint the old point
     */
    public Point(Point oldPoint)  {
        x = oldPoint.x;
        y = oldPoint.y;
    }

    /**
     * This method returns the distance between two points.
     *
     * @param other the other Point
     * @return the distance between two Points
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * This method checks if two Points are equal to one another using a threshold.
     *
     * @param other the other Point
     * @return True if they are equal, False if not
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        double epsilon = 0.000001;
        return ((Math.abs(this.x - other.x) < epsilon) && (Math.abs(this.y - other.y) < epsilon));

    }

    /**
     * This method gets the x value of a Point.
     *
     * @return the x value of a Point
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * This method gets the y value of a Point.
     *
     * @return the y value of a Point
     */
    public double getY() {
        return this.y;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Point p1 = new Point(2, 5);
        Point p2 = new Point(2, 5);
        System.out.println("equals p1 p2=" + p1.getY() + "\n");
        System.out.println("equals p1 p2=" + p1.getX() + "\n");
//        Point p3 = new Point(2, 5);
//        Point p4 = new Point(2, 7);
//        System.out.println("equals p3 p4=" + p3.distance(p4) + "\n");
//        Point p5 = new Point(2, 5);
//        Point p6 = new Point(3, 5);
//        System.out.println("equals p5 p6=" + p5.distance(p6) + "\n");


    }
}

