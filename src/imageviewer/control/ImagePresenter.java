package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imagesDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imagesDisplay) {
        this.images = images;
        this.imagesDisplay = imagesDisplay;
    }
    
}
