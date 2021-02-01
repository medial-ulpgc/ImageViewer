package imageviewer.view;

import imageviewer.model.Image;

public interface ImageDisplay {

    public void show(Image image);

    public Image image();
    public void on(ImageTransition imageTransition);
    
    interface ImageTransition {

        public void next();
        public void previous();
    }
    public void on(LoadPreview loadPreview);
    interface LoadPreview {

        public Image getNext(Image image);
        public Image getPrevious( Image image);
    }
}
