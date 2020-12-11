package imageviewer.control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class ChangeImageCommand implements Command{

    private final List<Image> images;
    private final ImageDisplay imageDisplay;
    private final int jumpSize;

    public ChangeImageCommand(List<Image> images, ImageDisplay imageDisplay, int jumpSize) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.jumpSize = jumpSize;
    }
    
    @Override
    public void execute() {
        if(!images.isEmpty()){
            int index = images.indexOf(imageDisplay.image());
            imageDisplay.show(images.get((images.size() + index + jumpSize % images.size())%images.size()));
        }
    }
    
}
