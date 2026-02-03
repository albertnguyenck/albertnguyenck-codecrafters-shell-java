package command;

import static util.StringUtil.escape;

public class EchoCommand implements Command {
    private static final EchoCommand INSTANCE = new EchoCommand();

    private EchoCommand() {}

    public static EchoCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(String prompt) {
        System.out.println(escape(prompt.split(" ", 2)[1]));
    }
}
