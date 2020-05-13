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
    private List<Integer> chatWindowsToCloseList;

    private User user;
    private Client client;


    public ClientModel(ClientController clientController, User user) throws IOException {
        initializeComponents();
        this.clientController = clientController;
        this.user = user;

        refreshUsersObservableList();
        setAndShowUsersListView();
        setUserStatus(ON);
    }

    private void initializeComponents() throws IOException {
        hibernateConnection =  HibernateConnection.getInstance();
        openedChatWindowsMap = new HashMap<>();
        chatWindowsToCloseList = new ArrayList<>();
        user = new User();
        client = new Client(clientController.port, user.getId());
    }


    public void refreshUsersObservableList() {
        clientController.usersObservableList.clear();
        ArrayList<User> friendList= new ArrayList<>(hibernateConnection.getUserFriends(user));
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


    public void setAndShowUsersListView() {
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
        hibernateConnection.setUserStatus(user,statusEnum);
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


}
