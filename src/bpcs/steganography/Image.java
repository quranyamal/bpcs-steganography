package bpcs.steganography;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static bpcs.steganography.BitPlane.SIZE;

public class Image {
    private List<BitPlane> redBitPlanes;
    private List<BitPlane> greenBitPlanes;
    private List<BitPlane> blueBitPlanes;
    private BufferedImage originalImage;
    private BufferedImage[] splittedImages;
    private int imageHeight;
    private int imageWidth;
    private final double complexityThreshold = 0.3;

    public Image(BufferedImage image) { ;
            originalImage = image;
            imageHeight = originalImage.getHeight();
            imageWidth = originalImage.getWidth();

            ImageSplitter imgSplitter = new ImageSplitter();
            splittedImages =  imgSplitter.split(image, image.getHeight()/ SIZE, image.getWidth()/ SIZE);

            redBitPlanes = new ArrayList<>();
            greenBitPlanes = new ArrayList<>();
            blueBitPlanes = new ArrayList<>();

            for (BufferedImage buffImg : splittedImages) {
                setBitPlane(buffImg);
            }

    }

    private void setBitPlane(BufferedImage buffImg) {
        List<String> binaryRedList = new ArrayList<>();
        List<String> binaryGreenList = new ArrayList<>();
        List<String> binaryBlueList = new ArrayList<>();


        for (int i = 0; i < buffImg.getHeight(); i++) {
            for (int j = 0; j < buffImg.getWidth(); j++) {
                int pixel = buffImg.getRGB(j,i);
                int red  = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                String binaryRed = Integer.toBinaryString(red);
                String binaryGreen = Integer.toBinaryString(green);
                String binaryBlue = Integer.toBinaryString(blue);


                binaryRedList.add(binaryRed);
                binaryGreenList.add(binaryGreen);
                binaryBlueList.add(binaryBlue);
            }
        }

        BitPlane[] redBitPlaneArray = produceBitPlane(binaryRedList);
        BitPlane[] greenBitPlaneArray = produceBitPlane(binaryGreenList);
        BitPlane[] blueBitPlaneArray = produceBitPlane(binaryBlueList);
        for (BitPlane plane : redBitPlaneArray) {
            redBitPlanes.add(plane);
        }
        for (BitPlane plane : greenBitPlaneArray) {
            greenBitPlanes.add(plane);
        }
        for (BitPlane plane : blueBitPlaneArray) {
            blueBitPlanes.add(plane);
        }
    }

    private BitPlane[] produceBitPlane(List<String> stringList) {
        boolean[][] bitplane = new boolean[8][SIZE*SIZE]; //Bit 0 means LSB

        for (int i = 0; i < SIZE*SIZE; i++) {
            int length = stringList.get(i).length();
            for (int j = 0; j < 8; j++) {
                if (j >= length) {
                    bitplane[j][i] = false;
                } else {
                    if (stringList.get(i).charAt(length-j-1) == '0') {
                        bitplane[j][i] = false;
                    } else {
                        bitplane[j][i] = true;
                    }
                }
            }

        }
        BitPlane[] bitPlane = new BitPlane[8];
        for (int i = 0; i < 8; i++) {
            bitPlane[i] = new BitPlane(bitplane[i],i);
        }
        return bitPlane;
    }

    public BufferedImage[] produceImage(List<BitPlane> redBitPlanes, List<BitPlane> greenBitPlanes, List<BitPlane> blueBitPlanes) {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        BufferedImage[] bufferedImageArray = null;
        String[] redString = new String[SIZE*SIZE];
        String[] greenString = new String[SIZE*SIZE];
        String[] blueString = new String[SIZE*SIZE];

        for (int i = 0; i < redBitPlanes.size(); i+= 8) {

            for (int j = 0; j < SIZE*SIZE; j++) {
                redString[j] = "";
                greenString[j] = "";
                blueString[j] = "";
            }

            for (int j = 0; j <8; j++ ) {
                BitPlane bitPlane =redBitPlanes.get(i + j);
                boolean[] bitArray = bitPlane.getBitPlane();
                int idx = 0;
                for (boolean bool : bitArray) {
                    if (bool) {
                        redString[idx] = '1' + redString[idx];
                    } else {
                        redString[idx] = '0' + redString[idx];
                    }
                    idx++;
                }

                bitPlane = greenBitPlanes.get(i + j);
                bitArray = bitPlane.getBitPlane();
                idx = 0;
                for (boolean bool : bitArray) {
                    if (bool) {
                        greenString[idx] = '1' + greenString[idx];
                    } else {
                        greenString[idx] = '0' + greenString[idx];
                    }
                    idx++;
                }

                bitPlane = blueBitPlanes.get(i + j);
                bitArray = bitPlane.getBitPlane();
                idx = 0;
                for (boolean bool : bitArray) {
                    if (bool) {
                        blueString[idx] = '1' + blueString[idx];
                    } else {
                        blueString[idx] = '0' + blueString[idx];
                    }
                    idx++;
                }
            }

            BufferedImage image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

            for (int j = 0; j < SIZE*SIZE; j++) {
                String pixelStr = redString[j] + greenString[j] + blueString[j];
                int pixel = Integer.parseInt(pixelStr,2);
                image.setRGB(j % 8, j / 8, pixel);
            }
            bufferedImages.add(image);

            bufferedImageArray = new BufferedImage[bufferedImages.size()];
            bufferedImageArray = bufferedImages.toArray(bufferedImageArray);
        }
        return bufferedImageArray;
    }

    public void injectMessage(BitPlane[] bitPlanes) {
        int messageToInject = bitPlanes.length;
        int idx = 0;
        System.out.println(bitPlanes.length);
        while (messageToInject >= 1) {
            if (redBitPlanes.get(idx).getComplexity() > complexityThreshold) {
                redBitPlanes.set(idx, bitPlanes[bitPlanes.length - messageToInject]);
                messageToInject--;
            }
            if (greenBitPlanes.get(idx).getComplexity() > complexityThreshold) {
                greenBitPlanes.set(idx, bitPlanes[bitPlanes.length - messageToInject]);
                messageToInject--;
            }
            if (blueBitPlanes.get(idx).getComplexity() > complexityThreshold) {
                blueBitPlanes.set(idx, bitPlanes[bitPlanes.length - messageToInject]);
                messageToInject--;
            }
            idx++;
        }
    }

    public List<BitPlane> getRedBitPlanes() {
        return redBitPlanes;
    }

    public List<BitPlane> getGreenBitPlanes() {
        return greenBitPlanes;
    }

    public List<BitPlane> getBlueBitPlanes() {
        return blueBitPlanes;
    }
}
