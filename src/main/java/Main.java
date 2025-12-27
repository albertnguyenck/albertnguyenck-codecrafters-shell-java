import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("$ ");
                String prompt = scanner.nextLine();
                String options[] = prompt.split(" ");
                String command = options[0];
                switch (command) {
                    case "echo":
                        System.out.println(prompt.replace("echo ", ""));
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println(String.format("%s: command not found", prompt));
                        break;
                }
            }
        }
    }
}