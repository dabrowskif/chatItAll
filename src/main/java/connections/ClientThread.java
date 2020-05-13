package connections;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import javafx.application.Platform;

public class ClientThread implements Runnable {

    private final Server server;

    private Socket clientSocket;

    private DataInputStream clientToServerReader;
    private DataOutputStream serverToClientWriter;

    public ClientThread(Socket clientSocket, Server server) {
        this.setClientSocket(clientSocket);
        this.server = server;
        try {
            clientToServerReader = new DataInputStream(clientSocket.getInputStream());
            serverToClientWriter = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            int writerUserId;
            int recieverUserId;
            byte commandCode;
            Platform.runLater(() -> System.out.println("Klient podłączony!"));
            while (true) {
                writerUserId = clientToServerReader.readInt();
                recieverUserId = clientToServerReader.readInt();
                commandCode = clientToServerReader.readByte();
                executeCommand(commandCode);
                System.out.println("Kod polecenia: " + writerUserId + " do " + recieverUserId + " polecenie " + commandCode);
            }
        } catch (IOException e) {
            server.clientDisconnected(this);
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



    private void executeCommand(byte command) {
        switch (command) {
            case 1:
                System.out.println("KOMENDA 1");
                break;
            case 2:
                System.out.println("KOMENDA 2");
                break;
            case 3:
                System.out.println("KOMENDA 3");
                break;
            case 4:
                System.out.println("KOMENDA 4");
                break;
            case 5:
                System.out.println("KOMENDA 5");
                break;
            case 6:
                System.out.println("KOMENDA 6");
                break;
            case 7:
                System.out.println("KOMENDA 7");
                break;
            case 8:
                System.out.println("KOMENDA 8");
                break;
            default:
                break;
        }
    }
}
