import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public abstract class DoingAll {
    private static final String LINE = "setting.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void readReport() {
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(LINE))
        ) {
            while (bufferedReader.ready()) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int choice() {
        int port;
        while (true) {
            System.out.print("Enter the port: ");
            port = Integer.parseInt(scanner.nextLine());
            if (port == 8000) {
                break;
            } else {
                System.out.println("invalid port entered");
            }
        }
        return port;
    }
}
