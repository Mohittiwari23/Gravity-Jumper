package object;

import utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

import static userinterface.GameScreen.groundY;

public class Worm extends Enemy {

    private BufferedImage image;
    private int posX, posY;
    private Rectangle rect;

    public Worm(){
        image = Resources.getResourceImg("resources/worm.png");
        posX = 810;
        posY= groundY-15;
        rect = new Rectangle();
    }

    public void update(){
        posX -= 6;
        rect.x = posX;
        rect.y = posY;
        rect.width = 20;
        rect.height = 30;
    }

    public Rectangle getBound(){
        return rect;
    }

    public void draw(Graphics g){
        g.drawImage(image, posX,posY,72,48,null);
    }

    public boolean isOutOfScreen() {
        return (posX + 72 < 0);
    }
}
