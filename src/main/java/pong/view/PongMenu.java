package pong.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import pong.controller.MenuListener;

import java.io.Serializable;
import java.sql.SQLException;

public class PongMenu implements Serializable {
    private MenuBar menuBar;
    private Menu menuPlayer;
    private Menu menuBall;
    private Menu menuRacket;
    private Menu menuGame;
    private Menu racketSize1;
    private Menu racketSize2;
    private Menu databaseOptions;

    private MenuItem itemPlayer1Name;
    private MenuItem itemPlayer2Name;
    private MenuItem itemBallSpeed;
    private MenuItem itemBallSpeedIncrease;
    private MenuItem itemRacket1Width;
    private MenuItem itemRacket2Width;
    private MenuItem itemRacket1Height;
    private MenuItem itemRacket2Height;
    private MenuItem itemGameExit;
    private MenuItem itemPause;
    private MenuItem itemEndScore;
    private MenuItem itemRestart;
    private MenuItem itemLoadGame;
    private MenuItem itemSaveGame;
    private MenuListener menuListener;

    public PongMenu(MenuListener menuListener){
        this.menuListener = menuListener;
        menuBar= new MenuBar();
        menuPlayer = new Menu("Player");
        menuBall = new Menu("Ball");
        menuGame = new Menu("Game");
        menuRacket = new Menu("Racket");
        racketSize1 = new Menu("Racket 1");
        racketSize2 = new Menu("Racket 2");
        databaseOptions = new Menu("DB");
        itemPlayer1Name = new MenuItem("Player1 Name");
        itemPlayer2Name = new MenuItem("Player2 Name");
        itemBallSpeed = new MenuItem("Ball Speed");
        itemBallSpeedIncrease = new MenuItem("Speed increase");
        itemRacket1Width = new MenuItem("Width");
        itemRacket2Width = new MenuItem("Width");
        itemRacket1Height = new MenuItem("Height");
        itemRacket2Height = new MenuItem("Height");
        itemGameExit = new MenuItem("Exit");
        itemEndScore = new MenuItem("End Score");
        itemPause = new MenuItem("Pause/Play");
        itemRestart = new MenuItem("Restart");
        itemLoadGame = new MenuItem("Load game from db");
        itemSaveGame = new MenuItem("Save game to db");

        racketSize1.getItems().addAll(itemRacket1Width, itemRacket1Height);
        racketSize2.getItems().addAll(itemRacket2Width, itemRacket2Height);
        menuRacket.getItems().addAll(racketSize1, racketSize2);
        databaseOptions.getItems().addAll(itemLoadGame, itemSaveGame);

        menuGame.getItems().addAll(itemEndScore, itemPause, itemRestart, databaseOptions, itemGameExit);

        menuBall.getItems().addAll(itemBallSpeed, itemBallSpeedIncrease);

        menuPlayer.getItems().addAll(itemPlayer1Name, itemPlayer2Name);
        menuBar.getMenus().addAll(menuPlayer, menuBall, menuRacket, menuGame);
        handleClicking();
    }
    public MenuBar getMenuBar(){
        return menuBar;
    }
    public void setMenuBar(MenuBar menubar){
        menuBar = menubar;
    }
    private void handleClicking() {
        itemGameExit.setOnAction(e -> menuListener.setExit());
        itemEndScore.setOnAction(e -> {
            menuListener.setEndScore();
        });
        itemPlayer1Name.setOnAction(e -> {
            menuListener.setPlayerName(0);
        });
        itemPlayer2Name.setOnAction(e -> {
            menuListener.setPlayerName(1);
        });
        itemBallSpeed.setOnAction(e -> {
            menuListener.setBallSpeed();
        });
        itemBallSpeedIncrease.setOnAction(e -> {
            menuListener.setBallSpeedIncrease();
        });
        itemRacket1Width.setOnAction(e -> {
            menuListener.setRacketWidth(0);
        });
        itemRacket2Width.setOnAction(e -> {
            menuListener.setRacketWidth(1);
        });
        itemRacket1Height.setOnAction(e -> {
            menuListener.setRacketHeight(0);
        });
        itemRacket2Height.setOnAction(e -> {
            menuListener.setRacketHeight(1);
        });
        itemPause.setOnAction(e -> {
            menuListener.setPause();
        });
        itemRestart.setOnAction(e -> {
            menuListener.setRestart();
        });
        itemSaveGame.setOnAction(e -> {
            try {
                menuListener.saveToDB();
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        itemLoadGame.setOnAction(e -> {
            menuListener.loadFromDB();
        });
    }
}
