package com.patrickfeltes.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Button extends JComponent {

    // string information
    private String text;
    private Font font;
    private int textWidth, textHeight;
    private int ascent;

    // is mouse inside?
    private boolean mouseInside;

    // colors
    private Color regColor;
    private Color hoverColor;
    private Color textColor;

    // button fields
    private int x, y, width, height;

    // used to grab font information one time only
    private boolean isFirst = true;
    private FontMetrics metrics;

    public Button(int x, int y, int width, int height, String text, Font font, Color regColor, Color hoverColor, Color textColor) {
        this.setBounds(x, y, width, height);
        this.text = text;
        this.font = font;
        this.regColor = regColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
    }

    public void update() {
        mouseInside = Util.isMouseInsideRectangle(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        if(mouseInside) {
            g.setColor(hoverColor);
        } else {
            g.setColor(regColor);
        }
        RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20, 20);
        g.draw(rect);

        g.setColor(textColor);
        g.setFont(font);

        if(isFirst) {
            isFirst = false;
            getFontInformation(g);
        }

        g.drawString(text, x + (width - textWidth) / 2, y + (height - textHeight) / 2 + ascent);
    }

    private void getFontInformation(Graphics2D g) {
        metrics = g.getFontMetrics();
        Rectangle2D r =  metrics.getStringBounds(text, g);
        textWidth = (int) r.getWidth();
        textHeight = (int) r.getHeight();
        ascent = metrics.getAscent();
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isMouseInside() {
        return mouseInside;
    }

}
