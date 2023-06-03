package thedrake.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import thedrake.game_logic.BoardPos;
import thedrake.game_logic.Move;
import thedrake.game_logic.Tile;

import java.util.Objects;

public class TileView extends Pane {

    private final BoardPos boardPos;

    private Tile tile;

    private final TileBackgrounds backgrounds = new TileBackgrounds();

    private final Border selectBorder = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));

    private final TileViewContext tileViewContext;
    private Move move;

    private final ImageView moveImage;

    public TileView(BoardPos boardPos, Tile tile, TileViewContext tileViewContext) {
        this.boardPos = boardPos;
        this.tile = tile;
        this.tileViewContext = tileViewContext;

        setPrefSize(100, 100);
        update();

        setOnMouseClicked(e -> onClick());

        moveImage = new ImageView(Objects.requireNonNull(getClass().getResource("/assets/move.png")).toString());
        moveImage.setVisible(false);
        getChildren().add(moveImage);
    }

    private void onClick() {
        if (move != null) {
            tileViewContext.executeMove(move);
        } else if (tile.hasTroop())
            select();
    }

    public void select() {
        setBorder(selectBorder);
        tileViewContext.tileViewSelected(this);
    }

    public void unselect() {
        setBorder(null);
    }

    public void update() {
        setBackground(backgrounds.get(tile));
    }

    public void setMove(Move move) {
        this.move = move;
        moveImage.setVisible(true);

    }

    public void clearMove() {
        this.move = null;
        moveImage.setVisible(false);
    }

    public BoardPos position() {
        return boardPos;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

}
