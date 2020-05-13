package connections;

import javafx.application.Platform;

import models.ServerModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server implements Runnable{

    private final ServerSocket serverSocket;
    private ArrayList<Socket> clientsList;
    private ArrayList<ClientThread> clientsThreadsList;
    private ServerModel serverModel;

    public Server(int port, ServerModel serverModel) throws IOException {
        serverSocket = new ServerSocket(port);
        this.serverModel = serverModel;
        clientsList = new ArrayList<>();
        clientsThreadsList = new ArrayList<>();

        Platform.runLater(() -> {
            serverModel.addCommunicateToServerLog("Serwer uruchomiony!");
            serverModel.addCommunicateToServerLog("Czekam na połączenia...");
        });
    }

    public void run() {
        try {
            while (true) {

                final Socket clientSocket = serverSocket.accept();
                clientsList.add(clientSocket);

                Platform.runLater(() ->
                        serverModel.addCommunicateToServerLog("Client "
                        + clientSocket.getRemoteSocketAddress()
                        + " connected")
                );

                ClientThread clientThreadHolderClass =
                        new ClientThread(clientSocket, this);
                Thread clientThread = new Thread(clientThreadHolderClass);
                clientsThreadsList.add(clientThreadHolderClass);
                clientThread.setDaemon(true);
                clientThread.start();

                serverModel.threadsList.add(clientThread);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void clientDisconnected(ClientThread client) {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                serverModel.addCommunicateToServerLog("Client "
                        + client.getClientSocket().getRemoteSocketAddress()
                        + " disconnected");
                clientsList.remove(clientsThreadsList.indexOf(client));
                clientsThreadsList.remove(clientsThreadsList.indexOf(client));
            }
        });
    }
}