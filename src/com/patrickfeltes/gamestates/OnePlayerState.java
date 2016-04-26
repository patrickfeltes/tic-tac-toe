package com.patrickfeltes.gamestates;

import com.patrickfeltes.game.Board;
import com.patrickfeltes.main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OnePlayerState extends GameState {

    private final int CELLS = 3;

    private final int BORDER = (int)(0.10 * GamePanel.WIDTH);
    private final int CELL_SIZE = (GamePanel.WIDTH - 2 * BORDER) / CELLS;

    private int row, col;

    public OnePlayerState(GameStateManager gsm) {
        super(gsm);
        Board b = new Board(CELLS);
    }

    public void update() {
        row = (GamePanel.mouseY - BORDER) / CELL_SIZE;
        col = (GamePanel.mouseX - BORDER) / CELL_SIZE;
    }

    public void draw(Graphics2D g) {
        // draw the board
        g.setColor(Color.BLACK);
        for(int i = 1; i <= CELLS - 1; i++) {
            g.drawLine(BORDER + CELL_SIZE * i, BORDER, BORDER + CELL_SIZE * i, GamePanel.HEIGHT - BORDER);
            g.drawLine(BORDER, BORDER + CELL_SIZE * i, GamePanel.WIDTH - BORDER, BORDER + CELL_SIZE * i);
        }

        if(row >= 0 && row < CELLS && col >= 0 && col < CELLS) {
            g.setColor(new Color(0.5f,0.5f,0.5f,0.5f));
            g.fillRect(BORDER + CELL_SIZE * col, BORDER + CELL_SIZE * row, CELL_SIZE, CELL_SIZE);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }
}
