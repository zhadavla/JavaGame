package thedrake.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import thedrake.game_logic.PlayingSide;

import java.util.List;

public class StackView extends HBox {
    public StackView(PlayingSide playingSide) {
        setSpacing(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);

        setOnMouseClicked(e -> {
            System.out.println("Click Stack");
        });

        for (String troopName : List.of("Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer")) {
//            UnitView unitView = ;
            getChildren().add(new UnitView(troopName, playingSide));
        }

//        for (UnitView unitView: getChildren())
    }
}
