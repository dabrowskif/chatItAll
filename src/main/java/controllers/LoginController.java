package controllers;

import hibernate.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.LoginModel;
import models.WindowLoader;
import sun.plugin2.util.ColorUtil;

import java.io.IOException;

public class LoginController extends WindowLoader {
    @FXML
    private Button loginButton;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Label errorLabel;

    private final Stage loginStage;
    private final int port;

    public User user = new User();
    private LoginModel loginModel;

    public LoginController(int port) throws IOException {
        this.port = port;
        createWindow(loginStage = new Stage(), "/views/login.fxml",
                "Login to chatIT", "/img/icon.png", this, false);

    }

    @FXML
    private void initialize() {
        loginModel = new LoginModel(this);

        loginButton.setOnAction(event -> {
            loginModel.setUserProperties();

            user = loginModel.getInsertedUser();

            if(user.getId() != 0)
                openClientWindowAndCloseLoginWindow();
            else
                showLoginErrorLabel();
        });
    }

    public void openClientWindowAndCloseLoginWindow(){
        try {
            loginStage.close();
            ClientController clientController = new ClientController(port, user.getId());
            clientController.showStage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoginErrorLabel() {
        errorLabel.setText("Zły login lub hasło!");
        errorLabel.setTextFill(Color.RED);
    }

    public void showStage() {
        loginStage.show();
    }



}
