//323871723 Roni Brikman

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * The Multiple Frames Bouncing Balls Animation.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class MultipleFramesBouncingBallsAnimation {
    public static final int SCREEN_H = 600;
    public static final int SCREEN_W = 600;
    public static final int REC1_BOTTOM_LIM = 50;
    public static final int REC1_TOP_LIM = 500;
    public static final int REC2_BOTTOM_LIM = 450;
    public static final int REC2_TOP_LIM = 600;


    /**
     * Returns the speed of the Ball according to his size .
     *
     * @param radius the radius
     * @return the speed
     */
    public double setSpeed(int radius) {
        if (radius >= 50) {
            return 2;
        }
        return radius * -0.3 + 17;
    }

    /**
     * This method creates random color.
     *
     * @return the new color
     */
    public Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    /**
     * This method Draws many balls using an array of the balls radius and the top and bottom limit of the screen.
     *
     * @param ballsRadius the balls radius
     * @param bottomLim   the bottom lim
     * @param topLim      the top lim
     * @return the ball [ ]
     */
    public Ball[] drawManyBalls(int[] ballsRadius, int bottomLim, int topLim) {
        Random rand1 = new Random();
        Ball[] balls = new Ball[ballsRadius.length];
        for (int i = 0; i < ballsRadius.length; i++) {
            // get random x and y for the center point of the ball
            double x = (rand1.nextInt(topLim) + bottomLim - 1 + rand1.nextDouble());
            double y = (rand1.nextInt(topLim) + bottomLim - 1 + rand1.nextDouble());
            Ball newBall = new Ball(x, y, ballsRadius[i], randomColor());
            newBall.setTopLim(topLim);
            newBall.setBottomLim(bottomLim);
            newBall.boundries(bottomLim, bottomLim, topLim, topLim);
            newBall.radiusCheck(bottomLim, bottomLim, topLim, topLim);
            balls[i] = newBall;
            double angle = rand1.nextInt(360);
            double speed = setSpeed(ballsRadius[i]);
            newBall.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return balls;
    }

    /**
     * Draw two frames and shows the balls on the screen, using two arrays of radius, one for each screen.
     *
     * @param ballsRadius1 the first balls radius array
     * @param ballsRadius2 the second balls radius array
     */
    public void drawTwoFrames(int[] ballsRadius1, int[] ballsRadius2) {
        GUI gui1 = new GUI("Multiple Frames Bouncing Balls Animation 1", SCREEN_W, SCREEN_H);
        biuoop.Sleeper sleeper1 = new biuoop.Sleeper();
        //draws the balls on the screen
        Ball[] balls1 = drawManyBalls(ballsRadius1, REC1_BOTTOM_LIM, REC1_TOP_LIM);
        Ball[] balls2 = drawManyBalls(ballsRadius2, REC2_BOTTOM_LIM, REC2_TOP_LIM);
        while (true) {
            //draws the first rectangle and all the balls on it
            DrawSurface d1 = gui1.getDrawSurface();
            d1.setColor(Color.lightGray);
            d1.fillRectangle(REC1_BOTTOM_LIM, REC1_BOTTOM_LIM, REC1_TOP_LIM - REC1_BOTTOM_LIM,
                    REC1_TOP_LIM - REC1_BOTTOM_LIM);
            for (int i = 0; i < ballsRadius1.length; i++) {
                balls1[i].moveOneStep(REC1_TOP_LIM, REC1_BOTTOM_LIM);
                balls1[i].drawOn(d1);
            }
            //draws the second rectangle and all the balls on it
            d1.setColor(Color.yellow);
            d1.fillRectangle(REC2_BOTTOM_LIM, REC2_BOTTOM_LIM, REC2_TOP_LIM, REC2_TOP_LIM);
            for (int j = 0; j < ballsRadius2.length; j++) {
                balls2[j].moveOneStep(REC2_TOP_LIM, REC2_BOTTOM_LIM);
                balls2[j].drawOn(d1);
            }
            gui1.show(d1);
            sleeper1.sleepFor(50);  // wait for 50 milliseconds.

        }
    }

    /**
     * This method splits the args[] in half into two arrays.
     *
     * @param args         the args
     * @param ballsRadius1 the first array to put radius in
     * @param ballsRadius2 the second array to put radius in
     */
    public static void splitArgs(String[] args, int[] ballsRadius1, int[] ballsRadius2) {
        for (int i = 0; i < (args.length / 2); i++) {
            ballsRadius1[i] = Integer.parseInt(args[i]);
        }
        for (int i = ballsRadius1.length, k = 0; i < args.length; i++, k++) {
            ballsRadius2[k] = Integer.parseInt(args[i]);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MultipleFramesBouncingBallsAnimation framesAndBalls = new MultipleFramesBouncingBallsAnimation();
        int[] ballsRadius1 = new int[args.length / 2];
        int[] ballsRadius2 = new int[args.length / 2 + 1];
        framesAndBalls.splitArgs(args, ballsRadius1, ballsRadius2);
        framesAndBalls.drawTwoFrames(ballsRadius1, ballsRadius2);
    }
}
