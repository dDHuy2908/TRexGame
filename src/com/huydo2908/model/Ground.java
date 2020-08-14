package com.huydo2908.model;

import com.huydo2908.gui.TRexFrame;
import com.huydo2908.utility.ImageLoader;

import java.awt.*;

public class Ground {
    public static final int GROUND = 297;
    public static final int SPEED = 3;

    protected float x = 0;

    protected Image ground;

    public Ground() {
        ground = ImageLoader.getImage("Ground1.png", getClass());
    }

    public void groundRun() {
        if (x > -TRexFrame.W_FRAME) {
            x -= SPEED;
        } else {
            x = 0;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(ground, (int) x, GROUND, null);
        g2d.drawImage(ground, (int) x + TRexFrame.W_FRAME, GROUND, null);
    }
}
