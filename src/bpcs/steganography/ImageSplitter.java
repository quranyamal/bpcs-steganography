package bpcs.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import java.awt.image.DataBufferByte;


public class ImageSplitter {
    
    // tinggal ditentukan split nya brp row dan col supaya ukuran chunk nya 8x8 pixel
    public BufferedImage[] split(BufferedImage image, int numRow, int numCol) {
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
                Graphics2D gr = imgs[count].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
                count++;
            }
        }
        
        return imgs;
    }
    
//    public boolean[][][] getBitPlane(BufferedImage image) {
//        int numPlane = image.getColorModel().getPixelSize();
//        boolean bitPlanes[][][] = new boolean[numPlane][8][8];
//        
//        int idx=0;
//        for (int i=0; i<8; i++) {
//            for (int j=0; j<8; j++) {
//                int pixel = image.getRGB(i, j);
//                for (int planeIdx=0; planeIdx<numPlane; planeIdx++) {
//                    bitPlanes[numPlane-1-planeIdx][i][j] = (1<<planeIdx & pixel) != 0;
//                }
//                System.out.println("pixel("+i+","+j+"): "+ Integer.toBinaryString(pixel));
//            }
//        }
//        return bitPlanes;
//    }
    
    public void printBitPlane(boolean bitPlane[][]) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(bitPlane[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public byte[] getPixels(BufferedImage image) {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }
    
    public static void main(String[] args) throws Exception {
        ImageSplitter imgSplitter = new ImageSplitter();
        
        File file = new File("rgb-test.bmp");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        
        BufferedImage[] imgChunks = imgSplitter.split(image, 2, 2);
        //imgSplitter.getBitPlane(imgChunks[0]);
    }
}