package com.patrickfeltes.gamestates;

import com.patrickfeltes.game.Board;
import com.patrickfeltes.main.GamePanel;
import com.patrickfeltes.ui.Button;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OnePlayerState extends GameState {

    // general info about board layout and dimensions for drawing
    private final int CELLS = 3;
    private final int BORDER = (int)(0.10 * GamePanel.WIDTH);
    private final int CELL_SIZE = (GamePanel.WIDTH - 2 * BORDER) / CELLS;

    // mouse position
    private int row, col;

    // board to handle AI/moves
    private Board board;

    // reset button
    private Button reset;

    public OnePlayerState(GameStateManager gsm) {
        super(gsm);
        board = new Board(CELLS);
        reset = new Button(100, GamePanel.HEIGHT - 100, 200, 100, "Reset", new Font("Arial", 38, Font.PLAIN), Color.BLACK, Color.RED, Color.BLACK);
    }

    public void update() {
        row = (GamePanel.mouseY - BORDER) / CELL_SIZE;
        col = (GamePanel.mouseX - BORDER) / CELL_SIZE;

        if(board.hasWon(board.getUser()) || board.hasWon(board.getAI())) {
            reset.update();
        }
    }

    public void draw(Graphics2D g) {
        drawBoard(g);
        drawSelectedCell(g);
        drawMoves(g);
        drawHasWon(g);
    }

    private void drawBoard(Graphics2D g) {
        g.setColor(Color.BLACK);
        for(int i = 1; i <= CELLS - 1; i++) {
            g.drawLine(BORDER + CELL_SIZE * i, BORDER, BORDER + CELL_SIZE * i, GamePanel.HEIGHT - BORDER);
            g.drawLine(BORDER, BORDER + CELL_SIZE * i, GamePanel.WIDTH - BORDER, BORDER + CELL_SIZE * i);
        }
    }

    private void drawSelectedCell(Graphics2D g) {
        if(row >= 0 && row < CELLS && col >= 0 && col < CELLS) {
            g.setColor(new Color(0.5f,0.9f,0.3f,0.3f));
            g.fillRect(BORDER + CELL_SIZE * col, BORDER + CELL_SIZE * row, CELL_SIZE, CELL_SIZE);
        }
    }

    private void drawMoves(Graphics2D g) {
        for(int r = 0; r < board.getBoard().length; r++) {
            for(int c = 0; c < board.getBoard()[r].length; c++) {
                g.setColor(Color.BLACK);
                if(board.getBoard()[r][c] == board.getAI()) { g.drawString("O", BORDER + c * CELL_SIZE + CELL_SIZE / 2, BORDER + r * CELL_SIZE + CELL_SIZE / 2); }
                else if(board.getBoard()[r][c] == board.getUser()) { g.drawString("X",  BORDER + c * CELL_SIZE + CELL_SIZE / 2, BORDER + r * CELL_SIZE + CELL_SIZE / 2); }
            }
        }
    }

    private void drawHasWon(Graphics2D g) {
        if(board.hasWon(board.getUser())) {
            g.drawString("X has won!", 0, GamePanel.HEIGHT);
            reset.draw(g);
        } else if(board.hasWon(board.getAI())) {
            g.drawString("O has won!", 0, GamePanel.HEIGHT);
            reset.draw(g);
        }

    }

    public void mouseClicked(MouseEvent e) {
        // make a move if clicked in cell
        if(row >= 0 && row < CELLS && col >= 0 && col < CELLS && !board.hasWon(board.getUser()) && !board.hasWon(board.getAI())) {
            board.makeUserMove(new Point(col, row));
        }

        if(reset.isMouseInside()) resetGame();
    }

    private void resetGame() {
        board.reset();
    }
}
