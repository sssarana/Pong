package pong.controller;

import javafx.scene.paint.Color;
import pong.model.Ball;
import pong.model.Game;
import pong.model.Player;

import java.io.Serializable;

public class GameBuilder{
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private String name;
    private int endScore;

    public Game build() {
        Game game = new Game();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.getPlayerName().setName(player1Name);
        player2.getPlayerName().setName(player2Name);
        player1.setScore(player1Score);
        player2.setScore(player2Score);
        player2.getPlayerName().setPositionX(420);
        player2.getRacket().setPosX(540);
        player2.getRacket().setColor(Color.PINK);
        player2.getPlayerName().setColour(Color.PINK);
        game.setBall(new Ball());
        game.setEndScore(endScore);
        game.setName(name);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        return game;
    }

    public GameBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GameBuilder withPlayer1Name(String p1Name) {
        this.player1Name = p1Name;
        return this;
    }

    public GameBuilder withPlayer2Name(String p2Name) {
        this.player2Name = p2Name;
        return this;
    }

    public GameBuilder withPlayer1Score(int score) {
        this.player1Score = score;
        return this;
    }

    public GameBuilder withPlayer2Score(int score) {
        this.player2Score = score;
        return this;
    }

    public GameBuilder withEndScore(int t) {
        this.endScore = t;
        return this;
    }
}