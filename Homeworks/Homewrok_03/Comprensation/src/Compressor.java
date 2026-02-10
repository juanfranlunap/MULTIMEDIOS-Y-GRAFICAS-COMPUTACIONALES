import java.awt.image.BufferedImage;
import java.io.*;

public class Compressor {

    public static void compress(BufferedImage image, String filename)
            throws IOException {

        double[][] gray = Grays.toGray(image);

        int width = image.getWidth();
        int height = image.getHeight();

        DataOutputStream out =
                new DataOutputStream(new FileOutputStream(filename));

        out.writeInt(width);
        out.writeInt(height);

        for (int y = 0; y < height; y += 8) {
            for (int x = 0; x < width; x += 8) {

                double[][] block = new double[8][8];

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (y+i < height && x+j < width)
                            block[i][j] = gray[y+i][x+j];
                    }
                }

                double[][] dct = DCT.dct(block);
                int[][] q = Quantization.quantize(dct);

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        out.writeInt(q[i][j]);
                    }
                }
            }
        }
        out.close();
    }
}
