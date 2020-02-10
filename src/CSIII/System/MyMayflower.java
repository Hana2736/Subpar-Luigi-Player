package CSIII.System;

import CSIII.Worlds.TitleScreen;
import mayflower.Mayflower;
import mayflower.World;

public class MyMayflower extends Mayflower {
    //Constructor
    public MyMayflower() {
        //Create a window with 800x600 resolution
        super("Platformer" + Math.random() * 1000, 800, 600);
    }

    public void init() {
        //Change this to true to run this program in fullscreen mode
        Mayflower.setFullScreen(false);
        World w = new TitleScreen();
        //World w =  new MyWorld();
        Mayflower.setWorld(w);
    }
}
