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
        bitPlane.setMatrix(4, 4, true);
        viewer.printBitPlane(bitPlane);
        System.out.println("complexity-1: " + bitPlane.getComplexity());
        System.out.println("isComplex: " + bitPlane.isComplexPlane()+"\n");
        
        
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                bitPlane.setMatrix(i, j, true);
            }
        }
        
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (i%2==0) {
                    if (j%2==0) bitPlane.setMatrix(i, j, false);
                } else {
                    if (j%2!=0) bitPlane.setMatrix(i, j, false);
                }
            }
        }
        
        viewer.printBitPlane(bitPlane);
        System.out.println("complexity-2: " + bitPlane.getComplexity());
        System.out.println("isComplex: " + bitPlane.isComplexPlane()+"\n");
        
        bitPlane.setMatrix(4, 4, false);
        bitPlane.setMatrix(4, 5, false);
        bitPlane.setMatrix(7, 0, false);
        viewer.printBitPlane(bitPlane);
        System.out.println("complexity-3: " + bitPlane.getComplexity());
        System.out.println("isComplex: " + bitPlane.isComplexPlane()+"\n");
    }
    
}