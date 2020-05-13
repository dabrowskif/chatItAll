package controllers;

import connections.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.ServerModel;
import models.WindowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class ServerController extends WindowLoader {
    private final Stage serverStage;
    private ServerModel serverModel;

    private MutableBoolean isServerRunning;
    private final int port;

    @FXML
    public ObservableList<String> serverLogObservableList;
    @FXML
    public ListView<String> serverLogListView;


    public ServerController(MutableBoolean isServerRunning, MutableInt port) throws IOException {
        this.port = port.intValue();
        this.isServerRunning = isServerRunning;
        isServerRunning.setValue(true);
        serverLogObservableList= FXCollections.observableArrayList();
        serverLogListView = new ListView<>();

        createWindow(serverStage = new Stage(), "/views/server.fxml", "chatIT - server", "/img/icon.png", this, false);
    }

    @FXML
    private void initialize() throws IOException {
        serverModel = new ServerModel(this,port);

        serverStage.setOnHiding( event ->
                isServerRunning.setValue(false));
    }

    public void showStage() {
        serverStage.show();
    }

    public void udpateLogListView() {
        serverLogListView.setItems(serverLogObservableList);
    }

}