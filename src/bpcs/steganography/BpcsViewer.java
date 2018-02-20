package bpcs.steganography;

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
}
