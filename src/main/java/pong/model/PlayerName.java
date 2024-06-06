package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class PlayerName implements Resizable, Serializable {
    private String name;
    private double positionX;
    private transient Color colour;
    private double fontSize;

    public PlayerName(){
        this("player", 100, Color.YELLOW, 30);
    }
    public PlayerName(String aName, double posX, Color new_colour, double new_fontSize){
        name = aName;
        positionX = posX;
        colour = new_colour;
        fontSize = new_fontSize;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }
    @Override
    public void resizeX(double factor) {
        this.positionX=this.positionX*factor;
        this.fontSize = this.fontSize * factor;
    }

    @Override
    public void resizeY(double factor) {
        this.fontSize = this.fontSize * factor;
    }
}
