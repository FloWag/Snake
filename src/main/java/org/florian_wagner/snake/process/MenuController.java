package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

        // close current window
        /*

        stage.close();
        */
    }

    /**
     * this method implements the functionality of the "Mehrspieler" button
     */
    public void handlePlayMultiplayer()
    {
        System.out.println("play multiplayer");
    }

    /**
     * this method implements the functionality of the "Über" button
     */
    public void handleCredits()
    {
        System.out.println("credits");
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
