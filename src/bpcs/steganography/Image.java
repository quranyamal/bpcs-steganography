package bpcs.steganography;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {
    static int CHUNK_SIZE = 8;
    
    private BufferedImage buffImage;
    private BufferedImage[][] chunks;

    public Image(String pathToFile) {
        loadImage(pathToFile);
        initImageChunks();
    }
    
    public BufferedImage getBuffer() {
        return buffImage;
    }
    
    private void loadImage(String pathToFile) {
        try {
            buffImage = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    int getHeight() {
        return buffImage.getHeight();
    }
    
    int getWidth() {
        return buffImage.getWidth();
    }
    
    private void printImageProperties() {
        if (buffImage!=null) {
            System.out.println("Image Height:\t " + buffImage.getHeight());
            System.out.println("Image Width :\t " + buffImage.getWidth());
            System.out.println("Image Type  :\t " + buffImage.getType());
            System.out.println("Pixel Size  :\t " + buffImage.getColorModel().getPixelSize());
            System.out.println(buffImage.getColorModel());
        } else {
            System.out.println("Image is not loaded");
        }
    }

    private void initImageChunks() {
        int cols = buffImage.getWidth()/CHUNK_SIZE;
        int rows = buffImage.getHeight()/CHUNK_SIZE;
        int numChunk = rows * cols;
        
        System.out.println("rows:"+rows+" cols:"+cols);
        BufferedImage[] buffImages = split(buffImage, rows, cols);
        ImageViewer viewer = new ImageViewer();
        chunks = new BufferedImage[rows][cols];
        
        int count=0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                chunks[i][j] = buffImages[count];
                viewer.viewImage(chunks[i][j], "chunk-"+i+","+j);
                count++;
            }
        }
    }
    
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
}
