package connections;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner in;
    PrintWriter out;
    Socket socket;
    int port;


    public Client(int port) throws IOException {
        this.port = port;
    }

    private void run() throws IOException {
        try {
            socket = new Socket("127.0.0.1", port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = in.nextLine();

            }
        } finally {

        }
    }

    private void sendMessageTo(int userId, String message) {

    }




}