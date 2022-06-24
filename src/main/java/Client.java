import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Client extends DoingAll{
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) throws IOException {

        logger.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler("logServer.log", true);
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
        readReport();

        int port = choice();
        Socket socket = new Socket("127.0.0.1", port);
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
        ) {
            System.out.println(bufferedReader.readLine());
            printWriter.println(scanner.nextLine());
            logger.info("Клиент залогинился");

            while (true) {
                System.out.println(bufferedReader.readLine());
                String str = scanner.nextLine();
                if(str.equalsIgnoreCase("/exit")){
                    logger.info("Клиент вышел");
                    printWriter.println(str);
                    break;
                }
                printWriter.println(str);
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
