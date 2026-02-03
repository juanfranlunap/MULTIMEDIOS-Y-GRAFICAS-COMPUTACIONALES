import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Clock {

    static int width = 800;
    static int height = 600;
    static int centerX = width / 2;
    static int centerY = height / 2;
    static int radius = 200;

    public static void main(String[] args) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int hour = 10;
        int minute = 5;
    

        // fro the blackground
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int dx = x - centerX;
                int dy = y - centerY;
                double r = Math.sqrt(dx * dx + dy * dy);

                if (r >= radius - 2 && r <= radius) {
                    image.setRGB(x, y, Color.WHITE.getRGB()); 
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB()); 
                }
            }
        }

        for (int i = 1; i <= 12; i++) {

            double angle = (2 * Math.PI / 12) * i - Math.PI / 2;

            int outerX = centerX + (int)(radius * Math.cos(angle));
            int outerY = centerY + (int)(radius * Math.sin(angle));

            int innerX = centerX + (int)((radius - 20) * Math.cos(angle));
            int innerY = centerY + (int)((radius - 20) * Math.sin(angle));

            drawLine(image, outerX, outerY, innerX, innerY, Color.WHITE);
        }

        double minuteAngle = (2 * Math.PI / 60) * minute - Math.PI / 2;
        double hourAngle =
                (2 * Math.PI / 12) * hour +
                (2 * Math.PI / 12) * (minute / 60.0) -
                Math.PI / 2;


        drawLine(image, centerX, centerY,
                centerX + (int)(150 * Math.cos(minuteAngle)),
                centerY + (int)(150 * Math.sin(minuteAngle)),
                Color.WHITE);

        drawLine(image, centerX, centerY,
                centerX + (int)(100 * Math.cos(hourAngle)),
                centerY + (int)(100 * Math.sin(hourAngle)),
                Color.WHITE);

        try {
            ImageIO.write(image, "png", new File("clock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawLine(BufferedImage img, int x1, int y1, int x2, int y2, Color color) {

        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));// determine the number of steps needed to draw the pixels for the line

        double xInc = dx / (double) steps; // to know the propotion thats gonna increase for eacht step
        double yInc = dy / (double) steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {
            int px = (int) x;
            int py = (int) y;

            if (px >= 0 && px < width && py >= 0 && py < height) {
                img.setRGB(px, py, color.getRGB());
            }

            x += xInc;
            y += yInc;
        }
    }
}
