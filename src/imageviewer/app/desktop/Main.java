package imageviewer.app.desktop;

import imageviewer.app.imageLoaders.FileImageLoader;
import imageviewer.app.imageDisplayers.SwingImageDisplay;
import imageviewer.control.*;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Main extends JFrame {
    Map<String, Command> commands = new HashMap<>();
    Main() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        ImageDisplay imageDisplay = imageDisplay();
        List<Image> images = new ArrayList();

        JPanel jPanel = createCommands(images, imageDisplay);

        this.add(jPanel, BorderLayout.SOUTH);
        imageDisplay.on(new ImageDisplay.ImageTransition() {
            @Override
            public void next() {
                commands.getOrDefault(">",NullCommand.instance).execute();
            }

            @Override
            public void previous() {
                commands.getOrDefault("<",NullCommand.instance).execute();
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();

    }

    private Component commandButton(String string) {
        JButton jButton = new JButton(string);
        jButton.addActionListener((ActionEvent e) -> commands.getOrDefault(string,NullCommand.instance).execute());
        jButton.setAlignmentX(TOP_ALIGNMENT);
        return jButton;
    }

    private ImageDisplay imageDisplay() {
        final SwingImageDisplay imageDisplay = new SwingImageDisplay();
        getContentPane().add(imageDisplay);
        return imageDisplay;
    }

    private JPanel createCommands(List<Image> images, final ImageDisplay imageDisplay) {
        JPanel jPanel = new JPanel();
        commands.put("<<", new ChangeImageCommand(images, imageDisplay, -5));
        commands.put("<<", new ChangeImageCommand(images, imageDisplay, -5));
        commands.put("<", new ChangeImageCommand(images, imageDisplay, -1));
        commands.put(">", new ChangeImageCommand(images, imageDisplay, 1));
        commands.put(">>", new ChangeImageCommand(images, imageDisplay, 5));
        commands.put("Load Images", new InitCommand(images, imageDisplay, new FileImageLoader("E:\\testImages")));
        commands.put("Exit", new ExitCommand());
        
        jPanel.add(commandButton("<<"), BorderLayout.CENTER);
        jPanel.add(commandButton("<"), BorderLayout.CENTER);
        jPanel.add(commandButton(">"), BorderLayout.CENTER);
        jPanel.add(commandButton(">>"), BorderLayout.CENTER);
        jPanel.add(commandButton("Load Images"), BorderLayout.CENTER);
        jPanel.add(commandButton("Exit"),BorderLayout.CENTER);
        return jPanel;
    }

}
