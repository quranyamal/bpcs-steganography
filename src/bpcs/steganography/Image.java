package bpcs.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Image {
    private List<BitPlane> bitPlanes;
    private List<BufferedImage> chunks;
    private BufferedImage originalImage;
    private int imageHeight;
    private int imageWidth;

    public Image(String pathToFile) { ;
        try {
            originalImage = ImageIO.read(new File(pathToFile));
            imageHeight = originalImage.getHeight();
            imageWidth = originalImage.getWidth();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public BitPlane[] initBitPlanes() {
        for (int i = 0; i < imageHeight; i += BitPlane.SIZE) {
            for (int j = 0; j < imageWidth; j += BitPlane.SIZE) {
            }
        }
    }

    private void printImageProperties() {
        if (originalImage!=null) {
            System.out.println("Image Height:\t " + originalImage.getHeight());
            System.out.println("Image Width :\t " + originalImage.getWidth());
            System.out.println("Image Type  :\t " + originalImage.getType());
            System.out.println("Pixel Size  :\t " + originalImage.getColorModel().getPixelSize());
            System.out.println(originalImage.getColorModel());
        } else {
            System.out.println("Image is not loaded");
        }
    }

    private BufferedImage[] getImageChunks() {
        int cols = originalImage.getWidth()/BitPlane.SIZE;
        int rows = originalImage.getHeight()/BitPlane.SIZE;
        BufferedImage imageChunks[] = new BufferedImage[rows*cols];

        int count = 0;
        for (int x=0; x<rows; x++) {
            for (int y=0; y<cols; y++) {
                imageChunks[count] = new BufferedImage(BitPlane.SIZE, BitPlane.SIZE,
                        originalImage.getType());
            }
        }
        return imageChunks;
    }
}
