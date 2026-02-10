package com.github.juanfranlunap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




public class Main {

    public static void main(String[] args) {

        Triangle triangle = new Triangle();
        BufferedImage imagen = triangle.generarImagen(400, 400);

        JFrame frame = new JFrame("Barycentric RGB Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel(new ImageIcon(imagen));
        frame.add(label);

        frame.pack();
        frame.setVisible(true);
    }
}
