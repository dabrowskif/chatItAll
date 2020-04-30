package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.windowLoader;

import java.io.IOException;

public class loginController  extends windowLoader {
    @FXML
    Button loginButton = new Button();

    private final Stage loginStage;
    private final int port;

    public loginController(int port) throws IOException {
        this.port = port;

        createWindow(loginStage = new Stage(), "/views/login.fxml",
                "Login to chatIT", "/img/icon.png", this, false);
    }

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> openMainWindowAndCloseLoginWindow());
    }

    public void showStage() {
        loginStage.show();
    }

    public void openMainWindowAndCloseLoginWindow(){
        try {
            loginStage.close();
            clientController mainAppController = new clientController(port);
            mainAppController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
