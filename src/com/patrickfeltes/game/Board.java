package com.patrickfeltes.game;

public class Board {

    private int size;
    private boolean isAIOn;

    private final int AI = -1;
    private final int EMPTY = 0;
    private final int USER = 1;

    private int[][] board;

    public Board(int size, boolean isAIOn) {
        this.size = size;
        this.isAIOn = isAIOn;
        board = new int[size][size];
    }





    public int[][] getBoard() {
        return board;
    }


}
