//323871723 Roni Brikman

/**
 * The type Line.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Line {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start Point
     * @param end   the end Point
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();

    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x value of the first Point
     * @param y1 the y value of the first Point
     * @param x2 the x value of the second Point
     * @param y2 the y value of the second Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

    }

    /**
     * This method return the lengths of a Line using 2 x values and 2 y values of specific Line.
     *
     * @return the length
     */
// Return the length of the line
    public double length() {
        return Math.sqrt(((this.x1 - this.x2) * (this.x1 - this.x2)) + ((this.y1 - this.y2) * (this.y1 - this.y2)));
    }

    /**
     * This method return the distance between two Points.
     *
     * @param p1 the first Point
     * @param p2 the second Point
     * @return the distance
     */
    public double distance(Point p1, Point p2) {
        return Math.sqrt(((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()))
                + ((p1.getY() - p2.getY()) * (p1.getY() - p2.getY())));
    }

    /**
     * This method returns the middle Point of a Line.
     *
     * @return the Middle point
     */
// Returns the middle point of the line
    public Point middle() {
        double middleX = (this.x1 + this.x2) / 2;
        double middleY = (this.y1 + this.y2) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * This method returns the start Point of a Line.
     *
     * @return the point
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * This method returns the end Point of a Line.
     *
     * @return the point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * This method checks if a given Point is on a given Line.
     *
     * @param l the line
     * @param p the point
     * @return True if the Point is on the Line, and False otherwise
     */
    public boolean isOnLine(Line l, Point p) {
        double epsilon = 0.000001;
        return (Math.abs((distance(l.start(), p) + distance(l.end(), p)) - l.length()) < epsilon);
    }

    /**
     * This method returns True if one Line is intersecting with other Line, and False otherwise.
     *
     * @param other the other Line
     * @return the boolean
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        } else {
            return (isOnLine(this, other.start())) || (isOnLine(this, other.end()))
                    || (isOnLine(other, this.start)) || (isOnLine(other, this.end));
        }
    }

    /**
     * This method returns the intersection Point between two lines, and if it doesn't exist/have infinite Point it will
     * return null.
     *
     * @param other the other Line
     * @return the intersection point
     */
// Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        Point a = this.start;
        Point b = this.end;
        Point c = other.end();
        Point d = other.start();
        // Line AB represented as a1x + b1y = c1
        double a1 = b.getY() - a.getY();
        double b1 = a.getX() - b.getX();
        double c1 = a1 * (a.getX()) + b1 * (a.getY());

        // Line CD represented as a2x + b2y = c2
        double a2 = d.getY() - c.getY();
        double b2 = c.getX() - d.getX();
        double c2 = a2 * (c.getX()) + b2 * (c.getY());
        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            if ((this.start.equals(other.start())) && !(this.end.equals(other.end()))) {
                return this.start;
            } else if ((this.start.equals(other.end())) && !(this.end.equals(other.start()))) {
                return this.start;
            } else if (!(this.start.equals(other.end())) && (this.end.equals(other.start()))) {
                return this.end;
            } else if ((this.end.equals(other.end())) && !(this.start.equals(other.start()))) {
                return this.end;
            }
            // The lines are parallel
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point p = new Point(x, y);
            if (!(isOnLine(this, p) && isOnLine(other, p))) {
                return null;
            }
            return p;
        }

    }

    /**
     * This method checks if two Lines are equal.
     *
     * @param other the other Line
     * @return True if they are equal, False if not
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if ((this.start() == other.start()) && (this.end() == other.end())) {
            return true;
        }
        return (this.start == other.end()) && (this.end == other.start());
    }

    /**
     * Part equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
//    public boolean partEquals(Line other) {
//        if ((this.start == other.start()) && !(this.end == other.end())) {
//            return true;
//        } else if ((this.start == other.end()) && !(this.end == other.start())) {
//            return true;
//        } else if (!(this.start == other.end()) && (this.end == other.start())) {
//            return true;
//        } else if ((this.start == other.end()) && !(this.start == other.start())) {
//            return true;
//        }
//        return false;
//    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Point p1 = new Point(2, 5);
        Line l1 = new Line(-8, 4, -5, 4);
        Line l2 = new Line(-5, 4, -2, 4);
        System.out.println(l1.isIntersecting(l2));
        System.out.println(l1.intersectionWith(l2).getX());
        System.out.println(l1.intersectionWith(l2).getY());
//        System.out.println(l1.end().getX());
//        System.out.println(l1.end().getY());

    }
}

