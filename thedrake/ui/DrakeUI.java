package thedrake.ui;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static thedrake.ui.TheDrakeApp.createSampleGameState;

public class DrakeUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneController sceneController = new SceneController();
        try {
            sceneController.run(stage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
