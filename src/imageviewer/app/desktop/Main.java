package imageviewer.app.desktop;

import imageviewer.app.imageLoaders.FileImageLoader;
import imageviewer.app.imageDisplayers.CanvasImageDisplay;
import imageviewer.control.*;
import imageviewer.model.Image;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public final class Main extends JFrame {

    Main(){
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        final CanvasImageDisplay nameImageDisplay = new CanvasImageDisplay(this);
        
        JMenu jMenu = new JMenu();
        this.add(nameImageDisplay,BorderLayout.CENTER);
        
        this.add(jMenu, BorderLayout.SOUTH);
        List<Image> images = new ArrayList();
        
        jMenu.add(commandButton("<<",new ChangeImageCommand( images, nameImageDisplay, -5) ),BorderLayout.CENTER);
        jMenu.add(commandButton("<",new ChangeImageCommand( images, nameImageDisplay, -1) ),BorderLayout.CENTER);
        jMenu.add(commandButton(">",new ChangeImageCommand( images, nameImageDisplay, 1) ),BorderLayout.CENTER);
        jMenu.add(commandButton(">>",new ChangeImageCommand( images, nameImageDisplay, 5) ),BorderLayout.CENTER);
        jMenu.add(commandButton("Load Images", new InitCommand(images,nameImageDisplay,new FileImageLoader("E:\\testImages")) ),BorderLayout.CENTER);
        jMenu.add(commandButton("Exit", new ExitCommand() ));
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        
    }
    
    private Component commandButton(String string, Command changeImageCommand) {
        JButton jButton = new JButton(string);
        jButton.addActionListener((ActionEvent e)->changeImageCommand.execute());
        jButton.setAlignmentX(TOP_ALIGNMENT);
        
        
        return jButton;
    }
    
}
