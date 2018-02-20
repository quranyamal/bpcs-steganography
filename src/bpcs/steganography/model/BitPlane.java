package bpcs.steganography.model;

public class BitPlane {
    public static final int SIZE = 8;
    public static final double TRESHOLD = 0.3;
    
    private static final int MAX_DIFF = 112;
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
    
    public int getDiff() {
        int diff=0;
        int maxDiff=0;
        for (int row=0; row<SIZE-1; row++) {
            for (int col=0; col<SIZE-1; col++) {
                if (matrix[row][col]!=matrix[row][col+1]) diff++;
                if (matrix[row][col]!=matrix[row+1][col]) diff++;
            }
        }
        for (int i=0; i<SIZE-1; i++) {
            if (matrix[7][i]!=matrix[7][i+1]) diff++;
            if (matrix[i][7]!=matrix[i+1][7]) diff++;
        }
        return diff;
    }
    
    public double getComplexity() {
        return (double) getDiff()/MAX_DIFF;
    }
    
    public boolean isInformative(){
        return getComplexity()<TRESHOLD;
    }
    
}
