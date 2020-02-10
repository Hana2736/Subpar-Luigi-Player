package CSIII.Objects;

public class Bubble extends ScrollActor {
    ScrollActor parent;

    public Bubble(ScrollActor parent) {
        setImage("img/Bubble.png");
        this.parent = parent;
    }

    @Override
    public void act() {
        setScrollLocation(parent.realX + parent.getWidth() / 2d - getWidth() / 2d, parent.realY + parent.getHeight() / 2d - getHeight() / 2d);
        scroll();
    }
}
