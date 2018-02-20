package bpcs.steganography.model;

public class BitPlane {
    public static final int SIZE = 8;
    boolean[][] matrix;
    
    BitPlane() {
        matrix = new boolean[SIZE][SIZE];
    }
    
    public void setMatrix(int row, int col, boolean val) {
        matrix[row][col] = val;
    }
    
    public boolean getBit(int row, int col) {
        return matrix[row][col];
    }
}
