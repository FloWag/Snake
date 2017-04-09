package org.florian_wagner.snake.game;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.florian_wagner.snake.gui.MatchupGUI;

import java.io.IOException;

/**
 * Created by Florian on 09.04.2017.
 */
public class Matchup {

    private MatchupGUI gui;

    public Matchup(Stage stage)
    {
        try {
            gui = new MatchupGUI();
            gui.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gui.getController().setMatchup(this);
    }

    public void hostGame()
    {

    }

    public void joinGame(String name, Color color_snake, Color color_head, String ip)
    {
        gui.getController().setStatus("Verbinde ...",Color.GREEN);
    }

}
