import java.awt.image.BufferedImage;
import java.io.*;

public class Decompressor {

    public static BufferedImage decompress(String filename)
            throws IOException {

        DataInputStream in =
                new DataInputStream(new FileInputStream(filename));

        int width = in.readInt();
        int height = in.readInt();

        double[][] image = new double[height][width];

        for (int y = 0; y < height; y += 8) {
            for (int x = 0; x < width; x += 8) {

                int[][] qBlock = new int[8][8];

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        qBlock[i][j] = in.readInt();
                    }
                }

                double[][] dq =
                        Quantization.dequantize(qBlock);
                double[][] block = DCT.idct(dq);

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (y+i < height && x+j < width)
                            image[y+i][x+j] = block[i][j];
                    }
                }
            }
        }
        in.close();
        return Grays.toImage(image);
    }
}
