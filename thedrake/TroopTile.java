package thedrake;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TroopTile implements Tile, JSONSerializable {

    private final Troop troop;
    private final PlayingSide side;
    private final TroopFace face;

    public TroopTile(Troop troop, PlayingSide side, TroopFace face) {
        this.troop = troop;
        this.side = side;
        this.face = face;
    }

    public PlayingSide side() {
        return side;
    }

    public TroopFace face() {
        return face;
    }

    public Troop troop() {
        return troop;
    }

    public boolean canStepOn() {
        return false;
    }

    public boolean hasTroop() {
        return true;
    }

    public TroopTile flipped() {
        TroopTile flip;
        if (this.face == TroopFace.REVERS) {
            flip = new TroopTile(troop, side, TroopFace.AVERS);
        } else {
            flip = new TroopTile(troop, side, TroopFace.REVERS);
        }
        return flip;
    }

    public List<Move> movesFrom(BoardPos pos, GameState state) {
        List<Move> result = new ArrayList<>();

        for (TroopAction action : troop.actions(face)) {
            result.addAll(action.movesFrom(pos, side, state));
        }

        return result;
    }

    public void toJSON(PrintWriter writer) {
        writer.printf("{\"troop\":\"%s\",\"side\":\"%s\",\"face\":\"%s\"}",
                troop.name(), side.toString(), face.toString());
    }
}