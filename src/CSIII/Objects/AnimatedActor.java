package CSIII.Objects;

import CSIII.System.Animation;
import mayflower.MayflowerImage;

/**
 * Write a description of class AnimatedActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AnimatedActor extends GravityActor {
    // instance variables - replace the example below with your own
    private Animation animation;

    /**
     * Constructor for objects of class AnimatedActor
     */
    //timer for the frames per second
    public AnimatedActor() {
        // initialise instance variables
    }


    //sets the animation of the actor
    public void setAnimation(Animation a) {
        // put your code here
        animation = a;
    }

    //runs through the frames in the img file
    public void act() {
        super.act();
        //animation process
        if (animation != null) {
            MayflowerImage nextFrame = animation.getNextFrame();
            setImage(nextFrame);
        }


    }
}
