package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pong.controller.*;
import pong.model.*;
import pong.view.PongCanvas;
import pong.view.PongMenu;

import java.sql.SQLException;

public class Main extends Application {
    private PongCanvas canvas = new PongCanvas();
    private MenuListener menuListener;
    private Game game;
    private GameController gameController = new GameController();
    private PongMenu menu;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        primaryStage.setTitle("Pong");


        gameController.setGame(game);

        BorderPane root = new BorderPane();
        canvas.setFocusTraversable(true);
        canvas.drawGame(game);
        root.setCenter(canvas);


        BallManager ballManager= new BallManager(game, canvas);
        KeyboardListener keyboardListener = new KeyboardListener(game, canvas, ballManager, gameController);
        menuListener = new MenuListener(game, canvas, ballManager, gameController, keyboardListener);
        menu = new PongMenu(menuListener);
        Thread thread = new Thread(ballManager);
        thread.start();
        Thread.yield();


        canvas.setOnKeyPressed(keyboardListener );
        canvas.setOnKeyTyped(keyboardListener );

        Scene scene = new Scene(root, 600, 600);
        root.setTop(menu.getMenuBar());
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(400);


        primaryStage.widthProperty().addListener(observable -> {
            double factor= primaryStage.getWidth()/game.getDimensionX();
            game.setDimensionX(primaryStage.getWidth());
            game.resizeX(factor);
            canvas.drawGame(game);
        });
        primaryStage.heightProperty().addListener(observable -> {
            double factor= primaryStage.getHeight()/game.getDimensionY();
            gameController.getGame().setDimensionY(primaryStage.getHeight());
            gameController.getGame().resizeY(factor);
            canvas.drawGame(gameController.getGame());
        });

        primaryStage.show();
    }

}
