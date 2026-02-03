package command;

import java.io.IOException;
import java.util.Set;

public interface Command {
    Set<String> SHELL_BUILTINS = Set.of("echo", "type", "exit", "pwd");

    void execute(String prompt) throws IOException;

    static Command getInstance() {
        throw new RuntimeException("Not implemented");
    }
}
