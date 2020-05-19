package models;

import connections.Client;
import connections.HibernateConnection;
import controllers.ChatController;
import controllers.ClientController;
import hibernate.entities.User;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.*;

import static hibernate.entities.User.StatusEnum;
import static hibernate.entities.User.StatusEnum.*;

public class ClientModel {

    private final ClientController clientController;
    private HibernateConnection hibernateConnection;

    private Map<Integer, ChatController> openedChatWindowsMap;
    private ArrayList<Integer> chatWindowsToCloseList;
    //private ArrayList<Thread> threadsList;

    private final int userId;
    private Client client;
    private final int port;

    public ClientModel(ClientController clientController, int userId) throws IOException {
        initializeComponents();
        this.port = clientController.port;
        this.clientController = clientController;
        this.userId = userId;

        refreshFriendsList();
        setUserStatus(ON);
        startClient();
    }

    private void initializeComponents() {
        hibernateConnection =  HibernateConnection.getInstance();
        openedChatWindowsMap = new HashMap<>();
        chatWindowsToCloseList = new ArrayList<>();
    }

    private void startClient() throws IOException {
        client = new Client(port, userId);
        Thread clientThread = new Thread(client);
        clientThread.start();
    }

    public void refreshFriendsList() {
        refreshUsersObservableList();
        refreshUsersListView();
    }

    private void refreshUsersObservableList() {
        clientController.usersObservableList.clear();
        ArrayList<User> friendList= new ArrayList<>(hibernateConnection.getUserFriends(userId));
        clientController.usersObservableList.addAll(friendList);
        sortUsersObservableList();
    }

    private void sortUsersObservableList() {
        FXCollections.sort(clientController.usersObservableList, new StatusComparator());
    }

    static class StatusComparator implements Comparator<User> {
        public int compare(User u1, User u2) {
            return u1.getStatus().compareTo(u2.getStatus());
        }
    }


    private void refreshUsersListView() {
        clientController.usersListView.setItems(clientController.usersObservableList);
        clientController.usersListView.setCellFactory(list -> new UserCell());

    }

    private static class UserCell extends ListCell<User> {
        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(createStatusImage(item.getStatus()));
                setText(item.getLogin());
            }
        }
    }

    public static ImageView createStatusImage(User.StatusEnum status) {
        ImageView statusImage = new ImageView();
        switch(status) {
            case ON:
                statusImage.setImage(new Image("/img/online.png"));
                break;
            case OFF:
            case INVIS:
                statusImage.setImage(new Image("/img/offline.png"));
                break;
            case BRB:
                statusImage.setImage(new Image("/img/brb.png"));
                break;
            default:
                break;
        }
        statusImage.setFitHeight(25);
        statusImage.setFitWidth(25);
        return statusImage;
    }

    public void setUserStatus(StatusEnum statusEnum) {
        hibernateConnection.setUserStatus(userId,statusEnum);
    }

    public void openChatWindow(User user) {
        if(!checkIfSpecificChatWindowIsAlreadyOpened(user)) {
            try {
                ChatController chatController = new ChatController(this, user);
                chatController.showStage();
                openedChatWindowsMap.put(user.getId(), chatController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkIfSpecificChatWindowIsAlreadyOpened(User user) {
        return openedChatWindowsMap.containsKey(user.getId());
    }

    public void closeChatWindow(int userID) {
        openedChatWindowsMap.remove(userID);
    }

    public void closeAllChatWindows() {
        getOpenedChatWindows();
        closeOpenedChatWindows();
    }

    private void getOpenedChatWindows() {
        for(Map.Entry<Integer, ChatController> entry : openedChatWindowsMap.entrySet()) {
            chatWindowsToCloseList.add(entry.getKey());
        }
    }

    private void closeOpenedChatWindows() {
        for(Integer i : chatWindowsToCloseList) {
            openedChatWindowsMap.get(i).closeStage();
            openedChatWindowsMap.remove(i);
        }
        chatWindowsToCloseList.clear();
    }



    public void disconnectFromServer() throws IOException {
        client.sendCommandToServer(0,(byte) 0);
    }


    public void sendMessage() {

    }

    public void sendGroupMessage() {
        //TODO
    }

    public void sendFriendRequest() {

    }

    public void acceptFriendRequest() {

    }

    public void testAddFriend() {
        System.out.println("Przed dodaniem:" + hibernateConnection.getUserFriends(userId));
        hibernateConnection.addUserFriend(userId, 4);
        System.out.println("Po dodaniu:" + hibernateConnection.getUserFriends(userId));
    }



}
