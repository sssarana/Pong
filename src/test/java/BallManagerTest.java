import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pong.model.Ball;
import pong.model.Game;
import pong.controller.BallManager;
import pong.model.Racket;
import pong.view.PongCanvas;


public class BallManagerTest {
    Game game = new Game();
    PongCanvas canvas = new PongCanvas();
    BallManager manager = new BallManager(game, canvas);
    @Before
    public void initialise()
    {
        game.setDimensionX(600);
        game.setDimensionY(600);
        game.setEndScore(10);
    }
    @Test
    public void testEndOfGame() {
        game.getPlayer2().setScore(7);
        game.getPlayer1().setScore(11);
        assertTrue(manager.checkEndOfGame(game));
        game.getPlayer1().setScore(7);
        assertFalse(manager.checkEndOfGame(game));
    }

    @Test
    public void testCollisionWithRacket1(){
        Ball ball = game.getBall();
        ball.setPosX(60);
        ball.setPosY(200);
        ball.setStepX(-5);
        Racket racket1 = game.getPlayer1().getRacket();
        racket1.setPosX(45);
        racket1.setPosY(150);
        racket1.setHeight(100);
        racket1.setWidth(10);

        ball.move(game.getDimensionY());

        manager.checkCollision1(ball, racket1);

        assertTrue(ball.getStepX() > 0);

        ball.setPosX(100);
        ball.setPosY(400);
        ball.setStepX(5);

        ball.move(game.getDimensionY());

        manager.checkCollision1(ball, game.getPlayer1().getRacket());
        assertFalse(ball.getStepX() < 0);
    }

    @Test
    public void testCollisionWithRacket2(){
        Ball ball = game.getBall();
        ball.setPosX(500);
        ball.setPosY(200);
        ball.setStepX(5);
        Racket racket2 = game.getPlayer2().getRacket();
        racket2.setPosX(495);
        racket2.setPosY(150);
        racket2.setHeight(100);
        racket2.setWidth(10);

        ball.move(game.getDimensionY());

        manager.checkCollision2(ball, game.getPlayer2().getRacket());

        assertTrue(ball.getStepX() < 0);

        ball.setPosX(100);
        ball.setPosY(400);
        ball.setStepX(-5);

        ball.move(game.getDimensionY());

        manager.checkCollision2(ball, game.getPlayer2().getRacket());
        assertFalse(ball.getStepX() > 0);
    }

    @Test
    public void testCollisionWithTop(){
        Ball ball = game.getBall();
        ball.setPosX(50);
        ball.setPosY(0);
        ball.setStepY(-5);
        ball.move(game.getDimensionY());

        assertTrue(ball.getStepY()>0);

        ball.setPosY(100);
        assertFalse(ball.getStepY()<0);
    }

    @Test
    public void testCollisionWithBottom(){
        Ball ball = game.getBall();
        ball.setPosX(50);
        ball.setPosY(game.getDimensionY()- ball.getRadius());
        ball.setStepY(5);
        ball.move(game.getDimensionY());

        assertTrue(ball.getStepY()<0);

        ball.setPosY(100);
        assertFalse(ball.getStepY()>0);
    }
}
