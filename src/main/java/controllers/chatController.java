package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import myclasses.Friend;

import java.io.IOException;

public class chatController {

    private Friend friend;
    private final Stage chatStage;
    private final mainController mainController;

    public chatController(mainController mainController, Friend friend) throws IOException {
        this.friend = friend;
        this.mainController = mainController;
        chatStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chat.fxml"));
        loader.setController(this);
        chatStage.setScene(new Scene(loader.load()));
        chatStage.setTitle("chatIT");
        chatStage.setResizable(false);
        chatStage.getIcons().add(new Image("/img/icon.png"));
        chatStage.setTitle("chatIT - " + friend.getImie() + " " + friend.getNazwisko());
        chatStage.setOnHiding( event -> {
            mainController.closeChatWindowForFriend(friend);
        } );
    }

    public void showStage() {
        chatStage.show();
    }



}
