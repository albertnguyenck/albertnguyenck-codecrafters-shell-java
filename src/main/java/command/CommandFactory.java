package command;

public class CommandFactory {
    private CommandFactory() {}


    public static Command getCommand(String commandName) {
        return switch (commandName.toLowerCase()) {
            case "echo" -> EchoCommand.getInstance();
            case "pwd" -> PwdCommand.getInstance();
            case "type" -> TypeCommand.getInstance();
            case "cd" -> CdCommand.getInstance();
            case "exit" -> ExitCommand.getInstance();
            default -> ExternalCommand.getInstance();
        };
    }
}
