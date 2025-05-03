package userinterface;

import javax.swing.*;

public class GameWindow extends JFrame{

    private GameScreen gameScreen;

    public GameWindow(){
        super("Gravity Jumper");
        setSize(800,420);
        setLocation(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
    }

    public void startGame(){
        gameScreen.startGame();
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }


}
