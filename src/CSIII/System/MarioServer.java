package CSIII.System;

import mayflower.net.Server;

public class MarioServer extends Server {
    public static int max = 4;
    public boolean game = true;
    int loaded = 0;

    public MarioServer() {
        super(6372);
    }

    @Override
    public void process(int i, String s) {
        if (s.equals("loaded")) {
            loaded++;
            if (loaded == max) {
                send("allLoaded!");
            }
        }
        if (game) {
            if (s.startsWith("update:") || s.startsWith("killed")) {
                if (i == 1) {
                    if (max >= 2)
                        send(2, s);
                    if (max >= 3)
                        send(3, s);
                    if (max >= 4)
                        send(4, s);
                }
                if (i == 2) {
                    send(1, s);
                    if (max >= 3)
                        send(3, s);
                    if (max >= 4)
                        send(4, s);
                }
                if (i == 3) {
                    send(1, s);
                    send(2, s);
                    if (max >= 4)
                        send(4, s);
                }
                if (i == 4) {
                    send(1, s);
                    send(2, s);
                    send(3, s);
                }
            }
            if (s.equals("flag!")) {
                game = false;
                String[] players = new String[]{"Mario", "Luigi", "BlueToad", "YellowToad"};
                send("winner is " + players[i - 1]);
            }
        }
    }

    @Override
    public void onJoin(int i) {
        if (i == 1) {
            send(i, "u r Mario");
        }
        if (i == 2) {
            send(i, "u r Luigi");
        }
        if (i == 3) {
            send(i, "u r BlueToad");
        }
        if (i == 4) {
            send(i, "u r YellowToad");
        }
        if (i == max) {
            send("go");
        }
        send("Waiting for " + (max - i) + " more players.");
    }

    @Override
    public void onExit(int i) {

    }
}
