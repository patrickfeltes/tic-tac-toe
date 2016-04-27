package com.patrickfeltes.game;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    private int size;

    private final int AI = -1;
    private final int EMPTY = 0;
    private final int USER = 1;

    private int currentPlayer;

    private int[][] board;

    public Board(int size) {
        this.size = size;
        board = new int[size][size];
    }

    private ArrayList<Point> getPossibleMoves() {
        ArrayList<Point> moves = new ArrayList<Point>();

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                if(board[r][c] == EMPTY) { moves.add(new Point(c, r)); }
            }
        }

        return moves;
    }

    // using the minimax method, make the best move for the AI player
    public void makeAIMove() {
        ArrayList<Point> possibleMoves = getPossibleMoves();

        Point minMove = null;
        int min = Integer.MAX_VALUE;

        for(Point move: possibleMoves) {
            makeMove(move, AI);

            int val = minimax(USER);
            if(val < min) {
                min = val;
                minMove = move;
            }

            undoMove(move);
        }
        makeMove(minMove, AI);
        switchPlayer();
    }


    private int minimax(int player) {
        if(hasWon(AI) || hasWon(USER)) { return score(); }

        ArrayList<Point> possibleMoves = getPossibleMoves();

        if(possibleMoves.size() == 0) { return score(); }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(Point move: possibleMoves) {
            if(player == USER) {
                makeMove(move, player);
                int stateScore = minimax(AI);
                max = Math.max(stateScore, max);
            } else {
                makeMove(move, player);
                int stateScore = minimax(USER);
                min = Math.min(stateScore, min);
            }
            undoMove(move);
        }

        return player == USER ? max : min;
    }

    private int score() {
        if(hasWon(AI)) {
            return AI * 10;
        } else if(hasWon(USER)) {
            return USER * 10;
        } else {
            return 0;
        }
    }

    private void makeMove(Point move, int player) {
        board[move.y][move.x] = player;
    }
    public void makeUserMove(Point move) {
        // only make the move if the selected cell is empty
        if(board[move.y][move.x] == EMPTY) {
            makeMove(move, USER);
            switchPlayer();
            makeAIMove();
        }
    }

    private void undoMove(Point move) {
        board[move.y][move.x] = EMPTY;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == USER) ? AI : USER;
    }

    public boolean hasWon(int player) {
        if(checkRows(player)) return true;
        else if(checkCols(player)) return true;
        else if(checkDiagonals(player)) return true;
        return false;
    }

    // helper methods for hasWon
    private boolean checkRows(int player) {
        for(int r = 0; r < size; r++) {
            if(allSame(board[r], player)) return true;
        }
        return false;
    }

    private boolean checkCols(int player) {
        int[] tempCol = new int[size];

        for(int c = 0; c < size; c++) {
            for(int r = 0; r < size; r++) {
                tempCol[r] = board[r][c];
            }
            if(allSame(tempCol, player)) { return true; }
        }

        return false;
    }

    private boolean checkDiagonals(int player) {
        int[] a1 = new int[size];
        int[] a2 = new int[size];

        for(int r = 0; r < size; r++) {
            a1[r] = board[r][r];
            a2[r] = board[r][size - r - 1];
        }

        return (allSame(a1, player) || allSame(a2, player));
    }

    private boolean allSame(int[] array, int value) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] != value) { return false; }
        }
        return true;
    }

    public void reset() {
        board = new int[size][size];
        currentPlayer = USER;
    }

    public int[][] getBoard() {
        return board;
    }
    public int getCurrentPlayer() { return currentPlayer; }

    public int getAI() {
        return AI;
    }

    public int getUser() {
        return USER;
    }

}
