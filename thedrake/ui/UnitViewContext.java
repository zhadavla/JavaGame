package thedrake.ui;

import thedrake.game_logic.*;

import java.util.List;

public interface UnitViewContext {
    void StackViewSelected(StackView stackView);
    void executeMove(Move move);
}
