package thedrake.game_logic;

import java.io.PrintWriter;
import java.util.List;

public class Troop implements JSONSerializable {
    private final String name;
    private final Offset2D aversPivot;
    private final Offset2D reversPivot;
    private final List<TroopAction> aversActions;
    private final List<TroopAction> reversActions;

    public Troop(String name, Offset2D aversPivot , Offset2D reversPivot,
                 List<TroopAction> aversActions, List<TroopAction> reversActions) {
        this.name = name;
        this.aversPivot = aversPivot;
        this.reversPivot = reversPivot;
        this.aversActions = aversActions;
        this.reversActions = reversActions;
    }

    public Troop(String name, Offset2D pivot, List<TroopAction> aversActions, List<TroopAction> reversActions) {
        this(name, pivot, pivot, aversActions, reversActions);
    }

    public Troop(String name,  List<TroopAction> aversActions, List<TroopAction> reversActions) {
        this(name, new Offset2D(1,1), new Offset2D(1,1), aversActions, reversActions);
    }

    public String name() {
        return name;
    }

    public Offset2D pivot(TroopFace face) {
        if (face == TroopFace.REVERS) {
            return this.reversPivot;
        } else {
            return this.aversPivot;
        }
    }

    public List<TroopAction> actions(TroopFace face) {
        if (face == TroopFace.REVERS) {
            return this.reversActions;
        } else {
            return this.aversActions;
        }
    }

    @Override
    public void toJSON(PrintWriter writer) {
        writer.printf("\"%s\"", name);
    }
}