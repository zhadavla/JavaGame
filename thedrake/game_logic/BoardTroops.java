package thedrake.game_logic;

import java.io.PrintWriter;
import java.util.*;

public class BoardTroops implements JSONSerializable {
    private final PlayingSide playingSide;
    private final Map<BoardPos, TroopTile> troopMap;
    private final TilePos leaderPosition;
    private final int guards;

    public BoardTroops(PlayingSide playingSide) {
        this.guards = 0;
        this.troopMap = Collections.emptyMap();
        this.leaderPosition = TilePos.OFF_BOARD;
        this.playingSide = playingSide;
    }

    public BoardTroops(
            PlayingSide playingSide,
            Map<BoardPos, TroopTile> troopMap,
            TilePos leaderPosition,
            int guards) {
        this.troopMap = troopMap;
        this.playingSide = playingSide;
        this.leaderPosition = leaderPosition;
        this.guards = guards;
    }

    public Optional<TroopTile> at(TilePos pos) {
        TroopTile tile = troopMap.get(pos);
        if (tile == null) {
            return Optional.empty();
        }
        return Optional.of(tile);
    }

    public PlayingSide playingSide() {
        return playingSide;
    }

    public TilePos leaderPosition() {
        return leaderPosition;
    }

    public int guards() {
        return guards;
    }

    public boolean isLeaderPlaced() {
        return leaderPosition != TilePos.OFF_BOARD;
    }

    public boolean isPlacingGuards() {
        return isLeaderPlaced() && guards < 2;
    }

    public Set<BoardPos> troopPositions() {
        return troopMap.keySet();
    }

    public BoardTroops placeTroop(Troop troop, BoardPos target) throws IllegalArgumentException {
        if (at(target).isPresent())
            throw new IllegalArgumentException();

        Map<BoardPos, TroopTile> newMap = new HashMap<>(troopMap);
        TroopTile newTroop = new TroopTile(troop, playingSide, TroopFace.AVERS);
        newMap.put(target, newTroop);

        TilePos newLeaderPos;
        int newGuards = guards;
        if (!isLeaderPlaced())
            newLeaderPos = target;
        else
            newLeaderPos = leaderPosition;
        if (isPlacingGuards())
            newGuards++;

        return new BoardTroops(playingSide, newMap, newLeaderPos, newGuards);
    }

    public BoardTroops troopStep(BoardPos origin, BoardPos target) throws IllegalStateException, IllegalArgumentException {
        if (!isLeaderPlaced() || isPlacingGuards()) {
            throw new IllegalStateException();
        }
        if (troopMap.containsKey(target) || !troopMap.containsKey(origin))
            throw new IllegalArgumentException();

        Map<BoardPos, TroopTile> newMap = new HashMap<>(troopMap);
        newMap.put(target, newMap.remove(origin).flipped());
        TilePos newLeaderPos;
        if (leaderPosition.equals(origin))
            newLeaderPos = target;
        else
            newLeaderPos = leaderPosition;
        return new BoardTroops(playingSide, newMap, newLeaderPos, guards);
    }

    public BoardTroops troopFlip(BoardPos origin) {
        if (!isLeaderPlaced())
            throw new IllegalStateException();
        if (isPlacingGuards())
            throw new IllegalStateException();
        if (!at(origin).isPresent())
            throw new IllegalArgumentException();

        Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
        newTroops.put(origin, newTroops.remove(origin).flipped());
        return new BoardTroops(playingSide(), newTroops, leaderPosition, guards);
    }

    public BoardTroops removeTroop(BoardPos target) {
        if (!isLeaderPlaced() || isPlacingGuards())
            throw new IllegalStateException();
        if (!at(target).isPresent())
            throw new IllegalArgumentException();

        Map<BoardPos, TroopTile> newMap = new HashMap<>(troopMap);
        TilePos newLeaderPos;
        if (leaderPosition.equals(target))
            newLeaderPos = TilePos.OFF_BOARD;
        else
            newLeaderPos  = leaderPosition;
        newMap.remove(target);
        return new BoardTroops(playingSide, newMap, newLeaderPos, guards);
    }

    @Override
    public void toJSON(PrintWriter writer) {
        writer.printf("{\"side\":\"%s\",\"leaderPosition\":", playingSide.toString());
        leaderPosition.toJSON(writer);
        writer.printf(",\"guards\":%d,\"troopMap\":{", guards);

        Vector<BoardPos> sorted = new Vector<>(troopMap.keySet());
        sorted.sort(Comparator.comparing(BoardPos::i).thenComparing(BoardPos::j));

        for (int i = 0; i < sorted.size(); i++) {
            BoardPos position = sorted.get(i);
            TroopTile tile = troopMap.get(position);
            writer.printf("\"%c%d\":", position.column(), position.row());
            tile.toJSON(writer);
            if (i < sorted.size() - 1)
                writer.print(",");
        }

        writer.print("}}");
    }
}