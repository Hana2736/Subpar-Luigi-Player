package CSIII.System;

import mayflower.Actor;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

public abstract class ClickButton extends Actor {
    boolean reversed = false;
    boolean clicked = false;
    boolean disabled = false;

    public ClickButton(MayflowerImage img) {
        setImage(img);
    }

    @Override
    public void act() {
        if (!disabled) {
            if (Mayflower.mouseClicked(this)) {
                clickEvent();
                clicked = true;
            }
        }
    }

    public boolean wasClicked() {
        return clicked;
    }

    public boolean wasReversed() {
        return reversed;
    }

    public void reverseClickState() {
        clicked = !clicked;
        reversed = true;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean s) {
        disabled = s;
    }

    protected abstract void clickEvent();
}
