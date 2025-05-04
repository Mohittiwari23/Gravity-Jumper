package userinterface;

import object.MobManager;
import object.Player;
import object.World;
import utils.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int gameState_Start = 0;
    public static final int gameState_Play = 1;
    public static final int gameState_Over = 2;
    public static final float gravity = .7f;
    public static final float gravity_neg = -0.7f;
    public static boolean gravityFlipped = false;
    public static final int groundY = 275;
    public static final int ceilingY = 175;
    private Thread thread;
    private Player player;
    private World world;
    private MobManager mobManager;

    private int gameState = gameState_Start;

    private BufferedImage imageGameStart, imageGameOver, imageDeadPlayer, imageReplayButton;

    public GameScreen(){
        thread = new Thread(this);
        player = new Player();
        player.setX(80);
        world = new World(this);
        mobManager = new MobManager(player);
        imageGameStart = Resources.getResourceImg("resources/start_text.png");
        imageGameOver = Resources.getResourceImg("resources/gameover_text.png");
        imageDeadPlayer = Resources.getResourceImg("resources/death.png");
        imageReplayButton = Resources.getResourceImg("resources/replay_button.png");
    }

    public void startGame(){
        thread.start();
    }

    public void run() {
        while(true){
            try {
                update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        switch (gameState){
            case gameState_Play:
                player.update();
                world.update();
                mobManager.update();
                if(!player.getState()){
                    gameState = gameState_Over;
                }
                break;
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        switch (gameState){
            case gameState_Start:
                player.draw(g,50,50);
                g.drawImage(imageGameStart, 200 ,50,350,300,null);
                break;

            case gameState_Play:
                world.draw(g);
                player.draw(g,50,50);
                mobManager.draw(g);
                break;

            case gameState_Over:
                world.draw(g);
                g.drawImage(imageDeadPlayer,80, groundY-10,50,50, null);
                mobManager.draw(g);
                g.drawImage(imageGameOver, 200 ,35,350,300,null);
                g.drawImage(imageReplayButton, 360 ,250,35,35,null);
                break;
        }

    }

    private void resetGame() {
        player = new Player();
        player.setX(80);
        mobManager = new MobManager(player);
        gravityFlipped = false;
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == gameState_Start){
                    gameState = gameState_Play;
                } else if (gameState == gameState_Play) {
                    player.jump();
                } else if (gameState == gameState_Over) {
                    resetGame();
                    gameState = gameState_Play;
                }
                break;
            case KeyEvent.VK_ENTER:
                if(gameState == gameState_Play){
                    if(gravityFlipped){
                        gravityFlipped=false;
                    }
                    else{
                        gravityFlipped=true;
                    }
                }
                break;

        }
    }

}
