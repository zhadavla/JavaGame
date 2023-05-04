package thedrake;

import java.io.PrintWriter;
import java.util.List;

public class Board implements JSONSerializable {

    private final int dimension;
    private final BoardTile[][] boardTiles;

    // Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru,
    // kde všechny dlaždice jsou prázdné, tedy BoardTile.EMPTY
    public Board(int dimension) {
        this.dimension = dimension;
        this.boardTiles = new BoardTile[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.boardTiles[i][j] = BoardTile.EMPTY;
            }
        }
    }

    // Rozměr hrací desky
    public int dimension() {
        return this.dimension;
    }

    // Vrací dlaždici na zvolené pozici.
    public BoardTile at(TilePos pos) {
        return this.boardTiles[pos.i()][pos.j()];
    }

    // Vytváří novou hrací desku s novými dlaždicemi.
    // Všechny ostatní dlaždice zůstávají stejné
    public Board withTiles(TileAt... ats) {
        Board b = new Board(this.dimension);
        for (int i = 0; i < this.dimension; i++)
            b.boardTiles[i] = this.boardTiles[i].clone();
        for (TileAt t : ats)
            for (int i = 0; i < this.dimension; i++)
                for (int j = 0; j < this.dimension; j++)
                    if (i == t.pos.i() && j == t.pos.j())
                        b.boardTiles[i][j] = t.tile;

        return (b);
    }

    // Vytvoří instanci PositionFactory pro výrobu pozic na tomto hracím plánu
    public PositionFactory positionFactory() {
        return new PositionFactory(this.dimension);
    }



    public static class TileAt {
        public final BoardPos pos;
        public final BoardTile tile;

        public TileAt(BoardPos pos, BoardTile tile) {
            this.pos = pos;
            this.tile = tile;
        }
    }

    @Override
    public void toJSON(PrintWriter writer) {
        writer.printf("{\"dimension\":%d,\"tiles\":", dimension);
        writer.printf("[");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardTiles[j][i].toJSON(writer);
                if (j < dimension - 1)
                    writer.printf(",");
            }
            if (i < dimension - 1)
                writer.printf(",");
        }
        writer.printf("]}");
    }
}

