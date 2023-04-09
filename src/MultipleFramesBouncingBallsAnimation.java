import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    public static final int SCREEN_H = 600;
    public static final int SCREEN_W = 600;

    public static final int REC1_BOTTOM_LIM = 50;
    public static final int REC1_TOP_LIM = 500;
    public static final int REC2_BOTTOM_LIM = 450;
    public static final int REC2_TOP_LIM = 600;


    public static double setSpeed(int radius) {
        if (radius >= 50) {
            return 2;
        }
        return radius * -0.3 + 17;
    }

    public static Color randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    static public Ball[] drawManyBalls(int[] ballsRadius, int bottomLim, int topLim) {
        Random rand1 = new Random();
        Ball[] balls = new Ball[ballsRadius.length];
        for (int i = 0; i < ballsRadius.length; i++) {
            double x = rand1.nextDouble(topLim) + bottomLim;
            double y = rand1.nextDouble(topLim) + bottomLim;
            Ball newBall = new Ball(x, y, ballsRadius[i], randomColor());
            balls[i] = newBall;
            double angle = rand1.nextDouble(360);
            double speed = setSpeed(ballsRadius[i]);
            newBall.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return balls;
//        while (true) {
//            DrawSurface d = gui.getDrawSurface();
//            d.setColor(color);
//            for (int i = 0; i < balls.length; i++) {
//                balls[i].moveOneStep();
//                balls[i].drawOn(d);
//            }
//            gui.show(d);
//            sleeper.sleepFor(50);  // wait for 50 milliseconds.
//        }
    }

    static public void drawTwoFrames(int[] ballsRadius1, int[] ballsRadius2) {
        GUI gui1 = new GUI("Multiple Frames Bouncing Balls Animation 1", SCREEN_W, SCREEN_H);
        biuoop.Sleeper sleeper1 = new biuoop.Sleeper();
        Ball[] balls1 = drawManyBalls(ballsRadius1, REC1_BOTTOM_LIM, REC1_TOP_LIM);
        Ball[] balls2 = drawManyBalls(ballsRadius2, REC2_BOTTOM_LIM, REC2_TOP_LIM);
        while (true) {
            DrawSurface d1 = gui1.getDrawSurface();
            d1.setColor(Color.lightGray);
            d1.fillRectangle(REC1_BOTTOM_LIM, REC1_BOTTOM_LIM, REC1_TOP_LIM - REC1_BOTTOM_LIM,
                    REC1_TOP_LIM - REC1_BOTTOM_LIM);
            for (int i = 0; i < ballsRadius1.length; i++) {
                balls1[i].moveOneStep(REC1_TOP_LIM, REC1_BOTTOM_LIM);
                balls1[i].drawOn(d1);
            }
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

    static public void splitArgs(String[] args, int[] ballsRadius1, int[] ballsRadius2) {
        for (int i = 0; i < (args.length / 2); i++) {
            ballsRadius1[i] = Integer.parseInt(args[i]);
//            System.out.println(ballsRadius1[i] + " ");
        }
        for (int i = ballsRadius1.length, k = 0; i < args.length; i++, k++) {
            ballsRadius2[k] = Integer.parseInt(args[i]);
//            System.out.println(ballsRadius2[k] + " ");
        }
    }

    public static void main(String[] args) {
        int[] ballsRadius1 = new int[args.length / 2];
        int[] ballsRadius2 = new int[args.length / 2 + 1];
        splitArgs(args, ballsRadius1, ballsRadius2);
        drawTwoFrames(ballsRadius1, ballsRadius2);
    }
}
