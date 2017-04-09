package org.florian_wagner.snake.game;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.florian_wagner.snake.SnakeGame;
import org.florian_wagner.snake.gui.MatchupGUI;

import java.io.IOException;

/**
 * Created by Florian on 09.04.2017.
 */
public class Matchup {

    private MatchupGUI gui;
    private Stage stage;

    public Matchup(Stage stage)
    {
        this.stage = stage;
        try {
            gui = new MatchupGUI();
            gui.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gui.getController().setMatchup(this);
    }

    public void hostGame(String name, Color color_snake, Color color_head, int speed)
    {
        gui.getController().setStatus("Starte Server ...", Color.GREEN);
        SnakeServer snakeServer = new SnakeServer(speed);
        snakeServer.start();
        SnakeGame.getInstance().setSnakeServer(snakeServer);
        joinGame(name,color_snake,color_head,"localhost");
    }

    public void joinGame(String name, Color color_snake, Color color_head, String ip)
    {
        gui.getController().setStatus("Verbinde ...",Color.GREEN);
        String snakec = "#" + Integer.toHexString(color_snake.hashCode());
        String headc = "#" + Integer.toHexString(color_head.hashCode());
        new OnlineGame(ip,11121,snakec,headc,name,stage);
    }

}
