package bpcs.steganography;

public class BitPlane {
    public static final int SIZE = 8;
    private boolean[][] matrix;

    public BitPlane(boolean[][] inputMatrix) {
        matrix = new boolean[8][8];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = inputMatrix[i][j];
            }
        }
    }

    public boolean[][] getBitPlane() {
        return matrix;
    }
}
