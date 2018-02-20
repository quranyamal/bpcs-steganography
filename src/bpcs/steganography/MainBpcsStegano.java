package bpcs.steganography;

import bpcs.steganography.controller.BpcsSteganoController;
import bpcs.steganography.viewer.BpcsSteganoViewer;
import bpcs.steganography.model.Image;
import bpcs.steganography.model.BitPlane;
import bpcs.steganography.model.Message;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainBpcsStegano {
    
    public static void test1() {
        Image image = new Image("images/rgb-16x16.bmp");
        BpcsSteganoViewer viewer = new BpcsSteganoViewer();

        viewer.printImageProperties(image);
        viewer.viewAllChunks(image.getAllChunk());
        
        BitPlane bitPlane = image.getChunk(0, 1).getBitPlane(16);
        bitPlane.setMatrix(4, 4, true);
        viewer.printBitPlane(bitPlane);
        System.out.println("complexity-1: " + bitPlane.getComplexity());
        System.out.println("isInformative: " + bitPlane.isInformative()+"\n");
        
        
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
        System.out.println("isInformative: " + bitPlane.isInformative()+"\n");
        
        bitPlane.setMatrix(4, 4, false);
        bitPlane.setMatrix(4, 5, false);
        bitPlane.setMatrix(7, 0, false);
        viewer.printBitPlane(bitPlane);
        System.out.println("complexity-3: " + bitPlane.getComplexity());
        System.out.println("isInformative: " + bitPlane.isInformative()+"\n");
    }
    
    public static void test2() throws Exception {
        Message pesan = new Message("test/short.txt");
        System.out.println(pesan);
        
        BpcsSteganoViewer viewer = new BpcsSteganoViewer();
        viewer.printMessageBytes(pesan);
        
        int input = pesan.getBytes()[0];
        
        System.out.println();
        //System.out.println(input + " = " + Arrays.toString(bits));
        
        pesan.setMatrix();
        viewer.printMessageSegment(pesan, 2);
        System.out.println();
        
        viewer.printMessageSegment(Message.wc);
        System.out.println();
        
        viewer.printMessageSegment(pesan.getConjgation(pesan.getSegment(2)));
    }
    
    public static void main(String[] args) throws Exception {
        
        Message message = new Message("test/short.txt");
        System.out.println(message);
        
        BpcsSteganoViewer viewer = new BpcsSteganoViewer();
        BpcsSteganoController controller = new BpcsSteganoController();
        
        Image image = new Image("images/rgb-16x16.bmp");
        System.out.println("chunk before");
        viewer.printBitPlane(image.getChunk(0, 0).getBitPlane(0));
        System.out.println();
        viewer.viewImage(image, "Image before");
        
        System.out.println("fill with:");
        viewer.printMessageSegment(Message.wc);
        System.out.println();
        
        controller.injectMessage(image, message);
        System.out.println("chunk after");
        viewer.printBitPlane(image.getChunk(0, 0).getBitPlane(0));
        viewer.viewImage(image, "Image after");
    }
    
}