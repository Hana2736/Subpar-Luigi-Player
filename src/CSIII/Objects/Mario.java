package CSIII.Objects;

import CSIII.System.Animation;
import CSIII.System.MarioClient;
import CSIII.Worlds.MyWorld;
import CSIII.Worlds.TitleScreen;
import mayflower.Keyboard;
import mayflower.Mayflower;

public class Mario extends MoveableAnimatedActor {
    public boolean bubble = false;
    public Bubble bubbleMade;
    public String name;
    public Mario winner = null;
    int frame = 7;

    public Mario(boolean local, String name) {
        this.name = name;
        this.local = local;
        localBtns = local;
        setUpSmall();
        setUpBig();
    }

    public void act() {
        if (size == -1) {
            isDead = true;
        }
        if (bubble && bubbleMade == null) {
            bubbleMade = new Bubble(this);
            getWorld().addObject(bubbleMade, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        if (!bubble && bubbleMade != null) {
            getWorld().removeObject(bubbleMade);
            if (local) {
                Mayflower.playSound("sfx/pop.wav");
            }
            bubbleMade = null;
        }
        super.act();
        if (local) {
            if (frame == 7) {
                TitleScreen.c.send("update:" + realX + ":" + realY + ":" + state + ":" + Mayflower.isKeyDown(Keyboard.KEY_RIGHT) + ":" + Mayflower.isKeyDown(Keyboard.KEY_LEFT) + ":" + Mayflower.isKeyDown(Keyboard.KEY_Z) + ":" + Mayflower.isKeyDown(Keyboard.KEY_X) + ":" + Mayflower.isKeyDown(Keyboard.KEY_DOWN) + ":" + MyWorld.tileSet + ":" + MarioClient.player + ":" + grav + ":" + xGrav + ":" + bubble + ":" + size);
                frame = 0;
            } else {
                frame++;
            }
            if (!isDead && !skipAct) {
                MyWorld.cameraX = Mayflower.getWidth() / 2d - realX - (getWidth() / 2d);
                MyWorld.cameraY = Mayflower.getHeight() / 2d - realY - (getHeight() / 2d) + 100;
            }
            MyWorld.clampCamera();
            //    if (MyWorld.cameraY < 600) {
            //        MyWorld.cameraY = 600;
            //    }
        } else {
            if (winner != null) {
                MyWorld.cameraX = Mayflower.getWidth() / 2d - winner.realX - (winner.getWidth() / 2d);
                MyWorld.cameraY = Mayflower.getHeight() / 2d - winner.realY - (winner.getHeight() / 2d) + 100;
            } else {
                if (!MarioClient.tileSet.equals(MyWorld.tileSet)) {
                    setLocation(-9999999, -9999999);
                    return;
                }
                if (rx) {
                    setScrollLocation(marioX, marioY);
                    rx = false;
                    scroll();
                }
            }
        }
        if (local) {
            setScrollLocation(realX, realY);
            scroll();
        }

    }

    void setUpSmall() {
        String[] arr = new String[2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "img/" + name + "/Run/Small/" + i + ".png";
        }
        walkRight = new Animation(15, arr);
        String[] arr2 = new String[]{"img/" + name + "/Run/Small/0.png"};
        idle = new Animation(1, arr2);
        walkLeft = new Animation(15, arr);
        walkLeft.mirrorHorizontally();
        String[] arr4 = new String[]{"img/" + name + "/Fall/Small/0.png"};
        fallRight = new Animation(1, arr4);
        fallLeft = new Animation(1, arr4);
        fallLeft.mirrorHorizontally();
        idleLeft = new Animation(1, arr2);
        idleLeft.mirrorHorizontally();
        String[] arr7 = new String[]{"img/" + name + "/Jump/Small/0.png"};
        jumpRight = new Animation(1, arr7);
        jumpLeft = new Animation(1, arr7);
        jumpLeft.mirrorHorizontally();
        String[] arr9 = new String[2];
        for (int i = 0; i < arr9.length; i++) {
            arr9[i] = "img/" + name + "/Sprint/Small/" + i + ".png";
        }
        sprint = new Animation(20, arr9);
        sprintLeft = new Animation(20, arr9);
        sprintLeft.mirrorHorizontally();
        arr9 = new String[2];
        for (int i = 0; i < arr9.length; i++) {
            arr9[i] = "img/" + name + "/Dead/" + i + ".png";
        }
        deadAnim = new Animation(5, arr9);
        arr9 = new String[]{"img/" + name + "/Dead/0.png"};
        dead = new Animation(1, arr9);
        arr9 = new String[]{"img/" + name + "/Slide/Small/0.png"};
        slide = new Animation(1, arr9);
        slideLeft = new Animation(1, arr9);
        slideLeft.mirrorHorizontally();
        arr9 = new String[]{"img/" + name + "/Win/Small/0.png"};
        win = new Animation(1, arr9);
    }

    void setUpBig() {
        String[] arr = new String[3];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "img/" + name + "/Run/Big/" + i + ".png";
        }
        walkRightBig = new Animation(15, arr);
        String[] arr2 = new String[]{"img/" + name + "/Run/Big/0.png"};
        idleBig = new Animation(1, arr2);
        walkLeftBig = new Animation(15, arr);
        walkLeftBig.mirrorHorizontally();
        String[] arr4 = new String[]{"img/" + name + "/Fall/Big/0.png"};
        fallRightBig = new Animation(1, arr4);
        fallLeftBig = new Animation(1, arr4);
        fallLeftBig.mirrorHorizontally();
        idleLeftBig = new Animation(1, arr2);
        idleLeftBig.mirrorHorizontally();
        String[] arr7 = new String[]{"img/" + name + "/Jump/Big/0.png"};
        jumpRightBig = new Animation(1, arr7);
        jumpLeftBig = new Animation(1, arr7);
        jumpLeftBig.mirrorHorizontally();
        String[] arr9 = new String[3];
        for (int i = 0; i < arr9.length; i++) {
            arr9[i] = "img/" + name + "/Sprint/Big/" + i + ".png";
        }
        sprintBig = new Animation(20, arr9);
        sprintLeftBig = new Animation(20, arr9);
        sprintLeftBig.mirrorHorizontally();
        arr9 = new String[]{"img/" + name + "/Slide/Big/0.png"};
        slideBig = new Animation(1, arr9);
        slideLeftBig = new Animation(1, arr9);
        slideLeftBig.mirrorHorizontally();
        arr9 = new String[]{"img/" + name + "/Win/Big/0.png"};
        winBig = new Animation(1, arr9);
    }
}
