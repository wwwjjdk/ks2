import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends DoingAll{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        readReport();
        int port = choice();
        Socket socket = new Socket("127.0.0.1", port);
        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
        ) {
            System.out.print("Enter the name: ");
            printWriter.println(scanner.nextLine());

            while (true) {
                System.out.println(bufferedReader.readLine());
                //---------------------------------------------------------------
                String str = scanner.nextLine();
                System.out.println("true");
                if(str.equalsIgnoreCase("/exit")){
                    printWriter.println(str);
                    break;
                }
                printWriter.println(str);
                //---------------------------------------------------------------
                System.out.println(bufferedReader.readLine());
                printWriter.println(scanner.nextLine());
                //--------------------------------------------------------------
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
