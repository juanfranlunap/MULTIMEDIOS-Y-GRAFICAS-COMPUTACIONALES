import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Triangles {
    public static void main(String[] args) {

        File outputFile = new File("triangles.svg");

        try (PrintWriter writer = new PrintWriter(outputFile)) {

            writer.println("<?xml version=\"1.0\" standalone=\"no\"?>");
            writer.println("<svg width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\">");

            // Triangle above the diagonal
            writer.println("<polygon points=\"0,0 800,0 800,600\" fill=\"red\" />");

            // Triangle under the diagonal
            writer.println("<polygon points=\"0,0 0,600 800,600\" fill=\"blue\" />");

            writer.println("</svg>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
