package org.florian_wagner.snake.game;

import de.nrw.schulentwicklung.netzklassen.Client;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.florian_wagner.snake.gui.OnlineGUI;

import java.io.IOException;

/**
 * Created by Florian on 09.04.2017.
 */
public class OnlineGame extends Client {

    private String snake_color;
    private String head_color;

    private OnlineGUI gui;


    public OnlineGame(String pIPAdresse, int pPortNr,String snake_color, String head_color, Stage stage) {
        super(pIPAdresse, pPortNr);
        this.snake_color = snake_color;
        this.head_color = head_color;
        start(stage);
    }

    public void start(Stage stage)
    {
        gui = new OnlineGUI();
        try {
            gui.start(stage,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processMessage(String pMessage) {

    }

    /**
     * key input handling
     * @param ev
     */
    public void handleKeyEvent(KeyEvent ev)
    {

        if(ev.getCode() == KeyCode.ENTER)
        {

        }
    }
}
