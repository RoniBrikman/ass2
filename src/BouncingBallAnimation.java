//323871723 Roni Brikman

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The type Bouncing Ball Animation.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class BouncingBallAnimation {
    public static final Point SCREEN_MIN = new Point(0, 0);
    public static final int SCREEN_H = 200;
    public static final int SCREEN_W = 200;

    /**
     * Draw animation.
     *
     * @param start the start
     * @param dx    the dx
     * @param dy    the dy
     */
    public void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", SCREEN_W, SCREEN_H);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int r = 30;
        Ball ball = new Ball(start.getX(), start.getY(), r, java.awt.Color.BLACK);
//        ball.setVelocity(Velocity.fromAngleAndSpeed(40, 10));
        ball.setBottomLim(0);
        ball.setTopLim(SCREEN_W);
        ball.boundries((int) SCREEN_MIN.getX(), (int) SCREEN_MIN.getY(), SCREEN_H, SCREEN_W);
        ball.radiusCheck((int) SCREEN_MIN.getX(), (int) SCREEN_MIN.getY(), SCREEN_H, SCREEN_W);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BouncingBallAnimation ball1 = new BouncingBallAnimation();
        Point p = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        ball1.drawAnimation(p, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
