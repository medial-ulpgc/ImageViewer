package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CanvasImageDisplay extends Canvas implements ImageDisplay{

    private Image image;
    private final JFrame frameToUpdate;
    private BufferedImage bimg;

    public CanvasImageDisplay(JFrame frameToUpdate) {
        this.frameToUpdate = frameToUpdate;
        
    }
    
    
    @Override
    public void paint (Graphics g){
        if (image != null){

            g.drawImage(bimg, 0,0,this);
        }
    }
    
    @Override
    public void show(Image image) {
        try {
            bimg = ImageIO.read(new File(image.getName()));
            int width  = bimg.getWidth();
            int height = bimg.getHeight();
            this.setBounds(0, 0, width, height);
            
        } catch (IOException ex) {
            Logger.getLogger(CanvasImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
            //java.awt.Image image1 = Toolkit.getDefaultToolkit().getImage(image.getName());
            
        this.image=image;
        this.repaint();
        frameToUpdate.pack();
        
    }

    @Override
    public Image image() {
        return this.image;
    }
    
}
