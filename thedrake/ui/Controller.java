package thedrake.ui;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView sword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rotate();
    }

    public void rotate() {
        RotateTransition rotate = new RotateTransition(Duration.millis(1000));
        rotate.setNode(sword);
        rotate.setByAngle(180);
        rotate.play();
    }

    public void rotateRight() {
        RotateTransition rotate = new RotateTransition(Duration.millis(1000));
        rotate.setNode(sword);
        rotate.setByAngle(-180);
        rotate.play();
    }
}
