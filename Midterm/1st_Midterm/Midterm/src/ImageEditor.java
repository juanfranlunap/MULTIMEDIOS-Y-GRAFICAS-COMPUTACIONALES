import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageEditor {

    private BufferedImage image;

    public ImageEditor(String path) throws IOException {
        image = ImageIO.read(new File(path));
        if (image == null) 
            throw new IOException("Could not read image: " + path);
    }

   
    public BufferedImage getImage() {
        return image;
    }


    public int getWidth()  { return image.getWidth(); }
    public int getHeight() { return image.getHeight(); }

    /**
     * Saves the current image to disk.
     * The format is determined from the file extension.
     */
    public void save(String path) throws IOException {
        String format = path.substring(path.lastIndexOf('.') + 1);
        ImageIO.write(image, format, new File(path));
        System.out.println("Image saved to: " + path);
    }

    //Crop Section

    /**
     * Keeps only the selected region and removes everything else.
     * After this, the image size changes.
     */
    public void crop(Rect_Selection r) {
        if (!isValidRegion(r)) {
            System.out.println("Invalid crop region. Image unchanged.");
            return;
        }

        image = deepCopy(
            image.getSubimage(r.x1, r.y1, r.getWidth(), r.getHeight())
        );

        System.out.println("Cropped to region " + r);
    }

    //Invert Colors Section

    // Each RGB value becomes 255 - original value
     
    public void invertRegion(Rect_Selection r) {
        if (!isValidRegion(r)) {
            System.out.println("Invalid region. Invert skipped.");
            return;
        }

        for (int y = r.y1; y < r.y2; y++) {
            for (int x = r.x1; x < r.x2; x++) {
                image.setRGB(x, y, invertPixel(image.getRGB(x, y)));
            }
        }

        System.out.println("Colors inverted in region " + r);
    }

    /**
     * Inverts a single pixel.
     * Keeps the alpha channel the same.
     */
    private int invertPixel(int rgb) {
        int a = (rgb >> 24) & 0xFF;
        int r = 255 - ((rgb >> 16) & 0xFF);
        int g = 255 - ((rgb >>  8) & 0xFF);
        int b = 255 - ( rgb        & 0xFF);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    //Rotate Region Section

    
    public void rotateRegion(Rect_Selection r, int angle) {

        if (!isValidRegion(r)) {
            System.out.println("Invalid region. Rotation skipped.");
            return;
        }

        if (angle != 90 && angle != 180 && angle != 270) {
            System.out.println("Unsupported angle. Use 90, 180 or 270.");
            return;
        }

        BufferedImage region = image.getSubimage(
            r.x1, r.y1, r.getWidth(), r.getHeight()
        );

        BufferedImage rotated = rotateImage(region, angle);

        // Step 1: Clear original area and fill it with white 
        for (int y = r.y1; y < r.y2; y++) {
            for (int x = r.x1; x < r.x2; x++) {
                image.setRGB(x, y, 0xFFFFFFFF);
            }
        }

        // Step 2: Draw rotated image back into the same location
        for (int y = 0; y < rotated.getHeight(); y++) {
            for (int x = 0; x < rotated.getWidth(); x++) {

                int destX = r.x1 + x;
                int destY = r.y1 + y;

                if (destX < image.getWidth() && destY < image.getHeight()) {
                    image.setRGB(destX, destY, rotated.getRGB(x, y));
                }
            }
        }

        System.out.println("Region " + r + " rotated " + angle + "°");
    }

    /**
     * Rotates an image manually using pixel-by-pixel mapping.
     * Creates a new BufferedImage with the correct size.
     */
    private BufferedImage rotateImage(BufferedImage img, int angle) {

        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage result = (angle == 90 || angle == 270)
            ? new BufferedImage(h, w, img.getType())
            : new BufferedImage(w, h, img.getType());

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {

                int pixel = img.getRGB(x, y);

                if (angle == 90)
                    result.setRGB(h - 1 - y, x, pixel);

                else if (angle == 180)
                    result.setRGB(w - 1 - x, h - 1 - y, pixel);

                else if (angle == 270)
                    result.setRGB(y, w - 1 - x, pixel);
            }
        }

        return result;
    }


    /**
     * Makes sure the selected region is inside the image
     * and actually has positive width and height.
     */
    private boolean isValidRegion(Rect_Selection r) {
        return r.x1 >= 0 && r.y1 >= 0
            && r.x2 <= image.getWidth()
            && r.y2 <= image.getHeight()
            && r.getWidth() > 0
            && r.getHeight() > 0;
    }

    // Creates a real copy of the image.
    private BufferedImage deepCopy(BufferedImage src) {
        BufferedImage copy = new BufferedImage(
            src.getWidth(),
            src.getHeight(),
            src.getType()
        );

        copy.getGraphics().drawImage(src, 0, 0, null);
        return copy;
    }
}