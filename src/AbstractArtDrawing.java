//323871723 Roni Brikman

import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * Abstract Drawing class.
 *
 * @author Roni Brikman < ronibrikman@gmail.com >
 * @version 1
 * @since 2023 -04-07
 */
public class AbstractArtDrawing {
    public static final int SCREEN_H = 300;
    /**
     * The constant SCREEN_W.
     */
    public static final int SCREEN_W = 400;

    /**
     * This method generates a random line.
     *
     * @return the new line
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        //The window the line is within the range is 400 pixels wide and 300 pixels high.
        double x1 = rand.nextInt(SCREEN_W - 2) + rand.nextDouble() + 1; // get double in range 1-400
        double y1 = rand.nextInt(SCREEN_H - 2) + rand.nextDouble() + 1; // get double in range 1-400
        double x2 = rand.nextInt(SCREEN_W - 2) + rand.nextDouble() + 1; // get double in range 1-400
        double y2 = rand.nextInt(SCREEN_H - 2) + rand.nextDouble() + 1; // get double in range 1-400
        return new Line(x1, y1, x2, y2);

    }

    /**
     * This method draws 10 random circles.
     */
    public void drawRandomCircles() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", SCREEN_W, SCREEN_H);
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            int x = rand.nextInt(SCREEN_W) + 1; // get integer in range 1-400
            int y = rand.nextInt(SCREEN_H) + 1; // get integer in range 1-300
            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20- the radius
            d.setColor(Color.RED);
            d.fillCircle(x, y, r);
        }
        gui.show(d);
    }

    /**
     * This method draws a line.
     *
     * @param l the line we draw
     * @param d the draw surface
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(),
                (int) l.end().getY());

    }

    /**
     * This method draws intersections between every two lines.
     *
     * @param lines the lines we drew
     * @param d1 the draw surface
     */
    public void drawIntersections(Line[] lines, DrawSurface d1) {
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (lines[i].isIntersecting(lines[j])) { //if the lines intersect
                    if (lines[i].intersectionWith(lines[j]) != null) { //if the intersection point is not null
                        double x = lines[i].intersectionWith(lines[j]).getX(); // x value of the intersection point
                        double y = lines[i].intersectionWith(lines[j]).getY(); // y value of the intersection point
                        int r = 3; //radius
                        d1.setColor(Color.RED);
                        //the intersection point is a red ball with radius 3
                        d1.fillCircle((int) x, (int) y, r);
                    }
                }
            }
        }

    }

    /**
     * This method is the general drawing. From this method we call all the other methods of the drawings.
     */
    public void drawing() {
        // Create a window with the title "Random Line"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Line", SCREEN_W, SCREEN_H);
        DrawSurface d1 = gui.getDrawSurface();
        Line[] lines = new Line[10]; //The array of the lines we will create.
        for (int i = 0; i < 10; i++) {
            //creates and draws the lines
            Line line = generateRandomLine();
            drawLine(line, d1);
            //we put the line in the "Lines" array
            lines[i] = line;
            //draws the middle points
            double x = line.middle().getX();
            double y = line.middle().getY();
            int r = 3;
            d1.setColor(Color.BLUE);
            d1.fillCircle((int) x, (int) y, r);
        }
        //checks intersections points and draws them
        drawIntersections(lines, d1);
        gui.show(d1);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawing();
    }
}