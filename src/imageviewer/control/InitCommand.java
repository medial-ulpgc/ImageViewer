package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.List;
public class InitCommand implements Command {

    private final List<Image> images;
    private final ImageDisplay imageDisplay;
    private final ImageLoader imageLoader;

    public InitCommand(List<Image> images, ImageDisplay imageDisplay, ImageLoader imageLoader) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageLoader = imageLoader;
    }

    @Override
    public void execute() {
        
        images.clear();
        images.addAll(imageLoader.load());
        if(!images.isEmpty()){
            imageDisplay.show(images.get(0));
        }
    }
}
