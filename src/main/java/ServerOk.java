import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerOk extends DoingAll {
    private static Logger logger = Logger.getLogger(ServerOk.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private static final int AMOUNT = 3;

    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler("logServer.log", true);
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        readReport();
        int port = choice();
        logger.info("server connected on port: " + port);

        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(AMOUNT);

        while (true) {
            executorService.submit(new Thread(() -> {
                while (true) {
                    try (
                            Socket socket = serverSocket.accept();
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
                    ) {
                        String nameOfClient = bufferedReader.readLine();
                        logger.info("Client " + nameOfClient + " connected\n" + "(id: " + Thread.currentThread().getId() + ")");
                        while (true) {
                            printWriter.println("find the area of a figure:1 Cylinder; 2 Cube; 3 Cone.Enter a number or /exit: ");
                            logger.info("request sent to client " + nameOfClient);

                            String str = bufferedReader.readLine();
                            if (str.equalsIgnoreCase("/exit")) {
                                logger.info("the client leaves the server");
                                break;
                            }
                            switch (str) {
                                case "1":
                                    printWriter.println("Enter height and radius(through a space)");
                                    String hr = bufferedReader.readLine();
                                    printWriter.println(searchCylinderArea(hr));
                                    logger.info("sent cylinder area");
                                    break;
                                case "2":
                                    printWriter.println("Enter side");
                                    int a = Integer.parseInt(bufferedReader.readLine());
                                    printWriter.println(searchCubeArea(a));
                                    logger.info("sent cube area");
                                    break;
                                case "3":
                                    printWriter.println("Enter height and radius(through a space)");
                                    String hr2 = bufferedReader.readLine();
                                    printWriter.println(searchConeArea(hr2));
                                    logger.info("sent cone area");
                                    break;
                            }
                        }
                    } catch (IOException e) {
                        logger.info("error");
                        System.out.println(e.getMessage());
                    }
                }
            }));
        }
    }

    public static double searchCylinderArea(String hr) {
        double p = 3.14;
        int[] array = doSplit(hr);
        return p * (array[1] * array[1]) * array[0];
    }

    public static int searchCubeArea(int a) {
        return a * a * a;
    }

    public static double searchConeArea(String hr) {
        int[] array = doSplit(hr);
        return 0.33 * (array[1] * array[1]) * array[0];
    }

    public static int[] doSplit(String str) {
        String[] arrayStr = str.split(" ");
        int[] arrayInt = {Integer.parseInt(arrayStr[0]), Integer.parseInt(arrayStr[1])};
        return arrayInt;
    }
}
