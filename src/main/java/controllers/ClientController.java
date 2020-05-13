package controllers;

import hibernate.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.ClientModel;
import models.WindowLoader;

import java.io.IOException;

import static hibernate.entities.User.StatusEnum.OFF;

public class ClientController extends WindowLoader {
    @FXML
    public ObservableList<User> usersObservableList;
    @FXML
    public ListView<User> usersListView;
    @FXML
    private ImageView userStatusImageView;
    @FXML
    private MenuItem logoutMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private Label portLabel; //test label

    public final int port;
    private final Stage clientStage;
    private User user;
    private ClientModel clientModel;


    public ClientController(int port, User user) throws IOException {
        usersObservableList = FXCollections.observableArrayList();
        this.port = port;
        this.user = user;

        createWindow(clientStage = new Stage(), "/views/client.fxml",
                "chatIT", "/img/icon.png", this, false);
    }
    @FXML
    private void initialize() throws IOException {
        portLabel.setText(String.valueOf(port));
        clientModel = new ClientModel(this, user);

        clientStage.setOnHiding( event -> {
            clientModel.closeAllChatWindows();
            clientModel.setUserStatus(OFF);
        });

        logoutMenuItem.setOnAction(event -> {
                closeStage();
                openLoginWindow();
        });

        exitMenuItem.setOnAction(event ->
                closeStage());

        //TODO make it double click
        usersListView.setOnMouseClicked(event -> {
            clientModel.openChatWindow(usersListView.getSelectionModel().getSelectedItem());
        });
    }

    private void openLoginWindow() {
        try {
            LoginController loginController = new LoginController(port);
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
