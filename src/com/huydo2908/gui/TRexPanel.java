package com.huydo2908.gui;

import com.huydo2908.model.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TRexPanel extends JPanel implements KeyListener, Runnable {
    private GameManager gameManager = new GameManager();

    public TRexPanel() {
        setBackground(Color.decode("#f7f7f7"));
        gameManager.initGame();
        setFocusable(true);
        addKeyListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        gameManager.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameManager.gameStatus) {
            case GameManager.START_GAME:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    GameManager.gameStatus = GameManager.GAME_PLAYING;
                }
                break;
            case GameManager.GAME_PLAYING:
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    gameManager.dinoJump();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameManager.dinoLowRun(true);
                }
                break;
            case GameManager.GAME_OVER:
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    GameManager.gameStatus = GameManager.GAME_PLAYING;
                    gameManager.initGame();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (GameManager.gameStatus == GameManager.GAME_PLAYING) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameManager.dinoLowRun(false);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            gameManager.AI();
            repaint();
            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
