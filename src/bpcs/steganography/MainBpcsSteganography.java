package bpcs.steganography;

import bpcs.steganography.viewer.BpcsViewer;
import bpcs.steganography.model.Image;
import bpcs.steganography.model.BitPlane;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainBpcsSteganography {
    
    public static void main(String[] args) throws Exception {
        
        Image image = new Image("images/rgb-16x16.bmp");
        BpcsViewer viewer = new BpcsViewer();

        viewer.printImageProperties(image);
        viewer.viewAllChunks(image.getAllChunk());
        
        BitPlane bitPlane = image.getChunk(0, 1).getBitPlane(16);
        viewer.printBitPlane(bitPlane);
        
    }
    
}