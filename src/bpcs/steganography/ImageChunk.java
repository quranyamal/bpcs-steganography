package bpcs.steganography;

import java.awt.image.BufferedImage;

/**
 *
 * @author malqu
 */
public class ImageChunk {
    static int CHUNK_SIZE = 8;
    
    private BufferedImage chunkBuffer;
    private boolean[][][] bitPlanes;
    
    ImageChunk(BufferedImage image) {
        System.out.println("init image chunk 0");
        chunkBuffer = image;
        System.out.println("init image chunk 1");
        initBitPlane();
        System.out.println("init image chunk 2");
        
    }
    
    BufferedImage getBuffer() {
        return chunkBuffer;
    }
    
    private void initBitPlane() {
        int numPlane = chunkBuffer.getColorModel().getPixelSize();
        bitPlanes = new boolean[numPlane][8][8];
        
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                int pixel = chunkBuffer.getRGB(i, j);
                for (int planeIdx=0; planeIdx<numPlane; planeIdx++) {
                    bitPlanes[numPlane-1-planeIdx][i][j] = (1<<planeIdx & pixel) != 0;
                }
                System.out.println("pixel("+i+","+j+"): "+ Integer.toBinaryString(pixel));
            }
        }
    }
    
    public boolean[][] getBitPlane(int planeIdx) {
        return bitPlanes[planeIdx];
    }
    
}
