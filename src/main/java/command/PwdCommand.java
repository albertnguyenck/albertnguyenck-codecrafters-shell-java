package command;

public class PwdCommand implements Command {
    private static String currentWorkingDictionary = System.getProperty("user.dir");
    private static final PwdCommand INSTANCE = new PwdCommand();

    private PwdCommand() {}

    public static PwdCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(String prompt) {
        System.out.println(currentWorkingDictionary);
    }

    public static String getCurrentWorkingDictionary() {
        return currentWorkingDictionary;
    }

    public static void setCurrentWorkingDictionary(String currentWorkingDictionary) {
        PwdCommand.currentWorkingDictionary = currentWorkingDictionary;
    }
}
