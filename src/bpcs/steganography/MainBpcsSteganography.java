package bpcs.steganography;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainBpcsSteganography {
    
    public static void main(String[] args) throws Exception {
        
        Image image = new Image("rgb-16x16.bmp");
        BpcsViewer viewer = new BpcsViewer();

        viewer.viewImage(image, "gajah original");
        
    }
    
}


//        BufferedImage coverImg;
//        
//        BpcsSteganography bpcs = new BpcsSteganography();
//        ImageViewer imgViewer = new ImageViewer();
//        ImageSplitter imgSplitter = new ImageSplitter();
//        
////        coverImg = bpcs.loadImage("gajah.jpg");
//        coverImg = bpcs.loadImage("rgb-16x16.bmp");
//        imgViewer.viewImage(coverImg, "Gajah - Original Image");
//

//        System.out.println("Cover image properties");
//        bpcs.printImageProperties(coverImg);
//        
//        BufferedImage[] imgChunks = imgSplitter.split(coverImg, 2, 2);
//        imgViewer.viewImages(imgChunks, "image chunk");
////        imgSplitter.getBitPlane(imgChunks[1]);
////        imgSplitter.;getBitPlane(imgChunks[2]);
////        imgSplitter.getBitPlane(imgChunks[3]);
//        byte[] pixels = imgSplitter.getPixels(imgChunks[0]);
//        System.out.println("pixels size = " + pixels.length);
//        
//        for (int i=0; i<pixels.length; i++) {
//            System.out.print((pixels[i]&0xff)+ " ");
//            if ((i+1)%8==0) System.out.println();
//        }
//        
//        System.out.println("chunk-0");
//        bpcs.printImageProperties(imgChunks[0]);
//        System.out.println("chunk-1");
//        bpcs.printImageProperties(imgChunks[1]);
//        System.out.println("chunk-2");
//        bpcs.printImageProperties(imgChunks[2]);
//        System.out.println("chunk-3");
//        bpcs.printImageProperties(imgChunks[3]);
        
//        boolean[][][] bitPlanes = imgSplitter.getBitPlane(imgChunks[2]);
//        for (int i=0; i<bitPlanes.length; i++) {
//            System.out.println("BP-" + i);
//            imgSplitter.printBitPlane(bitPlanes[i]);
//        }