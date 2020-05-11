import connections.HibernateConnection;
import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        openMainWindow();
    }

    private void openMainWindow() throws IOException {
        MainController mainController = new MainController();
        mainController.showStage();

        HibernateConnection hibernateConnection = HibernateConnection.getInstance();
        hibernateConnection.addDatabaseData();
    }



}
