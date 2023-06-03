package thedrake.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import thedrake.game_logic.GameState;
import thedrake.game_logic.PlayingSide;

public class GameView {
    private final Border selectBorder = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private final BoardView boardView;
    private final StackView orangeStack;
    private final StackView blueStack;
    private final BorderPane root;

    public GameView(GameState gameState) {
//        this.validMoves = new ValidMoves(gameState);

        this.boardView = new BoardView(gameState);

        this.root = new BorderPane(this.boardView);

        this.blueStack = new StackView(PlayingSide.BLUE);
        blueStack.setOnMouseClicked(e -> onClickBlue());
        root.setBottom(blueStack);

        this.orangeStack = new StackView(PlayingSide.ORANGE);
        orangeStack.setOnMouseClicked(e -> onClickOrange());
        root.setTop(orangeStack);

        boardView.setStackOrange(orangeStack);
        boardView.setStackBlue(blueStack);

        ImageView moveImage = new ImageView(String.valueOf(getClass().getResource("/assets/move.png")));
    }

    private void onClickBlue() {
        if (boardView.getGameState().sideOnTurn() == PlayingSide.ORANGE)
            return;

        blueStack.setBorder(selectBorder);

        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();
    }

    private void onClickOrange() {
        if (boardView.getGameState().sideOnTurn() == PlayingSide.BLUE)
            return;

        orangeStack.setBorder(selectBorder);

        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();
    }

    Scene getScene() {
        return new Scene(root, 800, 800);
    }
}
