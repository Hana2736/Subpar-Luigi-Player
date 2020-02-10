package CSIII.Objects;

import mayflower.MayflowerImage;

public class GoalPostTape extends ScrollActor {
    double minY;
    double maxY;
    int speed = 3;

    public GoalPostTape(double minY, double maxY) {
        this.maxY = maxY;
        this.minY = minY;
        setImage(new MayflowerImage("img/GoalPostTape.png"));
    }

    @Override
    public void act() {
        setScrollLocation(realX, realY + speed);
        if (realY >= maxY || realY <= minY) {
            speed = speed * -1;
            setScrollLocation(realX, realY + speed);
        }
        scroll();
    }

    public void die() {
        GoalPostTapeParticle p = new GoalPostTapeParticle();
        p.realX = realX + getWidth() / 2d - 32;
        p.realY = realY - 32;
        getWorld().addObject(p, Integer.MIN_VALUE, Integer.MAX_VALUE);
        getWorld().removeObject(this);
    }
}
