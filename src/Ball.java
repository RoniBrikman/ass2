import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The type Ball.
 */
public class Ball {

    public static final double PI = 3.14159;

    public static int SCREEN_H = 500;
    public static int SCREEN_W = 500;
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */

    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }


    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        int x = this.getX();
        int y = this.getY();
        int r = this.r;
        surface.setColor(this.getColor());
        surface.fillCircle(x, y, r);
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * Move one step.
     */
    public void moveOneStep(int topLim, int bottomLim) {
        if (this.center.getX() + this.r > topLim) {
            this.setCenter(topLim - this.r, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        } else if (this.center.getX() - this.r < bottomLim) {
            this.setCenter(this.r + bottomLim, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.center.getY() - this.r < bottomLim) {
            this.setCenter(this.getX(), this.r + bottomLim);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        } else if (this.center.getY() + this.r > topLim) {
            this.setCenter(this.getX(), topLim - this.r);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

//    public void moveOneStepHard(int xMin, int xMax, int yMin, int yMax) {
//        if (this.center.getX() + this.r >=) {
//
//        }
//
//
//        if (this.center.getX() + this.r >= topLim) {
//            this.setCenter(topLim - this.r, this.getY());
//            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
//        } else if (this.center.getX() - this.r <= bottomLim) {
//            this.setCenter(this.r + bottomLim, this.getY());
//            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
//        }
//        if (this.center.getY() - this.r <= bottomLim) {
//            this.setCenter(this.getX(), this.r + bottomLim);
//            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
//        } else if (this.center.getY() + this.r >= topLim) {
//            this.setCenter(this.getX(), topLim - this.r);
//            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
//        }
//        this.center = this.getVelocity().applyToPoint(this.center);
//    }

}