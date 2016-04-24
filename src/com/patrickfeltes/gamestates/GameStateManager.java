package com.patrickfeltes.gamestates;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class GameStateManager {

    // stack to store game states
    private Stack<GameState> gameStates;

    public GameStateManager() {
        gameStates = new Stack<GameState>();
        gameStates.add(new MenuState(this));
    }

    public void update() {
        if(gameStates.size() != 0) { gameStates.peek().update(); }
    }

    public void draw(Graphics2D g) {
        if(gameStates.size() != 0) { gameStates.peek().draw(g); }
    }

    public void mouseClicked(MouseEvent e) {
        if(gameStates.size() != 0) { gameStates.peek().mouseClicked(e); }
    }

}
