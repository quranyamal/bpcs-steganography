/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 */ 

package bpcs.steganography;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 */
public class ImageViewer extends Component {
          
    BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public ImageViewer() {
    }
    
    public void viewFromFile(String imageFile, String title) {
        JFrame f = new JFrame(title);
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        try {
            img = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println(e);
        }
        
        f.add(this);
        f.pack();
        f.setVisible(true);
    }

        public void viewFromBuffImage(BufferedImage buffImage, String title) {
        JFrame f = new JFrame(title);
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

//        try {
//            img = ImageIO.read(new File(imageFile));
//        } catch (IOException e) {
//            System.err.println(e);
//        }
        Graphics g = buffImage.getGraphics();
        g.drawImage(buffImage, 0, 0, null);
        JLabel imgLabel = new JLabel(new ImageIcon(buffImage));
        f.add(imgLabel);
        f.pack();
        f.setVisible(true);
    }
    
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
    
    public static void main(String[] args) {
        ImageViewer viewer = new ImageViewer();
        viewer.viewFromFile("gajah.jpg", "gajah-1");
        viewer.viewFromFile("tawakkal.jpg", "tawakkal");
        viewer.viewFromFile("gajah.jpg", "gajah-2");
    }
}

