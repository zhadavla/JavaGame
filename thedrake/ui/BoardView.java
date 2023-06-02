package thedrake.ui;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import thedrake.game_logic.*;

public class BoardView extends GridPane implements TileViewContext {

    private GameState gameState;

    private ValidMoves validMoves;
    private TileView selected;

    public void setCurrentBlue(String currentBlue) {
        this.currentBlue = currentBlue;
    }

    public void setCurrentOrange(String currentOrange) {
        this.currentOrange = currentOrange;
    }

    private String currentBlue;
    private String currentOrange;

    private boolean isPlacing = true;

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
        if (isPlacing) {

            placeFromStack(move);
        }
//        selected.unselect();
//        selected = null;
//        clearMoves();
//        gameState = move.execute(gameState);
//        validMoves = new ValidMoves(gameState);
//        updateTiles();
    }

    public void placeFromStack(Move move) {
        add(new UnitView(currentBlue, PlayingSide.BLUE), move.target().i(), move.target().j() + 3);
        System.out.println(currentBlue);
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

    private TileView tileViewAt(BoardPos target) {
        int index = (3 - target.j()) * 4 + target.i();
        return (TileView) getChildren().get(index);
    }
}