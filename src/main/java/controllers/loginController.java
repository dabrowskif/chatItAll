package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {
    @FXML
    Button loginButton = new Button();
    private final Stage loginStage;

    public loginController() throws IOException {
        loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
        loader.setController(this);
        loginStage.setScene(new Scene(loader.load()));
        loginStage.setTitle("Login to chatIT");
        loginStage.setResizable(false);
        loginStage.getIcons().add(new Image("/img/icon.png"));
    }

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> {
            try {
                openMainWindowAndCloseLoginWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showStage() {
        loginStage.show();
    }

    public void openMainWindowAndCloseLoginWindow() throws IOException {
        loginStage.close();
        clientController mainAppController = new clientController();
        mainAppController.showStage();
    }



}
