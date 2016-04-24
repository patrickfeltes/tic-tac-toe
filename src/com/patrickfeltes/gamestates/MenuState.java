package com.patrickfeltes.gamestates;

import com.patrickfeltes.main.GamePanel;
import com.patrickfeltes.ui.Button;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuState extends GameState {

    private Button[] buttons = new Button[3];
    private int ONE_PLAYER = 0;
    private int TWO_PLAYER = 1;
    private int QUIT = 2;

    private Font buttonFont = new Font("Arial", Font.PLAIN, 64);
    private int buttonX = 100;
    private int buttonWidth = GamePanel.WIDTH - 2 * buttonX;
    private int buttonHeight = 100;
    private int spacing = buttonHeight + 50;

    private String[] names = new String[] {"One Player", "Two Player", "Quit"};

    public MenuState(GameStateManager gsm) {
        super(gsm);

        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonX, i * spacing + spacing, buttonWidth, buttonHeight, names[i], buttonFont, Color.BLACK, Color.RED, Color.BLACK);
        }
    }

    public void update() {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].update();
        }
    }

    public void draw(Graphics2D g) {
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].draw(g);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if(buttons[ONE_PLAYER].isMouseInside()) {
            this.gsm.addState(new OnePlayerState(this.gsm));
        } else if(buttons[TWO_PLAYER].isMouseInside()) {
            this.gsm.addState(new TwoPlayerState(this.gsm));
        } else if(buttons[QUIT].isMouseInside()) {
            System.exit(0);
        }
    }
}
