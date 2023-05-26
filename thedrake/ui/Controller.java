package thedrake.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    private Label mainLabel;
    @FXML
    private VBox mainMenu;



    @FXML
    protected void handleExitButton() {
        // close the window
        System.exit(0);
    }
}