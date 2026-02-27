import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class ImagePanel extends JPanel {

    private BufferedImage image;
    private int selX1, selY1, selX2, selY2;
    private boolean hasSelection = false;

   
    public ImagePanel(BufferedImage image) {
        this.image = image;
        setupMouseListeners();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        clearSelection();
        revalidate(); 
        repaint();    
    }

    
    public Rect_Selection getSelection() {
        if (!hasSelection) return null;

        /**Scale the selection coordinates from panel space to image space, 
        * because the panel has not the same dimensions as the image */
        double scaleX = (double) image.getWidth()  / getWidth();
        double scaleY = (double) image.getHeight() / getHeight();

        // Convert panel-based selection into real image coordinates
        int x1 = (int)(Math.min(selX1, selX2) * scaleX);
        int y1 = (int)(Math.min(selY1, selY2) * scaleY);
        int x2 = (int)(Math.max(selX1, selX2) * scaleX);
        int y2 = (int)(Math.max(selY1, selY2) * scaleY);

        return new Rect_Selection(x1, y1, x2, y2);
    }


    public void clearSelection() {
        hasSelection = false;
        repaint();
    }

    //Paint Section

    //Draws the scaled image and overlays the selection rectangle.

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the image scaled to fill the panel
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        // Draw semi-transparent selection overlay
        if (hasSelection) {
            int x = Math.min(selX1, selX2);
            int y = Math.min(selY1, selY2);
            int w = Math.abs(selX2 - selX1);
            int h = Math.abs(selY2 - selY1);

            g.setColor(new Color(0, 120, 255, 50));
            g.fillRect(x, y, w, h);

            g.setColor(new Color(0, 120, 255));
            g.drawRect(x, y, w, h);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) return new Dimension(image.getWidth(), image.getHeight());
        return new Dimension(600, 400);
    }

    //Mouse Selection Section

    //Enables drag to select functionality
    
    private void setupMouseListeners() {
        MouseAdapter ma = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Capture starting point of selection
                selX1 = e.getX();
                selY1 = e.getY();
                selX2 = e.getX();
                selY2 = e.getY();
                hasSelection = true;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Update selection dynamically while dragging
                selX2 = e.getX();
                selY2 = e.getY();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Finalize selection rectangle
                selX2 = e.getX();
                selY2 = e.getY();
                repaint();
            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }
}