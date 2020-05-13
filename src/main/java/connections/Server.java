package connections;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import cconnections.ClientThread;
import models.ServerModel;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Server implements Runnable{

    private ServerSocket serverSocket;
    private ArrayList<Socket> clientsList;
    private ArrayList<ClientThread> clientsThreadsList;
    private ServerModel serverModel;

    public Server(int port, ServerModel serverModel) throws IOException {
        serverSocket = new ServerSocket(port);
        this.serverModel = serverModel;
        clientsList = new ArrayList<>();
        clientsThreadsList = new ArrayList<ClientThread>();
    }

    public void run() {
        try {
            while (true) {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverModel.addCommunicateToServerLog("Serwer uruchomiony!");
                        serverModel.addCommunicateToServerLog("Czekam na połączenia...");
                    }
                });

                final Socket clientSocket = serverSocket.accept();
                clientsList.add(clientSocket);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        serverModel.addCommunicateToServerLog("Client "
                                + clientSocket.getRemoteSocketAddress()
                                + " connected");
                    }
                });

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



    public void disconnectClient(ClientThread client) {

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

    private void executeCommand(byte command) {
        switch (command) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                break;
        }
    }




}