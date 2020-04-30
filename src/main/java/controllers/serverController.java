package controllers;

import connections.Server;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.windowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class serverController  extends windowLoader {
    private final Stage serverStage;
    private Server server;

    private MutableBoolean isServerRunning;
    private final MutableInt port;


    public serverController(MutableBoolean isServerRunning, MutableInt port) throws IOException {
        this.port = port;
        this.isServerRunning = isServerRunning;
        initializeComponents();

        createWindow(serverStage = new Stage(), "/views/server.fxml",
                "chatIT - server", "/img/icon.png", this, false);
    }

    private void initializeComponents() {
        isServerRunning.setValue(true);
    }

    @FXML
    private void initialize() {

        //serverStage.setOnHiding( event ->());
    }

    public void showStage() {
        serverStage.show();
    }

}