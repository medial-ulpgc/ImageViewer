package imageviewer.app.imageDisplayers;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.function.Consumer;

public class TitleUpdaterImageDisplay implements ImageDisplay {

    private final ImageDisplay innerImageDisplay;
    private final Consumer<String> titleUpdater;

    public TitleUpdaterImageDisplay(ImageDisplay innerImageDisplay, Consumer<String> titleUpdater) {
        this.innerImageDisplay = innerImageDisplay;
        this.titleUpdater = titleUpdater;
    }

    @Override
    public void show(Image image) {
        innerImageDisplay.show(image);
        titleUpdater.accept(image.getName());
    }

    @Override
    public Image image() {
        return innerImageDisplay.image();
    }

    @Override
    public void on(ImageTransition imageTransition) {
        innerImageDisplay.on(new ImageTransition() {
            @Override
            public void next() {
                imageTransition.next();
                titleUpdater.accept(innerImageDisplay.image().getName());
            }

            @Override
            public void previous() {
                imageTransition.previous();
                titleUpdater.accept(innerImageDisplay.image().getName());
            }
        });
    }

    @Override
    public void on(LoadPreview loadPreview) {
        innerImageDisplay.on(loadPreview);
    }

}
