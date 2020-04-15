package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    private final Stage loginStage;

    public loginController() throws IOException {
        loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/login.fxml"));
        loader.setController(this);
        loginStage.setScene(new Scene(loader.load()));
        loginStage.setTitle("Login to chatITg");
        loginStage.setResizable(false);
    }

    @FXML
    private void initialize() {
    }

    public void showStage() {
        loginStage.show();
    }

    public void openMainWindowAndCloseLoginWindow() throws IOException {
        loginStage.close();
        mainController mainAppController = new mainController();
        mainAppController.showStage();
    }



}
