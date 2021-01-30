package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;

    private BufferedImage bufferedImage;
    private java.awt.Image scaledBufferedImage;

    @Override
    public void paint(Graphics g) {
        if (image != null && bufferedImage != null) {
            int targetWidth = this.getWidth();
            int targetHeigth = this.getHeight();
            g.clearRect(0, 0, targetWidth, targetHeigth);

            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();
            if (imageWidth / imageHeight > targetWidth / targetHeigth) {
                scaledBufferedImage = bufferedImage.getScaledInstance(targetWidth, -1, 0);
            } else {
                scaledBufferedImage = bufferedImage.getScaledInstance(-1, targetHeigth, 0);
            }
            g.drawImage(scaledBufferedImage, (targetWidth - scaledBufferedImage.getWidth(null)) / 2, (targetHeigth - scaledBufferedImage.getHeight(null)) / 2, null);

        }
    }

    @Override
    public void show(Image image) {
        try {
            bufferedImage = ImageIO.read(new File(image.getName()));

        } catch (IOException ex) {
            Logger.getLogger(SwingImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SwingImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.image = image;
        this.repaint();

    }

    @Override
    public Image image() {
        return this.image;
    }

}
