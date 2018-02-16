package bpcs.steganography;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class BpcsSteganography {
    
    private static int CHUNK_HEIGHT = 8;
    private static int CHUNK_WIDTH = 8;
    
    private BufferedImage loadImage(String pathToFile) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println(e);
        }
        return image;
    }
    
    private void printImageProperties(BufferedImage image) {
        if (image!=null) {
            System.out.println("Image Height:\t " + image.getHeight());
            System.out.println("Image Width :\t " + image.getWidth());
            System.out.println("Image Type  :\t " + image.getType());
            System.out.println("Pixel Size  :\t " + image.getColorModel()
                    .getPixelSize());
            System.out.println(image.getColorModel());
        } else {
            System.out.println("Image is not loaded");
        }
    }
    
    private BufferedImage[] getImageChunks(BufferedImage image) {
        int cols = image.getWidth()/CHUNK_WIDTH;
        int rows = image.getHeight()/CHUNK_HEIGHT;
        BufferedImage imageChunks[] = new BufferedImage[rows*cols];
        
        int count = 0;
        for (int x=0; x<rows; x++) {
            for (int y=0; y<cols; y++) {
                imageChunks[count] = new BufferedImage(CHUNK_WIDTH, CHUNK_HEIGHT, 
                        image.getType());
            }
        }
        return imageChunks;
    }
    
    private BufferedImage getImagePlane(BufferedImage image, int planeIdx) {
        return null;
    }
    
    public static void main(String[] args) {
        
        BufferedImage coverImg;
        
        BpcsSteganography bpcs = new BpcsSteganography();
        String imageFile = "gajah.jpg";
        
        coverImg = bpcs.loadImage(imageFile);
        System.out.println("Cover image properties");
        bpcs.printImageProperties(coverImg);
        
        BufferedImage[] coverImgChunks = bpcs.getImageChunks(coverImg);
        
        System.out.println("\nChunk-0 properties");
        bpcs.printImageProperties(coverImgChunks[0]);
        
    }
    
}
