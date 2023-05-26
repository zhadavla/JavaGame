package thedrake.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label mainLabel;

    @FXML
    protected void handleExitButton(){
        // close the window
        System.exit(0);
    }
}