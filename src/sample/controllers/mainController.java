package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class mainController {
    @FXML
    ListView<String> friendsListView = new ListView<String>();
    @FXML
    ObservableList<String> friendsObservableList = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown", "red", "magenta", "pink", "white", "black", "orange", "green", "purple");

    private final Stage mainAppStage;

    public mainController() throws IOException {
        mainAppStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/fxml/main.fxml"));
        loader.setController(this);
        mainAppStage.setScene(new Scene(loader.load()));
        mainAppStage.setTitle("chatIT");
        mainAppStage.setResizable(false);
    }

    @FXML
    private void initialize() {
        setAndShowListView();   
    }

    private void setAndShowListView() {
        friendsListView.setItems(friendsObservableList);
        friendsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new ColorRectCell();
            }
        });

    }

    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }

    public void showStage() {
        mainAppStage.show();
    }

    private void closeMainWindowAndOpenLoginWindow() throws IOException {
        mainAppStage.close();
        loginController loginController = new loginController();
        loginController.showStage();
    }

}
