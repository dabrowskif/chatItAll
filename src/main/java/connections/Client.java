package connections;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Runnable{
    DataInputStream serverToClientReader;
    DataOutputStream clientToServerWriter;
    Socket socket;
    int userId;


    public Client(int port, int userId) throws IOException {
        this.userId = userId;

        socket = new Socket("127.0.0.1", port);
        serverToClientReader = new DataInputStream(socket.getInputStream());
        clientToServerWriter = new DataOutputStream(socket.getOutputStream());
        sendId();
    }


    private void sendId() throws IOException {
        clientToServerWriter.writeInt(userId);
    }

    public void sendCommandToServer(int recieverUserId, byte commandByte) throws IOException {
        sendWriterUserId();
        sendRecieverUserId(recieverUserId);
        sendCommandCode(commandByte);
    }

    private void sendWriterUserId() throws IOException {
        clientToServerWriter.writeInt(userId);
    }

    private void sendRecieverUserId(int recieverUserId) throws IOException {
        clientToServerWriter.writeInt(recieverUserId);
    }

    private void sendCommandCode(byte commandByte) throws IOException {
        clientToServerWriter.writeByte(commandByte);
    }


    public void run() {
        while (true) {
            try {
                String inputFromServer = serverToClientReader.readUTF();
                Platform.runLater(() -> System.out.println(inputFromServer));
            } catch (SocketException e) {
                Platform.runLater(() ->
                        System.out.println("Awaria serwera!")
                );
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }






}