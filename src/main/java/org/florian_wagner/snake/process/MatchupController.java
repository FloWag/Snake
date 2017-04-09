package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.florian_wagner.snake.game.Matchup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 09.04.2017.
 */
public class MatchupController implements Initializable {

    private Matchup matchup;

    @FXML private TextField name;

    @FXML private ColorPicker color_snake;

    @FXML private ColorPicker color_head;

    @FXML private TextField ip_text;

    @FXML private TextField speed_text;

    @FXML private Button button_host;

    @FXML private Button button_join;

    @FXML private Text status;

    /**
     * handler for the "Host" button
     */
    public void handleHost()
    {
        String n = name.getText();
        if(n.isEmpty())
        {
            setStatus("Bitte Namen eingeben!",Color.RED);
            return;
        }
        if(n.contains(";"))
        {
            setStatus("Bitte keine ; verwenden!",Color.RED);
            return;
        }if(n.contains("'"))
        {
            setStatus("Bitte keine ' verwenden!",Color.RED);
            return;
        }if(n.contains(","))
        {
            setStatus("Bitte keine , verwenden!",Color.RED);
            return;
        }

        int speed = 10;
        try
        {
            speed = Integer.parseInt(speed_text.getText());
        }catch(Exception e)
        {
            setStatus("'"+speed_text.getText() + "' ist keine Zahl!", Color.RED);
            return;
        }

        matchup.hostGame(n,color_snake.getValue(),color_head.getValue(),speed);
    }

    /**
     * handler for the "join" button
     */
    public void handleJoin()
    {
        String n = name.getText();
        if(n.isEmpty())
        {
            setStatus("Bitte Namen eingeben!",Color.RED);
            return;
        }
        if(n.contains(";"))
        {
            setStatus("Bitte keine ; verwenden!",Color.RED);
            return;
        }if(n.contains("'"))
        {
            setStatus("Bitte keine ' verwenden!",Color.RED);
            return;
        }if(n.contains(","))
        {
            setStatus("Bitte keine , verwenden!",Color.RED);
            return;
        }
        String ip = ip_text.getText();
        if(ip.isEmpty())
        {
            setStatus("Bitte IP eingeben!",Color.RED);
            return;
        }
        if(ip.contains(";"))
        {
            setStatus("Bitte kiene ; verwenden!",Color.RED);
            return;
        }if(ip.contains("|"))
        {
            setStatus("Bitte kiene | verwenden!",Color.RED);
            return;
        }if(ip.contains(","))
        {
            setStatus("Bitte kiene , verwenden!",Color.RED);
            return;
        }

        matchup.joinGame(n,color_snake.getValue(),color_head.getValue(),ip);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        color_snake.setValue(Color.web("#ff7e00"));
        color_head.setValue(Color.web("#c96707"));


    }

    public void setMatchup(Matchup matchup) {
        this.matchup = matchup;
    }

    public void setStatus(String text, Color color)
    {
        status.setFill(color);
        status.setText(text);
    }

    public void setStatus(String text)
    {
        status.setFill(Color.BLACK);
        status.setText(text);
    }
}
