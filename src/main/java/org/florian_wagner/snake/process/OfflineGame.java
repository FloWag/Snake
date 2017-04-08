package org.florian_wagner.snake.process;

import javafx.stage.Stage;
import org.florian_wagner.snake.gui.OfflineGUI;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineGame {

    public OfflineGame(Stage stage)
    {

        try {
            new OfflineGUI().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
