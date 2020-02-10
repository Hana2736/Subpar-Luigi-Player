package CSIII.Objects;

/**
 * Write a description of class Block here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Block extends ScrollActor {
    // instance variables - replace the example below with your own


    //set image to the block specified
    public Block(int style) {
        // set image
        setImage("img/Ground/" + style + ".png");
    }


    public void act() {
        scroll();
    }
}
