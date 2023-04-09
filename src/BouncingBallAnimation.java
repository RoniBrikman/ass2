import biuoop.DrawSurface;
import biuoop.GUI;

public class BouncingBallAnimation {
    public static int SCREEN_H = 500;
    public static int SCREEN_W = 500;

    static public void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", SCREEN_W, SCREEN_H);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(Velocity.fromAngleAndSpeed(40, 10));
//        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(SCREEN_W, 0);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    
    
    public static void main(String[] args) {
        Point p = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        drawAnimation(p, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        
    }
    
}
