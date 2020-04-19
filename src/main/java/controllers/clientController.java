package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import myclasses.Friend;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class clientController {
    @FXML
    private ListView<Friend> friendsListView ;
    @FXML
    private ObservableList<Friend> friendsObservableList;
    @FXML
    private ImageView userStatusImageView;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private MenuItem exitApplicationMenuItem;
    @FXML
    private Label portLabel;

    private final Stage userWindowStage;
    private final int port;

    private Map<Integer, chatController> chatWindowsMap;
    private List<Integer> chatWindowsToCloseList;

    public clientController(int port) throws IOException {
        this.port = port;
        initializeComponents();

        userWindowStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/client.fxml"));
        loader.setController(this);
        userWindowStage.setScene(new Scene(loader.load()));
        userWindowStage.setTitle("chatIT");
        userWindowStage.setResizable(false);
        userWindowStage.getIcons().add(new Image("/img/icon.png"));
        userWindowStage.setOnHiding( event -> closeAllChatWindows());
    }
    private void initializeComponents() {
        friendsListView = new ListView<>();
        userStatusImageView = new ImageView();
        logoutMenuItem = new MenuItem();
        exitApplicationMenuItem = new MenuItem();
        chatWindowsMap = new TreeMap<>();
        chatWindowsToCloseList = new ArrayList<>();
        friendsObservableList = FXCollections.observableArrayList();
        portLabel = new Label();
    }

    private void closeAllChatWindows() {
        getOpenedChatWindows();
        closeOpenedChatWindows();
    }

    private void getOpenedChatWindows() {
        for(Entry<Integer, chatController> entry : chatWindowsMap.entrySet()) {
            chatWindowsToCloseList.add(entry.getKey());
        }
    }

    private void closeOpenedChatWindows() {
        for(Integer i : chatWindowsToCloseList) {
            chatWindowsMap.get(i).closeStage();
            chatWindowsMap.remove(i);
        }
        chatWindowsToCloseList.clear();
    }

    public void showStage() {
        userWindowStage.show();
    }

    @FXML
    private void initialize()
    {
        portLabel.setText(String.valueOf(port));
        addFriendsToObservableList();
        setAndShowListView();

        logoutMenuItem.setOnAction(event -> {
                closeMainWindow();
                openLoginWindow();
        });

        exitApplicationMenuItem.setOnAction(event ->
                closeMainWindow());

        //TODO make it double click
        friendsListView.setOnMouseClicked(event ->
                openChatWindowWithAnotherUser(friendsListView.getSelectionModel().getSelectedItem()) );

    }

    //adding static friends for testing purposes
    private void addFriendsToObservableList() {
        friendsObservableList.add(new Friend(1, "Filip", "Dąbrowski", Friend.UserStatusEnum.ON));
        friendsObservableList.add(new Friend(2, "Simba", "Minimakaberka", Friend.UserStatusEnum.ON));
        friendsObservableList.add(new Friend(3, "Grupa", "Trubadurów", Friend.UserStatusEnum.BRB));
        friendsObservableList.add(new Friend(4, "Maciej", "Lekowski", Friend.UserStatusEnum.OFF));
        friendsObservableList.add(new Friend(5, "Weronika", "Kozłowska <3", Friend.UserStatusEnum.ON));
        friendsObservableList.add(new Friend(6, "Błażej", "Kowalski", Friend.UserStatusEnum.OFF));
        friendsObservableList.add(new Friend(7, "Justyn", "Lekkomyślny", Friend.UserStatusEnum.INVIS));
        friendsObservableList.add(new Friend(8, "Zdzisław", "Stary", Friend.UserStatusEnum.OFF));
        friendsObservableList.add(new Friend(9, "Ryszard", "Ryszewski", Friend.UserStatusEnum.OFF));
        friendsObservableList.add(new Friend(10, "Mirosław", "Tegoroczny", Friend.UserStatusEnum.OFF));
        sortFriendsObservableList();
    }

    private void sortFriendsObservableList() {
        FXCollections.sort(friendsObservableList, new StatusComparator());
    }

    static class StatusComparator implements Comparator<Friend> {
        public int compare(Friend f1, Friend f2) {
            return f1.getStatus().compareTo(f2.getStatus());
        }
    }

    private void setAndShowListView() {
        friendsListView.setItems(friendsObservableList);
        friendsListView.setCellFactory(list -> new friendCell());

    }

    private static class friendCell extends ListCell<Friend> {
        @Override
        public void updateItem(Friend item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(createStatusImage(item.getStatus()));
                setText(item.getImie() + " " + item.getNazwisko());
            }
        }
    }

    private static ImageView createStatusImage(Friend.UserStatusEnum status) {
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

    private void closeMainWindow(){
        userWindowStage.close();
    }

    private void openLoginWindow() {
        try {
            loginController loginController = new loginController(port);
            loginController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void openChatWindowWithAnotherUser(Friend friend) {
        if(!checkIfChatWindowIsAlreadyOpenWithFriend(friend)) {
            try {
                chatController chatController = new chatController(this, friend);
                chatController.showStage();
                chatWindowsMap.put(friend.getId(), chatController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkIfChatWindowIsAlreadyOpenWithFriend(Friend friend) {
        return chatWindowsMap.containsKey(friend.getId());
    }

    public void closeChatWindowForFriend(int userID) {
        chatWindowsMap.remove(userID);
    }

}
