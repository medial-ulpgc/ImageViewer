package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NameImageDisplay extends JLabel implements imageviewer.view.ImageDisplay {

    private Image image;
    private final JFrame frameToAdjust;

    public NameImageDisplay(JFrame frameToAdjust) {
        this.frameToAdjust = frameToAdjust;
    }

    @Override
    public void show(Image image) {
        this.image = image;
        this.setText(image.getName());
        this.frameToAdjust.pack();
    }

    @Override
    public Image image() {
        return this.image;
    }

    @Override
    public void on(ImageTransition imageTransition) {
    }

    @Override
    public void on(ImagePreview imageTransition) {
        
    }

}
