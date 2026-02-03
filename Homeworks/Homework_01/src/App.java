import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Area & Perimeter Calculator");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 25, 25));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton squareBtn = new JButton("Square");
        JButton rectangleBtn = new JButton("Rectangle");
        JButton triangleBtn = new JButton("Triangle");
        JButton circleBtn = new JButton("Circle");
        JButton pentagonBtn = new JButton("Regular Pentagon");
        JButton semicircleBtn = new JButton("Regular Semicircle");
        JButton pentagramBtn = new JButton("Star Pentagram");
        JButton exitBtn = new JButton("Exit");

        panel.add(squareBtn);
        panel.add(rectangleBtn);
        panel.add(triangleBtn);
        panel.add(circleBtn);
        panel.add(pentagonBtn);
        panel.add(semicircleBtn);
        panel.add(pentagramBtn);
        panel.add(exitBtn);

        frame.add(panel);

        // Suqare
        squareBtn.addActionListener(e -> {
            try {
                double side = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the side length (cm):")
                );

                double area = side * side;
                double perimeter = 4 * side;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });

        // Rectangle
        rectangleBtn.addActionListener(e -> {
            try {
                double length = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the length (cm):")
                );
                double width = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the width (cm):")
                );

                double area = length * width;
                double perimeter = 2 * (length + width);

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });

        //Triangle
        triangleBtn.addActionListener(e -> {
            try {
                double base = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the base (cm):")
                );
                double height = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the height (cm):")
                );
                double side1 = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter side 1 (cm):")
                );
                double side2 = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter side 2 (cm):")
                );
                double side3 = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter side 3 (cm):")
                );

                double area = 0.5 * base * height;
                double perimeter = side1 + side2 + side3;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });

        //Circle
        circleBtn.addActionListener(e -> {
            try {
                double radius = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the radius (cm):")
                );

                double area = Math.PI * radius * radius;
                double perimeter = 2 * Math.PI * radius;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });

        //Pentagon
        pentagonBtn.addActionListener(e -> {
            try {
                double side = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the side length (cm):")
                );

                double area = (5 * side * side) / (4 * Math.tan(Math.PI / 5));
                double perimeter = 5 * side;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });
        //SemiCircle
        semicircleBtn.addActionListener(e -> {
            try {
                double r = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the radius (cm):")
                );

                double area = 0.5 * Math.PI * r * r;
                double perimeter = Math.PI * r + 2 * r;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });
        //Pentagram
        pentagramBtn.addActionListener(e -> {
            try {
                double length = Double.parseDouble(
                        JOptionPane.showInputDialog(frame, "Enter the length of one star point side (cm):")
                );
                
                double area = (Math.sqrt(10 * (25 + 11 * Math.sqrt(5))) / 4) * Math.pow(length, 2);
                double perimeter = 10 * length;

                JOptionPane.showMessageDialog(frame,
                        "Area: " + area + " cm²\nPerimeter: " + perimeter + " cm");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Only numbers allowed");
            }
        });
        
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
