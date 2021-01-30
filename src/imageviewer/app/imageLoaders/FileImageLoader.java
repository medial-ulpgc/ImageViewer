package imageviewer.app.imageLoaders;

import imageviewer.model.Image;
import imageviewer.view.ImageLoader;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;

public class FileImageLoader implements ImageLoader {

    private final String route;
    private final Set<String> validExtensions = new HashSet<>(Arrays.asList("png", "jpg", "jpeg", "bmp"));

    public FileImageLoader(String route) {
        this.route = route;
    }

    @Override
    public List<Image> load() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select a Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }
        File fileroute = chooser.getSelectedFile();
        File file = new File(fileroute.getAbsolutePath());
        System.out.println(Arrays.toString(file.list()));
        return Arrays.stream(file.list())
                .filter(this::withValidExtension)
                .map(filename -> fileroute + "\\" + filename)
                .map(File::new)
                .filter(File::isFile)
                .map(File::getAbsolutePath)
                .map(Image::new)
                .collect(Collectors.toList());

    }

    private boolean withValidExtension(String t) {
        return validExtensions.contains(t.substring(t.lastIndexOf(".") + 1).toLowerCase());
    }
}
