package imageviewer.app.console.mock;

import imageviewer.app.imageLoaders.MockImageLoader;
import imageviewer.app.imageDisplayers.MockImageDisplay;
import imageviewer.control.NullCommand;
import imageviewer.control.InitCommand;
import imageviewer.control.ChangeImageCommand;
import imageviewer.control.Command;
import imageviewer.control.ExitCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String,Command> commands = initCommands(new ArrayList<>(), new MockImageDisplay(), new MockImageLoader());
        System.out.println("Commands:");
        System.out.println("<< < > >>  x(exit)  i(init)");
        while(true){
           commands.getOrDefault(scanner.next(), NullCommand.instance).execute();
        }
    }

    private static Map<String, Command> initCommands(List<Image> images, ImageDisplay imageDisplay, final ImageLoader imageLoader) {
        Map<String,Command> commands = new HashMap<>();
        commands.put(">", new ChangeImageCommand(images,imageDisplay,1));
        commands.put("<", new ChangeImageCommand(images,imageDisplay,-1));
        commands.put(">>", new ChangeImageCommand(images,imageDisplay,5));
        commands.put("<<", new ChangeImageCommand(images,imageDisplay,-5));
        commands.put("x", new ExitCommand());
        commands.put("i", new InitCommand(images, imageDisplay, imageLoader));
        return commands;
    }
    
}
