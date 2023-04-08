/**
 * The type Velocity.
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * This method instantiates a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the old point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * This method gets the dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * This method gets the dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }
}