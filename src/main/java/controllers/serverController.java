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
<<<<<<< HEAD
    private int port;

    public serverController(int port) throws IOException {
        serverStage = new Stage();
        this.port = port;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/server.fxml"));
        loader.setController(this);
        serverStage.setScene(new Scene(loader.load()));
        serverStage.setTitle("chatIT - Server");
        serverStage.setResizable(false);
        serverStage.getIcons().add(new Image("/img/icon.png"));
    }

    private void initializeComponents() {
=======
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
>>>>>>> mvc-test
    }

    @FXML
    private void initialize() {
<<<<<<< HEAD
=======

        //serverStage.setOnHiding( event ->());
>>>>>>> mvc-test
    }

    public void showStage() {
        serverStage.show();
    }

}