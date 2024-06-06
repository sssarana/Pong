package pong.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pong.model.Ball;
import pong.model.Player;
import pong.model.Racket;
import pong.model.Game;

import java.io.Serializable;

public class PongCanvas extends Canvas implements Serializable {
    private transient GraphicsContext gc;
    public PongCanvas(){
        super(1600,1200);
        gc = this.getGraphicsContext2D();
    }
    private void bg(GraphicsContext gc, double w, double h, Color color){
        gc.setFill(color);
        gc.fillRect(0, 0, w, h);
    }

    private void drawBall(GraphicsContext gc, Ball ball){
        gc.setFill(ball.getColor());
        gc.fillOval(ball.getPosX(), ball.getPosY(), ball.getRadius(), ball.getRadius());
    }

    private void drawRacket(GraphicsContext gc, Racket racket){
        gc.setFill(racket.getColor());
        gc.fillRect(racket.getPosX(), racket.getPosY(), racket.getWidth(), racket.getHeight());
    }

    private void drawPlayerName(GraphicsContext gc, Player player){
        gc.setFill(player.getPlayerName().getColour());
        gc.setFont(new Font("Serif Regular",player.getPlayerName().getFontSize()));
        gc.fillText(player.getPlayerName().getName() + ": " + player.getScore(), player.getPlayerName().getPositionX(), 70);
    }

    private void drawAdditionalText(GraphicsContext gc){
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Serif Regular", 25));
        gc.fillText("ESC to serialize the game", 200, 400);
        gc.fillText("L to deserialize the game", 200, 450);
    }
    private void resetSize(Game game) {
        this.setWidth(game.getDimensionX());
        this.setHeight(game.getDimensionY());
    }
    /**
     * Draw background, ball, rackets, and players' names and scores
     * @param game - game to draw
     **/
    public void drawGame(Game game){
        bg(this.getGraphicsContext2D(), 1600, 1200, Color.BLACK);
       drawBall(this.getGraphicsContext2D(), game.getBall());
       drawRacket(this.getGraphicsContext2D(), game.getPlayer1().getRacket());
       drawRacket(this.getGraphicsContext2D(), game.getPlayer2().getRacket());
       drawPlayerName(this.getGraphicsContext2D(), game.getPlayer1());
       drawPlayerName(this.getGraphicsContext2D(), game.getPlayer2());
       drawAdditionalText(this.getGraphicsContext2D());
    }
    /**
     * Display text with the name of the player that scored a goal
     * @param winner - player that scored a goal
     * @param game - game that is in process
     **/
    public void showGoal(Game game, Player winner) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.LAVENDER);
        gc.setFont(new Font("Serif Regular",50));
        gc.fillText("GOAL " + winner.getPlayerName().getName(),game.getDimensionX()/2-100, game.getDimensionY()/2-100);
    }
    /**
     * Display the final message with the declared winner
     * @param winner - player that won
     * @param game - game that is in process
     **/
    public void showEndOfGame(Game game, Player winner){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.LAVENDER);
        gc.setFont(new Font("Serif Regular",50));
        gc.fillText("Winner is  " + winner.getPlayerName().getName(),game.getDimensionX()/2-150, game.getDimensionY()/2-100);
    }
}
