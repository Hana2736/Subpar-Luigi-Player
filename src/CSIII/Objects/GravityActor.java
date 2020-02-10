package CSIII.Objects;

/**
 * Write a description of class GravityActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GravityActor extends ScrollActor {
    /// instance variables - replace the example below with your own

    private int gravity;

    /**
     * Constructor for objects of class GravityActor
     */
    //set velocity of gravity
    public GravityActor() {
        /*// initialise instance variables
        landed = false;
        speed = 0;*/
        gravity = 2;
    }


    //check to see if the actor is blocked by a block
    public void act() {
        
        /*landed = isBlocked();
        if(landed)
        {
            setScrollLocation(realX, realY-speed);
            speed=0;
            landed = false;
        }
        else{
            setScrollLocation(realX, realY+speed);
            speed++;
        }*/
        //setScrollLocation(realX, realY + gravity);
        //setScrollLocation(realX, realY-gravity);
    }

}

