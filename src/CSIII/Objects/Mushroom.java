package CSIII.Objects;

import CSIII.Worlds.MyWorld;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class Mushroom extends ScrollActor {
    int cnt = 0;

    public Mushroom() {
        setImage(new MayflowerImage("img/mushroom.png"));
        Mayflower.playSound("sfx/item.wav");
        realX = -MyWorld.cameraX + 368;
        realY = -MyWorld.cameraY - 65;
    }

    @Override
    public void act() {
        cnt++;
        if (cnt == 15) {
            getImage().setTransparency(100);
        }
        if (cnt == 30) {
            getImage().setTransparency(0);
            cnt = 0;
        }
        realY += 2;
        scroll();
    }
}
