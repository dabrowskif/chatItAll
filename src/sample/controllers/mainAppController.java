package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainAppController {
    private final Stage mainAppStage;
    public int userId;

    public mainAppController() throws IOException {
        mainAppStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/mainApp.fxml"));
        loader.setController(this);
        mainAppStage.setScene(new Scene(loader.load()));
        mainAppStage.setTitle("chatItAll");
        mainAppStage.setResizable(false);
    }

    @FXML
    private void Initialize() {
    }

    public void showStage(){
        mainAppStage.show();
    }

    public void closeMainAppAndOpenLogin() throws IOException {
        mainAppStage.close();
        loginController loginController = new loginController();
        loginController.showStage();
    }
}
