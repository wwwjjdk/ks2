import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerOk extends DoingAll {
    private static ConcurrentHashMap<Integer, Socket> concurrentHashMap = new ConcurrentHashMap<>();
    private static Logger logger = Logger.getLogger(ServerOk.class.getName());
    private static final int AMOUNT = 5;

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT);

        logger.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler("logServer.log", true);
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        readReport();

        int port = choice();
        logger.info("server connected on port: " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                final var clientSocket = serverSocket.accept();
                logger.info("Клиент подключился");
                executorService.submit(new Thread(() -> {
                    try (
                            final var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            final var out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                    ) {
                        concurrentHashMap.put(clientSocket.getLocalPort(), clientSocket);
                        logger.info("Клиент добавлен в мапу");
                        out.println("Введите имя:");

                        String name = in.readLine();
                        logger.info("Пользователь: " + name + "\nПорт:" + clientSocket.getPort());
                        while (true) {
                            out.println("Введите сообщение");
                            String exitOrNo = in.readLine();
                            if (exitOrNo.equalsIgnoreCase("/exit")) {
                                logger.info("Пользователь вышел");
                                concurrentHashMap.remove(clientSocket.getLocalPort());
                                break;
                            }
                            sendAll(exitOrNo, out);
                        }

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sendAll(String message, PrintWriter out) {
        concurrentHashMap.forEach((k, y) -> {
            System.out.println("k="+k+", y="+y);
            out.println(message);
        });
    }
}
