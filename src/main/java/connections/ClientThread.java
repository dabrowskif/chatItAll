package cconnections;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import connections.Server;
import javafx.application.Platform;

public class ClientThread implements Runnable {

    private final Server server;

    private Socket clientSocket;

    private DataInputStream in;
    private DataOutputStream out;

    public ClientThread(Socket clientSocket, Server server) {
        this.setClientSocket(clientSocket);
        this.server = server;
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String inputToServer;
            while (true) {
                // todo m

            }
        } catch (Exception e) {
            server.disconnectClient(this);
            e.printStackTrace();
        }
    }

    /*public void writeToServer(String input) {
        out.writeByte(1);
    }*/

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
