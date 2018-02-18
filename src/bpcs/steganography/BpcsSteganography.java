/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpcs.steganography;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author malqu
 */
public class BpcsSteganography {
    public static int CHUNK_HEIGHT = 300;
    public static int CHUNK_WIDTH = 300;
    
    BufferedImage loadImage(String pathToFile) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToFile));
        } catch (IOException e) {
            System.err.println(e);
        }
        return image;
    }
    
    void printImageProperties(BufferedImage image) {
        if (image!=null) {
            System.out.println("Image Height:\t " + image.getHeight());
            System.out.println("Image Width :\t " + image.getWidth());
            System.out.println("Image Type  :\t " + image.getType());
            System.out.println("Pixel Size  :\t " + image.getColorModel()
                    .getPixelSize());
            System.out.println(image.getColorModel());
        } else {
            System.out.println("Image is not loaded");
        }
    }
    
    BufferedImage[] getImageChunks(BufferedImage image) {
        int cols = image.getWidth()/CHUNK_WIDTH;
        int rows = image.getHeight()/CHUNK_HEIGHT;
        BufferedImage imageChunks[] = new BufferedImage[rows*cols];
        
        int count = 0;
        for (int x=0; x<rows; x++) {
            for (int y=0; y<cols; y++) {
                imageChunks[count] = new BufferedImage(CHUNK_WIDTH, CHUNK_HEIGHT, 
                        image.getType());
            }
        }
        return imageChunks;
    }
    
    BufferedImage[] getMonochromes(BufferedImage image) {
        int colorComponentSize = image.getColorModel().getNumComponents();
        BufferedImage[] imageChromes = new BufferedImage[colorComponentSize];
        
        switch (colorComponentSize) {
            case 3: //rgb
                for (int i=0; i<image.getHeight(); i++) {
                    for (int j=0; j>image.getWidth(); j++) {
                        int rgb = image.getRGB(j, i);
                        
                    }
                }
        }
        return null;
    }
    
    BufferedImage getImagePlane(BufferedImage image, int planeIdx) {
        return null;
    }
    
    void previewAllChunkImages(BufferedImage[] images) {
        ImageViewer imgViewer = new ImageViewer();
        for (int i=0; i<images.length; i++) {
            imgViewer.viewFromBuffImage(images[i], "image-"+i);
        }
    }
}
