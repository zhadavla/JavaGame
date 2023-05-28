package thedrake.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static thedrake.ui.TheDrakeApp.createSampleGameState;

public class SceneController {

    private Parent root;
    private Stage stage;
    private Button twoPlayersButton;

    public void run(Stage stage) throws IOException {
        this.stage = stage;
        switchScene1();

        twoPlayersButton.setOnAction(e -> switchScene2());
    }

    public void switchScene1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DrakeUI.class.getResource("scenes/mainMenu.fxml"));
        root = fxmlLoader.load();
        Scene scene1 = new Scene(root);
        stage.setTitle("The Drake!");
        scene1.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("scenes/mainMenu.css")).toExternalForm());

        twoPlayersButton = (Button) root.lookup("#two_players");

        stage.setScene(scene1);
        stage.show();

        twoPlayersButton.setOnAction(e -> {
            switchScene2();
        });
    }


    public void switchScene2() {
        BoardView boardView = new BoardView(createSampleGameState());
        Scene scene2 = new Scene(boardView);
        stage.setScene(scene2);
        stage.show();

        scene2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    switchScene1();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                scene2.setOnKeyPressed(null); // Disable further Escape key events in scene2
            }
        });
    }

}
