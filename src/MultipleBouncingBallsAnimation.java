import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static final int SCREEN_H = 500;
    public static final int SCREEN_W = 500;

    public static double setSpeed(int radius) {
        if (radius >= 50) {
            return 2;
        }
        return radius * -0.3 + 17;
    }

    static public void drawManyBalls(int[] ballsRadius) {
        GUI gui = new GUI("Bouncing Ball Animation", Ball.SCREEN_W, Ball.SCREEN_H);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        Ball[] balls = new Ball[ballsRadius.length];
        for (int i = 0; i < ballsRadius.length; i++) {
            double x = rand.nextDouble(SCREEN_W) + 1;
            double y = rand.nextDouble(SCREEN_H) + 1;
            Ball ball = new Ball(x, y, ballsRadius[i], java.awt.Color.BLACK);
            balls[i] = ball;
            double angle = rand.nextDouble(360);
            double speed = setSpeed(ballsRadius[i]);
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

    public static void main(String[] args) {
        int[] ballsRadius = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            ballsRadius[i] = Integer.parseInt(args[i]);
        }
        drawManyBalls(ballsRadius);
    }

}
