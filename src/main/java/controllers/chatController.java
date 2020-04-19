package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.clientModel;
import myclasses.Friend;

import java.io.IOException;

public class chatController {

    private Friend friend;
    private final clientModel clientModel;

    private final Stage chatStage;

    public chatController(clientModel clientModel, Friend friend) throws IOException {
        this.friend = friend;
        this.clientModel = clientModel;

        chatStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/chat.fxml"));
        loader.setController(this);
        chatStage.setScene(new Scene(loader.load()));
        chatStage.setTitle("chatIT");
        chatStage.setResizable(false);
        chatStage.getIcons().add(new Image("/img/icon.png"));
        chatStage.setTitle("chatIT - " + friend.getImie() + " " + friend.getNazwisko());
        chatStage.setOnHiding( event -> {
            clientModel.closeChatWindowForFriend(friend.getId());
        } );
    }

    public void showStage() {
        chatStage.show();
    }

    public void closeStage() {
        chatStage.close();
    }



}
