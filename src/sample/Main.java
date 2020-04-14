package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controllers.loginController;
import sample.controllers.mainAppController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loginController loginController = new loginController();
        loginController.showStage();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
