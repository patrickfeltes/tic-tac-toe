package com.patrickfeltes.gamestates;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void mouseClicked(MouseEvent e);
}
