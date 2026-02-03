package command;

import java.io.File;
import java.io.IOException;

public class CdCommand implements Command {
    private static final CdCommand INSTANCE = new CdCommand();

    private CdCommand() {}

    @Override
    public void execute(String prompt) {
        String[] parts = prompt.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("cd: missing operand");
        } else {
            String path = parts[1];
            String currentWorkingDictionary = PwdCommand.getCurrentWorkingDictionary();
            if (path.equals("~")) {
                PwdCommand.setCurrentWorkingDictionary(System.getenv("HOME"));
            } else {
                File dir = path.startsWith("/") ? new File(path) : new File(currentWorkingDictionary, path);
                if (dir.exists() && dir.isDirectory()) {
                    try {
                        PwdCommand.setCurrentWorkingDictionary(dir.getCanonicalPath());
                    } catch (IOException e) {
                        System.out.printf("cd: %s: Error retrieving canonical path\n", parts[1]);
                    }
                } else {
                    System.out.printf("cd: %s: No such file or directory\n", parts[1]);
                }
            }
        }
    }

    public static Command getInstance() {
        return INSTANCE;
    }
}
