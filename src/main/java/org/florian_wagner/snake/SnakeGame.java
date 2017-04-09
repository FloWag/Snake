package org.florian_wagner.snake;

import javafx.application.Application;
import org.florian_wagner.snake.game.SnakeServer;
import org.florian_wagner.snake.gui.MainMenu;


/**
 * Created by Florian on 03.04.2017.
 */
public class SnakeGame {

    private static SnakeGame instance;
    private SnakeServer snakeServer = null;

    public static void main(String[] args)
    {
        new SnakeGame().init();
    }

    /**
     * starts the JavaFX application (main menu first)
     */
    public void init()
    {
        instance = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Application.launch(MainMenu.class);
            }
        }).start();

        testServer();
    }

    public static SnakeGame getInstance()
    {
        return instance;
    }

    public SnakeServer getSnakeServer()
    {
        return snakeServer;
    }

    public void setSnakeServer(SnakeServer snakeServer)
    {
        this.snakeServer = snakeServer;
    }

    private void testServer()
    {
        snakeServer = new SnakeServer(2);
        snakeServer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    snakeServer.processNewConnection("127.0.0.1",56744+i);
                    snakeServer.processMessage("127.0.0.1",56744+i,"1;Flo"+i+";rot;gruen");
                    snakeServer.processMessage("127.0.0.1",56744+i,"4");
                }
            }
        }).start();

    }

}
