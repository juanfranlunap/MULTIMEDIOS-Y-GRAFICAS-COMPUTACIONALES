import java.awt.image.BufferedImage;

public class Grays {

    public static double[][] toGray(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        double[][] gray = new double[h][w];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                gray[y][x] = (r + g + b) / 3.0 - 128;
            }
        }
        return gray;
    }

    public static BufferedImage toImage(double[][] data) {
        int h = data.length;
        int w = data[0].length;

        BufferedImage img =
                new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int v = (int) Math.round(data[y][x] + 128);
                v = Math.max(0, Math.min(255, v));
                int rgb = (v << 16) | (v << 8) | v;
                img.setRGB(x, y, rgb);
            }
        }
        return img;
    }
}
