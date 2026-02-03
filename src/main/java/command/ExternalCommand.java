package command;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static util.StringUtil.escapeToList;

public class ExternalCommand implements Command {
    private static final ExternalCommand INSTANCE = new ExternalCommand();

    private ExternalCommand() {}

    public static ExternalCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(String prompt) throws IOException {
        String[] parts = prompt.split(" ");
        String cmdToCheck = parts[0];
        String systemPATH = System.getenv("PATH");
        String[] paths = systemPATH != null ? systemPATH.split(File.pathSeparator) : new String[0];
        for (String path : paths) {
            File file = new File(path, cmdToCheck);
            if (file.exists() && file.canExecute()) {
                ProcessBuilder processBuilder = new ProcessBuilder();
                if ("cat".equals(cmdToCheck)) {
                    List<String> argList = escapeToList(prompt.split(" ", 2)[1]);
                    parts = new String[argList.size() + 1];
                    parts[0] = "cat";
                    for (int i = 0; i < argList.size(); i++) {
                        parts[i + 1] = argList.get(i);
                    }
                }
                processBuilder.command(parts);
                Process process = processBuilder.start();
                StringBuilder output = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                }

                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Process was interrupted");
                }
                System.out.print(output);
                return;
            }
        }

        System.out.printf("%s: command not found\n", cmdToCheck);
    }
}
