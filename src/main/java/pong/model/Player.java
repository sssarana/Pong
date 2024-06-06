package pong.model;

import java.io.Serializable;

public class Player implements Serializable {
    private PlayerName name;
    private int score;
    private Racket racket;
    //constructors
    public Player(){
        this(new PlayerName());
    }

    public Player(PlayerName aName){
        name = aName;
        racket = new Racket();
        score = 0;
    }

    //setters and getters
    public void setPlayerName(PlayerName aName){
        name = aName;
    }

    public void setScore(int new_score){
        score = new_score;
    }

    public void setRacket(Racket new_racket){
        racket = new_racket;
    }

    public PlayerName getPlayerName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public Racket getRacket(){
        return racket;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", score=" + score +
                ", racket=" + racket +
                '}';
    }
}
