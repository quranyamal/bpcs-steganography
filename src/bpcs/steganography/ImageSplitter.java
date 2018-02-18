package bpcs.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;


public class ImageSplitter {
    
    public void split(BufferedImage image, int numRow, int numCol) {
        ImageViewer imgViewer = new ImageViewer();

        int chunks = numRow * numCol;

        int chunkWidth = image.getWidth() / numCol; // determines the chunk width and height
        int chunkHeight = image.getHeight() / numRow;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < numRow; x++) {
            for (int y = 0; y < numCol; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // draws the image chunk
                imgViewer.viewFromBuffImage(imgs[count], "image-"+count);
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        System.out.println("Splitting done");
    }
    
    public static void main(String[] args) throws Exception {
        ImageSplitter imgSplitter = new ImageSplitter();
        
        File file = new File("gajah.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        
        imgSplitter.split(image, 2, 2);
    }
}