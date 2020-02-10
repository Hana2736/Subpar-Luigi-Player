package CSIII.Objects;

public class GridBound extends Block {
    public GridBound(int side) {
        super(0);
        setImage("img/Grid.png");
        setRotation(90 * side);
    }

    public void act() {
        scroll();
    }
}
