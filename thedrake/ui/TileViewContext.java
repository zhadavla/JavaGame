package thedrake.ui;

import thedrake.game_logic.Move;

public interface TileViewContext {

    void tileViewSelected(TileView tileView);

    void executeMove(Move move);

}
