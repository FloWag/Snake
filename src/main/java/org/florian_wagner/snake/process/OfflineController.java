package org.florian_wagner.snake.process;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Florian on 08.04.2017.
 */
public class OfflineController implements Initializable {

    @FXML private Canvas game_screen;

    @FXML private ColorPicker color_snake;

    @FXML private ColorPicker color_head;

    @FXML private TextField speed;

    @FXML private TitledPane settings;

    @FXML private VBox rootbox;


    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = game_screen.getGraphicsContext2D();

        // set default settings
        speed.setText("10");
        color_snake.setValue(Color.web("#008aff"));
        color_head.setValue(Color.web("#1995fe"));

        speed.setFocusTraversable(false);
        color_snake.setFocusTraversable(false);
        color_head.setFocusTraversable(false);

        settings.setFocusTraversable(false);

        rootbox.requestFocus();
    }

    public void disableSettings()
    {
        rootbox.requestFocus();
        speed.setDisable(true);
        color_snake.setDisable(true);
        color_head.setDisable(true);
    }

    public void enableSettings()
    {
        speed.setDisable(false);
        color_snake.setDisable(false);
        color_head.setDisable(false);
    }



    public void cleanGC()
    {
        gc.setFill(Color.SILVER);
        gc.fillRect(0,0,1280,720);
    }

    public void fillVirtualPixel(int x, int y, Color color)
    {
        int size = 30;

        gc.setFill(color);
        gc.fillRect(x * size, y * size, size, size);
    }

    public int getSpeed()
    {
        int sp = 10;
        try
        {
            sp = Integer.parseInt(speed.getText());
        }catch(NumberFormatException e)
        {
            speed.setText("10");
        }
        return sp;
    }

    public Color getColorSnake()
    {
        return color_snake.getValue();
    }

    public Color getColorHead()
    {
        return color_head.getValue();
    }

}
