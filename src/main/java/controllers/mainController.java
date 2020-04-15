package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class mainController {
    @FXML
    private ListView<String> friendsListView = new ListView<String>();
    @FXML
    private ObservableList<String> friendsObservableList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");
    @FXML
    private ImageView userStatusImage = new ImageView();
    @FXML
    private MenuItem logoutMenuItem = new MenuItem();
    @FXML
    private MenuItem exitApplicationMenuItem = new MenuItem();
    private final Stage mainStage;

    public mainController() throws IOException {
        mainStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setController(this);
        mainStage.setScene(new Scene(loader.load()));
        mainStage.setTitle("chatIT");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image("/img/icon.png"));
    }

    @FXML
    private void initialize() {
        setAndShowListView();
        logoutMenuItem.setOnAction(event -> {
            try {
                closeMainWindowAndOpenLoginWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        exitApplicationMenuItem.setOnAction(event -> {
            try {
                closeApplication();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void closeMainWindowAndOpenLoginWindow() throws IOException {
        mainStage.close();
        loginController loginController = new loginController();
        loginController.showStage();
    }

    private void closeApplication() throws IOException {
        mainStage.close();
    }

    private void setAndShowListView() {
        friendsListView.setItems(friendsObservableList);
        friendsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> list) {
                return new friendCell();
            }
        });

    }

    private static class friendCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(createStatusImage("off"));
                //setContentDisplay(ContentDisplay.RIGHT);
                setText("Filip Dabrowski");
            }
        }
    }

    private static ImageView createStatusImage(String status) {
        ImageView statusImage = new ImageView();
        switch(status) {
            case "on":
                statusImage.setImage(new Image("/img/online.png"));
                break;
            case "off":
                statusImage.setImage(new Image("/img/offline.png"));
                break;
            case "invisible":
                statusImage.setImage(new Image("/img/invisible.png"));
                break;
            case "brb":
                statusImage.setImage(new Image("/img/brb.png"));
                break;
            default:
                break;
        }
        statusImage.setFitHeight(25);
        statusImage.setFitWidth(25);
        return statusImage;
    }

    public void showStage() {
        mainStage.show();
    }


    private void setUserStatusImage() {
        userStatusImage.setImage(new Image("/sample/img/online.png"));
    }
}
