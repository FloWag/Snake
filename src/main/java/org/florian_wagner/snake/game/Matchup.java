package org.florian_wagner.snake.game;

import javafx.stage.Stage;
import org.florian_wagner.snake.gui.MatchupGUI;

import java.io.IOException;

/**
 * Created by Florian on 09.04.2017.
 */
public class Matchup {

    public Matchup(Stage stage)
    {
        try {
            new MatchupGUI().start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
