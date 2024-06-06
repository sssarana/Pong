package pong.controller;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector{
    private static Connection connection;
    /**
    * get the connection to the database
    * @return - connection to the database
    **/
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        if (connection==null || connection.isClosed())
        {
            createConnection();
        }
        return connection;
    }
    /**
     * create the connection to the database
     **/
    private static void createConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/assignment";
        String username="root";
        String password="";
        connection= DriverManager.getConnection(url,username, password);
    }
}