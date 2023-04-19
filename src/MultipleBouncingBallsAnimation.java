//323871723 Roni Brikman

import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.Random;

/**
 * The Multiple Bouncing Balls Animation.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class MultipleBouncingBallsAnimation {
    public static final Point SCREEN_MIN = new Point(0, 0);
    public static final int SCREEN_H = 200;
    public static final int SCREEN_W = 200;

    /**
     * Returns the speed of the Ball according to his size .
     *
     * @param radius the radius
     * @return the speed
     */
    public static double setSpeed(int radius) {
        if (radius >= 50) {
            return 2;
        }
        return radius * (-0.25) + 15;
    }

    /**
     * Creates and draws many balls on a screen and shows them using given radius array.
     *
     * @param ballsRadius the balls radius
     */
    public void drawManyBalls(int[] ballsRadius) {
        GUI gui = new GUI("Bouncing Ball Animation", SCREEN_W, SCREEN_H);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        //creates the array of the balls
        Ball[] balls = new Ball[ballsRadius.length];
        for (int i = 0; i < ballsRadius.length; i++) {
            // get random x and y for the center point of the ball
            double x = rand.nextInt(SCREEN_W) + rand.nextDouble();
            double y = rand.nextInt(SCREEN_H) + rand.nextDouble();
            Ball ball = new Ball(x, y, ballsRadius[i], java.awt.Color.BLACK);
            ball.setBottomLim(0);
            ball.setTopLim(SCREEN_W);
            ball.boundries((int) SCREEN_MIN.getX(), (int) SCREEN_MIN.getY(), SCREEN_H, SCREEN_W);
            ball.radiusCheck((int) SCREEN_MIN.getX(), (int) SCREEN_MIN.getY(), SCREEN_H, SCREEN_W);
            balls[i] = ball;
            double angle = rand.nextInt(359) + rand.nextDouble();
            double speed = setSpeed(balls[i].getSize());
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep(SCREEN_H, 0);
                balls[i].drawOn(d);
            }
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
        MultipleBouncingBallsAnimation multyBalls = new MultipleBouncingBallsAnimation();
        int[] ballsRadius = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            ballsRadius[i] = Integer.parseInt(args[i]);
        }
        multyBalls.drawManyBalls(ballsRadius);
    }

}
