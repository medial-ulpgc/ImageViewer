package imageviewer.control;

public class NullCommand implements Command {

    public static final Command instance = new NullCommand();

    private NullCommand() {
    }

    @Override
    public void execute() {
        //do nothing...
    }

}
