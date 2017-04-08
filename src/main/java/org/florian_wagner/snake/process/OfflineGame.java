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


/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGame {


    private OfflineGUI gui;
    private Thread thread;

    private Snake snake;

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
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    update();
                    render();
                }
            }
        });
        init();
        thread.start();
    }

    private void init()
    {
        snake = new Snake(0,5,5);
        render();
    }

    private void update()
    {
        snake.move();
    }

    private void render()
    {
        gui.getController().cleanGC();

        //render snake
        List<Location> parts = snake.getAllLocations(true);
        for(parts.toFirst();parts.hasAccess();parts.next())
        {
            Location loc = parts.getContent();
            gui.getController().fillVirtualPixel(loc.getX(),loc.getY(), Color.web("#008aff"));
        }

        gui.getController().fillVirtualPixel(snake.getHeadLocation().getX(),snake.getHeadLocation().getY(),Color.web("#1995fe"));
    }

    public void handleKeyEvent(KeyEvent ev)
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

        if(ev.getCode() == KeyCode.ENTER)
        {
            // new game
            init();
        }
    }

    public void dispose()
    {
        thread.stop();
    }

}
