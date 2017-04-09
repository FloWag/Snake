package org.florian_wagner.snake.process;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 09.04.2017.
 */
public class MatchupController implements Initializable {

    @FXML private TextField name;

    @FXML private ColorPicker color_snake;

    @FXML private ColorPicker color_head;

    @FXML private TextField ip_text;

    @FXML private TextField speed_text;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        color_snake.setValue(Color.web("#ff7e00"));
        color_head.setValue(Color.web("#c96707"));
    }
}
