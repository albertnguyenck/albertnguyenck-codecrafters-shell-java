package command;

public class ExitCommand implements  Command {
    private static final ExitCommand INSTANCE = new ExitCommand();

    private ExitCommand() {}

    public static ExitCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(String prompt) {
        System.exit(0);
    }
}

