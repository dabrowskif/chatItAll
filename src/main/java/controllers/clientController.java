package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.chatModel;
import models.Friend;
import models.windowLoader;

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
    private MenuItem exitMenuItem;
    @FXML
    private Label portLabel; //test label

    private final int port;
    private final Stage clientStage;
    private chatModel chatModel;



    public clientController(int port) throws IOException {
        initializeComponents();
        this.port = port;
        initializeComponents();
        createWindow(clientStage = new Stage(), "/views/client.fxml",
                "chatIT", "/img/icon.png", this, false);
    }

    private void initializeComponents() {
        friendsObservableList = FXCollections.observableArrayList();
        portLabel = new Label();
    }

    @FXML
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
    }

    private void openLoginWindow() {
        try {
            loginController loginController = new loginController(port);
            loginController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void closeStage(){
        clientStage.close();
    }

    public void showStage() {
        clientStage.show();
    }

}
