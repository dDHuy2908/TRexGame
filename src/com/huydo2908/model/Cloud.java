package com.huydo2908.model;

import com.huydo2908.gui.TRexFrame;
import com.huydo2908.utility.ImageLoader;

import java.awt.*;
import java.util.ArrayList;

public class Cloud {
    public static final float SPEED = 0.4f;

    private Image cloud;

    private float x = 0;

    public Cloud() {
        cloud = ImageLoader.getImage("cloud1.png", getClass());
    }

    public void cloudRun() {
        if (x > -TRexFrame.W_FRAME) {
            x -= SPEED;
        } else {
            x = 0;
        }
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(cloud, (int) x, 0, null);
        g2d.drawImage(cloud, (int) x + TRexFrame.W_FRAME, 0, null);
    }
}
