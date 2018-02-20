package bpcs.steganography;


public class BitPlane {
    public static final int SIZE = 8;
    private boolean[] binaries;
    private int bitLevel;

    public BitPlane(boolean[] inputBinary, int level) {
        binaries = new boolean[SIZE*SIZE];
        bitLevel = level;
        for (int i = 0; i < SIZE*SIZE; i++) {
            binaries[i] = inputBinary[i];
        }
    }

    public double getComplexity() {
        boolean[][] matrix = new boolean[SIZE][SIZE];
        int idx = 0;
        for (int i = 0; i< SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = binaries[idx];
                idx++;
            }
        }

        int bitChange = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //sapu kanan
                if (j < SIZE - 1) {
                    if (matrix[i][j] == false && matrix[i][j+1] == true) {
                        bitChange++;
                    }
                }
                //sapu kiri
                if (j > 0) {
                    if (matrix[i][j] == false && matrix[i][j-1] == true) {
                        bitChange++;
                    }
                }
                //sapu atas
                if (i > 0) {
                    if (matrix[i][j] == false && matrix[i-1][j] == true) {
                        bitChange++;
                    }
                }
                //sapu bawah
                if (i < SIZE - 1) {
                    if (matrix[i][j] == false && matrix[i+1][j] == true) {
                        bitChange++;
                    }
                }
            }
        }
        return (double) bitChange/ 112.0;
    }

    public int getBitLevel() {
        return bitLevel;
    }

    public boolean[] getBitPlane() {
        return binaries;
    }
}
