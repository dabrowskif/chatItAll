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
import models.clientModel;
import myclasses.Friend;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class clientController {
    @FXML
    public ListView<Friend> friendsListView ;
    @FXML
    public ObservableList<Friend> friendsObservableList;
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
    private clientModel clientModel;



    public clientController(int port) throws IOException {
        this.port = port;
        initializeComponents();

        clientStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/client.fxml"));
        loader.setController(this);
        clientStage.setScene(new Scene(loader.load()));
        clientStage.setTitle("chatIT");
        clientStage.setResizable(false);
        clientStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {
        friendsListView = new ListView<>();
        userStatusImageView = new ImageView();
        logoutMenuItem = new MenuItem();
        exitMenuItem = new MenuItem();
        friendsObservableList = FXCollections.observableArrayList();
        portLabel = new Label();
    }

    @FXML
    private void initialize()
    {
        portLabel.setText(String.valueOf(port));
        clientModel = new clientModel(this);

        clientStage.setOnHiding( event ->
                clientModel.closeAllChatWindows());

        logoutMenuItem.setOnAction(event -> {
                closeStage();
                openLoginWindow();
        });

        exitMenuItem.setOnAction(event ->
                closeStage());

        //TODO make it double click
        friendsListView.setOnMouseClicked(event ->
                clientModel.openChatWindowWithAnotherUser(friendsListView.getSelectionModel().getSelectedItem()) );

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
