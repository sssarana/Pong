package pong.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import pong.model.Game;
import javafx.application.Platform;
import pong.view.PongCanvas;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;

public class MenuListener{
    private Game game;
    private PongCanvas canvas;

    private boolean isPaused = false;
    private BallManager ballManager;
    private GameController gameController;
    private KeyboardListener keyboardListener;
    public MenuListener(Game game,PongCanvas canvas,BallManager ballManager, GameController gameController, KeyboardListener kl )
    {
        this.game=game;
        this.canvas=canvas;
        this.ballManager=ballManager;
        this.gameController=gameController;
        keyboardListener = kl;
    }
    public void setExit() {
        Platform.exit();
    }
    /**
     * Set the score that should be achieved for game to end
     **/
    public void setEndScore() {
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("End Score");
        dialog.setHeaderText("Enter a number");
        Optional<String> result = dialog.showAndWait();
        try {
            result.ifPresent(string -> {
                game.setEndScore(Integer.parseInt(result.get()));
            });
        }
        catch (Exception e){
            errorNumbersOnly();
        }finally {
            game.resume();
        }
    }
    /**
     * Set the name of the player of user's choice
     * @param i - 0 if player 1 name should be changed and 1 if player 2 name should be changed
     **/
    public void setPlayerName(int i){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Player's name");
        dialog.setHeaderText("Enter a name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
            if(i == 0) game.getPlayer1().getPlayerName().setName(result.get());
            if(i == 1) game.getPlayer2().getPlayerName().setName(result.get());

            canvas.drawGame(game);
        });
        game.resume();
    }
    /**
     * Method sets the speed of the ball, which is stepX and stepY
     **/
    public void setBallSpeed(){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ball Speed");
        dialog.setHeaderText("Enter a number");
        Optional<String> result = dialog.showAndWait();
        try{
        result.ifPresent(string -> {
            if(checkRange(Integer.parseInt(result.get()), 2, 15)){
                game.getBall().setStepX(Integer.parseInt(result.get()));
                game.getBall().setStepY(Integer.parseInt(result.get()));
                game.resume(Integer.parseInt(result.get()));
            }
            else{
                errorOutOfRange();
            }
        });
        }
        catch (Exception e) {
            errorNumbersOnly();
            game.resume();
        }
    }
    /**
     * Method sets the number of bounces after which ball's speed will increase
     **/
    public void setBallSpeedIncrease(){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ball Speed Inc");
        dialog.setHeaderText("Enter a number");
        Optional<String> result = dialog.showAndWait();
        try {
            result.ifPresent(string -> {
                game.getBall().setSpeedIncrease(Integer.parseInt(result.get()));
            });
        }
        catch (Exception e){
            errorNumbersOnly();
        }finally {
            game.resume();
        }
    }
    /**
     * Pauses a game if it's in process, resumes a game if it's on pause
     **/
    public void setPause(){
        if(!isPaused){
            game.pause();
            isPaused = true;
        }
        else{
            game.resume();
            isPaused = false;
        }
    }
    /**
     * Restarts the game
     **/
    public void setRestart(){
        game.restart();
    }
    /**
     * Set the width of the racket
     * @param i - 0 if player 1 racket width should be changed and 1 if player 2 racket width should be changed
     **/
    public void setRacketWidth(int i){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Racket Width");
        dialog.setHeaderText("Enter a number");
        Optional<String> result = dialog.showAndWait();
        try{
        result.ifPresent(string -> {
            if(checkRange(Integer.parseInt(result.get()), 3, 180)) {
                if (i == 0) game.getPlayer1().getRacket().setWidth(Integer.parseInt(result.get()));
                if (i == 1) game.getPlayer2().getRacket().setWidth(Integer.parseInt(result.get()));
                canvas.drawGame(game);
            }
            else{
                errorOutOfRange();}

        });
        }
        catch (Exception e){
            errorNumbersOnly();
        }finally {
            game.resume();
        }
    }
    /**
     * Set the height of the racket
     * @param i - 0 if player 1 racket height should be changed and 1 if player 2 racket height should be changed
     **/
    public void setRacketHeight(int i){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Racket Height");
        dialog.setHeaderText("Enter a number");
        Optional<String> result = dialog.showAndWait();
        try{
        result.ifPresent(string -> {
            if(checkRange(Integer.parseInt(result.get()), 25, 210)){
                if (i == 0) game.getPlayer1().getRacket().setHeight(Integer.parseInt(result.get()));
                if (i == 1) game.getPlayer2().getRacket().setHeight(Integer.parseInt(result.get()));
                canvas.drawGame(game);
            }
            else{
                errorOutOfRange();
            }
        });
        }
        catch (Exception e){
            errorNumbersOnly();
        }finally {
            game.resume();
        }
    }
    /**
     * Set the name of the game to then retrieve from/save to the database
     **/
    public void setGameName(){
        game.pause();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Game name");
        dialog.setHeaderText("Enter a name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(string -> {
            game.setName(result.get());
        });
        game.resume();
    }
    /**
     * Save the game to the database
     **/
    public void saveToDB() throws SQLException, ClassNotFoundException {
        setGameName();
        DatabaseManager db_man = new DatabaseManager();
        boolean inserted  = db_man.insertGame(game);
        if(inserted){
            showMessage("Game saved with name: " + game.getName(), 0);
        }
        else{
            showMessage("Couldn't save the game", 1);
        }
    }
    /**
     * Load the latest game from the database
     **/
    public void loadFromDB(){
        game.pause();
        DatabaseManager db_man = new DatabaseManager();

            try {
                game.resume();
                game = db_man.getLatestGame();
                if(game != null){
                    showMessage("Game loaded", 0);
                }
                else{
                    showMessage("Couldn't load the game", 1);
                    return;
                }
                game.getBall().reposition(game.getDimensionX()/2, game.getDimensionY()/2);
                ballManager.setGame(game);
                gameController.setGame(game);
                keyboardListener.setGame(game);
                canvas.drawGame(game);
            } catch (ClassNotFoundException | SQLException e) {
                showMessage("Couldn't load the game", 1);
                throw new RuntimeException(e);
            }
    }
    /**
     * Displays an error that indicates that user must input only numbers
     **/
    private void errorNumbersOnly(){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ping Pong");
        alert.setHeaderText("ERROR");
        alert.setContentText("Input numbers only!!!");
        alert.showAndWait().ifPresent((btnType) -> {
        });
    }
    /**
     * Method checks if the value is in the allowed range
     * @param val - value to be checked
     * @param low - the lowest value allowed
     * @param high - the highest value allowed
     * @return boolean true if value is in the allowed range, false otherwise
     **/
    private boolean checkRange(int val, int low, int high){
        if (low<val && val<high) return true;
        return false;
    }
    /**
     * Display an error that indicates that user's input is out of the allowed range
     **/
    private void errorOutOfRange(){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ping Pong");
        alert.setHeaderText("ERROR");
        alert.setContentText("Input reasonable numbers");
        alert.showAndWait().ifPresent((btnType) -> {
        });
    }
    /**
     * Display the message depending on the needs of the method
     * @param contentText - text for the message
     * @param isError - 0 if the message is not an error, 1 if the message is an error
     **/
    private void showMessage(String contentText, int isError){
        var alert = new Alert(Alert.AlertType.INFORMATION);
        String message = "ERROR";
        if(isError == 0) message = "SUCCESS";
        alert.setTitle("Ping Pong");
        alert.setHeaderText(message);
        alert.setContentText(contentText);
        alert.showAndWait().ifPresent((btnType) -> {});
    }
}
