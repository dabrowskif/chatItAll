package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;

public class serverPropertiesController {
    @FXML
    Button startServerButton;
    @FXML
    RadioButton defaultRadioButton;
    @FXML
    RadioButton customRadioButton;
    @FXML
    TextField portTextField;

    private final Stage serverPropertiesStage;
    private final mainController mainController;
    private int port;


    public serverPropertiesController(mainController mainController) throws IOException {
        this.mainController = mainController;

        serverPropertiesStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/serverProperties.fxml"));
        loader.setController(this);
        serverPropertiesStage.setScene(new Scene(loader.load()));
        serverPropertiesStage.setTitle("chatIT - Server Properties");
        serverPropertiesStage.setResizable(false);
        serverPropertiesStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {
        startServerButton = new Button();
        defaultRadioButton = new RadioButton();
        customRadioButton = new RadioButton();
        portTextField = new TextField();
        port = 50000;
    }

    @FXML
    private void initialize() {
        startServerButton.setOnAction(event -> {
            setPortValue();
            sendPortValueToMainWindow();
            openServerWindow();
            closeStage();
        });

        defaultRadioButton.setOnAction(event ->
                portTextField.setDisable(true));

        customRadioButton.setOnAction(event ->
                portTextField.setDisable(false));
    }

    private void setPortValue() {
        port = Integer.parseInt(portTextField.getText());
    }

    private void sendPortValueToMainWindow() {
        mainController.setPort(this.port);
    }

    private void openServerWindow() {
        try {
            serverController serverController = new serverController(port);
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