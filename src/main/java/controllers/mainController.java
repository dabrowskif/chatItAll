package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.windowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class mainController extends windowLoader {
    @FXML
    private Button clientButton;
    @FXML
    private Button serverButton;


    private final Stage mainStage;public MutableBoolean isServerRunning;
    public MutableInt port;

    public mainController() throws IOException {
        initializeComponents();


        mainStage = new Stage();
        createWindow(mainStage, "/views/main.fxml", "chatIT",
                "/img/icon.png", this, false);

    }

    private void initializeComponents() {
        isServerRunning = new MutableBoolean(false);
        port = new MutableInt(50000);
    }

    @FXML
    private void initialize() {
        clientButton.setOnAction(event -> openLoginWindow());
        serverButton.setOnAction(event -> openServerPropertiesWindow());
    }

    private void openLoginWindow() {
        try {
            if(!checkIfServerIsRunning()) {
                //TODO make it popup
                System.out.println("Serwer nie został włączony!");
            }
            else {
                loginController loginController = new loginController(port.getValue());
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
                serverPropertiesController serverPropertiesController = new serverPropertiesController(isServerRunning, port);
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
