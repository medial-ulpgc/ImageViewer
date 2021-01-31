package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;

public class MockImageDisplay implements ImageDisplay {

    private Image currentImage;

    @Override
    public void show(Image image) {
        currentImage = image;
        System.out.println(image.getName());
    }

    @Override
    public Image image() {
        return currentImage;
    }

    @Override
    public void on(ImageTransition imageTransition) {
    }

    @Override
    public void on(ImagePreview imageTransition) {
        
    }
}
