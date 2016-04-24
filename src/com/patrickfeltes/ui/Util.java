package com.patrickfeltes.ui;

import com.patrickfeltes.main.GamePanel;

import java.awt.*;

public class Util {

    public static boolean isMouseInsideRectangle(int x, int y, int width, int height) {
        return isMouseInsideRectangle(new Rectangle(x, y, width, height));
    }

    public static boolean isMouseInsideRectangle(Rectangle r) {
        int mx = GamePanel.mouseX;
        int my = GamePanel.mouseY;

        return (mx >= r.x && mx <= r.x + r.width && my >= r.y && my <= r.y + r.height);
    }

}
