private static final Set<String> SHELL_BUILTINS = Set.of("echo", "type", "exit");

public static void main(String[] args) throws Exception {
    try (Scanner scanner = new Scanner(System.in)) {
        while (true) {
            System.out.print("$ ");
            String prompt = scanner.nextLine().trim();
            String command = prompt.split(" ")[0];
            switch (command) {
                case "echo" -> System.out.println(prompt.split(" ", 2)[1]);
                case "type" -> {
                    type(prompt);
                }
                case "exit" -> System.exit(0);
                default -> System.out.println(String.format("%s: command not found", prompt));
            }
        }
    }
}

private static int type(String prompt) {
    String cmdToCheck = prompt.split(" ", 2)[1];
    if (SHELL_BUILTINS.contains(cmdToCheck)) {
        System.out.println(String.format("%s is a shell builtin", cmdToCheck));
        return 0;
    } else {
        String systemPATH = System.getenv("PATH");
        String[] paths = systemPATH != null ? systemPATH.split(File.pathSeparator) : new String[0];
        for (String path : paths) {
            File file = new File(path, cmdToCheck);
            if (file.exists() && file.canExecute()) {
                System.out.println(String.format("%s is %s", cmdToCheck, file.getAbsolutePath()));
                return 0;
            }
        }
        System.out.println(String.format("%s not found", cmdToCheck));
    }

    return 0;
}
