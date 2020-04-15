package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {
    private final Stage mainAppStage;

    public mainController() throws IOException {
        mainAppStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/main.fxml"));
        loader.setController(this);
        mainAppStage.setScene(new Scene(loader.load()));
        mainAppStage.setTitle("chatIT");
        mainAppStage.setResizable(false);
    }

    @FXML
    private void initialize() {
    }

    public void showStage() {
        mainAppStage.show();
    }

    public void closeMainWindowAndOpenLoginWindow() throws IOException {
        mainAppStage.close();
        loginController loginController = new loginController();
        loginController.showStage();
    }
}
