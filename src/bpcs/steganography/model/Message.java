package bpcs.steganography.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Message {
    
    private String message;
    private byte[] messageBytes;
    private boolean[][][] matrix;
    public int numSegment;
    
    public Message(String filePath) throws Exception {
        loadMessage(filePath);
        messageBytes = message.getBytes();
        
        numSegment = (int) (message.length()/8) + (message.length()%8==0? 0:1);
        matrix = new boolean[numSegment][8][8];
    }
    
    private void loadMessage(String filePath) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        message = new String(data, "UTF-8");
    }
    
    public byte[] getBytes() {
        return messageBytes;
    }
    
    public int getNumSegment() {
        return numSegment;
    }
    
    @Override
    public String toString() {
        return message;
    }
    
    public void setMatrix() {
        for (int i=0; i<numSegment-1; i++) {
            setMatrix(i);
        }
        setLastSegmentMatrix();
    }
    
    void setMatrix(int matrixIdx) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                matrix[matrixIdx][i][7-j] = (message.getBytes()[matrixIdx*8 + i] & (1 << j)) != 0;
            }
        }
    }
    
    public boolean[][][] getMatrix() {
        return matrix;
    }
    
    void setLastSegmentMatrix() {
        for (int i=0; i<8; i++) {
            if (i<message.length()%8) {
                for (int j=0; j<8; j++) {
                    matrix[numSegment-1][i][7-j] = 
                            (message.getBytes()[(numSegment-1)*8 + i] & (1 << j)) != 0;
                }    
            } else {
                for (int j=0; j<8; j++) {
                    matrix[numSegment-1][i][j] = true;
                }                
            }
        }
    }
    
}
