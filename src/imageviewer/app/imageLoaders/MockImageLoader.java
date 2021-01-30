package imageviewer.app.imageLoaders;

import java.util.List;
import imageviewer.model.Image;
import imageviewer.view.ImageLoader;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockImageLoader implements ImageLoader {

    @Override
    public List<Image> load() {
        return IntStream.range(1, 100)
                .mapToObj(v -> "Image" + String.format("%04d", v))
                .map(Image::new)
                .collect(Collectors.toList());
    }

}
