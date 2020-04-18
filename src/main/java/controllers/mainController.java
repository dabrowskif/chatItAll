package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {
    @FXML
    private Button clientButton;
    @FXML
    private Button serverButton;

    private final Stage mainStage;
    private int port = 50000;

    public mainController() throws IOException {
        mainStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
        loader.setController(this);
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle("chatIT");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {
        clientButton = new Button();
        serverButton = new Button();
        port = 50000;
    }

    @FXML
    private void initialize() {
        clientButton.setOnAction(event -> {
            openLoginWindow();
        });

        serverButton.setOnAction(event -> {
            openServerPropertiesWindow();
        });
    }

    private void openLoginWindow() {
        try {
            loginController loginController = new loginController(port);
            loginController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openServerPropertiesWindow() {
        serverPropertiesController serverPropertiesController = null;
        try {
            serverPropertiesController = new serverPropertiesController(this);
            serverPropertiesController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void showStage() {
        mainStage.show();
    }

}
