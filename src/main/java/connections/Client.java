package connections;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    int port;
    int userId;


    public Client(int port, int userId) throws IOException {
        this.port = port;
        this.userId = userId;

        run();
    }

    private void run() throws IOException {
        try {
            socket = new Socket("127.0.0.1", port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeInt(userId);
            out.flush();

        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }
    }






}