package bpcs.steganography.viewer;

import bpcs.steganography.model.Image;
import bpcs.steganography.model.ImageChunk;
import bpcs.steganography.model.BitPlane;
import bpcs.steganography.model.Message;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BpcsViewer extends Component {
    
    public BpcsViewer() {
    }
    
    public void printImageProperties(Image image) {
        System.out.println("=========== Image Properties ===========");
        System.out.println("Image Height:\t " + image.getHeight());
        System.out.println("Image Width :\t " + image.getWidth());
        System.out.println("Image Type  :\t " + image.getBuffer().getType());
        System.out.println("Pixel Size  :\t " + image.getPixelSize());
        System.out.println("Num Compnn. :\t " + image.getNumComponent());
        System.out.println("Num Col.Comp:\t " + image.getNumColorComponent());
        System.out.println("========================================");
    }
    
    public void viewImage(Image image, String title) {
        viewImage(image.getBuffer(), title);
    }
    
    public void viewImage(String imageFile, String title) {
        BufferedImage imgBuffer = null;
        try {
            imgBuffer = ImageIO.read(new File(imageFile));        
            viewImage(imgBuffer, title);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void viewImage(BufferedImage buffImage, String title) {
        JFrame f = new JFrame(title);
            
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        Graphics g = buffImage.getGraphics();
        g.drawImage(buffImage, 0, 0, null);
        JLabel imgLabel = new JLabel(new ImageIcon(buffImage));
        f.add(imgLabel);
        f.pack();
        f.setVisible(true);
    }
    
    public void viewAllChunks(ImageChunk[][] chunks) {
        int row = chunks.length;
        int col = chunks[0].length;
        
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                viewImage(chunks[i][j].getBuffer(), "chunk["+i+":"+j+"]");
            }
        }
    }
    
    public void printBitPlane(BitPlane bitPlane) {
        for (int i=0; i<ImageChunk.SIZE; i++) {
            for (int j=0; j<ImageChunk.SIZE; j++) {
                System.out.print(bitPlane.getBit(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    public void printMessageBytes(Message message) {
        byte[] bytes = message.getBytes();
        
        for (int i=0; i<bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
    }
    
    public void printMessageSegment(Message message, int segmentIdx) {
        boolean[][] segment = message.getSegment(segmentIdx);
        
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(segment[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMessageSegment(boolean[][] segment) {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                System.out.print(segment[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
