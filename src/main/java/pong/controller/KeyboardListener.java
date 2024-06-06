package pong.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import pong.model.Ball;
import pong.model.Game;
import pong.view.PongCanvas;

import java.io.Serializable;

public class KeyboardListener implements EventHandler<KeyEvent>{
    private Game game;
    private PongCanvas canvas;
    private Serializer serializer = Serializer.getSerializer();

    private BallManager ballManager;
    private GameController gameController;
    public KeyboardListener(Game new_game, PongCanvas new_canvas,BallManager ballManager,GameController gameController){
        game = new_game;
        canvas = new_canvas;
        this.ballManager=ballManager;
        this.gameController=gameController;
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode key = keyEvent.getCode();
        if (KeyCode.UP.equals(key))
        {
            game.getPlayer2().getRacket().moveUp();
        }
        if (KeyCode.DOWN.equals(key))
        {
            game.getPlayer2().getRacket().moveDown(game.getDimensionY());
        }
        if (KeyCode.W.equals(key))
        {
            game.getPlayer1().getRacket().moveUp();
        }
        if (KeyCode.S.equals(key))
        {
            game.getPlayer1().getRacket().moveDown(game.getDimensionY());
        }
        if(KeyCode.ESCAPE.equals(key)){
            if(serializer.serialize(game)){
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ping Pong");
                alert.setHeaderText("SUCCESS");
                alert.setContentText("Game was serialized");
                alert.showAndWait().ifPresent((btnType) -> {
                });
            }
            else{
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ping Pong");
                alert.setHeaderText("ERROR");
                alert.setContentText("IOException");
                alert.showAndWait().ifPresent((btnType) -> {
                });
            }
        }
        if(KeyCode.L.equals(key)){
            Game new_game = (Game)serializer.deserialize();
            System.out.println(new_game);
            if(new_game != null){
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ping Pong");
                alert.setHeaderText("SUCCESS");
                alert.setContentText("Game was deserialized");
                alert.showAndWait().ifPresent((btnType) -> {
                });
                game = new_game;
                game.getBall().setColor(Color.PURPLE);
                game.getPlayer1().getPlayerName().setColour(Color.YELLOW);
                game.getPlayer1().getRacket().setColor(Color.YELLOW);
                game.getPlayer2().getPlayerName().setColour(Color.PINK);
                game.getPlayer2().getRacket().setColor(Color.PINK);
                ballManager.setGame(game);
                gameController.setGame(game);
            }
            else{
                var alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ping Pong");
                alert.setHeaderText("ERROR");
                alert.setContentText("Exception occurred");
                alert.showAndWait().ifPresent((btnType) -> {
                });
            }
        }
        canvas.drawGame(game);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
