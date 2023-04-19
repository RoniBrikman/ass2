//323871723 Roni Brikman

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The type ball.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class Ball {
    private int topLim;
    private int bottomLim;
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;

    /**
     * Instantiates a new Ball with center Point, radius and color.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        double x = center.getX();
        double y = center.getY();
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.topLim = 0;
        this.bottomLim = 0;
    }

    /**
     * This method checks if the radius of the ball fits the screen limits.
     *
     * @param screenMinX the screen minimum point x value
     * @param screenMinY the screen minimum point y value
     * @param screenH    the screen height
     * @param screenW    the screen width
     */
    public void radiusCheck(int screenMinX, int screenMinY, int screenH, int screenW) {
        if (this.r * 2 >= screenH - screenMinY || this.r * 2 >= screenW - screenMinX) {
            System.out.println("The radius you entered does not fit the screen limits"
                    + " and has been changed according to screen size");
            this.r = (int) Math.min(screenH - screenMinY, screenW - screenMinX) / 5;
        }
    }

    /**
     * This method checks if the ball is in the screen, and if not it will change the center according to screen.
     *
     * @param screenMinX the screen minimum point x value
     * @param screenMinY the screen minimum point y value
     * @param screenH    the screen height
     * @param screenW    the screen width
     */
    public void boundries(int screenMinX, int screenMinY, int screenH, int screenW) {
        int flag = 0;
        double x = this.center.getX();
        double y = this.center.getY();
        //checks if the ball is off limits, if so it will enter it to the screen
        if (x - this.r < screenMinX) {
            x = screenMinX + 1;
            flag = -1;
        } else if (x + this.r > screenW) {
            x = screenW - 1 - this.r;
            flag = -1;
        }
        if (y - this.r < screenMinY) {
            y = screenMinY + 1 + this.r;
            flag = -1;
        } else if (y + this.r > screenH) {
            y = screenH - 1 - this.r;
            flag = -1;
        }
        this.center = new Point(x, y);
        if (flag != 0) {
            System.out.println("The center point values you entered does not fit the screen limits"
                    + " and has been changed according to them");
        }
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets bottom lim.
     *
     * @param lim the lim
     */
    public void setBottomLim(int lim) {
        this.bottomLim = lim;
    }

    /**
     * Sets top lim.
     *
     * @param lim the lim
     */
    public void setTopLim(int lim) {
        this.topLim = lim;
    }

    /**
     * Gets top lim.
     *
     * @return the top lim
     */
    public int getTopLim() {
        return this.topLim;
    }

    /**
     * Gets bottom lim.
     *
     * @return the bottom lim
     */
    public int getBottomLim() {
        return this.bottomLim;
    }

    /**
     * Instantiates a new Ball using x and y values for center, radius and color.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        //checks if the ball is off limits, if so it will enter it to the screen
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }


    /**
     * Gets the x value of the center point.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the x value of the center point.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size (the radius) of the ball.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * This method draws the ball on the given DrawSurface.
     *
     * @param surface the surface
     */
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
     * Sets velocity of the ball using dx and dy.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets velocity using a Velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the center point.
     *
     * @param x the x
     * @param y the y
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }


    /**
     * Move the ball one step on the screen.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        //if the ball is out of any limit that he knows (his limit)- return it to the limit and change the dx/dy
        if (this.center.getX() + this.r >= this.topLim) {
            this.setCenter(this.topLim - this.r, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        } else if (this.center.getX() - this.r <= this.bottomLim) {
            this.setCenter(this.r, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.center.getY() - this.r <= this.bottomLim) {
            this.setCenter(this.getX(), this.r);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        } else if (this.center.getY() + this.r >= this.topLim) {
            this.setCenter(this.getX(), this.topLim - this.r);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
    }

    /**
     * Move one step with given limits.
     *
     * @param topLim    the top lim
     * @param bottomLim the bottom lim
     */
    public void moveOneStep(int topLim, int bottomLim) {
        this.center = this.getVelocity().applyToPoint(this.center);
        //if the ball is out of any limit given, return it to the limit and change the dx/dy
        if (this.center.getX() + this.r >= topLim) {
            this.setCenter(topLim - this.r, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        } else if (this.center.getX() - this.r <= bottomLim) {
            this.setCenter(this.r + bottomLim, this.getY());
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.center.getY() - this.r <= bottomLim) {
            this.setCenter(this.getX(), this.r + bottomLim);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        } else if (this.center.getY() + this.r >= topLim) {
            this.setCenter(this.getX(), topLim - this.r);
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
//        this.center = this.getVelocity().applyToPoint(this.center);
    }
}