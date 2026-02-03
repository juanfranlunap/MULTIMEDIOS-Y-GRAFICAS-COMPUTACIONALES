import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Landscape {

    static int width = 800;
    static int height = 600;

    public static void main(String[] args) {

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int sunX = 150;
        int sunY = 120;
        int sunRadius = 60;

       
        int grassBase = height - 150;
        int amplitude = 30;
        double frequency = 0.05;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

             
                image.setRGB(x, y, Color.WHITE.getRGB());

                // for create circle for the sun
                int dx = x - sunX;
                int dy = y - sunY;
                double r = Math.sqrt(dx * dx + dy * dy);

                if (r <= sunRadius) {
                    image.setRGB(x, y, Color.YELLOW.getRGB());
                }

                // for create grass with sine wave
                int grassY = (int) (grassBase + amplitude * Math.sin(x * frequency));

                if (y >= grassY) {
                    image.setRGB(x, y, Color.GREEN.getRGB());
                }
            }
        }

        // sun rays
        for (int i = 0; i < 8; i++) {

            double angle = (2 * Math.PI / 8) * i;

            int x1 = sunX + (int)(sunRadius * Math.cos(angle));
            int y1 = sunY + (int)(sunRadius * Math.sin(angle));

            int x2 = sunX + (int)((sunRadius + 30) * Math.cos(angle));
            int y2 = sunY + (int)((sunRadius + 30) * Math.sin(angle));

            drawLine(image, x1, y1, x2, y2, Color.RED);
        }

    
        try {
            ImageIO.write(image, "png", new File("paisaje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // for draw line function using Bresenham's algorithm to know the pixel closest to the ''real line '' . 
    public static void drawLine(BufferedImage img, int x1, int y1, int x2, int y2, Color color) {

        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        double xInc = dx / (double) steps;
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
