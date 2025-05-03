package object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MobManager {
    private List<Enemy> enemies;
    private Player player;

    public MobManager(Player player){
        this.player = player;
        enemies = new ArrayList<Enemy>();

        Worm worm = new Worm();
        enemies.add(worm);
    }

    public void update(){
        for(Enemy e: enemies){
            e.update();
            if(e.getBound().intersects(player.getBound())){
                player.setAlive(false);
            }
        }

        Enemy currentMob = enemies.get(0);

        if(currentMob.isOutOfScreen()){
            enemies.remove(currentMob);
            Worm worm = new Worm();
            enemies.add(worm);
        }
    }

    public void draw(Graphics g){
        for(Enemy e: enemies){
            e.draw(g);
        }
    }
}
