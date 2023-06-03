package thedrake.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.w3c.dom.css.CSS2Properties;
import thedrake.game_logic.GameResult;
import thedrake.game_logic.GameState;
import thedrake.game_logic.PlayingSide;

import java.io.IOException;

public class GameView {
    private final Border selectBorder = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private final BoardView boardView;
    private final StackView orangeStack;
    private final StackView blueStack;
    private final BorderPane root;
    private WhoseTurnView whoseTurnView;

    public GameView(GameState gameState) throws IOException {

        this.boardView = new BoardView(gameState);

        this.root = new BorderPane(this.boardView);

        this.blueStack = new StackView(PlayingSide.BLUE);
        blueStack.setOnMouseClicked(e -> onClickBlue());
        root.setBottom(blueStack);

        this.orangeStack = new StackView(PlayingSide.ORANGE);
        orangeStack.setOnMouseClicked(e -> onClickOrange());
        root.setTop(orangeStack);

        this.whoseTurnView = new WhoseTurnView();

        boardView.setWhoseTurnView(whoseTurnView);
        boardView.setStackOrange(orangeStack);
        boardView.setStackBlue(blueStack);

        root.setLeft(whoseTurnView);
    }

    private void onClickBlue() {
        if (boardView.getGameState().sideOnTurn() == PlayingSide.ORANGE
        || boardView.getGameState().result() == GameResult.VICTORY)
            return;

        blueStack.setBorder(selectBorder);

        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();


    }

    private void onClickOrange() {
        if (boardView.getGameState().sideOnTurn() == PlayingSide.BLUE
                || boardView.getGameState().result() == GameResult.VICTORY)
            return;

        orangeStack.setBorder(selectBorder);

        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();


    }

    Scene getScene() {
        return new Scene(root, 800, 800);
    }

    public BorderPane getRoot() {
        return root;
    }
}
