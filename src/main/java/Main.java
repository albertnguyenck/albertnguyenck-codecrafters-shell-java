import command.Command;
import command.CommandFactory;

void main(String[] args) throws Exception {
    try (Scanner scanner = new Scanner(System.in)) {
        while (true) {
            System.out.print("$ ");
            String prompt = scanner.nextLine().trim();
            if (prompt.trim().isEmpty()) {
                continue;
            }

            String command = prompt.split(" ")[0];
            Command cmd = CommandFactory.getCommand(command);
            cmd.execute(prompt);
        }
    }
}
