package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    protected double posX;
    protected double posY;
    protected transient Color color;

    //setters and getters
    public void setPosX(double posx){
        posX = posx;
    }

    public void setPosY(double posy){
        posY = posy;
    }

    public void setColor(Color col){
        color = col;
    }

    public double getPosX(){
        return posX;
    }

    public double getPosY(){
        return posY;
    }

    public Color getColor(){
        return color;
    }
}
