package thedrake.game_logic;

import java.io.PrintWriter;

public enum PlayingSide implements JSONSerializable{
    ORANGE, BLUE;

    public void toJSON(PrintWriter writer) {
        writer.printf("\"%s\"" , toString());
    }
}
