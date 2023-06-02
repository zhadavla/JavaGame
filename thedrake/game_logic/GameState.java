package thedrake.game_logic;

import java.io.PrintWriter;
import java.util.Optional;

public class GameState implements JSONSerializable{
    private final Board board;
    private final PlayingSide sideOnTurn;
    private final Army blueArmy;
    private final Army orangeArmy;
    private final GameResult result;

    public GameState(
            Board board,
            Army blueArmy,
            Army orangeArmy) {
        this(board, blueArmy, orangeArmy, PlayingSide.BLUE, GameResult.IN_PLAY);
    }

    public GameState(
            Board board,
            Army blueArmy,
            Army orangeArmy,
            PlayingSide sideOnTurn,
            GameResult result) {
        this.board = board;
        this.sideOnTurn = sideOnTurn;
        this.blueArmy = blueArmy;
        this.orangeArmy = orangeArmy;
        this.result = result;
    }

    public Board board() {
        return board;
    }

    public PlayingSide sideOnTurn() {
        return sideOnTurn;
    }

    public GameResult result() {
        return result;
    }

    public Army army(PlayingSide side) {
        if (side == PlayingSide.BLUE) {
            return blueArmy;
        }

        return orangeArmy;
    }

    public Army armyOnTurn() {
        return army(sideOnTurn);
    }

    public Army armyNotOnTurn() {
        if (sideOnTurn == PlayingSide.BLUE)
            return orangeArmy;

        return blueArmy;
    }

    public Tile tileAt(TilePos pos) {
        // create an array of both armies
        Army[] armies = new Army[]{armyOnTurn(), armyNotOnTurn()};
        for (Army army : armies) {
            // troop that may be at the position
            Optional<TroopTile> possibleTroop = army.boardTroops().at(pos);
            // if troop is at the position
            if (possibleTroop.isPresent()) {
                // return him
                return possibleTroop.get();
            }
        }
        return board.at(pos);
    }

    private boolean canStepFrom(TilePos origin) {
        // if game ended
        if (result() != GameResult.IN_PLAY)
            return false;
        Tile tile = tileAt(origin);
        // if troop on the tile and tile is TroopTile
        if (tile.hasTroop() && tile instanceof TroopTile)
            return ((TroopTile) tile).side() == sideOnTurn();
        return false;
    }

    private boolean canStepTo(TilePos target) {
        if (result() != GameResult.IN_PLAY)
            return false;
        return tileAt(target).canStepOn();
    }

    private boolean canCaptureOn(TilePos target) {
        if (result() != GameResult.IN_PLAY)
            return false;
        Tile tile = tileAt(target);
        // if troop on the tile and tile is TroopTile
        if (tile.hasTroop() && tile instanceof TroopTile)
            return ((TroopTile) tile).side() != sideOnTurn();
        return false;
    }


    public boolean canStep(TilePos origin, TilePos target) {
        if (origin == TilePos.OFF_BOARD || target == TilePos.OFF_BOARD || this.blueArmy.boardTroops().isPlacingGuards() || this.orangeArmy.boardTroops().isPlacingGuards()) {
            return false;
        }
        return canStepFrom(origin) && canStepTo(target);
    }

    public boolean canCapture(TilePos origin, TilePos target) {
        if (origin == TilePos.OFF_BOARD || target == TilePos.OFF_BOARD || this.blueArmy.boardTroops().isPlacingGuards() || this.orangeArmy.boardTroops().isPlacingGuards()) {
            return false;
        }
        return canStepFrom(origin) && canCaptureOn(target);
    }


    public boolean canPlaceFromStack(TilePos target) {
        if (target == TilePos.OFF_BOARD
                || result() != GameResult.IN_PLAY
                || armyOnTurn().stack().isEmpty()
                || !tileAt(target).canStepOn())
            return false;
        if (armyOnTurn().side() == PlayingSide.BLUE
                && target.row() == 1
                && !this.blueArmy.boardTroops().isLeaderPlaced() )
            return true;
        else if (armyOnTurn().side() == PlayingSide.ORANGE
                && target.row() == board().dimension()
                && !this.orangeArmy.boardTroops().isLeaderPlaced() )
            return true;

        TilePos leaderPos = armyOnTurn().boardTroops().leaderPosition();

        if (armyOnTurn().boardTroops().isPlacingGuards()) {
            for (TilePos neighbor : target.neighbours()) {
                if (neighbor.equals(leaderPos)) {
                  return true;
                }
            }
        } else {
            for (TilePos neighbor : target.neighbours()) {
                Tile neighborTile = tileAt(neighbor);
                if (neighborTile instanceof TroopTile
                        && ((TroopTile) neighborTile).side() == armyOnTurn().side()) {
                    return true;
                }
            }
        }

        return false;
    }

    public GameState stepOnly(BoardPos origin, BoardPos target) {
        if (canStep(origin, target))
            return createNewGameState(
                    armyNotOnTurn(),
                    armyOnTurn().troopStep(origin, target), GameResult.IN_PLAY);

        throw new IllegalArgumentException();
    }

    public GameState stepAndCapture(BoardPos origin, BoardPos target) {
        if (canCapture(origin, target)) {
            Troop captured = armyNotOnTurn().boardTroops().at(target).get().troop();
            GameResult newResult = GameResult.IN_PLAY;

            if (armyNotOnTurn().boardTroops().leaderPosition().equals(target))
                newResult = GameResult.VICTORY;

            return createNewGameState(
                    armyNotOnTurn().removeTroop(target),
                    armyOnTurn().troopStep(origin, target).capture(captured), newResult);
        }

        throw new IllegalArgumentException();
    }

    public GameState captureOnly(BoardPos origin, BoardPos target) {
        if (canCapture(origin, target)) {
            Troop captured = armyNotOnTurn().boardTroops().at(target).get().troop();
            GameResult newResult = GameResult.IN_PLAY;

            if (armyNotOnTurn().boardTroops().leaderPosition().equals(target))
                newResult = GameResult.VICTORY;

            return createNewGameState(
                    armyNotOnTurn().removeTroop(target),
                    armyOnTurn().troopFlip(origin).capture(captured), newResult);
        }

        throw new IllegalArgumentException();
    }

    public GameState placeFromStack(BoardPos target) {
        if (canPlaceFromStack(target)) {
            return createNewGameState(
                    armyNotOnTurn(),
                    armyOnTurn().placeFromStack(target),
                    GameResult.IN_PLAY);
        }

        throw new IllegalArgumentException();
    }

    public GameState resign() {
        return createNewGameState(
                armyNotOnTurn(),
                armyOnTurn(),
                GameResult.VICTORY);
    }

    public GameState draw() {
        return createNewGameState(
                armyOnTurn(),
                armyNotOnTurn(),
                GameResult.DRAW);
    }

    private GameState createNewGameState(Army armyOnTurn, Army armyNotOnTurn, GameResult result) {
        if (armyOnTurn.side() == PlayingSide.BLUE) {
            return new GameState(board, armyOnTurn, armyNotOnTurn, PlayingSide.BLUE, result);
        }

        return new GameState(board, armyNotOnTurn, armyOnTurn, PlayingSide.ORANGE, result);
    }


    public void toJSON(PrintWriter writer) {
        writer.print("{\"result\":");
        result.toJSON(writer);
        writer.print(",\"board\":");
        board.toJSON(writer);
        writer.print(",\"blueArmy\":");
        blueArmy.toJSON(writer);
        writer.print(",\"orangeArmy\":");
        orangeArmy.toJSON(writer);
        writer.print("}");
    }

}