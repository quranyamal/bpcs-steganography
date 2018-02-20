package bpcs.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;


public class ImageSplitter {
    
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
                //imgViewer.viewFromBuffImage(imgs[count], "image-"+count);
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        return imgs;
    }

    public BufferedImage combine(BufferedImage[] bufferedImages, int numRow, int numCol) {
        int totalWidth = numCol * BitPlane.SIZE;
        int totalHeight = numRow * BitPlane.SIZE;

        int heightCurr = 0;
        int widthCurr = 0;

        BufferedImage concatImage = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < bufferedImages.length; j++) {
            g2d.drawImage(bufferedImages[j], widthCurr, heightCurr, null);

            if ((j + 1) % numCol == 0) {
                widthCurr = 0;
                heightCurr += BitPlane.SIZE;
            } else {
                widthCurr += BitPlane.SIZE;
            }
        }
        g2d.dispose();
        return concatImage;
    }
    
    public static void main(String[] args) throws Exception {
        ImageSplitter imgSplitter = new ImageSplitter();
        ImageViewer imgViewer = new ImageViewer();
        
        File file = new File("gajah.jpg");
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
        imgViewer.viewFromBuffImage(image,"INITIAL");
        
        BufferedImage[] buffImgs =  imgSplitter.split(image, image.getHeight()/BitPlane.SIZE, image.getWidth()/BitPlane.SIZE);
        BufferedImage buffImg = imgSplitter.combine(buffImgs, image.getHeight()/BitPlane.SIZE, image.getWidth()/BitPlane.SIZE);
        imgViewer.viewFromBuffImage(buffImg,"COMBINED");
    }
}