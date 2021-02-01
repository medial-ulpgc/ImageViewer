package imageviewer.app.desktop;

import imageviewer.app.imageLoaders.FileImageLoader;
import imageviewer.app.imageDisplayers.SwingImageDisplay;
import imageviewer.app.imageDisplayers.TitleUpdaterImageDisplay;
import imageviewer.control.*;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Main extends JFrame {

    private final ImagePresenter imagePresenter;
    private static final String DEFAULT_TITLE = "Image Viewer";

    Main() {
        this.setTitle(DEFAULT_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        ImageDisplay imageDisplay = imageDisplay();
        imageDisplay = updateTitleImageDisplay(imageDisplay);

        List<Image> images = new ArrayList<>();
        this.imagePresenter = new ImagePresenter(images, imageDisplay);
        JPanel jPanel = createCommands(images, imageDisplay);
        this.add(jPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();

    }

    private Component commandButton(String string, Command command) {
        JButton jButton = new JButton(string);
        jButton.addActionListener((ActionEvent e) -> command.execute());
        jButton.setAlignmentX(TOP_ALIGNMENT);
        return jButton;
    }

    private ImageDisplay imageDisplay() {
        final SwingImageDisplay imageDisplay = new SwingImageDisplay();
        getContentPane().add(imageDisplay);
        return imageDisplay;
    }

    private ImageDisplay updateTitleImageDisplay(ImageDisplay imageDisplay) {
        return new TitleUpdaterImageDisplay(imageDisplay, (imageName) -> this.setTitle(DEFAULT_TITLE + " - " + imageName));
    }

    private JPanel createCommands(List<Image> images, final ImageDisplay imageDisplay) {
        JPanel jPanel = new JPanel();

        jPanel.add(commandButton("<<", new ChangeImageCommand(images, imageDisplay, -5)), BorderLayout.CENTER);
        jPanel.add(commandButton("<", new ChangeImageCommand(images, imageDisplay, -1)), BorderLayout.CENTER);
        jPanel.add(commandButton(">", new ChangeImageCommand(images, imageDisplay, 1)), BorderLayout.CENTER);
        jPanel.add(commandButton(">>", new ChangeImageCommand(images, imageDisplay, -5)), BorderLayout.CENTER);
        jPanel.add(commandButton("Load Images", new InitCommand(images, imageDisplay, new FileImageLoader("E:\\testImages"))), BorderLayout.CENTER);
        jPanel.add(commandButton("Exit", new ExitCommand()), BorderLayout.CENTER);
        return jPanel;
    }

}
