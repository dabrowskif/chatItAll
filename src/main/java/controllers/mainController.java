package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import myclasses.Friend;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class mainController {
    @FXML
    private ListView<Friend> friendsListView = new ListView<>();
    @FXML
    private ObservableList<Friend> friendsObservableList = FXCollections.observableArrayList();
    @FXML
    private ImageView userStatusImage = new ImageView();
    @FXML
    private MenuItem logoutMenuItem = new MenuItem();
    @FXML
    private MenuItem exitApplicationMenuItem = new MenuItem();

    private final Stage mainStage;
    private Map<Integer, chatController> windowChatMap= new TreeMap<>();

    public mainController() throws IOException {
        mainStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setController(this);
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle("chatIT");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image("/img/icon.png"));
    }

    public void showStage() {
        mainStage.show();
    }

    @FXML
    private void initialize() {
        addFriendsToObservableList();
        setAndShowListView();

        logoutMenuItem.setOnAction(event -> {
            try {
                closeMainWindowAndOpenLoginWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitApplicationMenuItem.setOnAction(event -> {
            try {
                closeApplication();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //TODO make it double click
        friendsListView.setOnMouseClicked(event -> {
            checkWhichUserGotClickedAndOpenHisChat();
        });

    }

    //adding static friends for testing purposes
    private void addFriendsToObservableList() {
        friendsObservableList.add(new Friend(1, "Filip", "Dąbrowski", "on"));
        friendsObservableList.add(new Friend(2, "Simba", "Minimakaberka", "on"));
        friendsObservableList.add(new Friend(3, "Grupa", "Trubadurów", "on"));
        friendsObservableList.add(new Friend(4, "Maciej", "Lekowski", "off"));
        friendsObservableList.add(new Friend(5, "Weronika", "Kozłowska <3", "on"));
        friendsObservableList.add(new Friend(6, "Błażej", "Kowalski", "invisible"));
        friendsObservableList.add(new Friend(7, "Justyn", "Lekkomyślny", "off"));
        friendsObservableList.add(new Friend(8, "Zdzisław", "Stary", "on"));
        friendsObservableList.add(new Friend(9, "Ryszard", "Ryszewski", "invisible"));
        friendsObservableList.add(new Friend(10, "Mirosław", "Tegoroczny", "off"));
    }

    private void setAndShowListView() {
        friendsListView.setItems(friendsObservableList);
        friendsListView.setCellFactory(new Callback<ListView<Friend>, ListCell<Friend>>() {
            @Override
            public ListCell<Friend> call(ListView<Friend> list) {
                return new friendCell();
            }
        });

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

    private static ImageView createStatusImage(String status) {
        ImageView statusImage = new ImageView();
        switch(status) {
            case "on":
                statusImage.setImage(new Image("/img/online.png"));
                break;
            case "off":
            case "invisible":
                statusImage.setImage(new Image("/img/offline.png"));
                break;
            case "brb":
                statusImage.setImage(new Image("/img/brb.png"));
                break;
            default:
                break;
        }
        statusImage.setFitHeight(25);
        statusImage.setFitWidth(25);
        return statusImage;
    }

    private void closeMainWindowAndOpenLoginWindow() throws IOException {
        mainStage.close();
        loginController loginController = new loginController();
        loginController.showStage();
    }

    private void closeApplication() throws IOException {
        mainStage.close();
    }

    private void checkWhichUserGotClickedAndOpenHisChat() {
        try {
            openChatWindowWithAnotherUser(friendsListView.getSelectionModel().getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openChatWindowWithAnotherUser(Friend friend) throws IOException {
        if(!checkIfChatWindowIsAlreadyOpenWithFriend(friend)) {
            chatController chatController = new chatController(this, friend);
            chatController.showStage();
            windowChatMap.put(friend.getId(), chatController);
        }
    }

    private boolean checkIfChatWindowIsAlreadyOpenWithFriend(Friend friend) {
        if(windowChatMap.containsKey(friend.getId())) {
                return true;
        }
        return false;
    }

    public void closeChatWindowForFriend(Friend friend) {
        windowChatMap.remove(friend.getId());
    }

}
