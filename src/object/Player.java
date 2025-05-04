package object;

import utils.Animation;
import utils.Resources;

import java.awt.*;

import static userinterface.GameScreen.*;

public class Player {
    private float x = 0;
    private float y = groundY;
    private float y_neg = ceilingY;
    private float speedY = 0;
    private Animation playerrun;
    private Rectangle rect;
    private boolean isAlive = true;

    public Player(){
        playerrun = new Animation(100);
    }

    public void update(){
        playerrun.update();

        if(gravityFlipped) {
            playerrun.clearFrame();
            playerrun.addFrame(Resources.getResourceImg("resources/walking_neg1.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking_neg2.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking_neg3.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking_neg4.png"));
            rect = new Rectangle();
            if (y <= ceilingY - playerrun.getFrame().getHeight()) {
                speedY = 0;
                y = ceilingY - playerrun.getFrame().getHeight();
            } else {
                speedY += gravity_neg;
                y += speedY;
            }
        }
        else{
            playerrun.clearFrame();
            playerrun.addFrame(Resources.getResourceImg("resources/walking1.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking2.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking3.png"));
            playerrun.addFrame(Resources.getResourceImg("resources/walking4.png"));
            rect = new Rectangle();
            if (y >= groundY - playerrun.getFrame().getHeight()) {
                speedY = 0;
                y = groundY - playerrun.getFrame().getHeight();
            } else {
                speedY += gravity;
                y += speedY;
            }
        }
        rect.x = (int) x;
        rect.y = (int) y;
        rect.width = 20;
        rect.height = 30;
    }

    public Rectangle getBound(){
        return rect;
    }

    public void draw(Graphics g, int width, int height){
        if(gravityFlipped){
            g.drawImage(playerrun.getFrame(), (int) x, (int) y_neg, width, height, null);
        }
        else {
            g.drawImage(playerrun.getFrame(), (int) x, (int) y, width, height, null);
        }
    }

    public void jump(){
        speedY = -12;
        y += speedY;
    }

    public void setAlive(boolean alive){
        isAlive = alive;
    }

    public boolean getState(){
        return isAlive;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
