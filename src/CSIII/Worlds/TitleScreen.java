package CSIII.Worlds;

import CSIII.System.ClickButton;
import CSIII.System.MarioClient;
import CSIII.System.MarioServer;
import mayflower.Mayflower;
import mayflower.MayflowerImage;
import mayflower.World;

import javax.swing.*;


/**
 * Write a description of class TitleScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TitleScreen extends World {

    public static String me = "bob";
    public static MarioClient c = new MarioClient();
    public static MarioServer s = null;
    // instance variables - replace the exalmple below with your own
    public static boolean serverStarted = false;
    public static boolean clientStarted = false;
    ClickButton cli;
    ClickButton sev;

    /**
     * Constructor for objects of class TitleScreen
     */
    public TitleScreen() {
        // initialise instance variables
        setBackground("img/BG/title.png");
        addObject(sev = new ClickButton(new MayflowerImage("img/hostbutton.png")) {
            @Override
            protected void clickEvent() {
                if (!serverStarted) {
                    clientStarted = true;
                    serverStarted = true;
                    getWorld().removeObject(cli);
                    this.setImage(new MayflowerImage("img/waiting.png"));
                    MarioServer.max = Integer.parseInt(JOptionPane.showInputDialog("How many players?"));
                    s = new MarioServer();
                    c.connect("localhost", 6372);
                }
            }
        }, 325, 300);
        addObject(cli = new ClickButton(new MayflowerImage("img/joinbutton.png")) {
            @Override
            protected void clickEvent() {
                if (!clientStarted) {
                    clientStarted = true;
                    serverStarted = true;
                    this.setImage(new MayflowerImage("img/waiting.png"));
                    getWorld().removeObject(sev);
                    c.connect(JOptionPane.showInputDialog("Server IP?"), 6372);
                }
            }
        }, 325, 450);
    }

    public void act() {
        if (MarioClient.start) {
            Mayflower.setWorld(new MyWorld());
            Mayflower.stopSound("sfx/menu.ogg");
        }
        //Button button = new Button();
        //addObject(button, 300, 300);

    }
}
