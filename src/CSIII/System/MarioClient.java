package CSIII.System;

import CSIII.Objects.Mario;
import CSIII.Worlds.MyWorld;
import CSIII.Worlds.TitleScreen;
import mayflower.net.Client;

public class MarioClient extends Client {
    public static Mario otherPlayer1;
    public static Mario otherPlayer2;
    public static Mario otherPlayer3;
    public static boolean start;
    public static String player = "jreniohrewjghrw";
    public static String tileSet = "tiles";
    public String name1 = "krykyrkkkry";
    public String name2 = "tykjmrdtkmrykm";
    public String name3 = "ntdgmnedtrmrdtkm";

    @Override
    public void process(String s) {
        if (s.equals("allLoaded!")) {
            MyWorld.allLoaded = true;
        }
        if (s.startsWith("update:") || s.contains("killed") || s.contains("winner is ")) {
            Mario otherPlayer = null;
            if (s.contains(player)) {
                otherPlayer = MyWorld.player;
            }
            if (s.contains(name1)) {
                otherPlayer = otherPlayer1;
            }
            if (s.contains(name2)) {
                otherPlayer = otherPlayer2;
            }
            if (s.contains(name3)) {
                otherPlayer = otherPlayer3;
            }
            if (otherPlayer != null) {
                if (s.startsWith("update:")) {
                    String[] stuff = s.split(":");
                    otherPlayer.marioX = Double.parseDouble(stuff[1]); //Player 1
                    otherPlayer.marioY = Double.parseDouble(stuff[2]); // Player 2
                    //otherPlayer.size = 0;
                    otherPlayer.state = stuff[3];
                    otherPlayer.keyRight = Boolean.parseBoolean(stuff[4]);
                    otherPlayer.keyLeft = Boolean.parseBoolean(stuff[5]);
                    otherPlayer.keyShift = Boolean.parseBoolean(stuff[6]);
                    otherPlayer.keyUp = Boolean.parseBoolean(stuff[7]);
                    otherPlayer.keyDown = Boolean.parseBoolean(stuff[8]);
                    otherPlayer.tileSet = stuff[9];
                    otherPlayer.grav = Double.parseDouble(stuff[11]);
                    otherPlayer.xGrav = Double.parseDouble(stuff[12]);
                    otherPlayer.rx = true;
                    otherPlayer.isDead = s.contains("dead");
                    if (!otherPlayer.isDead) {
                        otherPlayer.deadTime = 0;
                    }
                    otherPlayer.bubble = Boolean.parseBoolean(stuff[13]);
                    otherPlayer.size = Integer.parseInt(stuff[14]);
                    return;
                }
                if (s.startsWith("killed")) {
                    otherPlayer.size--;
                    return;
                }
                MyWorld.player.winner = otherPlayer;
                MyWorld.player.local = false;
                MyWorld.player.localBtns = false;
            } else {
                System.out.println("null!");
            }
        }
        if (s.equals("go")) {
            start = true;
        }
        if (s.startsWith("u r ")) {
            TitleScreen.me = s;
            if (s.contains("Mario")) {
                player = "Mario";
                name1 = "Luigi";
                name2 = "BlueToad";
                name3 = "YellowToad";
            }
            if (s.contains("Luigi")) {
                player = "Luigi";
                name1 = "Mario";
                name2 = "BlueToad";
                name3 = "YellowToad";
            }
            if (s.contains("BlueToad")) {
                player = "BlueToad";
                name1 = "Mario";
                name2 = "Luigi";
                name3 = "YellowToad";
            }
            if (s.contains("YellowToad")) {
                player = "YellowToad";
                name1 = "Mario";
                name2 = "Luigi";
                name3 = "BlueToad";
            }
        }
    }

    @Override
    public void onDisconnect(String s) {

    }

    @Override
    public void onConnect() {

    }
}
