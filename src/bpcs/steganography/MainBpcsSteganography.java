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
        String imageFile = "gajah.jpg";
        
        imgViewer.viewFromFile(imageFile, "Gajah - Original Image");
        
        coverImg = bpcs.loadImage(imageFile);
        System.out.println("Cover image properties");
        bpcs.printImageProperties(coverImg);
        imgViewer.viewFromBuffImage(coverImg, "Gajah - Original Image (from buff image viewer");
        
        BufferedImage[] coverImageMonochromes = bpcs.getMonochromes(coverImg);
        BufferedImage[] coverImgChunks = bpcs.getImageChunks(coverImg);
        
        bpcs.previewAllChunkImages(coverImgChunks);
        
        System.out.println("\nChunk-0 properties");
        bpcs.printImageProperties(coverImgChunks[0]);
        
    }
    
}
