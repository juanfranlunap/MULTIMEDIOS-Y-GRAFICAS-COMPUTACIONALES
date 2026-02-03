import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException; 

public class Triangles { 
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < 800; x++) {
            for (int y = 0; y < 600; y++) {
                // Triangle above the diagonal 
                if (y < x * 0.75) {
                    image.setRGB(x, y, Color.RED.getRGB());
                }
                // Triangle below the diagonal 
                else {
                    image.setRGB(x, y, Color.BLUE.getRGB());
                }
            }
        }

        File outputFile = new File("image.png");
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
