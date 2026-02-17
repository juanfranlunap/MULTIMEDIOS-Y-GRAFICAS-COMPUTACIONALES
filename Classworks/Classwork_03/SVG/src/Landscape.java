import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Landscape {
    
        public static void main(String[] args) {
    
            int sunX = 150;
            int sunY = 120;
            int sunRadius = 60;
    
            int grassBase = 600 - 150;
        int amplitude = 30;
        double frequency = 0.05;

        try (PrintWriter writer = new PrintWriter(new File("paisaje.svg"))) {

            writer.println("<?xml version=\"1.0\" standalone=\"no\"?>");

            writer.println("<svg width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\">");

            // White background
            writer.println("<rect width=\"800\" height=\"600\" fill=\"white\" />");

            // sun
            writer.println("<circle cx=\"" + sunX + "\" cy=\"" + sunY +
                    "\" r=\"" + sunRadius + "\" fill=\"yellow\" />");

            // Ray Sun
            for (int i = 0; i < 8; i++) {

                double angle = (2 * Math.PI / 8) * i;

                int x1 = sunX + (int)(sunRadius * Math.cos(angle));
                int y1 = sunY + (int)(sunRadius * Math.sin(angle));

                int x2 = sunX + (int)((sunRadius + 30) * Math.cos(angle));
                int y2 = sunY + (int)((sunRadius + 30) * Math.sin(angle));

                writer.println("<line x1=\"" + x1 + "\" y1=\"" + y1 +
                        "\" x2=\"" + x2 + "\" y2=\"" + y2 +
                        "\" stroke=\"red\" stroke-width=\"3\" />");
            }

            // Grass
            writer.print("<path d=\"M 0 " + 600 + " ");

            for (int x = 0; x <= 800; x++) {
                int grassY = (int)(grassBase + amplitude * Math.sin(x * frequency));
                writer.print("L " + x + " " + grassY + " ");
            }

            writer.println("L " + 800 + " " + 600 + " Z\" fill=\"green\" />");

            writer.println("</svg>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
