package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Racket extends Shape implements Resizable{
    private double width;
    private double height;

    private int racketSpeed = 30;
    //constructors
    public Racket(){
        this(30, 150, 30, 200, Color.YELLOW);
    }

    public Racket(double wid, double hei, int posx, int posy, Color col){
        width = wid;
        height = hei;
        posX = posx;
        posY = posy;
        color = col;
    }
    //setters and getters
    public void setWidth(double wid){
        width = wid;
    }

    public void setHeight(double hei){
        height = hei;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }
    public int getRacketSpeed() {
        return racketSpeed;
    }

    public void setRacketSpeed(int racketSpeed) {
        this.racketSpeed = racketSpeed;
    }
    /**
     * Change Racket's size to default
     **/
    public void setInitialSize(){
        setWidth(30);
        setHeight(150);
    }
    public void moveUp(){
        if (posY > 0)
            setPosY(posY - racketSpeed);
    }
    public void moveDown(double dimensionY){
        if(posY < dimensionY- height)
            setPosY(posY + racketSpeed);
    }
    @Override
    public void resizeX(double factor) {
        this.posX=this.posX*factor;
        this.width = this.width*factor;
    }

    @Override
    public void resizeY(double factor) {
        this.posY=this.posY*factor;
        this.height = this.height*factor;
    }
}
