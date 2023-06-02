package thedrake.ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import thedrake.game_logic.Move;
import thedrake.game_logic.PlayingSide;

import static thedrake.ui.TheDrakeApp.createSampleGameState;

public class GameView {
    private BoardView boardView;
    private StackView stackView;
    private StackView orangeStack;
    private StackView blueStack;
    private boolean isBlueTurn = true;

    private BorderPane root;

    public GameView() {
        this.root = new BorderPane(new BoardView(createSampleGameState()));

        this.blueStack = new StackView(PlayingSide.BLUE);
        root.setBottom(blueStack);

        this.orangeStack = new StackView(PlayingSide.ORANGE);
        root.setTop(orangeStack);
    }

    Scene getScene() {
        return new Scene(root, 800, 800);
    }
}
