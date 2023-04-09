import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    public static final int SCREEN1_H = 500;
    public static final int SCREEN1_W = 500;
    public static final int SCREEN2_H = 500;
    public static final int SCREEN2_W = 500;

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

    static public Ball[] drawManyBalls(int[] ballsRadius, GUI gui) {
        Random rand1 = new Random();
        Ball[] balls = new Ball[ballsRadius.length];
        for (int i = 0; i < ballsRadius.length; i++) {
            double x = rand1.nextDouble(gui.getDrawSurface().getWidth()) + 1;
            double y = rand1.nextDouble(gui.getDrawSurface().getHeight()) + 1;
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
        GUI gui1 = new GUI("Multiple Frames Bouncing Balls Animation 1", SCREEN1_W, SCREEN1_H);
        biuoop.Sleeper sleeper1 = new biuoop.Sleeper();
        GUI gui2 = new GUI("Multiple Frames Bouncing Balls Animation 2", SCREEN2_W, SCREEN2_H);
        biuoop.Sleeper sleeper2 = new biuoop.Sleeper();
        Color color1 = Color.gray;
        Color color2 = Color.yellow;
        Ball[] balls1 = drawManyBalls(ballsRadius1, gui1);
        Ball[] balls2 = drawManyBalls(ballsRadius2, gui2);
        while (true) {
            DrawSurface d1 = gui1.getDrawSurface();
            DrawSurface d2 = gui2.getDrawSurface();
            d1.setColor(color1);
            d2.setColor(color2);
            for (int i = 0, j = 0; i < ballsRadius1.length && j < ballsRadius2.length; i++, j++) {
                balls1[i].moveOneStep();
                balls1[i].drawOn(d1);
                balls2[j].moveOneStep();
                balls2[j].drawOn(d2);
            }
            gui1.show(d1);
            gui2.show(d2);
            sleeper1.sleepFor(50);  // wait for 50 milliseconds.
            sleeper2.sleepFor(50);
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
