package org.florian_wagner.snake;

import org.florian_wagner.snake.gui.MainMenu;

/**
 * Created by Florian on 03.04.2017.
 */
public class SnakeGame {



    public static void main(String[] args)
    {
        new SnakeGame().init();
    }

    public void init()
    {
        new MainMenu();
    }

}