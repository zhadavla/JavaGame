package thedrake.game_logic;

import java.io.PrintWriter;

public enum GameResult implements JSONSerializable {
    VICTORY, DRAW, IN_PLAY;

    @Override
    public void toJSON(PrintWriter writer) {
        if (this == VICTORY)
            writer.print("\"" + "VICTORY" + "\"");
        else if (this == DRAW)
            writer.print("\"result\": \"" + "DRAW" + "\"");
        else if (this == IN_PLAY)
            writer.print("\"" + "IN_PLAY" + "\"");
    }
}
