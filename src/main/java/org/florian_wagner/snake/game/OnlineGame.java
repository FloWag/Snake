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

    private String username;


    public OnlineGame(String pIPAdresse, int pPortNr,String snake_color, String head_color,String username, Stage stage) {
        super(pIPAdresse, pPortNr);
        this.snake_color = snake_color;
        this.head_color = head_color;
        this.username = username;
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

        // send login
        send("1;"+ username + ";" + snake_color + ";" + head_color);
    }

    @Override
    public void processMessage(String pMessage) {
        String[] split = pMessage.split(";");
        switch(split[0])
        {
            case "2":
                if(split.length > 1)
                {
                    String[] list = new String[split.length-1];
                    for(int i = 1; i < split.length; i++)
                    {
                        list[i-1] = split[i];
                    }
                    gui.getController().updateScoreboard(list);
                }
                break;
        }
    }

    /**
     * key input handling
     * @param ev
     */
    public void handleKeyEvent(KeyEvent ev)
    {

        if(ev.getCode() == KeyCode.ENTER)
        {
            // request snake spawn
            System.out.println("Hi");
            send("4");
        }
    }
}
