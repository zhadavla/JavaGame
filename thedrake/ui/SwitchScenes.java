package thedrake.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


import static thedrake.ui.TheDrakeApp.createSampleGameState;

public class SwitchScenes extends Application {
    private Stage stage;
    private Scene scene1;

    private Scene scene2;


    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setTitle("The Drake");

        scene1 = createScene1();
        scene2 = createScene2();

        stage.setScene(scene1);

        stage.show();
    }

    private Scene createScene1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("scenes/mainMenu.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("The Drake menu");

        scene.getStylesheets().add(Objects
                .requireNonNull(this.getClass().getResource("scenes/mainMenu.css"))
                .toExternalForm());

        Button twoPlayersButton = (Button) root.lookup("#two_players");
        twoPlayersButton.setOnAction(e -> switchScenes(scene2));

        Button exitButton = (Button) root.lookup("#exitButton");
        exitButton.setOnAction(e -> System.exit(0));

        return scene;
    }

    private Scene createScene2() throws IOException {
        BoardView boardView = new BoardView(createSampleGameState());
        Scene scene = new Scene(boardView);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                switchScenes(scene1);
            }
        });
        return scene;
    }


    public void switchScenes(Scene scene) {
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}
