package CSIII.Objects;

import CSIII.System.Animation;

public class GoalPostTapeParticle extends AnimatedActor {
    int cnt = 0;

    public GoalPostTapeParticle() {
        String[] imgs = new String[4];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = "img/GoalSmoke/" + i + ".png";
        }
        Animation a = new Animation(5, imgs);
        setAnimation(a);
    }

    public void act() {
        cnt++;
        if (cnt == 52) {
            getWorld().removeObject(this);
        }
        super.act();
        scroll();
    }
}
