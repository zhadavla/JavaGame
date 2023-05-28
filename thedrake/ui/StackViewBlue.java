package thedrake.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import thedrake.*;

import java.lang.reflect.Array;
import java.util.List;

public class StackViewBlue extends HBox {
    public StackViewBlue() {
        setSpacing(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
        List<String> troopNames = List.of("Drake", "Clubman", "Clubman", "Monk", "Spearman", "Swordsman", "Archer");
        List<Button> buttons = List.of();
        // add ten buttons
        for (int i = 0; i < 6; i++) {
            getChildren().add(new UnitView(troopNames.get(i)));
        }
    }
}
