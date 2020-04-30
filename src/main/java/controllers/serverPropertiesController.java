package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.windowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.xml.soap.Text;
import java.io.IOException;

public class serverPropertiesController extends windowLoader {
    @FXML
    Button startServerButton;
    @FXML
    RadioButton defaultRadioButton;
    @FXML
    RadioButton customRadioButton;
    @FXML
    TextField portTextField;

    private final Stage serverPropertiesStage;
    private MutableInt port;
    private MutableBoolean isServerRunning;


    public serverPropertiesController(MutableBoolean isServerRunning, MutableInt port) throws IOException {
        this.isServerRunning = isServerRunning;
        this.port = port;
        //initializeComponents();
        
        createWindow(serverPropertiesStage = new Stage(), "/views/serverProperties.fxml",
                "chatIT - Server Properties", "/img/icon.png", this, false);
    }

    private void initializeComponents() {
    }

    @FXML
    private void initialize() {
        startServerButton.setOnAction(event -> {
            setPortValue();
            openServerWindow();
            closeStage();
        });

        defaultRadioButton.setOnAction(event ->
                portTextField.setDisable(true));

        customRadioButton.setOnAction(event ->
                portTextField.setDisable(false));
    }

    private void setPortValue() {
        port.setValue(Integer.parseInt(portTextField.getText()));
    }

    private void openServerWindow() {
        try {
            serverController serverController = new serverController(isServerRunning, port);
            serverController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeStage() {
        serverPropertiesStage.close();
    }

    public void showStage() {
        serverPropertiesStage.show();
    }



}