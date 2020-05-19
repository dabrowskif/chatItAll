package models;

import connections.Server;
import controllers.ServerController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerModel {

    private Server server;
    private final int port;

    public HashMap<Integer, Thread> usersWithThreadsMap;
    private ServerController serverController;


    public ServerModel(ServerController serverController, int port) throws IOException {
        this.port = port;
        usersWithThreadsMap = new HashMap<>();
        this.serverController = serverController;
        startServer();
    }

    public void startServer() throws IOException {
        server = new Server(port, this);
        Thread serverThread= (new Thread(server));
        serverThread.setName("chatIT server");
        serverThread.setDaemon(true);
        serverThread.start();
    }

    public void addCommunicateToServerLogListView(String communicat) {
        serverController.serverLogObservableList.add(communicat);
        serverController.udpateLogListView();
    }

    public void addUserToConnectedUsersListView(int connectedUserId, Thread clientThread) {
       usersWithThreadsMap.put(connectedUserId, clientThread);
       serverController.connectedUsersObservableList.add(connectedUserId);
       serverController.udpateUsersListView();
    }

    public void removeUserFromConnectedUsersListView(int connectedUserId) {
        usersWithThreadsMap.remove(connectedUserId);
        serverController.connectedUsersObservableList.remove((Integer) connectedUserId);
        serverController.udpateUsersListView();
        System.out.println(usersWithThreadsMap);
        System.out.println(serverController.connectedUsersObservableList);
    }
}
