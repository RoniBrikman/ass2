import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * The type Abstract art drawing.
 */
public class AbstractArtDrawing {

    /**
     * Generate random line line.
     *
     * @return the line
     */
    public Line generateRandomLine() {
        Random rand = new Random();
        double x1 = rand.nextDouble(400) + 1; // get double in range 1-400
        double y1 = rand.nextDouble(300) + 1; // get double in range 1-400
        double x2 = rand.nextDouble(400) + 1; // get double in range 1-400
        double y2 = rand.nextDouble(300) + 1; // get double in range 1-400
        return new Line(x1, y1, x2, y2);

    }

//
//    /**
//     * * Draw random circles.
//     */
//    public void drawRandomCircles() {
//        Random rand = new Random(); // create a random-number generator
//        // Create a window with the title "Random Circles Example"
//        // which is 400 pixels wide and 300 pixels high.
//        GUI gui = new GUI("Random Circles Example", 400, 300);
//        DrawSurface d = gui.getDrawSurface();
//        for (int i = 0; i < 10; ++i) {
//            int x = rand.nextInt(400) + 1; // get integer in range 1-400
//            int y = rand.nextInt(300) + 1; // get integer in range 1-300
//            int r = 5 * (rand.nextInt(4) + 1); // get integer in 5,10,15,20
//            d.setColor(Color.RED);
//            d.fillCircle(x, y, r);
//        }
//        gui.show(d);
//    }

    /**
     * Draw line.
     *
     * @param l the l
     * @param d the d
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(),
                (int) l.end().getY());

    }

    /**
     * Drawing.
     */
    public void drawing() {
        GUI gui = new GUI("Random Line", 400, 300);
        DrawSurface d1 = gui.getDrawSurface();
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            Line line = generateRandomLine();
            drawLine(line, d1);
            lines[i] = line;
        }
        gui.show(d1);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example1 = new AbstractArtDrawing();
        example1.drawing();
        //Line[] lines = new Line[10];
//        for (int i = 0; i < 10; i++) {
//            Line line = generateRandomLine();
//            example.drawLine(line, d1);
//            lines[i] = line;
//        }


//        AbstractArtDrawing example = new AbstractArtDrawing();
//        GUI gui = new GUI("Random Line", 400, 300);
//        DrawSurface d1 = gui.getDrawSurface();
//        Line[] lines = new Line[10];
//        for (int i = 0; i < 10; i++) {
//            Line line = generateRandomLine();
//            example.drawLine(line, d1);
//            lines[i] = line;
//        }
//        Line l2 = new Line(2, 1, 3, 5);
//        // Line line = generateRandomLine();
//        example.drawLine(l2, d1);
//        gui.show(d1);

    }
}