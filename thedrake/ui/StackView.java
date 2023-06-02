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

public class StackView extends HBox {
    private List<String> names;

    int i = 0;
    private List<UnitView> blueUnits;
    private List<UnitView> orangeUnits;
    private final Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private boolean isSelected = false;

    public StackView(PlayingSide playingSide) {
        blueUnits = new ArrayList<>();
        orangeUnits = new ArrayList<>();

        if (playingSide == PlayingSide.BLUE) {
            for (String name : new String[]{"Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer"}) {
                blueUnits.add(new UnitView(name, playingSide));
            }
        } else {
            for (String name : new String[]{"Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer"}) {
                orangeUnits.add(new UnitView(name, playingSide));
            }
        }

        setSpacing(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);

        setOnMouseClicked(e -> {
            select();
            System.out.println("Click Stack" + playingSide);
            removePlaced(playingSide);
        });

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

    private void removePlaced(PlayingSide playingSide) {
        System.out.println("in there");
        if (playingSide == PlayingSide.BLUE) {
            getChildren().remove(blueUnits.get(i));
            blueUnits.remove(i);
        } else {
            getChildren().remove(orangeUnits.get(i));
            orangeUnits.remove(i);
        }
    }
}
