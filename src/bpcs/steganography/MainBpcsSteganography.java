package bpcs.steganography;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainBpcsSteganography {
    
    public static void main(String[] args) {
        
        BufferedImage coverImg;
        
        BpcsSteganography bpcs = new BpcsSteganography();
        ImageViewer imgViewer = new ImageViewer();
        ImageSplitter imgSplitter = new ImageSplitter();
        
        coverImg = bpcs.loadImage("gajah.jpg");
        imgViewer.viewImage(coverImg, "Gajah - Original Image");
        
        System.out.println("Cover image properties");
        bpcs.printImageProperties(coverImg);
        
        BufferedImage[] imgChunks = imgSplitter.split(coverImg, 2, 2);
        imgViewer.viewImages(imgChunks, "image chunk");
    }
    
}
