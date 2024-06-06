package pong.controller;

import pong.model.Ball;
import pong.model.Game;
import pong.model.Player;
import pong.model.Racket;
import pong.view.PongCanvas;

import java.io.Serializable;

public class BallManager implements Runnable, Serializable {
    private Game game;
    private PongCanvas canvas;
    private Ball ball;
    public BallManager(Game new_game, PongCanvas new_canvas){
        game = new_game;
        canvas = new_canvas;
    }
    @Override
    public void run() {
        ball = game.getBall();
        while(true)
        {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ball.move(game.getDimensionY());

            //goal detection
            if (ball.getPosX()<10)
            {
                game.getPlayer2().setScore(game.getPlayer2().getScore() + 1);
                showGoal(2);
            }
            if (ball.getPosX()>game.getDimensionX()-ball.getRadius())
            {
                game.getPlayer1().setScore(game.getPlayer1().getScore() + 1);
                showGoal(1);
            }

            // CODE to CHECK BOUNCING WITH RACKETS
            Racket racket1 = game.getPlayer1().getRacket();
            Racket racket2 = game.getPlayer2().getRacket();

            //collision with player 1
           if (ball.getPosX() < racket1.getPosX()+ racket1.getWidth() && (ball.getPosY() +ball.getRadius()> racket1.getPosY() &&
                    ball.getPosY() -ball.getRadius()< racket1.getPosY() + racket1.getHeight())){
                ball.setBounceCounter(ball.getBounceCounter()+1);
                ball.changeDirectionX();
            }

            //collision with player 2
            if (ball.getPosX() + ball.getRadius() > racket2.getPosX() && ball.getPosY() +ball.getRadius()> racket2.getPosY() &&
                    ball.getPosY()-ball.getRadius() < racket2.getPosY() + racket2.getHeight()){
                ball.setBounceCounter(ball.getBounceCounter()+1);
                ball.changeDirectionX();
            }
            canvas.drawGame(game);
        }
    }
    /**
     * Method shows a goal message for 2 seconds if user scored
     * And if the goal was a winning goal, winner is displayed and the game is restarted
     * @param i - integer that indicates which player scored a goal/won
     **/
    private void showGoal(int i) {
        Player winner = game.getPlayer1();
        if (i == 2) winner = game.getPlayer2();
        if (checkEndOfGame(game)){
            canvas.showEndOfGame(game, winner);
            game.restart();
        }
        else {
            canvas.showGoal(game, winner);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        game.getBall().reposition(game.getDimensionX()/2, game.getDimensionY()/2);

    }
    /**
     * Check if the end score achieved by any of the players
     * @param game - game that is in process
     * @return - boolean value, true if the game is finished and false if it's not
     **/
    public boolean checkEndOfGame(Game game)
    {
        int maxScore= Math.max(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        return game.getEndScore()<=maxScore;
    }
    /**
     * Collision with player 1
     **/
   public void checkCollision1(Ball ball, Racket racket){
        if (ball.getPosX() <= racket.getPosX()+ racket.getWidth() && (ball.getPosY() +ball.getRadius()> racket.getPosY() &&
                ball.getPosY() -ball.getRadius()< racket.getPosY() + racket.getHeight())){
            ball.setBounceCounter(ball.getBounceCounter()+1);
            ball.changeDirectionX();
        }
    }
    /**
     * Collision with player 2
     **/
    public void checkCollision2(Ball ball, Racket racket){
        if (ball.getPosX() + ball.getRadius() >= racket.getPosX() && ball.getPosY() +ball.getRadius()> racket.getPosY() &&
                ball.getPosY()-ball.getRadius() < racket.getPosY() + racket.getHeight()){
            ball.setBounceCounter(ball.getBounceCounter()+1);
            ball.changeDirectionX();
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
        ball = game.getBall();
    }
}
