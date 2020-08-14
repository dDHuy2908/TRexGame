package com.huydo2908.model;

import com.huydo2908.utility.ImageLoader;
import com.huydo2908.utility.SoundLoader;

import java.awt.*;
import java.util.ArrayList;

public class Dino {
    public static final int NORMAL_RUN = 0;
    public static final int JUMP = 1;
    public static final int LOW_RUN = 2;
    public static final int DIE = 3;
    public static final float GRAVITY = 1.0f;
    public static final int DINO_LEFT = 100;
    public static final int DINO_TOP_DEFAULT = 280;

    protected float dinoTop;
    protected float speed;
    protected int status = NORMAL_RUN;
    private Image[] normalRun = new Image[]{ImageLoader.getImage("Dino-left-up.png", getClass()), ImageLoader.getImage("Dino-right-up.png", getClass())};
    private Image[] lowRun = new Image[]{ImageLoader.getImage("Dino-below-left-up.png", getClass()), ImageLoader.getImage("Dino-below-right-up.png", getClass())};
    private Image[] die = new Image[]{ImageLoader.getImage("Dino-big-eyes.png", getClass())};
    private Image[] jump = new Image[]{ImageLoader.getImage("Dino-stand.png", getClass())};
    private int index = 0, count;
    private Image[][] images = {normalRun, lowRun, die, jump};

    public Dino() {
        dinoTop = 280;
    }

    public void jump() {
        if (dinoTop >= DINO_TOP_DEFAULT) {
            SoundLoader.play("media.io_Mario Jump - Gaming Sound Effect (HD).wav");
            speed = -18f;
            dinoTop += speed;
            status = JUMP;
        }
    }

    public void update() {
        if (dinoTop >= DINO_TOP_DEFAULT) {
            dinoTop = DINO_TOP_DEFAULT;
            if (status != LOW_RUN) {
                status = NORMAL_RUN;
            }
        } else {
            speed += GRAVITY;
            dinoTop += speed;
        }
    }

    public void lowRun(boolean checkLowRun) {
        if (status == JUMP) {
            return;
        }
        if (checkLowRun == true) {
            status = LOW_RUN;
        } else {
            status = NORMAL_RUN;
        }
    }

    public void die(ArrayList<Cactus> arr) {
        if (checkAlive(arr) == false) {
            SoundLoader.play("media.io_Nope (Construction Worker TF2) - Gaming Sound Effect (HD).wav");
            status = DIE;
        } else {
            status = NORMAL_RUN;
        }
    }

//    public void changeImage() {
//        count++;
//        if (count >= 10) {
//            index++;
//            if (index >= images[status].length) {
//                index = 0;
//            }
//            count = 0;
//        }
//    }

    public void draw(Graphics2D g2d) {
        switch (status) {
            case NORMAL_RUN:
                g2d.drawImage(normalRun[index], DINO_LEFT, (int) dinoTop, null);
//                changeImage();
                break;
            case JUMP:
                g2d.drawImage(jump[0], DINO_LEFT, (int) dinoTop, null);
                break;
            case DIE:
                g2d.drawImage(die[0], DINO_LEFT, (int) dinoTop, null);
                break;
            case LOW_RUN:
                g2d.drawImage(lowRun[index], DINO_LEFT, (int) (dinoTop + 17), null);
//                changeImage();
                break;
        }
        count++;
        if (count >= 10) {
            index++;
            if (index >= images[status].length) {
                index = 0;
            }
            count = 0;
        }
    }

    public Rectangle getRect() {
        Rectangle rectDino = new Rectangle();
        rectDino.x = DINO_LEFT;
        rectDino.y = (int) dinoTop;
        rectDino.width = images[status][0].getWidth(null);
        rectDino.height = images[status][0].getHeight(null);
        return rectDino;
    }

    public boolean checkAlive(ArrayList<Cactus> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Rectangle rect = getRect().intersection(arr.get(i).getRect());
            if (rect.isEmpty() == false) {
                return false;
            }
        }
        return true;
    }
}