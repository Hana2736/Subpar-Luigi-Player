package CSIII.Objects;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public class Countdown extends Actor {
    public MayflowerImage three;
    public MayflowerImage two;
    public MayflowerImage one;
    public MayflowerImage zero;
    public MayflowerImage four;
    int counter;

    public Countdown(int remaining) {
        counter = remaining;
        three = new MayflowerImage("img/Countdown/3.png");
        two = new MayflowerImage("img/Countdown/2.png");
        one = new MayflowerImage("img/Countdown/1.png");
        zero = new MayflowerImage("img/Countdown/0.png");
        four = new MayflowerImage("img/Countdown/4.png");
        setImage(four);
    }

    @Override
    public void act() {
        counter--;
        if (counter == 240) {
            setImage(three);
            Mayflower.playSound("sfx/count.wav");
        }
        if (counter == 180) {
            setImage(two);
            Mayflower.playSound("sfx/count.wav");
        }
        if (counter == 120) {
            setImage(one);
            Mayflower.playSound("sfx/count.wav");
        }
        if (counter == 60) {
            setImage(zero);
            Mayflower.playSound("sfx/start.wav");
        }
        if (counter == 0) {
            getWorld().removeObject(this);
        }
        setLocation(400 - (getWidth() / 2d), 300 - (getHeight() / 2d));
    }
}
