package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.florian_wagner.snake.game.Matchup;
import org.florian_wagner.snake.game.OfflineGame;
import org.florian_wagner.snake.gui.AboutGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 08.04.2017.
 */
public class MenuController implements Initializable {

    @FXML private Button button_playoffline;

    @FXML private Button button_playmultiplayer;

    @FXML private Button button_credits;


    /**
     * this method implements the functionality of the "Offline Spielen" button
     */
    public void handlePlayOffline()
    {
        System.out.println("play offline");

        Stage stage = (Stage) button_playoffline.getScene().getWindow();
        // start offline game window
        new OfflineGame(stage);

    }

    /**
     * this method implements the functionality of the "Mehrspieler" button
     */
    public void handlePlayMultiplayer()
    {
        Stage stage = (Stage) button_playmultiplayer.getScene().getWindow();
        new Matchup(stage);
        System.out.println("play multiplayer");
    }

    /**
     * this method implements the functionality of the "Über" button
     */
    public void handleCredits()
    {
        Stage stage = (Stage) button_playoffline.getScene().getWindow();
        try {
            new AboutGUI().start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * JavaFX stuff
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init menu");

    }
}
