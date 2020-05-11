package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.WindowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class ServerPropertiesController extends WindowLoader {
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


    public ServerPropertiesController(MutableBoolean isServerRunning, MutableInt port) throws IOException {
        this.isServerRunning = isServerRunning;
        this.port = port;
        
        createWindow(serverPropertiesStage = new Stage(), "/views/serverProperties.fxml",
                "chatIT - Server Properties", "/img/icon.png", this, false);
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
            ServerController serverController = new ServerController(isServerRunning, port);
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