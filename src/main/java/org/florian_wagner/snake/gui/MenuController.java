package org.florian_wagner.snake.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 08.04.2017.
 */
public class MenuController implements Initializable {

    @FXML
    public Button button_playoffline;

    @FXML
    public Button button_playmultiplayer;

    @FXML
    public Button button_credits;


    public void handlePlayOffline()
    {
        System.out.println("play offline");
    }

    public void handlePlayMultiplayer()
    {
        System.out.println("play multiplayer");
    }

    public void handleCredits()
    {
        System.out.println("credits");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init menu");

    }
}
