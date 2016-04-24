package com.patrickfeltes.main;

import com.patrickfeltes.gamestates.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    // dimensions
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final Dimension size = new Dimension(WIDTH, HEIGHT);

    // mouse position
    public static int mouseX, mouseY;

    // game loop
    private Thread thread;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    private boolean isRunning = false;

    // Graphics
    private Graphics2D g2d;

    // game state manager
    private GameStateManager gsm;

    // constructor
    public GamePanel() {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        startGame();
    }

    public void update() {
        gsm.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // antialiasing to have decent looking graphics
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gsm.draw(g2d);

        g2d.dispose();
    }

    // main loop for the game
    public void run() {
        long start, elapsed, wait;
        while(isRunning) {
            start = System.currentTimeMillis();

            // update and draw
            update();
            repaint();

            elapsed = System.currentTimeMillis() - start;
            wait = targetTime - elapsed;
            if(wait < 0) { wait = 5; }
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endGame();
    }

    // events
    public void mouseClicked(MouseEvent e) {
        gsm.mouseClicked(e);
    }

    public void mouseDragged(MouseEvent e) {
        setMousePosition(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        setMousePosition(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    // helper methods
    private void startGame() {
        gsm = new GameStateManager();
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void endGame() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setMousePosition(int x, int y) {
        mouseX = x;
        mouseY = y;
    }
}
