package pong.controller;

import javafx.scene.control.Alert;
import pong.model.Game;

import java.io.*;

public class Serializer {
    private static Serializer serializer = new Serializer();
    private String filename;
    private Serializer(){
        filename = "pong.ser";
    }
    public static Serializer getSerializer() {
        return serializer;
    }

    /**
     * Serialize the game
     * @param game - game to serialize
     * **/
    public boolean serialize(Game game){

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outstream = new ObjectOutputStream(file);

            outstream.writeObject(game);

            outstream.close();
            file.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Deserialize the game
     * **/
    public Object deserialize(){
        Game game_object = null;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream instream = new ObjectInputStream(file);

            game_object = (Game)instream.readObject();

            instream.close();
            file.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return game_object;
    }

}
