package thedrake.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class DrakeUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DrakeUI.class.getResource("src/mainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("The Drake!");
            String css = Objects.requireNonNull(this.getClass().getResource("src/mainMenu.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}