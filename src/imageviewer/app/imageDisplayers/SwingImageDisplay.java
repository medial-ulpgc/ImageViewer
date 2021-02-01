package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;
    private BufferedImage bufferedImage;

    private Image previewImage;
    private BufferedImage previewBufferedImage;

    private int offset;
    private List<ImageDisplay.ImageTransition> imageTransitionListeners = new ArrayList<>();
    private ImageDisplay.LoadPreview previewLoader = null;

    public SwingImageDisplay() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.offset = 0;
    }

    @Override
    public void paint(Graphics g) {
        if (image != null && bufferedImage != null) {
            g.setColor(Color.black);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            BufferedImage composedBufferedImage = composeImage(bufferedImage, this.getWidth(), this.getHeight());
            g.drawImage(composedBufferedImage, offset, 0, null);
            if (offset != 0 && previewImage != null && previewBufferedImage != null) {
                 BufferedImage composedBufferedImage2 = composeImage(previewBufferedImage, this.getWidth(), this.getHeight());
                 int offsetPreview = (offset<0)?offset+getWidth():offset-getWidth();
                 
                 g.drawImage(composedBufferedImage2, offsetPreview, 0, null);
            } 
            
        }

    }

    @Override
    public void show(Image image) {
        bufferedImage = loadFromDisk(image);
        this.image = image;
        this.repaint();
    }

    @Override
    public Image image() {
        return this.image;
    }

    @Override
    public void on(ImageDisplay.ImageTransition imageTransition) {
        this.imageTransitionListeners.add(imageTransition);
    }

    @Override
    public void on(ImageDisplay.LoadPreview previewLoader) {
        this.previewLoader = previewLoader;
    }

    private void generatePreview(BiFunction<LoadPreview, Image, Image> previewFetcher) {
        if (previewLoader != null) {
            previewImage = previewFetcher.apply(previewLoader, image);
            previewBufferedImage = loadFromDisk(previewImage);
        }
    }

    private static BufferedImage composeImage(BufferedImage originalImage, int targetWidth, int targetHeigth) {
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        java.awt.Image scaledImage;
        if (imageWidth / imageHeight > targetWidth / targetHeigth) {
            scaledImage = originalImage.getScaledInstance(targetWidth, -1, 0);
        } else {
            scaledImage = originalImage.getScaledInstance(-1, targetHeigth, 0);
        }
        BufferedImage result = new BufferedImage(targetWidth, targetHeigth, BufferedImage.TYPE_INT_RGB);
        Graphics2D createGraphics = result.createGraphics();
        createGraphics.drawImage(scaledImage, (targetWidth - scaledImage.getWidth(null)) / 2, (targetHeigth - scaledImage.getHeight(null)) / 2, null);
        return result;
    }

    private BufferedImage loadFromDisk(Image image) {
        try {
            return ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            Logger.getLogger(SwingImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SwingImageDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        private int initial;

        @Override
        public void mousePressed(MouseEvent event) {
            this.initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            if (Math.abs(offset) > getWidth() / 2) {
                imageTransitionListeners.forEach(offset < 0 ? ImageTransition::next : ImageTransition::previous);
            }
            initial = 0;
            offset = 0;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            int newOffset = event.getX() - initial;
            if (newOffset != offset && (offset == 0 || newOffset * offset < 0)) {
                generatePreview(newOffset < 0 ? LoadPreview::getNext : LoadPreview::getPrevious);
            }
            offset = event.getX() - initial;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
    }

}
