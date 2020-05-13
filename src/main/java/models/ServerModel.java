package models;

import connections.Server;
import controllers.ServerController;

import java.io.IOException;
import java.util.ArrayList;

public class ServerModel {

    private Server server;
    private final int port;

    public static ArrayList<Thread> threadsList;
    private ServerController serverController;


    public ServerModel(ServerController serverController, int port) throws IOException {
        this.port = port;
        threadsList = new ArrayList<>();
        this.serverController = serverController;
        startServer();
    }

    public void startServer() throws IOException {
        server = new Server(port, this);
        Thread serverThread= (new Thread(server));
        serverThread.setName("chatIT server");
        serverThread.setDaemon(true);
        serverThread.start();
        threadsList.add(serverThread);
    }

    public void addCommunicateToServerLog(String communicat) {
        serverController.serverLogObservableList.add(communicat);
        serverController.udpateLogListView();
    }
}
