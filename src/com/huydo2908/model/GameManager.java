package com.huydo2908.model;

import com.huydo2908.gui.TRexFrame;
import com.huydo2908.utility.ImageLoader;
import com.huydo2908.utility.SoundLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    public static final int START_GAME = 0;
    public static final int GAME_PLAYING = 1;
    public static final int GAME_OVER = 2;

    private Dino dino;
    private Ground ground;
    private Cloud cloud;
    private int score, scoreShow;
    private ArrayList<Cactus> arrCactus;
    private Random rd = new Random();
    private long t;
    public static int gameStatus = START_GAME;
    private Image replayButton = ImageLoader.getImage("replay_button.png", getClass());
    private Image gameOver = ImageLoader.getImage("gameover_text.png", getClass());
    private Image dinoStand = ImageLoader.getImage("Dino-stand.png", getClass());

    public void initGame() {
        dino = new Dino();
        ground = new Ground();
        cloud = new Cloud();
        arrCactus = new ArrayList<>();
    }

    public void AI() {
        if (gameStatus == GAME_PLAYING) {
            dino.update();
            cloud.cloudRun();
            ground.groundRun();
            generateCactus();
            cactusUpdate();
            scoreUpdate();
            if (dino.checkAlive(arrCactus) == false) {
                dino.die(arrCactus);
                gameStatus = GAME_OVER;
            }
        }
    }

    public void dinoJump() {
        dino.jump();
    }

    public void dinoLowRun(boolean checkLowRun) {
        if (checkLowRun == true) {
            dino.lowRun(true);
        } else {
            dino.lowRun(false);
        }
    }

    public void scoreUpdate() {
        if (dino.checkAlive(arrCactus) == true) {
            score++;
            scoreShow = score / 70;
        } else {
            score = 0;
        }
    }

    public void drawCactus(Graphics2D g2d, ArrayList<Cactus> arr) {
        for (Cactus c : arr) {
            c.draw(g2d);
        }
    }

    public void generateCactus() {
        long T = System.currentTimeMillis();
        if (T - t < 2000) {
            return;
        }
        t = T;
        Cactus cactus1 = new Cactus(800, "Cactus-1.png");
        Cactus cactus2 = new Cactus(800, "Cactus-2.png");
        Cactus cactus3 = new Cactus(800, "Cactus-5.png");
        int cac = rd.nextInt(3);
        if (cac == 0) {
            arrCactus.add(cactus1);
        } else if (cac == 1) {
            arrCactus.add(cactus2);
        } else if (cac == 2) {
            arrCactus.add(cactus3);
        }
    }

    public void cactusUpdate() {
        for (int i = arrCactus.size() - 1; i >= 0; i--) {
            boolean check = arrCactus.get(i).checkOutMap();
            if (check == true) {
                arrCactus.remove(i);
            } else {
                arrCactus.get(i).cactusRun();
            }
        }
    }

    public void draw(Graphics2D g2d) {
        switch (gameStatus) {
            case START_GAME:
                g2d.drawImage(dinoStand, (TRexFrame.W_FRAME - dinoStand.getWidth(null)) / 2, 250, null);
                g2d.setFont(new Font("HILOGINBOLD", Font.PLAIN, 35));
                g2d.drawString("T-REX GAME", 260, 120);
                g2d.drawString("PRESS SPACE / UP TO START", 60, 190);
                g2d.setFont(new Font("HILOGINREG", Font.ITALIC, 25));
                g2d.drawString("Made by dDHuy", 280, 150);
                break;
            case GAME_PLAYING:
            case GAME_OVER:
                cloud.draw(g2d);
                ground.draw(g2d);
                dino.draw(g2d);
                drawCactus(g2d, arrCactus);
                g2d.setFont(new Font("HILOGINCON", Font.BOLD, 35));
                g2d.drawString(Integer.toString(scoreShow), 700, 100);
                if (gameStatus == GAME_OVER) {
                    g2d.drawImage(replayButton, (TRexFrame.W_FRAME - replayButton.getWidth(null)) / 2,
                            130, null);
                    g2d.drawImage(gameOver, (TRexFrame.W_FRAME - gameOver.getWidth(null)) / 2,
                            100, null);
                }
                break;
        }
    }
}
