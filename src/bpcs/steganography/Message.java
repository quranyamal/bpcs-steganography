package bpcs.steganography;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static bpcs.steganography.BitPlane.SIZE;

public class Message {

    private byte[] streamFile;
    private BitPlane[] bitPlanes;
    private int numberOfDummyBit;

    public Message(String filePath) {
        streamFile = readBytesFromFile(filePath);
        numberOfDummyBit = 0;

        String streamByte = "";

        for (byte b : streamFile) {
            streamByte += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        }
        bitPlanes = new BitPlane[(streamByte.length()/(SIZE*SIZE)) + 1];
        int bitPlanesIdx = 0;


        while (streamByte.length() >= SIZE*SIZE) {
            String temp = streamByte.substring(0,SIZE*SIZE);
            streamByte = streamByte.substring(SIZE*SIZE);

            boolean[] binary = new boolean[SIZE*SIZE];
            for (int i = 0; i < SIZE*SIZE; i++) {
                if (temp.charAt(i) == '0') {
                    binary[i] = false;
                } else {
                    binary[i] = true;
                }
            }
            BitPlane bitPlane = new BitPlane(binary, -1);
            bitPlanes[bitPlanesIdx] = bitPlane;
            bitPlanesIdx++;
        }

        if (streamByte.length() > 0) {
            boolean[] binary = new boolean[SIZE*SIZE];
            for (int i = 0; i < SIZE*SIZE; i++) {
                if (streamByte.length() > i) {
                    if (streamByte.charAt(i) == '0') {
                        binary[i] = false;
                    } else {
                        binary[i] = true;
                    }
                } else {
                    binary[i] = true;
                    numberOfDummyBit++;
                }
            }
        }
    }

    public BitPlane[] getBitPlanes() {
        return bitPlanes;
    }

    public void setNumberOfDummyBit(int numberOfDummyBit) {
        this.numberOfDummyBit = numberOfDummyBit;
    }

    private byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }
}
