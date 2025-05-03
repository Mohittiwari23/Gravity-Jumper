package object;

import userinterface.GameScreen;
import utils.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private List<ImageLayout> listImage;
    private BufferedImage imageLayout1, imageLayout2, imageLayout3;
    private Random random;

    public World(GameScreen game){
        random = new Random();
        imageLayout1 = Resources.getResourceImg("resources/layout1.png");
        imageLayout2 = Resources.getResourceImg("resources/layout2.png");
        imageLayout3 = Resources.getResourceImg("resources/layout3.png");
        listImage = new ArrayList<ImageLayout>();
        int numberOfTiles = 5;
        for(int i = 0; i< numberOfTiles +2; i++){
            ImageLayout imageLayout = new ImageLayout();
            imageLayout.posx = (int) (i * 600);
            imageLayout.height = 400;
            imageLayout.width = 600;
            imageLayout.image = getImage();
            listImage.add(imageLayout);
        }
    }

    public void update(){
        for(ImageLayout imageLayout:listImage){
            imageLayout.posx-=6;
        }
        ImageLayout firstelement = listImage.get(0);

        if(firstelement.posx + 600 < 0){
            firstelement.posx = listImage.get(listImage.size() - 1).posx + 600;
            listImage.add(listImage.get(0));
            listImage.remove(0);
        }

    }

    public void draw(Graphics g){
        for(ImageLayout imageLayout:listImage) {
            g.drawImage(imageLayout.image, imageLayout.posx, 0, imageLayout.width, imageLayout.height, null);
        }
    }

    private BufferedImage getImage(){
        int i = random.nextInt(5);
        return switch (i) {
            case 0 -> imageLayout2;
            case 1 -> imageLayout3;
            default -> imageLayout1;
        };
    }

    private class ImageLayout{
        int posx;
        int width;
        int height;
        BufferedImage image;
    }
}
