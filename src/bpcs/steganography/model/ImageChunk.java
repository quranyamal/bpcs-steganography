package bpcs.steganography.model;

import bpcs.steganography.model.BitPlane;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 *
 * @author malqu
 */
public class ImageChunk {
    public static int SIZE = 8;
    
    private BufferedImage chunkBuffer;
    private int numBitPlane;
    private BitPlane[] bitPlanes;
    
    ImageChunk(BufferedImage image) {
        chunkBuffer = image;
        
        numBitPlane = image.getColorModel().getPixelSize();
        bitPlanes = new BitPlane[numBitPlane];
        
        for (int i=0; i<numBitPlane; i++) {
            bitPlanes[i] = new BitPlane();
        }
        initBitPlane();
    }
    
    public BufferedImage getBuffer() {
        return chunkBuffer;
    }
    
    public BitPlane getPlane(int idx) {
        return bitPlanes[idx];
    }
    
    private void initBitPlane() {
        int numPlane = chunkBuffer.getColorModel().getPixelSize();
        
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                int pixel = chunkBuffer.getRGB(i, j);
                for (int planeIdx=0; planeIdx<numPlane; planeIdx++) {
                    boolean val = (1<<planeIdx & pixel) != 0;
                    bitPlanes[numPlane-1-planeIdx].setMatrix(i, j, val);
                }
                // System.out.println("pixel("+i+","+j+"): "+ Integer.toBinaryString(pixel));
            }
        }
    }
    
    public BitPlane getBitPlane(int planeIdx) {
        return bitPlanes[planeIdx];
    }
    
    // sepertinya tidak jadi digunakan
    public byte[] getPixels(BufferedImage image) {
        return ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
    }
    
}
