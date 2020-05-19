package connections;

import javafx.application.Platform;

import models.ServerModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server implements Runnable{

    private final ServerSocket serverSocket;
    private ArrayList<Socket> clientsSocketsList;
    private ArrayList<ClientThread> clientsThreadsList;
    private ServerModel serverModel;

    public Server(int port, ServerModel serverModel) throws IOException {
        serverSocket = new ServerSocket(port);
        this.serverModel = serverModel;
        clientsSocketsList = new ArrayList<>();
        clientsThreadsList = new ArrayList<>();

        Platform.runLater(() -> {
            serverModel.addCommunicateToServerLogListView("Serwer uruchomiony!");
            serverModel.addCommunicateToServerLogListView("Czekam na połączenia...");
        });
    }

    public void run() {
        try {
            while (true) {
                int connectedUserId;
                final Socket clientSocket = serverSocket.accept();
                clientsSocketsList.add(clientSocket);

                Platform.runLater(() ->
                        serverModel.addCommunicateToServerLogListView("Client "
                        + clientSocket.getRemoteSocketAddress()
                        + " connected")
                );

                ClientThread clientThreadHolderClass = new ClientThread(clientSocket, this);
                Thread clientThread = new Thread(clientThreadHolderClass);
                clientsThreadsList.add(clientThreadHolderClass);
                clientThread.setDaemon(true);
                clientThread.start();

                connectedUserId = clientThreadHolderClass.getConnectedUserId();
                serverModel.addUserToConnectedUsersListView(connectedUserId, clientThread);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clientDisconnected(ClientThread clientThread) {
        Platform.runLater(() -> {
            serverModel.addCommunicateToServerLogListView("Client "
                    + clientThread.getClientSocket().getRemoteSocketAddress()
                    + " disconnected");
        });
        clientsSocketsList.remove(clientsThreadsList.indexOf(clientThread));
        clientsThreadsList.remove(clientThread);
        serverModel.removeUserFromConnectedUsersListView(clientThread.getUserId());
    }
}