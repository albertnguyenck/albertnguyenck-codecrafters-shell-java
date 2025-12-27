import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("$ ");
                String command = scanner.nextLine();
                switch (command) {
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println(String.format("%s: command not found", command));
                        break;
                }
            }
        }
    }
}