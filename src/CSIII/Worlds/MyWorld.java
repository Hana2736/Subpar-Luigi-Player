package CSIII.Worlds;

import CSIII.Objects.*;
import CSIII.System.MarioClient;
import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.World;

import java.util.*;

public class MyWorld extends World {
    public static boolean allLoaded;
    public static double cameraX = 0;
    public static double cameraY = 0;
    public static String tileSet = "tiles";
    public static Mario player = null;
    private static int[][] tiles2 = {
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 40, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 00, 00, 00, 20, 20, 20, 20, 20, 00, 00, 00, 33},
            {31, 10, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 00, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 00, 00, 00, 00, 00, 20, 00, 20, 20, 00, 20, 20, 00, 00, 00, 00, 33},
            {31, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 00, 20, 00, 00, 00, 00, 00, 00, 00, 20, 00, 00, 00, 00, 00, 00, 00, 00, 20, 00, 20, 00, 00, 20, 20, 00, 00, 00, 33},
            {31, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 00, 21, 00, 00, 00, 00, 00, 00, 00, 21, 00, 00, 00, 00, 00, 00, 00, 00, 21, 00, 21, 00, 00, 21, 21, 00, 00, 00, 33}
    };
    private static int[][] tiles = {
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 40, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 22, 22, 22, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 20, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 21, 00, 22, 00, 00, 00, 00, 33},
            {31, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 00, 22, 22, 22, 00, 00, 22, 21, 21, 21, 21, 21, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 20, 20, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 20, 20, 22, 22, 21, 00, 00, 00, 21, 00, 00, 00, 00, 33},
            {31, 10, 00, 00, 00, 00, 00, 00, 00, 00, 20, 21, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 20, 20, 20, 20, 00, 20, 20, 20, 20, 20, 20, 20, 20, 21, 00, 21, 00, 21, 21, 00, 00, 20, 20, 20, 20, 21, 21, 00, 22, 00, 00, 21, 21, 00, 00, 00, 00, 21, 00, 00, 00, 00, 33},
            {31, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 21, 00, 00, 00, 00, 21, 00, 21, 00, 00, 22, 00, 21, 00, 00, 00, 00, 21, 21, 00, 00, 22, 21, 00, 00, 00, 21, 00, 00, 00, 00, 00, 21, 00, 00, 00, 00, 21, 00, 00, 00, 00, 33},
            {31, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 21, 00, 21, 00, 00, 21, 00, 21, 00, 00, 00, 00, 21, 00, 00, 00, 21, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33},
            {31, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 00, 20, 00, 00, 00, 00, 00, 00, 22, 22, 22, 22, 00, 00, 00, 00, 00, 00, 21, 00, 21, 00, 00, 21, 21, 00, 00, 00, 33, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 33}};
    public List<GridBound> panels = new LinkedList<>();
    boolean gameOver = false;
    int winWalkRight = 0;
    boolean intro = false;
    long time;
    int countdown = 300;
    Map<Integer, Class> possibleTiles = new HashMap<>();

    public MyWorld() {
        setBackground(new MayflowerImage("img/bgOW.png"));
        Mayflower.loadSound("sfx/intro.wav");
        Mayflower.loadSound("sfx/main.wav");
        Mayflower.loadSound("sfx/pop.wav");
        Mayflower.loadSound("sfx/win.wav");
        Mayflower.loadSound("sfx/die.wav");
        Mayflower.loadSound("sfx/up.wav");
        Mayflower.loadSound("sfx/down.wav");
        Mayflower.loadSound("sfx/item.wav");
        possibleTiles.put(2, Block.class);
        possibleTiles.put(3, GridBound.class);
        possibleTiles.put(4, GoalPost.class);
        setPaintOrder(Block.class, GoalPost.class, Mario.class, GridBound.class, Bubble.class, Countdown.class);
        setActOrder(Mario.class, Block.class);
        // all potential playable objects and spawns
        drawMap(tiles, true);
        Mayflower.playSound("sfx/intro.wav");
        time = System.currentTimeMillis();
        TitleScreen.c.send("loaded");
    }

    public static void clampCamera() {
        double cameraMax;
        if (cameraX > -96) {
            cameraX = -96;
        }
        switch (tileSet) {
            case "tiles":
                cameraMax = (tiles[0].length - 1) * 96;
                break;
            case "tiles2":
                cameraMax = (tiles2[0].length - 1) * 96;
                break;
            default:
                cameraMax = 800;
        }
        cameraMax -= 800;
        if (cameraX < -cameraMax) {
            cameraX = -cameraMax;
        }
        if ((-cameraY) + 600 > (tileSet.equals("tiles") ? tiles.length * 96 : tiles2.length * 96)) {
            cameraY = (-(tileSet.equals("tiles") ? tiles.length * 96 : tiles2.length * 96)) + 600;
        }
    }

    public void act() {
        if (player.winner != null) {
            if (!gameOver) {
                Mayflower.stopSound("sfx/main.wav");
                Mayflower.stopSound("sfx/intro.wav");
                Mayflower.playSound("sfx/win.wav");
                gameOver = true;
            }
            player.winner.keyRight = false;
            player.winner.keyUp = false;
            player.winner.keyDown = false;
            player.winner.keyLeft = false;
            player.winner.keyShift = false;
            player.local = false;
            player.localBtns = false;
            player.winner.local = true;
            player.winner.localBtns = false;
            MarioClient.otherPlayer1.localBtns = false;
            MarioClient.otherPlayer2.localBtns = false;
            MarioClient.otherPlayer3.localBtns = false;
            winWalkRight++;
            if (winWalkRight < 75) {
                player.winner.keyRight = true;
            }
            if (winWalkRight > 120 && player.winner.xGrav == 0) {
                player.winner.winAnim = true;
            }
            if (winWalkRight == 560) {
                System.exit(0);
            }
        }

        if (!intro) {
            if (System.currentTimeMillis() - time >= 2011.4285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714285714286) {
                Mayflower.playSound("sfx/main.wav");
                time = System.currentTimeMillis();
                intro = true;
            }
        }
        if (!gameOver && System.currentTimeMillis() - time >= 39413.401360544217687074829931972789115646258503401360544217687074829931972789115646258503401360544217687074829931972789115646258503401360544217687074829931972789115646258503401360544217687074829931973) {
            Mayflower.playSound("sfx/main.wav");
            time = System.currentTimeMillis();
        }
        countdown--;
        if (countdown == 0) {
            for (int i = 0; i < panels.size(); i++) {
                removeObject(panels.remove(i));
                i--;
            }
        }
        if (countdown < -350) {
            countdown = -350;
        }
        if (countdown == -180) {
            player.bubble = false;
            MarioClient.otherPlayer1.bubble = false;
            MarioClient.otherPlayer2.bubble = false;
            MarioClient.otherPlayer3.bubble = false;
        }
        if (!allLoaded) {
            countdown = 300;
        }
    }

    //map generation, assigns every used object to an int
    public void drawMap(int[][] mapArr, boolean panel) {
        removeAll();
        for (int r = 0; r < mapArr.length; r++) {
            for (int c = 0; c < mapArr[0].length; c++) {
                int myX = 0;
                if (mapArr[r][c] == 10) {
                    if (MarioClient.player.equals("Mario")) {
                        myX = c * 96 + (75 * 3);
                        MarioClient.otherPlayer1 = new Mario(false, "Luigi");
                        MarioClient.otherPlayer2 = new Mario(false, "BlueToad");
                        MarioClient.otherPlayer3 = new Mario(false, "YellowToad");
                    }
                    if (MarioClient.player.equals("Luigi")) {
                        myX = c * 96 + (75 * 2);
                        MarioClient.otherPlayer1 = new Mario(false, "Mario");
                        MarioClient.otherPlayer2 = new Mario(false, "BlueToad");
                        MarioClient.otherPlayer3 = new Mario(false, "YellowToad");
                    }
                    if (MarioClient.player.equals("BlueToad")) {
                        myX = c * 96 + 75;
                        MarioClient.otherPlayer1 = new Mario(false, "Mario");
                        MarioClient.otherPlayer2 = new Mario(false, "Luigi");
                        MarioClient.otherPlayer3 = new Mario(false, "YellowToad");
                    }
                    if (MarioClient.player.equals("YellowToad")) {
                        myX = c * 96;
                        MarioClient.otherPlayer1 = new Mario(false, "Mario");
                        MarioClient.otherPlayer2 = new Mario(false, "Luigi");
                        MarioClient.otherPlayer3 = new Mario(false, "BlueToad");
                    }
                    myX += 64;
                    player = new Mario(true, MarioClient.player);
                    addObject(player, myX, r * 96);
                    player.setScrollLocation(myX, r * 96);
                    player.spawnX = myX;
                    player.spawnY = r * 96;
                    player.bubble = true;
                    addObject(MarioClient.otherPlayer1, myX, r * 96);
                    MarioClient.otherPlayer1.setScrollLocation(Integer.MIN_VALUE, r * 96);
                    MarioClient.otherPlayer1.bubble = true;
                    addObject(MarioClient.otherPlayer2, Integer.MIN_VALUE, r * 96);
                    MarioClient.otherPlayer2.bubble = true;
                    MarioClient.otherPlayer2.setScrollLocation(Integer.MIN_VALUE, r * 96);
                    addObject(MarioClient.otherPlayer3, Integer.MIN_VALUE, r * 96);
                    MarioClient.otherPlayer3.bubble = true;
                    MarioClient.otherPlayer3.setScrollLocation(Integer.MIN_VALUE, r * 96);
                    if (panel) {
                        addObject(new Countdown(countdown + 60), 0, 0);
                        for (int i = 0; i < 4; i++) {
                            GridBound b = new GridBound(2);
                            b.setScrollLocation((c + i) * 96, (r - 2) * 96);
                            addObject(b, (c + i) * 96, (r - 2) * 96);
                            panels.add(b);
                        }
                        for (int i = 0; i < 4; i++) {
                            GridBound b = new GridBound(0);
                            b.setScrollLocation((c + i) * 96, (r + 1) * 96);
                            addObject(b, (c + i) * 96, (r + 1) * 96);
                            panels.add(b);
                        }
                        for (int i = 0; i < 2; i++) {
                            GridBound b = new GridBound(3);
                            b.setScrollLocation((c + 4) * 96, (r - i) * 96);
                            addObject(b, (c + 4) * 96, (r - i) * 96);
                            panels.add(b);
                        }
                        for (int i = 0; i < 2; i++) {
                            GridBound b = new GridBound(1);
                            b.setScrollLocation((c - 1) * 96, (r - i) * 96);
                            addObject(b, (c - 1) * 96, (r - i) * 96);
                            panels.add(b);
                        }
                    }
                } else {
                    int type = Integer.parseInt(String.valueOf(mapArr[r][c]).substring(0, 1));
                    int param = 0;
                    if (String.valueOf(mapArr[r][c]).length() > 1) {
                        param = Integer.parseInt(String.valueOf(mapArr[r][c]).substring(1));
                    }
                    ScrollActor a = null;
                    if (type != 0) {
                        try {
                            a = (ScrollActor) possibleTiles.get(type).newInstance();
                        } catch (Exception e) {
                            try {
                                a = (ScrollActor) possibleTiles.get(type).getConstructor(int.class).newInstance(param);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (a != null) {
                        a.setScrollLocation(c * 96, r * 96);
                        addObject(a, c * 96, r * 96);
                    }
                }
            }
        }

    }


    public void removeAll() {
        //removes everything in the world, used for switching levels and screens
        List<Actor> objects = new ArrayList<Actor>();
        objects = getObjects();
        for (int i = 0; i < objects.size(); i++) {
            removeObject(objects.get(i));
            i--;
        }
        removeText(10, 30);
    }
}