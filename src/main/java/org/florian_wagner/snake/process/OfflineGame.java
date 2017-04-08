package org.florian_wagner.snake.process;

import de.nrw.schulentwicklung.listenklassen.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.florian_wagner.snake.core.Direction;
import org.florian_wagner.snake.core.Location;
import org.florian_wagner.snake.core.Snake;
import org.florian_wagner.snake.gui.OfflineGUI;

import java.util.Random;


/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGame {


    private OfflineGUI gui;
    private Thread thread;

    private Snake snake;
    private int length;

    private int SPEED = 10;
    private Color color_snake;
    private Color color_head;

    private Location apple;

    public OfflineGame(Stage stage)
    {

        gui = new OfflineGUI();
        try {
            gui.start(stage,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    try {
                        Thread.sleep(1000 / SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    update();
                    render();
                }
            }
        });

        thread.start();
    }

    private void init()
    {
        // refresh settings
        color_snake = gui.getController().getColorSnake();
        color_head = gui.getController().getColorHead();
        SPEED = gui.getController().getSpeed();

        length = 5;
        gui.getController().setStatusText("Länge: " + length);

        // spawn apple
        Random r = new Random();
        apple = new Location(r.nextInt(32),r.nextInt(24));

        // init snake + render
        snake = new Snake(0,5,length);
        render();
    }

    private void update()
    {
        if(snake != null)
        {
            snake.move();

            // check for game over

            // check if head is out of bounds
            if(snake.getHeadLocation().getX() > 32 || snake.getHeadLocation().getX() < 0)
            {
                gameOver();
                return;
            }
            if(snake.getHeadLocation().getY() > 24 || snake.getHeadLocation().getY() < 0)
            {
                gameOver();
                return;
            }

            // check if snake eats a snake part
            List<Location> parts = snake.getAllLocations(false);
            for(parts.toFirst();parts.hasAccess();parts.next())
            {
                Location loc = parts.getContent();
                if(loc.getX() == snake.getHeadLocation().getX())
                {
                    if(loc.getY() == snake.getHeadLocation().getY())
                    {
                        gameOver();
                        return;
                    }
                }
            }

            // check if snake eats apple
            if(snake.getHeadLocation().getX() == apple.getX())
            {
                if(snake.getHeadLocation().getY() == apple.getY())
                {
                    snake.grow();
                    length++;
                    gui.getController().setStatusText("Länge: " + length);
                    Random r = new Random();
                    apple = new Location(r.nextInt(32),r.nextInt(24));
                }
            }

        }
    }

    private void render()
    {
        gui.getController().cleanGC();

        if(snake == null)
        {
            return;
        }
        // render apple
        gui.getController().fillVirtualPixel(apple.getX(),apple.getY(),Color.GREEN);


        //render snake
        List<Location> parts = snake.getAllLocations(true);
        for(parts.toFirst();parts.hasAccess();parts.next())
        {
            Location loc = parts.getContent();
            gui.getController().fillVirtualPixel(loc.getX(),loc.getY(), color_snake);
        }
        gui.getController().fillVirtualPixel(snake.getHeadLocation().getX(),snake.getHeadLocation().getY(),color_head);


    }

    public void handleKeyEvent(KeyEvent ev)
    {
        if(snake != null)
        {
            if(ev.getCode() == KeyCode.UP)
            {
                if(snake.getDirection() != Direction.SOUTH)snake.setDirection(Direction.NORTH);
            }if(ev.getCode() == KeyCode.DOWN)
            {
                if(snake.getDirection() != Direction.NORTH)snake.setDirection(Direction.SOUTH);
            }if(ev.getCode() == KeyCode.RIGHT)
            {
                if(snake.getDirection() != Direction.WEST)snake.setDirection(Direction.EAST);
            }if(ev.getCode() == KeyCode.LEFT)
            {
                if(snake.getDirection() != Direction.EAST)snake.setDirection(Direction.WEST);
            }
        }
        if(ev.getCode() == KeyCode.ENTER)
        {
            // new game
            gui.getController().disableSettings(); // just a little bugfix
            init();
        }
    }

    public void gameOver()
    {
        snake = null;
        gui.getController().enableSettings();
        gui.getController().resetStatusText();
    }

    public void dispose()
    {
        thread.stop();
    }

}
