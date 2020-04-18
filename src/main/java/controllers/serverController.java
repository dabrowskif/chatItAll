package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class serverController {
    private final Stage serverStage;
    private int port;

    public serverController() throws IOException {
        serverStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/server.fxml"));
        loader.setController(this);
        serverStage.setScene(new Scene(loader.load()));
        serverStage.setTitle("chatIT - Server");
        serverStage.setResizable(false);
        serverStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {

    }

    @FXML
    private void initialize() {
        /*clientButton.setOnAction(event -> {
            try {
                openClientWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void showStage() {
        serverStage.show();
    }

}