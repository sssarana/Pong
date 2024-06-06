package pong.model;

import javafx.scene.paint.Color;
import pong.controller.MenuListener;
import pong.view.PongMenu;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Resizable, Serializable {
    private Player player1;
    private Player player2;
    private transient Color player2Color;
    private Ball ball;
    private MenuListener menuListener;
    private int endScore;
    private double[] pauseState = new double[3];
    private double dimensionX;
    private double dimensionY;
    private String name;
    //constructors
    public Game(){
        player1 = new Player();
        player2 = new Player();
        player2.getPlayerName().setPositionX(420);
        player2.getRacket().setPosX(540);
        player2Color = Color.PINK;
        player2.getRacket().setColor(player2Color);
        player2.getPlayerName().setColour(player2Color);
        ball = new Ball();
        ball.setPosX(200.0);
        endScore = 10;
        dimensionX = 600;
        dimensionY = 600;
        name = "Pong";
    }

    public Game(Player pl1, Player pl2, Ball new_ball, int new_endScore, String new_name){
        player1 = pl1;
        player2 = pl2;
        ball = new_ball;
        endScore = new_endScore;
        name = new_name;
    }

    //setters and getters
    public void setPlayer1(Player pl1){
        player1 = pl1;
    }

    public void setPlayer2(Player pl2){
        player2 = pl2;
    }

    public void setBall(Ball new_ball){
        ball = new_ball;
    }


    public void setMenuListener(MenuListener menuListener){
        this.menuListener = menuListener;
    }


    public void setEndScore(int new_endScore){
        endScore = new_endScore;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public Ball getBall(){
        return ball;
    }

    public int getEndScore(){
        return endScore;
    }


    public MenuListener getMenuListener(){
        return menuListener;
    }
    public double getDimensionX() {
        return dimensionX;
    }

    public double getDimensionY() {
        return dimensionY;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public void setDimensionX(double dimensionX) {
        this.dimensionX = dimensionX;
    }

    public void setDimensionY(double dimensionY) {
        this.dimensionY = dimensionY;
    }

    /**
     * Restarts the game by setting scores to 0 and setting ball's speed and position to default
     **/
    public void restart(){
        player1.setScore(0);
        player2.setScore(0);
        ball.reposition(dimensionX/2, dimensionY/2);
        ball.setStepX(5);
        ball.setStepY(-5);
        player1.getRacket().setInitialSize();
        player2.getRacket().setInitialSize();
        player1.getRacket().setPosY(200);
        player2.getRacket().setPosY(200);
    }

    /**
     * Used to pause a game and save it's state
     **/
    public void pause(){
        pauseState = new double[] {ball.getStepX(), ball.getStepY(), player1.getRacket().getRacketSpeed()};
        ball.setStepX(0);
        ball.setStepY(0);
        player1.getRacket().setRacketSpeed(0);
        player2.getRacket().setRacketSpeed(0);
    }
    /**
     * Continue the game with the values saved after pausing it
     **/
    public void resume(){
        ball.setStepX(pauseState[0]);
        ball.setStepY(pauseState[1]);
        player1.getRacket().setRacketSpeed((int)pauseState[2]);
        player2.getRacket().setRacketSpeed((int)pauseState[2]);
    }

    /**
     * Continue the game with the values saved after pausing it and new value for the speed of the ball
     **/
    public void resume(double ballSpeed){
        ball.setStepX(ballSpeed);
        ball.setStepY(ballSpeed);
        player1.getRacket().setRacketSpeed((int)pauseState[2]);
        player2.getRacket().setRacketSpeed((int)pauseState[2]);
    }
    @Override
    public void resizeX(double factor) {
        player1.getRacket().resizeX(factor);
        player2.getRacket().resizeX(factor);
        ball.resizeX(factor);
        player1.getPlayerName().resizeX(factor);
        player2.getPlayerName().resizeX(factor);
    }

    @Override
    public void resizeY(double factor) {
        player1.getRacket().resizeY(factor);
        player2.getRacket().resizeY(factor);
        ball.resizeY(factor);
        player1.getPlayerName().resizeY(factor);
        player2.getPlayerName().resizeY(factor);
    }

    @Override
    public String toString() {
        return "Game{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", player2Color=" + player2Color +
                ", ball=" + ball +
                ", menuListener=" + menuListener +
                ", endScore=" + endScore +
                ", pauseState=" + Arrays.toString(pauseState) +
                ", dimensionX=" + dimensionX +
                ", dimensionY=" + dimensionY +
                ", name='" + name + '\'' +
                '}';
    }
}
