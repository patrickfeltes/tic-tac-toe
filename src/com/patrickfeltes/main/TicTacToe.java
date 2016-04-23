package com.patrickfeltes.main;


import javax.swing.*;
import java.awt.*;

public class TicTacToe {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.add(new GamePanel());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}
