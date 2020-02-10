package CSIII.System;

import mayflower.MayflowerImage;

/**
 * Write a description of class Animation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animation {
    // instance variables - replace the example below with your own
    private MayflowerImage[] frames;
    private int framerate;
    private int currentFrame;
    private long time;

    /**
     * Constructor for objects of class Animation
     */
    public Animation(int fps, String[] imgs) {
        time = System.currentTimeMillis();
        // initialise instance variables
        framerate = fps;
        frames = new MayflowerImage[imgs.length];
        for (int i = 0; i < imgs.length; i++) {
            frames[i] = new MayflowerImage(imgs[i]);
        }
        currentFrame = 0;
        //setTransparency(10);
    }

    //returns the frame
    public int getFrameRate() {
        // put your code here
        return framerate;
    }

    //gets the next frame
    public MayflowerImage getNextFrame() {
        MayflowerImage x = frames[currentFrame];
        if (System.currentTimeMillis() - 1000 / framerate > time) {
            time = System.currentTimeMillis();
            currentFrame++;
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
        return x;
    }

    //scales an image
    public void scale(int w, int h) {
        for (MayflowerImage frame : frames) {
            frame.scale(w, h);
        }
    }

    //makes an image more transparent
    public void setTransparency(int x) {
        for (MayflowerImage frame : frames) {
            frame.setTransparency(x);
        }
    }

    //mirrors an image
    public void mirrorHorizontally() {
        for (MayflowerImage frame : frames) {
            frame.mirrorHorizontally();
        }
    }

    //sets the bounds of the image
    public void setBounds(int x, int y, int w, int h) {
        for (MayflowerImage frame : frames) {
            frame.crop(x, y, w, h);
        }
    }
}
