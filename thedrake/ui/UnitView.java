package thedrake.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import thedrake.game_logic.PlayingSide;
import thedrake.game_logic.TroopFace;

public class UnitView extends Pane {
    public UnitView(String name) {
        setPrefSize(100, 100);
        update();

        Image image = new TroopImageSet(name).get(PlayingSide.BLUE, TroopFace.AVERS);
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));

        setOnMouseClicked(e -> onClick());
    }

    private void onClick() {
        // todo
    }

    private void update() {
        // todo
    }
}
