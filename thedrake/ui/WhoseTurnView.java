package thedrake.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class WhoseTurnView extends StackPane {
    private Controller controller;

    public WhoseTurnView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scenes/whoseTurn.fxml"));
        Pane myPane = fxmlLoader.load();
        getChildren().add(myPane);

        this.controller = fxmlLoader.getController();
    }

    public Controller getController() {
        return controller;
    }
}
