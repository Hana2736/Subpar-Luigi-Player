package CSIII.Objects;

import mayflower.MayflowerImage;

public class GoalPost extends ScrollActor {
    boolean fixed = false;
    boolean poset = false;
    boolean killedFlag = false;

    public GoalPost() {
        setImage(new MayflowerImage("img/Goal.png"));
    }

    public void act() {
        if (!fixed) {
            realY = realY - getHeight() + 96;
            fixed = true;
            GoalPostTape t = new GoalPostTape(realY + 32, realY + 382);
            t.setScrollLocation(realX + 32, realY + 382);
            getWorld().addObject(t, Integer.MIN_VALUE, Integer.MIN_VALUE);

        }
        if (isTouching(Mario.class)) {
            if (!killedFlag) {
                getIntersectingObjects(GoalPostTape.class).get(0).die();
                killedFlag = true;
            }
            if (!poset) {
                if (getIntersectingObjects(Mario.class).get(0).getX() - 70 > getX()) {
                    getWorld().setPaintOrder(Block.class, Mario.class, Bubble.class, GoalPost.class, GridBound.class, Countdown.class);
                }
            }
        }
        scroll();
    }
}
