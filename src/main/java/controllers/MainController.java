package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.WindowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class MainController extends WindowLoader {
    @FXML
    private Button clientButton;
    @FXML
    private Button serverButton;
    @FXML
    private Label errorLabel;


    private final Stage mainStage;public MutableBoolean isServerRunning;
    public MutableInt port;

    public MainController() throws IOException {
        isServerRunning = new MutableBoolean(false);
        port = new MutableInt(50000);

        mainStage = new Stage();
        createWindow(mainStage, "/views/main.fxml", "chatIT",
                "/img/icon.png", this, false);
    }

    @FXML
    private void initialize() {
        clientButton.setOnAction(event -> openLoginWindow());
        serverButton.setOnAction(event -> openServerPropertiesWindow());
    }

    private void openLoginWindow() {
        try {
            if(!checkIfServerIsRunning()) {
                errorLabel.setText("Serwer nie został włączony!");
                errorLabel.setTextFill(Color.RED);
            }
            else {
                errorLabel.setText("");
                LoginController loginController = new LoginController(port.getValue());
                loginController.showStage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openServerPropertiesWindow() {
        try {
            if(checkIfServerIsRunning()) {
                //TODO make it popup
                System.out.println("Serwer już działa!");
            }
            else {
                ServerPropertiesController serverPropertiesController = new ServerPropertiesController(isServerRunning, port);
                serverPropertiesController.showStage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfServerIsRunning() {
        return isServerRunning.getValue();
    }

    public void showStage() {
        mainStage.show();
    }

}
