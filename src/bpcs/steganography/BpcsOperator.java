package bpcs.steganography;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BpcsOperator {



    public static void main(String[] args) {
        BufferedImage bufferedImage = null;
        try {
            File file = new File("gajah.jpg");
            FileInputStream fis = new FileInputStream(file);
            bufferedImage = ImageIO.read(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = new Image(bufferedImage);

        BufferedImage[] imgs = image.produceImage(image.getRedBitPlanes(),image.getGreenBitPlanes(),image.getBlueBitPlanes());
        Message message = new Message("test.txt");
        image.injectMessage(message.getBitPlanes());


        ImageSplitter imgSplitter = new ImageSplitter();
        BufferedImage buffImg = imgSplitter.combine(imgs, bufferedImage.getHeight()/BitPlane.SIZE, bufferedImage.getWidth()/BitPlane.SIZE);


        ImageViewer imgViewer = new ImageViewer();
        imgViewer.viewFromBuffImage(buffImg,"TEST");


    }

}
