import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Uso:");
            System.out.println("  java Main c   (comprimir)");
            System.out.println("  java Main d   (descomprimir)");
            return;
        }

        if (args[0].equals("c")) {

            BufferedImage input =
                ImageIO.read(new File("images/input.png"));

            Compressor.compress(input, "compressed.dct");
            System.out.println("Compresión terminada");
        }

        else if (args[0].equals("d")) {

            BufferedImage output =
                Decompressor.decompress("compressed.dct");

            ImageIO.write(output, "png",
                new File("images/output.png"));

            System.out.println("Descompresión terminada");
        }

        System.out.println(new File("images/output.png").getAbsolutePath());
        System.out.println(new File("compressed.dct").getAbsolutePath());
    }
}

