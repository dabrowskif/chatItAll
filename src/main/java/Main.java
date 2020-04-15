import javafx.application.Application;
import javafx.stage.Stage;
import controllers.loginController;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        openLoginWindow();
    }

    private void openLoginWindow() throws IOException {
        loginController loginController = new loginController();
        loginController.showStage();
    }



}
