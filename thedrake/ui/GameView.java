package thedrake.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import thedrake.game_logic.GameState;
import thedrake.game_logic.PlayingSide;

public class GameView{
    private ImageView moveImage;
    private ValidMoves validMoves;
    private BoardView boardView;
    private StackView orangeStack;
    private StackView blueStack;
    private boolean isBlueTurn = true;

    private BorderPane root;
    private GameState gameState;

    public GameView(GameState gameState) {
        this.validMoves = new ValidMoves(gameState);

        this.gameState = gameState;

        this.boardView = new BoardView(gameState);

        this.root = new BorderPane(this.boardView);


        this.blueStack = new StackView(PlayingSide.BLUE);
        blueStack.setOnMouseClicked(e -> onClickBlue());
        root.setBottom(blueStack);

        this.orangeStack = new StackView(PlayingSide.ORANGE);
        orangeStack.setOnMouseClicked(e -> onClickOrange());
        root.setTop(orangeStack);

        moveImage = new ImageView(String.valueOf(getClass().getResource("/assets/move.png")));
    }

    private void onClickBlue() {
//        System.out.println("No way..");
//        System.out.println("Current blue stack " + blueStack.getBlueUnitsNames().get(0));
//
        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();
//        boardView.setCurrentBlue(blueStack.getBlueUnitsNames().get(0));
//        validMoves = new ValidMoves(boardView.getGameState());
//
//        blueStack.removePlaced(PlayingSide.BLUE);
    }

    private void onClickOrange() {
//        System.out.println("No way..");
//        System.out.println("Current blue stack " + orangeStack.getOrangeUnitsNames().get(0));

        boardView.setIsStackPressed(true);
        boardView.showPossibleMoves();
//        System.out.println("SIDE TO TURN: " + boardView.getGameState().sideOnTurn());
//
//        boardView.setCurrentOrange(orangeStack.getOrangeUnitsNames().get(0));
//
//        orangeStack.removePlaced(PlayingSide.ORANGE);
    }

    Scene getScene() {
        return new Scene(root, 800, 800);
    }
    private void updateTiles() {
        for (Node node : boardView.getChildren()) {
            TileView tileView = (TileView) node;
            tileView.setTile(gameState.tileAt(tileView.position()));
            tileView.update();
        }
    }

    void showPossibleMoves() {
        boardView.showMoves(validMoves.movesFromStack());

    }

}
