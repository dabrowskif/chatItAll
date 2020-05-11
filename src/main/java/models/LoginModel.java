package models;

import connections.HibernateConnection;
import controllers.LoginController;
import hibernate.entities.User;

public class LoginModel {

    private HibernateConnection hibernateConnection = HibernateConnection.getInstance();

    private LoginController loginController;

    public LoginModel(LoginController loginController) {
        this.loginController = loginController;
    }


    public void setUserProperties() {
        loginController.user.setLogin(loginController.loginField.getText());
        loginController.user.setPassword(loginController.passwordField.getText());
    }

    public User getInsertedUser() {
        return hibernateConnection.findUser(loginController.user);
    }



}
