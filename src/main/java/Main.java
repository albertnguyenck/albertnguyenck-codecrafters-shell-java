private static final Set<String> SHELL_BUILTINS = Set.of("echo", "type", "exit");

public static void main(String[] args) throws Exception {
    try (Scanner scanner = new Scanner(System.in)) {
        while (true) {
            System.out.print("$ ");
            String prompt = scanner.nextLine().trim();
            String command = prompt.split(" ")[0];
            switch (command) {
                case "echo" -> System.out.println(prompt.split(" ", 2)[1]);
                case "type" -> type(prompt);
                case "exit" -> System.exit(0);
                default -> executeExternalCommand(prompt);
            }
        }
    }
}

private static void type(String prompt) {
    String cmdToCheck = prompt.split(" ", 2)[1];
    if (SHELL_BUILTINS.contains(cmdToCheck)) {
        System.out.println(String.format("%s is a shell builtin", cmdToCheck));
    } else {
        String systemPATH = System.getenv("PATH");
        String[] paths = systemPATH != null ? systemPATH.split(File.pathSeparator) : new String[0];
        for (String path : paths) {
            File file = new File(path, cmdToCheck);
            if (file.exists() && file.canExecute()) {
                System.out.println(String.format("%s is %s", cmdToCheck, file.getAbsolutePath()));
                return;
            }
        }
        System.out.println(String.format("%s not found", cmdToCheck));
    }
}

private static void executeExternalCommand(String prompt) throws IOException, InterruptedException {
    String[] parts = prompt.split(" ");
    String cmdToCheck = parts[0];
    String systemPATH = System.getenv("PATH");
    String[] paths = systemPATH != null ? systemPATH.split(File.pathSeparator) : new String[0];
    for (String path : paths) {
        File file = new File(path, cmdToCheck);
        if (file.exists() && file.canExecute()) {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(parts);
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            process.waitFor();
            System.out.print(output);
            return;
        }
    }

    System.out.println(String.format("%s not found", cmdToCheck));
}
