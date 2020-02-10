package CSIII.Objects;

import CSIII.System.Animation;
import CSIII.Worlds.TitleScreen;
import mayflower.Color;
import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.MayflowerImage;

/**
 * Write a description of class MoveableAnimatedActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MoveableAnimatedActor extends AnimatedActor {
    public int bubbleCnt;
    public boolean winAnim = false;
    public boolean keyLeft = false;
    public boolean keyRight = false;
    public boolean keyShift = false;
    public boolean keyUp = false;
    public boolean keyDown = false;
    public boolean rx;
    public int size;
    public double marioX;
    public double grav;
    public double marioY;
    public String state = "idleRight";
    public String tileSet = "tiles1";
    public double xGrav = 0;
    public boolean isDead;
    public double spawnX;
    public double spawnY;
    public int deadTime = 0;
    public boolean local;
    public boolean localBtns;
    public Animation sprint;
    public Animation sprintLeft;
    public Animation slide;
    public Animation slideLeft;
    public Animation dead;
    public Animation deadAnim;
    public Animation win;
    public Animation walkRight;
    public Animation idle;
    public Animation walkLeft;
    public Animation fallRight;
    public Animation fallLeft;
    public Animation jumpRight;
    public Animation jumpLeft;
    public Animation idleLeft;
    public Animation sprintBig;
    public Animation sprintLeftBig;
    public Animation slideBig;
    public Animation slideLeftBig;
    public Animation winBig;
    public Animation walkRightBig;
    public Animation idleBig;
    public Animation walkLeftBig;
    public Animation fallRightBig;
    public Animation fallLeftBig;
    public Animation jumpRightBig;
    public Animation jumpLeftBig;
    public Animation idleLeftBig;
    int mushroomTimer = 0;
    boolean keepJump = true;
    int oldSize = 0;
    MayflowerImage baby;
    boolean skipAct = false;
    int freezeTimer = 0;
    boolean daMushro;
    private boolean lastDirection = true;
    private boolean setted = false;

    /**
     * Constructor for objects of class MoveableAnimatedActor
     */
    public MoveableAnimatedActor() {
        Mayflower.loadSound("sfx/dead.wav");
        Mayflower.loadSound("sfx/pop.wav");
        Mayflower.loadSound("sfs/1up.wav");
        baby = new MayflowerImage(1, 1, Color.BLACK);
        baby.setTransparency(100);
    }


    public void act() {
        if (local && !daMushro) {
            mushroomTimer = (int) (Math.random() * 150 + 600);
            daMushro = true;
        }
        if (size == 0) {
            mushroomTimer--;
        }
        if (local && mushroomTimer <= 0 && localBtns) {
            daMushro = false;
            getWorld().addObject(new Mushroom(), Integer.MIN_VALUE, -65);
        }
        if (local && getY() > 750) {
            if (size == 1) {
                size = 0;
                oldSize = 0;
            } else {
                size = -1;
                oldSize = -1;
            }
            ((Mario) this).bubble = false;
        }
        if (size != -1 && size != oldSize) {
            if (!setted) {
                skipAct = true;
                if (oldSize > size) {
                    if (local)
                        Mayflower.playSound("sfx/down.wav");
                } else {
                    if (local)
                        Mayflower.playSound("sfx/up.wav");
                }
                ((Mario) this).bubble = false;
                getWorld().removeObject(((Mario) this).bubbleMade);
                setImage(baby);
                setted = true;
                GoalPostTapeParticle p = new GoalPostTapeParticle();
                p.realX = realX + getWidth() / 2d - p.getWidth() / 2d;
                p.realY = realY + getHeight() / 2d - p.getHeight() / 2d + 16;
                getWorld().addObject(p, Integer.MIN_VALUE, Integer.MIN_VALUE);
            }
            freezeTimer++;
            if (freezeTimer == 30) {
                freezeTimer = 0;
                skipAct = false;
                setted = false;
                if (oldSize == 0 && size == 1) {
                    realY -= 32.25;
                }
                if (oldSize == 1 && size == 0) {
                    realY += 31.75;
                    bubbleCnt = 200;
                    ((Mario) this).bubble = true;
                }
                oldSize = size;
            }
            if (skipAct)
                return;
        }
        bubbleCnt--;
        if (bubbleCnt == 0) {
            ((Mario) this).bubble = false;
        }
        super.act();
        double deadLockX = realX;
        double deadLockY = realY;
        String newAction = "idle";
        if (isDead) {
            deadTime++;
        }
        // each action is assigned a string, which is then used to assign
        // animations
        //checks for action
        //all possible movements for the actor
        boolean shift;
        boolean left;
        boolean right;
        boolean up;
        boolean down;
        boolean tapUp = false;
        if (localBtns) {
            shift = Mayflower.isKeyDown(Keyboard.KEY_Z);
            left = Mayflower.isKeyDown(Keyboard.KEY_LEFT);
            right = Mayflower.isKeyDown(Keyboard.KEY_RIGHT);
            up = Mayflower.isKeyDown(Keyboard.KEY_X);
            tapUp = Mayflower.isKeyPressed(Keyboard.KEY_X);
            down = Mayflower.isKeyDown(Keyboard.KEY_DOWN);
        } else {
            shift = keyShift;
            left = keyLeft;
            right = keyRight;
            up = keyUp;
            down = keyDown;
        }
        double ox = realX;
        double oy = realY;
        int speed;
        if (shift) {
            speed = 9;
        } else {
            speed = 4;
        }
        if (right) {
            xGrav += .25;
            if (isDead) {
                xGrav = 0;
            }
            realX += xGrav;
            if (xGrav > speed) {
                xGrav = speed;
            }
            lastDirection = true;
            if (local)
                newAction = "walkRight";
            if (Math.abs(xGrav) > 4) {
                if (local)
                    newAction = "sprint";
            }
        } else if (left) {
            xGrav -= .25;
            if (isDead) {
                xGrav = 0;
            }
            realX += xGrav;
            if (xGrav < -speed) {
                xGrav = -speed;
            }
            lastDirection = false;
            if (local)
                newAction = "walkLeft";
            if (Math.abs(xGrav) > 4) {
                if (local)
                    newAction = "sprintLeft";
            }
        }
        if (local) {
            if (lastDirection) {
                if (xGrav < 0) {
                    newAction = "slideLeft";
                }
            }
            if (!lastDirection) {
                if (xGrav > 0) {
                    newAction = "slide";
                }
            }
        }
        if (!right && !left) {
            if (lastDirection) {
                if (xGrav < .25d) {
                    xGrav = 0;
                } else {
                    xGrav -= .25;
                }
            } else {
                if (xGrav > -.25d) {
                    xGrav = 0;
                } else {
                    xGrav += .25;
                }
            }
            realX += xGrav;
        }
        scroll();
        if (isTouching(Block.class)) {
            setScrollLocation(ox, realY);
            scroll();
        }
        realY += grav;
        scroll();
        if (tapUp && keepJump) {
            grav = -10;
            Mayflower.playSound("sfx/jump.wav");
            realY--;
            scroll();
        }
        if (!up && keepJump) {
            grav = grav / 2;
            keepJump = false;
        }
        if (local && newAction.equals("idle") && !lastDirection) {
            newAction = "idleLeft";
        }
        scroll();
        if (isTouching(Block.class) && !isDead) {
            setScrollLocation(realX, oy);
            grav = 0;
            keepJump = true;
            scroll();
        }
        if (local && grav > 0.5) {
            newAction = "fallRight";
            if (!lastDirection) {
                newAction = "fallLeft";
            }
        }
        if (local && grav < -0.5) {
            newAction = "jumpRight";
            if (!lastDirection) {
                newAction = "jumpLeft";
            }
        }
        grav += .25;
        //animations assigned based on current action
        if (!local) {
            newAction = state;
        }
        if (deadTime <= 45 && isDead) {
            if (local)
                newAction = "dead";
            grav = 0;
            setScrollLocation(deadLockX, deadLockY);
            scroll();
        }
        if (deadTime == 45) {
            grav = -8;
        }
        if (deadTime > 45) {
            if (local)
                newAction = "deadAnim";
            setScrollLocation(deadLockX, realY);
            scroll();
        }
        if (local) {
            if (!isDead && isTouching(Mario.class) && grav > 0.25) {
                Mario m = getIntersectingObjects(Mario.class).get(0);
                if (!m.isDead && !m.bubble && realY + getHeight() + 31 > m.realY && realY + getHeight() - m.realY < 34) {
                    TitleScreen.c.send("killed" + m.name);
                    m.bubble = true;
                    grav = -9;
                    realY--;
                    keepJump = true;
                    if (keyUp) {
                        grav = -10;
                    }
                }
            }
            if (isTouching(Mushroom.class)) {
                if (size == 0) {
                    size++;
                }
                removeTouching(Mushroom.class);
            }
            if (deadTime == 1) {
                Mayflower.playSound("sfx/dead.wav");
            }
            if (deadTime == 300) {
                if (local)
                    isDead = false;
                deadTime = 0;
                grav = 0;
                xGrav = 0;
                size = 0;
                setScrollLocation(spawnX, spawnY);
                oldSize = 0;
                try {
                    ((Mario) this).bubble = true;
                    bubbleCnt = 240;
                } catch (Exception e) {
                    System.out.print("non mario actor died");
                }
                if (local) {
                    Mayflower.playSound("sfx/1up.wav");
                    newAction = "idle";
                }
                lastDirection = true;
                scroll();
            }
            state = newAction;
            if (isTouching(GoalPost.class)) {
                TitleScreen.c.send("flag!");
            }
        }
        if (winAnim) {
            newAction = "win";
        }
        if (size < 1) {
            switch (newAction) {
                case "idle":
                    setAnimation(idle);
                    break;
                case "walkRight":
                    setAnimation(walkRight);
                    break;
                case "walkLeft":
                    setAnimation(walkLeft);
                    break;
                case "fallRight":
                    setAnimation(fallRight);
                    break;
                case "fallLeft":
                    setAnimation(fallLeft);
                    break;
                case "jumpRight":
                    setAnimation(jumpRight);
                    break;
                case "jumpLeft":
                    setAnimation(jumpLeft);
                    break;
                case "idleLeft":
                    setAnimation(idleLeft);
                    break;
                case "sprint":
                    setAnimation(sprint);
                    break;
                case "sprintLeft":
                    setAnimation(sprintLeft);
                    break;
                case "slide":
                    setAnimation(slide);
                    break;
                case "slideLeft":
                    setAnimation(slideLeft);
                    break;
                case "dead":
                    setAnimation(dead);
                    break;
                case "deadAnim":
                    setAnimation(deadAnim);
                    break;
                case "win":
                    setAnimation(win);
                    break;
            }
        }
        if (size == 1) {
            switch (newAction) {
                case "idle":
                    setAnimation(idleBig);
                    break;
                case "walkRight":
                    setAnimation(walkRightBig);
                    break;
                case "walkLeft":
                    setAnimation(walkLeftBig);
                    break;
                case "fallRight":
                    setAnimation(fallRightBig);
                    break;
                case "fallLeft":
                    setAnimation(fallLeftBig);
                    break;
                case "jumpRight":
                    setAnimation(jumpRightBig);
                    break;
                case "jumpLeft":
                    setAnimation(jumpLeftBig);
                    break;
                case "idleLeft":
                    setAnimation(idleLeftBig);
                    break;
                case "sprint":
                    setAnimation(sprintBig);
                    break;
                case "sprintLeft":
                    setAnimation(sprintLeftBig);
                    break;
                case "slide":
                    setAnimation(slideBig);
                    break;
                case "slideLeft":
                    setAnimation(slideLeftBig);
                    break;
                case "dead":
                    setAnimation(dead);
                    break;
                case "deadAnim":
                    setAnimation(deadAnim);
                    break;
                case "win":
                    setAnimation(winBig);
                    break;
            }
        }
    }

    public void setAnimation(Animation a) {
        super.setAnimation(a);
    }
}
