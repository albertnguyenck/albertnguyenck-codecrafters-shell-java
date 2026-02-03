package command;

import java.io.File;

public class TypeCommand implements Command {
    private static final TypeCommand INSTANCE = new TypeCommand();

    private TypeCommand() {}

    public static TypeCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(String prompt) {
        String cmdToCheck = prompt.split(" ", 2)[1];
        if (SHELL_BUILTINS.contains(cmdToCheck)) {
            System.out.printf("%s is a shell builtin\n", cmdToCheck);
        } else {
            String systemPATH = System.getenv("PATH");
            String[] paths = systemPATH != null ? systemPATH.split(File.pathSeparator) : new String[0];
            for (String path : paths) {
                File file = new File(path, cmdToCheck);
                if (file.exists() && file.canExecute()) {
                    System.out.printf("%s is %s\n", cmdToCheck, file.getAbsolutePath());
                    return;
                }
            }
            System.out.printf("%s not found\n", cmdToCheck);
        }
    }
}
