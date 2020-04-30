package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.Friend;
import models.chatModel;
import models.windowLoader;

import java.io.IOException;

public class chatController extends windowLoader {

    @FXML
    public ObservableList <String> messagesObservableList;
    @FXML
    public ListView<String> messagesListView;
    @FXML
    private TextArea typingTextArea;
    @FXML
    private Button sendButton;



    private Friend friend;
    private chatModel chatModel;
    private Stage chatStage;

    public chatController(chatModel chatModel, Friend friend) throws IOException {
        initializeComponents();
        this.friend = friend;
        this.chatModel = chatModel;

        createWindow(chatStage = new Stage(), "/views/chat.fxml",
                "chatIT - Priv", "/img/icon.png", this, false);
    }

    private void initializeComponents() {
        messagesObservableList = FXCollections.observableArrayList();
    }

    @FXML
    private void initialize() {
        typingTextArea.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)) {
                if(typingTextArea.getText() != null) {
                    sendMessage();
                    clearTypingTextField();
                }
            }
        });
        sendButton.setOnAction(event -> {
            sendMessage();
            clearTypingTextField();
        });

    }

    private void sendMessage() {

    }

    private void clearTypingTextField() {
        typingTextArea.setText("");
    }



    public void showStage() {
        chatStage.show();
    }

    public void closeStage() {
        chatStage.close();
    }



}
