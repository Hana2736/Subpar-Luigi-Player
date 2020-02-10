package CSIII.Objects;

import CSIII.Worlds.MyWorld;
import mayflower.Actor;

public class ScrollActor extends Actor {
    public double realX;
    public double realY;
    public double oldX;
    public double oldY;

    public void setScrollLocation(double x, double y) {
        realY = y;
        realX = x;
        scroll();
    }

    public void scroll() {
        setLocation(MyWorld.cameraX + realX, MyWorld.cameraY + realY);
    }

    @Override
    public void act() {
        oldX = realX;
        oldY = realY;
    }
}
