package pong.controller;

import pong.model.Game;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager{
    /**
     * get game from the database by name
     * @param name - name of the game to load
     * @return - null if couldn't load the game, the game requested if loading successful
     **/
    public Game getGame(String name) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from game where name='" + name + "';";
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            GameBuilder builder = new GameBuilder().
                    withName(rs.getString("name")).
                    withPlayer1Name(rs.getString("player1Name")).
                    withPlayer2Name(rs.getString("player2Name")).
                    withPlayer1Score(rs.getInt("player1Score")).
                    withPlayer2Score(rs.getInt("player1Score")).
                    withEndScore(rs.getInt("endScore"));
            Game game = builder.build();
            return game;
        }
            return null;
    }
    /**
     * get the latest game from the database
     * @return - null if couldn't load the game, the latest game if loading successful
     **/
    public Game getLatestGame() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM game WHERE id=(SELECT MAX(id) FROM game);";
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            GameBuilder builder = new GameBuilder().
                    withName(rs.getString("name")).
                    withPlayer1Name(rs.getString("player1Name")).
                    withPlayer2Name(rs.getString("player2Name")).
                    withPlayer1Score(rs.getInt("player1Score")).
                    withPlayer2Score(rs.getInt("player1Score")).
                    withEndScore(rs.getInt("endScore"));
            Game game = builder.build();
            game.getBall().reposition(game.getDimensionX()/2, game.getDimensionY()/2);
            return game;
        }
        return null;
    }
    /**
     * insert the game to the database
     * @param game - the game to insert in the database
     * @return - false if the game was inserted, true if it was
     **/
    public boolean insertGame(Game game) throws ClassNotFoundException, SQLException{
        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement();

        String query = "insert into game (player1Name, player2Name,player1Score,player2Score, name, endScore) "+
                " values('" + game.getPlayer1().getPlayerName().getName() + "','" +
                game.getPlayer2().getPlayerName().getName() + "',"+
                game.getPlayer1().getScore()+","+
                game.getPlayer2().getScore()+","+
                "'"+ game.getName()+"',"+
                game.getEndScore()+ ");";

        System.out.println(query);
        int is_inserted = statement.executeUpdate(query);
        return is_inserted != 0;
    }
}