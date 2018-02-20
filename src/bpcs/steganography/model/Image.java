package bpcs.steganography.model;

import bpcs.steganography.model.ImageChunk;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image {
    
    private BufferedImage buffImage;
    private ImageChunk[][] chunks;
    
    public int rows;
    public int cols;

    public Image(String pathToFile) {
        loadImage(pathToFile);
        
        cols = getWidth()/ImageChunk.SIZE;
        rows = getHeight()/ImageChunk.SIZE;
        
        initImageChunks();
    }
    
    public BufferedImage getBuffer() {
        return buffImage;
    }
    
    public int getPixelSize() {
        return buffImage.getColorModel().getPixelSize();
    }
    
    public int getNumColorComponent() {
        return buffImage.getColorModel().getNumColorComponents();
    }
    
    public int getNumComponent() {
        return buffImage.getColorModel().getNumComponents();
    }
    
    private void loadImage(String pathToFile) {
        try {
            buffImage = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public int getHeight() {
        return buffImage.getHeight();
    }
    
    public int getWidth() {
        return buffImage.getWidth();
    }
    
    public ImageChunk getChunk(int row, int col) {
        return chunks[row][col];
    }
    
    public ImageChunk[][] getAllChunk(){
        return chunks;
    }

    private void initImageChunks() {
        int numChunk = rows * cols;
        
        BufferedImage[] buffImages = split(buffImage, rows, cols);
        chunks = new ImageChunk[rows][cols];
        
        int count=0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                chunks[i][j] = new ImageChunk(buffImages[count]);
                count++;
            }
        }
    }
    
    public BufferedImage[] split(BufferedImage image, int numRow, int numCol) {
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
