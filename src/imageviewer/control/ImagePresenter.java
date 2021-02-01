package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class ImagePresenter {

    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        imageDisplay.on(imageTransition());
        imageDisplay.on(loadPreview());

    }

    private ImageDisplay.ImageTransition imageTransition() {
        return new ImageDisplay.ImageTransition() {
            @Override
            public void next() {
                imageDisplay.show(fetchImage(imageDisplay.image(), 1));
            }

            @Override
            public void previous() {
                imageDisplay.show(fetchImage(imageDisplay.image(), -1));
            }
        };
    }

    Image fetchImage(Image image, int jump) {
        return images.get((jump + images.indexOf(image) + images.size()) % images.size());
    }

    private ImageDisplay.LoadPreview loadPreview() {
        return new ImageDisplay.LoadPreview() {
            @Override
            public Image getNext(Image image) {
                return fetchImage(image, 1);
            }

            @Override
            public Image getPrevious(Image image) {
                return fetchImage(image, -1);
            }
        };
    }
}
