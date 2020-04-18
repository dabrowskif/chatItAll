package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class serverPropertiesController {
    @FXML
    Button startServerButton;

    private final Stage serverPropertiesStage;
    private final mainController mainController;
    private int port;


    public serverPropertiesController(mainController mainController) throws IOException {
        this.mainController = mainController;

        serverPropertiesStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/serverProperties.fxml"));
        loader.setController(this);
        serverPropertiesStage.setScene(new Scene(loader.load()));
        serverPropertiesStage.setTitle("chatIT - Server Properties");
        serverPropertiesStage.setResizable(false);
        serverPropertiesStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {
        startServerButton = new Button();
    }

    @FXML
    private void initialize() {
        startServerButton.setOnAction(event -> {
            sendPortValueToMainWindow();
            openServerWindow();
            closeStage();
        });
    }

    private void sendPortValueToMainWindow() {
        mainController.setPort(this.port);
    }

    private void openServerWindow() {
        try {
            serverController serverController = new serverController();
            serverController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        serverPropertiesStage.show();
    }

    private void closeStage() {
        serverPropertiesStage.close();
    }


}