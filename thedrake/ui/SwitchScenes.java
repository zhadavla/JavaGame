package thedrake.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import thedrake.game_logic.Board;
import thedrake.game_logic.BoardTile;
import thedrake.game_logic.PositionFactory;
import thedrake.game_logic.StandardDrakeSetup;

import java.io.IOException;
import java.util.Objects;

import static thedrake.ui.TheDrakeApp.createSampleGameState;


public class SwitchScenes extends Application {
    private Stage stage;
    private Scene scene1;
    private Scene scene2;
    private GameView gameView;

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


        twoPlayersButton.setOnAction(e -> {
            try {
                switchScenes(createScene2());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button exitButton = (Button) root.lookup("#exitButton");
        exitButton.setOnAction(e -> System.exit(0));

        return scene;
    }


    private Scene createScene2() throws IOException {
        Board board = new Board(4);
        PositionFactory positionFactory = board.positionFactory();
        board = board.withTiles(new Board.TileAt(positionFactory.pos(1, 1), BoardTile.MOUNTAIN));


        this.gameView = new GameView(new StandardDrakeSetup().startState(board));
        Scene scene = gameView.getScene();

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