package pong.controller;

import pong.model.Game;

import java.io.Serializable;

public class GameController{
    private Game game;

    public GameController(){
        game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
