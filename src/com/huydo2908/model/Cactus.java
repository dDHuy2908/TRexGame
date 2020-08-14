package com.huydo2908.model;

import com.huydo2908.utility.ImageLoader;

import java.awt.*;

public class Cactus {
    public static final int BOTTOM = 325;
    public static final int SPEED = 3;

    private int x;
    private Image cactus;

    public Cactus(int x, String name) {
        this.x = x;
        cactus = ImageLoader.getImage(name, getClass());
    }

    public void cactusRun() {
        x -= SPEED;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(cactus, x, BOTTOM - cactus.getHeight(null), null);
    }

    public boolean checkOutMap() {
        if (x < -cactus.getWidth(null)) {
            return true;
        }
        return false;
    }

    public Rectangle getRect() {
        Rectangle rectCactus = new Rectangle(x, BOTTOM - cactus.getHeight(null), cactus.getWidth(null), cactus.getHeight(null));
        return rectCactus;
    }
}
