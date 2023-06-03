package thedrake.ui;

import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import thedrake.game_logic.*;

public class BoardView extends GridPane implements TileViewContext {

    private StackView stackBlue;

    private StackView stackOrange;

    private GameState gameState;

    private ValidMoves validMoves;

    private TileView selected;
    private boolean stackPressed = false;

    public void setWhoseTurnView(WhoseTurnView whoseTurnView) {
        this.whoseTurnView = whoseTurnView;
    }

    private WhoseTurnView whoseTurnView;

    public BoardView(GameState gameState) {
        this.gameState = gameState;
        this.validMoves = new ValidMoves(gameState);

        PositionFactory positionFactory = gameState.board().positionFactory();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                BoardPos boardPos = positionFactory.pos(x, 3 - y);
                add(new TileView(boardPos, gameState.tileAt(boardPos), this), x, y);
            }
        }

        setHgap(5);
        setVgap(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
    }

    @Override
    public void tileViewSelected(TileView tileView) {
        if (selected != null && selected != tileView)
            selected.unselect();

        selected = tileView;

        clearMoves();
        showMoves(validMoves.boardMoves(tileView.position()));
    }

    @Override
    public void executeMove(Move move) {
        whoseTurnView.getController().rotate();
        if (stackPressed) {
            if (getGameState().sideOnTurn() == PlayingSide.BLUE) {
                stackBlue.removePlaced(gameState.sideOnTurn());
                stackBlue.setBorder(null);
            } else {
                stackOrange.removePlaced(gameState.sideOnTurn());
                stackOrange.setBorder(null);
            }

            showMoves(validMoves.movesFromStack());
            gameState = move.execute(gameState);
            validMoves = new ValidMoves(gameState);
            clearMoves();
            updateTiles();
            stackPressed = false;
        } else {
            selected.unselect();
            selected = null;
            clearMoves();
            gameState = move.execute(gameState);
            validMoves = new ValidMoves(gameState);
            updateTiles();
        }
    }

    public void setStackBlue(StackView stackBlue) {
        this.stackBlue = stackBlue;
    }

    public void setStackOrange(StackView stackOrange) {
        this.stackOrange = stackOrange;
    }

    public void setIsStackPressed(boolean b) {
        this.stackPressed = b;
    }

    private void updateTiles() {
        for (Node node : getChildren()) {
            TileView tileView = (TileView) node;
            tileView.setTile(gameState.tileAt(tileView.position()));
            tileView.update();
        }
    }

    private void clearMoves() {
        for (Node node : getChildren()) {
            TileView tileView = (TileView) node;
            tileView.clearMove();
        }
    }

    public void showMoves(List<Move> moveList) {
        for (Move move : moveList)
            tileViewAt(move.target()).setMove(move);
    }

    public void showPossibleMoves() {
        showMoves(validMoves.movesFromStack());
    }

    private TileView tileViewAt(BoardPos target) {
        int index = (3 - target.j()) * 4 + target.i();
        return (TileView) getChildren().get(index);
    }

    public GameState getGameState() {
        return gameState;
    }
}