package controllers;

import hibernate.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.ClientModel;
import models.WindowLoader;

import java.io.IOException;

public class ChatController extends WindowLoader {

    @FXML
    public ObservableList <String> messagesObservableList;
    @FXML
    public ListView<String> messagesListView;
    @FXML
    private TextArea typingTextArea;
    @FXML
    private Button sendButton;



    private User user;
    private ClientModel clientModel;
    private Stage chatStage;

    public ChatController(ClientModel clientModel, User user) throws IOException {
        initializeComponents();
        this.user = user;
        this.clientModel = clientModel;

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

        chatStage.setOnHiding(event ->
                clientModel.closeChatWindow(user.getId())
        );
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
