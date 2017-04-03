package org.florian_wagner.snake.gui;

import javax.swing.*;

/**
 * Created by Florian on 03.04.2017.
 */
public class MainMenu {

    private JFrame frame;

    public MainMenu()
    {
        frame = new JFrame("Snake");
        frame.setSize(400,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }


}
