package thedrake.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.w3c.dom.events.Event;
import thedrake.game_logic.PlayingSide;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackView extends HBox {
    private List<UnitView> blueUnits;
    private List<String> blueUnitsNames;
    private List<String> orangeUnitsNames;

    public List<String> getBlueUnitsNames() {

        return blueUnitsNames;
    }

    public List<String> getOrangeUnitsNames() {
        for (String s: orangeUnitsNames)
            System.out.println(s);
        return orangeUnitsNames;
    }

    private List<UnitView> orangeUnits;
    private final Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private boolean isSelected = false;

    public StackView(PlayingSide playingSide) {

        blueUnits = new ArrayList<>();
        orangeUnits = new ArrayList<>();
        blueUnitsNames = new ArrayList<>();
        orangeUnitsNames = new ArrayList<>();

        if (playingSide == PlayingSide.BLUE) {
            for (String name : new String[]{"Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer"}) {
                blueUnitsNames.add(name);
                blueUnits.add(new UnitView(name, playingSide));
            }
        } else {
            for (String name : new String[]{"Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer"}) {
                orangeUnitsNames.add(name);
                orangeUnits.add(new UnitView(name, playingSide));
            }
        }

        setSpacing(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);

        update(playingSide);
    }

    private void select() {
        if (!isSelected) {
            setBorder(blackBorder);
        } else {
            setBorder(null);
        }
        isSelected = !isSelected;
    }

    private void update(PlayingSide playingSide) {
        if (playingSide == PlayingSide.BLUE) {
            for (UnitView unitView : blueUnits) {
                getChildren().add(unitView);
            }
        } else {
            for (UnitView unitView : orangeUnits) {
                getChildren().add(unitView);
            }
        }
    }

    public void removePlaced(PlayingSide playingSide) {
        System.out.println("in there");
        if (playingSide == PlayingSide.BLUE) {
            getChildren().remove(blueUnits.get(0));
            blueUnitsNames.remove(0);
            blueUnits.remove(0);
        } else {
            getChildren().remove(orangeUnits.get(0));
            orangeUnitsNames.remove(0);
            orangeUnits.remove(0);
        }
    }
}
