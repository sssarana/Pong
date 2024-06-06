package pong.model;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class Ball extends Shape implements Resizable {
    private double radius;
    private int speedIncrease;
    private int bounceCounter;
    private double stepX;
    private double stepY;
    //constructors
    public Ball(){
        this(40, 300, 80, Color.PURPLE, 20);
    }

    public Ball(double rad, int posx, int posy, Color col, int new_speedIncrease){
        radius = rad;
        posX = posx;
        posY = posy;
        color = col;
        stepX = 5;
        stepY = -5;
        speedIncrease = new_speedIncrease;
        bounceCounter = 0;
    }

    //setters and getters
    public void setRadius(double rad){
        radius = rad;
    }

    public double getRadius(){
        return radius;
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }

    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }
    public double getStepX() {
        return stepX;
    }

    public void setStepX(double stepX) {
        this.stepX = stepX;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepY(double stepY) {
        this.stepY = stepY;
    }

    public int getBounceCounter() {
        return bounceCounter;
    }

    public void setBounceCounter(int bounceCounter) {
        this.bounceCounter = bounceCounter;
    }
    @Override
    public void resizeX(double factor) {
        this.posX=this.posX*factor;
    }

    @Override
    public void resizeY(double factor) {
        this.posY=this.posY*factor;
        this.radius = this.radius*factor;
    }
    /**
     * Method used for ball to move and bounce when it reaches the top or bottom
     * Speed of the ball increases after x bounces
     * @param dimensionY - height of the game
     **/
    public void move(double dimensionY) {
        posY += stepY;
        posX += stepX;
        if (posY < 0 || posY > dimensionY - 2 * radius) {
            bounceCounter++;
            changeDirectionY();
        }
        if(bounceCounter == speedIncrease){
            stepX=stepX*1.3;
            stepY=stepY*1.3;
            bounceCounter = 0;
        }
    }
    /**
     * Change the position of the ball according to the parameters
     * @param x - position of the ball on X axis
     * @param y  - position of the ball on Y axis
     **/
    public void reposition(double x, double y) {
        setPosX(x);
        setPosY(y);
        changeDirectionX();
    }
    public void changeDirectionX(){
        stepX=-stepX;
    }
    public void changeDirectionY(){
        stepY=-stepY;
    }

}