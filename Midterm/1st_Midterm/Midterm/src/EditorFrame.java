import javax.swing.*;
import java.awt.*;
import java.io.File;


public class EditorFrame extends JFrame {

    private ImageEditor editor;     
    private ImagePanel  imagePanel; 

    public EditorFrame(String imagePath) throws Exception {
        super("Image Editor");

        editor     = new ImageEditor(imagePath);
        imagePanel = new ImagePanel(editor.getImage());

        buildUI(); 

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); // Help to size the window according to the image and toolbar
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void buildUI() {
        setLayout(new BorderLayout(5, 5));

        JScrollPane scroll = new JScrollPane(imagePanel);
        scroll.setPreferredSize(new Dimension(800, 600));
        add(scroll, BorderLayout.CENTER);

        add(buildToolbar(), BorderLayout.SOUTH);
    }

    //UI Compnents Section

    private JPanel buildToolbar() {
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        toolbar.setBorder(BorderFactory.createEtchedBorder());

    
        JLabel angleLabel  = new JLabel("Angle:");
        String[] angles    = {"90°", "180°", "270°"};
        JComboBox<String> angleBox = new JComboBox<>(angles);

     
        JButton btnOpen   = new JButton("Open");
        JButton btnCrop   = new JButton("Crop");
        JButton btnInvert = new JButton("Invert Colors");
        JButton btnRotate = new JButton("Rotate");
        JButton btnSave   = new JButton("Save");

 
        btnSave.setBackground(new Color(60, 180, 60));
        btnSave.setForeground(Color.WHITE);
        btnSave.setOpaque(true);

        toolbar.add(btnOpen);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(btnCrop);
        toolbar.add(btnInvert);
        toolbar.add(angleLabel);
        toolbar.add(angleBox);
        toolbar.add(btnRotate);
        toolbar.add(new JSeparator(SwingConstants.VERTICAL));
        toolbar.add(btnSave);



        btnOpen.addActionListener(e -> openImage());

        btnCrop.addActionListener(e -> {
            Rect_Selection r = getSelectionOrWarn();
            if (r == null) return;
            editor.crop(r);
            refreshImage();
        });

        btnInvert.addActionListener(e -> {
            Rect_Selection r = getSelectionOrWarn();
            if (r == null) return;
            editor.invertRegion(r);
            refreshImage();
        });

        btnRotate.addActionListener(e -> {
            Rect_Selection r = getSelectionOrWarn();
            if (r == null) return;
            int angle = (angleBox.getSelectedIndex() + 1) * 90; 
            // Convert "90°" into 90, "180°" into  180, "270°" into 270, for the rotateRegion method.
            editor.rotateRegion(r, angle);
            refreshImage();
        });

        btnSave.addActionListener(e -> saveImage());

        return toolbar;
    }

    
    private Rect_Selection getSelectionOrWarn() {
        Rect_Selection r = imagePanel.getSelection();
        if (r == null) {
            JOptionPane.showMessageDialog(this,
                "Please drag to select a region first.",
                "No selection", JOptionPane.WARNING_MESSAGE);
        }
        return r;
    }

    private void refreshImage() {
        imagePanel.setImage(editor.getImage());
        pack();
    }

    //The file chooser to upload image
    private void openImage() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String path = chooser.getSelectedFile().getAbsolutePath();
                editor = new ImageEditor(path);
                imagePanel.setImage(editor.getImage());
                pack();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Could not open image: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //The file chooser to save the image
    private void saveImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("output.png"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                editor.save(chooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this,
                    "Image saved successfully!",
                    "Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Could not save: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}