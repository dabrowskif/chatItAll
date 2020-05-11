package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowLoader {

    public void createWindow(Stage stage, String fxml, String title, String icon, Object controller, boolean isResizable) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.getIcons().add(new Image(icon));
        stage.setResizable(isResizable);
    }
}
