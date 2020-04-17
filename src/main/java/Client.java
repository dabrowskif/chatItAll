import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;


public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Podaj IP jako argument!");
            return;
        }
        Socket socket = new Socket(args[0], 59090);
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Odpowiedź od serwera: " + in.nextLine());
    }
}