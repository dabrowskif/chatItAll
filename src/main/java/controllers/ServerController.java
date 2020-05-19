package controllers;

import hibernate.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.ClientModel;
import models.ServerModel;
import models.WindowLoader;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.IOException;

public class ServerController extends WindowLoader {

    @FXML
    public ObservableList<String> serverLogObservableList;
    @FXML
    public ListView<String> serverLogListView;

    @FXML
    public ObservableList<Integer> connectedUsersObservableList;
    @FXML
    public ListView<Integer> connectedUsersListView;

    private final Stage serverStage;
    private ServerModel serverModel;

    private MutableBoolean isServerRunning;
    private final int port;



    public ServerController(MutableBoolean isServerRunning, MutableInt port) throws IOException {
        this.port = port.intValue();
        this.isServerRunning = isServerRunning;
        isServerRunning.setValue(true);
        initializeComponents();

        serverModel = new ServerModel(this,this.port);
        createWindow(serverStage = new Stage(), "/views/server.fxml", "chatIT - server", "/img/icon.png", this, false);
    }

    private void initializeComponents() {
        serverLogObservableList = FXCollections.observableArrayList();
        serverLogListView = new ListView<>(serverLogObservableList);
        connectedUsersObservableList = FXCollections.observableArrayList();
        connectedUsersListView = new ListView<>(connectedUsersObservableList);
        //TODO make cellfactory
        /*connectedUsersListView.setCellFactory(new Callback<ListView<ConnectedUser>, ListCell<ConnectedUser>>() {
            @Override
            public ListCell<ConnectedUser> call(ListView<ConnectedUser> param) {
                return null;
            }
        });*/
    }

    //TODO make cellfactory
    /*private static class ConnectedUser  extends ListCell<User>{
        int id;
        Thread thread;

        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getLogin());
            }
        }
    }*/


    @FXML
    private void initialize() {
        serverStage.setOnHiding( event ->
                isServerRunning.setValue(false));
    }

    public void showStage() {
        serverStage.show();
    }

    public void udpateLogListView() {
        serverLogListView.setItems(serverLogObservableList);
    }

    public void udpateUsersListView() {
        connectedUsersListView.setItems(connectedUsersObservableList);
    }

}