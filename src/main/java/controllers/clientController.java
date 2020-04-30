package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
<<<<<<< HEAD
import javafx.scene.control.ListCell;
=======
>>>>>>> mvc-test
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
<<<<<<< HEAD
import myclasses.Friend;
=======
import models.chatModel;
import models.Friend;
import models.windowLoader;
>>>>>>> mvc-test

import java.io.IOException;

public class clientController  extends windowLoader {
    @FXML
    public ObservableList<Friend> friendsObservableList;
    @FXML
    public ListView<Friend> friendsListView ;
    @FXML
    private ImageView userStatusImageView;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
<<<<<<< HEAD
    private MenuItem exitApplicationMenuItem;
    @FXML
    private Label portLabel;

    private final Stage userWindowStage;
    private final int port;

    private Map<Integer, chatController> chatWindowsMap;
    private List<Integer> chatWindowsToCloseList;

    public clientController(int port) throws IOException {
=======
    private MenuItem exitMenuItem;
    @FXML
    private Label portLabel; //test label

    private final Integer port;
    private final Stage clientStage;
    private chatModel chatModel;



    public clientController(Integer port) throws IOException {
        initializeComponents();
>>>>>>> mvc-test
        this.port = port;
        initializeComponents();

<<<<<<< HEAD
        userWindowStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/client.fxml"));
        loader.setController(this);
        userWindowStage.setScene(new Scene(loader.load()));
        userWindowStage.setTitle("chatIT");
        userWindowStage.setResizable(false);
        userWindowStage.getIcons().add(new Image("/img/icon.png"));
        userWindowStage.setOnHiding( event -> closeAllChatWindows());
=======
        createWindow(clientStage = new Stage(), "/views/client.fxml",
                "chatIT", "/img/icon.png", this, false);
>>>>>>> mvc-test
    }

    private void initializeComponents() {
        friendsObservableList = FXCollections.observableArrayList();
        portLabel = new Label();
    }

    @FXML
<<<<<<< HEAD
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
=======
    private void initialize() {
        portLabel.setText(String.valueOf(port));
        chatModel = new chatModel(this);

        clientStage.setOnHiding( event ->
                chatModel.closeAllChatWindows());

        logoutMenuItem.setOnAction(event -> {
                closeStage();
                openLoginWindow();
        });

        exitMenuItem.setOnAction(event ->
                closeStage());

        //TODO make it double click
        friendsListView.setOnMouseClicked(event ->
                chatModel.openChatWindow(friendsListView.getSelectionModel().getSelectedItem()) );

>>>>>>> mvc-test
    }

    private void openLoginWindow() {
        try {
            loginController loginController = new loginController(port);
            loginController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
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
=======
    private void closeStage(){
        clientStage.close();
    }

    public void showStage() {
        clientStage.show();
>>>>>>> mvc-test
    }

}
