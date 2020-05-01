package connections;

import com.sun.javafx.collections.MappingChange;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private static Map<Integer, PrintWriter> clientsWriterMap = new HashMap<>();




    public Server(int port) throws IOException {
        System.out.print("Serwer zaczął działać");
        ExecutorService clientPool = Executors.newFixedThreadPool(100);
        ServerSocket listener = new ServerSocket(port);
        while(true){
            clientPool.execute(new ClientHandler(listener.accept()));
        }
    }


    private static class ClientHandler implements Runnable {
        private String name;
        private Socket socket;
        private Scanner in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (Exception e) {
                System.out.println(e);
            } finally {

            }
        }
    }

    private void addNewClientToMap() {

    }

    private void manageMessage(int senderID, int recieverID) {
        recieveMessage(senderID, recieverID);
        addMessageToDatabase();
        sendMessage(senderID, recieverID);
    }

    private void recieveMessage(int senderID, int recieverID) {
    }

    private void addMessageToDatabase() {

    }

    private void sendMessage(int senderID, int recieverID) {
    }






}